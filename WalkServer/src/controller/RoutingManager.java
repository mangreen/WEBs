package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

public class RoutingManager {
	
	/** JNI function interface */
	/*
	 * Get the complete response from mass-transit planner
	 */
	private static native String JNI_MassPlanner(int sLatE6,
										  int sLonE6,
										  int eLatE6,
										  int eLonE6,
										  String transitAvoid);
	
	/*
	 * Get the "step-level" response from the indoor engine
	 */
	public static native String JNI_StartIdrRoute(int slat, 
												int slon, 
												int sfloor, 
												int elat, 
												int elon, 
												int efloor, 
												String lockFile);
	
	/** Constant definition */
	private static final String D_TAG = "RoutingManager";
	private static final String TARGET_JNI = "C:\\WalkData\\dll\\libWalkServerJNI.dll";
	private static final String TARGET_MPLANNER = "C:\\WalkData\\dll\\libMPlannerLIB.dll";
	private static final String TARGET_INDOOR = "C:\\WalkData\\dll\\libIdrPlannerLIB.dll";
//	private static final String TARGET_JNI = "D:\\Works\\Project\\[091031-101231] Mass_Transit\\[Corporation]\\Source\\MPlanner\\WalkServer_JNI\\Debug\\libWalkServerJNI.dll";
//	private static final String TARGET_MPLANNER = "D:\\Works\\Project\\[091031-101231] Mass_Transit\\[Corporation]\\Source\\MPlanner\\MPlanner_LIB\\Debug\\libMPlannerLIB.dll";
//	private static final String TARGET_INDOOR = "D:\\Works\\Project\\[091031-101231] Mass_Transit\\[Corporation]\\Source\\MPlanner\\IdrPlanner_LIB\\Debug\\libIdrPlannerLIB.dll";
	private static final String MODE_WALK_ONLY = "walk";
	private static final String MODE_TRANSIT = "mass";
	private static final String DECORATOR_MIN = " 分鐘";
	private static final String DECORATOR_KM = " 公里";
	private static final String HTML_INS_KEY_DEST = "目的地";
	private static final String HTML_INS_KEY_EXIT = "出入口";
	private static final String ATTR_EXIT = "EXIT";
	private static final String ATTR_STOP = "STOP";
	private static final int PATCH_MODE_GOOGLE = 0;
	private static final int PATCH_MODE_MASS = 1;
	private static final DecimalFormat decimalFormat = new DecimalFormat("#.#");
	
	/** Tool methods */
	/*
	 * Get the platform location (if existed) for certain transit stop
	 */
	public static RequestLocation getPoiPlatform(int stopID, String buildingID)
	{
		RequestLocation poi = new RequestLocation();
		
		// TODO: Query from DB
		
		poi.latE6 = 91000000;
        poi.lonE6 = 181000000;
        poi.floorID = 0;
        poi.buildingID = null;
		
		return poi;
	}
	/*
	 * Get the exit location for certain building
	 */
	public static RequestLocation getPoiExit(int latE6, int lonE6, int floorID, String buildingID)
	{
		RequestLocation poi = new RequestLocation();
		
		int temp_lat=0, temp_lon=0 ,count=0;
		String table = "poi_" + buildingID;
		
		//資料庫帳密位置
		String user = "tester";
		String pass = "000000";
        String database = "test";
        String url = "jdbc:mysql://140.92.13.231:3306/" + database + "?useUnicode=true&characterEncoding=UTF-8";
        
        //建立一個聯結物件
        Connection conn;
		//建立ResultSet物件
		ResultSet rs;
        //建立Statement物件
		Statement stmt;
        
        try{
        	//定義驅動程式與資料來源之間的連結
			Class.forName("org.gjt.mm.mysql.Driver");
			//建立聯結
			conn = DriverManager.getConnection(url,user,pass);
			
	        //建立陳述式物件
            //stmt = conn.createStatement();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            //System.out.println("Connect SUCCESS!!");
			//stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
			String query = "SELECT * FROM "+table+" WHERE floor = "+ floorID +" AND catalog LIKE 'exit';";
            //執行sql
            rs = stmt.executeQuery(query);       
            //顯示資料
            while(rs.next()){
            	//System.out.print(rs.getString(2)+", "+rs.getString(3)+", "+rs.getString(4)+", "+rs.getString(5)+", "+rs.getString(8));
            	if(count >= 1){
	            	if(Math.abs(latE6 - temp_lat)+ Math.abs(lonE6 - temp_lon) > Math.abs(latE6 - Integer.parseInt(rs.getString(2)))+Math.abs(lonE6 - Integer.parseInt(rs.getString(3)))){
	            		temp_lat = Integer.parseInt(rs.getString(2)); 
		            	temp_lon = Integer.parseInt(rs.getString(3));
	            	}	
            	}else{
            		temp_lat = Integer.parseInt(rs.getString(2)); 
	            	temp_lon = Integer.parseInt(rs.getString(3)); 
            	}
            	count ++;
            }
            
            poi.latE6 = temp_lat;
            poi.lonE6 = temp_lon;
            poi.floorID = floorID;
            poi.buildingID = buildingID;
            
            if(stmt != null)
		    	stmt.close();
			if(conn != null)
				conn.close();	

        }catch(Exception sqle){

        	poi.latE6 = 91000000;
            poi.lonE6 = 181000000;
            poi.floorID = 0;
            poi.buildingID = null;
            
            System.out.println("SQL Exception : " + sqle);
        }
		return poi;
	}
	
	/*
	 * Get the walking direction from Google direction service
	 */
	private static String getDirectionFrGoogle(int sLatE6, int sLonE6, int eLatE6, int eLonE6)
	{
		final String D_TAG = "getDirectionFrGoogle";
		String responseBody = null;
		
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet("http://maps.google.com/maps/api/directions/json?origin=" + 
        							   Double.toString(sLatE6 / 1e6) +
        							   ","+
        							   Double.toString(sLonE6 / 1e6) + 
        							   "&destination=" + 
        							   Double.toString(eLatE6 / 1e6) +
        							   ","+
        							   Double.toString(eLonE6 / 1e6) + 
        							   "&mode=walking&sensor=true&language=zh-TW&ie=zh-TW"); 
        // Create a response handler
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
		try {
			responseBody = httpclient.execute(httpget, responseHandler);
			printLog(D_TAG, responseBody);
		} catch (ClientProtocolException e) {
			printErr(D_TAG, "ClientProtocolException: " + e.getMessage());
		} catch (IOException e) {
			printErr(D_TAG, "IOException: " + e.getMessage());
		}

        // When HttpClient instance is no longer needed, 
        // shut down the connection manager to ensure
        // immediate deallocation of all system resources
        httpclient.getConnectionManager().shutdown();
		return responseBody;
	}
	
	/*
	 * Inner routing management - Mass + Google
	 */
	private static void mergeMassGoogle(ResponseJSON responseJson, RequestLocation startLocation, RequestLocation endLocation, String transit_avoid)
	{
		// Request mass-transit plans, get the JSON response
		responseJson.parseJSON(JNI_MassPlanner(startLocation.latE6,
				startLocation.lonE6,
				endLocation.latE6,
				endLocation.lonE6,
				(transit_avoid != null ? transit_avoid : "")), false, false);
		
		// Analyze the steps that need to be patched of each route if existed
		if(responseJson.status.equals(ResponseJSON.SUCCESS) &&
				responseJson.routes != null &&
				responseJson.routes.size() > 0)
		{
			boolean patched;
			ResponseJSON tmpJson = new ResponseJSON();
			
			// [NOTE] Processing loop: Get google -> modify step
			for(int i = 0; i < responseJson.routes.size(); i++)		// For each route(with single leg)
			{
				ResponseJSON.Routes originRoute = responseJson.routes.get(i);
				ResponseJSON.Legs originLeg = originRoute.legs.get(0);
				List<ResponseJSON.Steps> originSteps = originRoute.legs.get(0).steps;
				ResponseJSON.Steps originStep = null;
				int transitStepCount = 0;
				
				originRoute.summary = "";
				
				for(int j = 0; j < originSteps.size(); j++)			// For each step
				{
					originStep = originSteps.get(j);
					if(originStep.travel_mode.equals(ResponseJSON.TMODE_MT_WALK))	// Try to patch the "WALK step" from MPlanner
					{
						patched = false;
						
						// Check whether the target stop of start(end) step is within the request building 
						if(j == 0 && startLocation.buildingID != null && (j + 1) < (originSteps.size() - 1) && originSteps.get(j + 1).stop_to_id > 0)					// Check the first step
						{
							if(patchIndoorFirst(responseJson, i, PATCH_MODE_MASS, startLocation))
								patched = true;
						}
						else if(j == (originSteps.size() - 1) && endLocation.buildingID != null && 1 < originSteps.size() && originSteps.get(j - 1).stop_from_id > 0)	// Check the last step
						{
							if(patchIndoorLast(responseJson, i, PATCH_MODE_MASS, endLocation))
								patched = true;
						}
						
						if(!patched)
						{
							tmpJson.parseJSON(getDirectionFrGoogle(originStep.start_location.latE6, 
									originStep.start_location.lngE6,
									originStep.end_location.latE6,
									originStep.end_location.lngE6), true, true);
							
							if(tmpJson.status.equals(ResponseJSON.SUCCESS))	// Get the google response
							{
								ResponseJSON.Steps tmpGStep = tmpJson.routes.get(0).legs.get(0).steps.get(0);
								// Modify the first google walking step
								tmpGStep.start_location.latE6 = originStep.start_location.latE6;
								tmpGStep.start_location.lngE6 = originStep.start_location.lngE6;
								
								// Modify the last google walking step
								tmpGStep = tmpJson.routes.get(0).legs.get(0).steps.get(tmpJson.routes.get(0).legs.get(0).steps.size() - 1);
								tmpGStep.end_location.latE6 = originStep.end_location.latE6;
								tmpGStep.end_location.lngE6 = originStep.end_location.lngE6;
								
								// Modify the last html_instruction
								if(originStep.html_instructions != null && !originStep.html_instructions.equals(""))
									tmpGStep.html_instructions = tmpGStep.html_instructions.replaceAll(HTML_INS_KEY_DEST, "-" + originStep.html_instructions);
								
								// Modify the original duration
								originLeg.duration.value = originLeg.duration.value - originStep.duration.value + tmpJson.routes.get(0).legs.get(0).duration.value;
								originLeg.duration.text = decimalFormat.format((float)originLeg.duration.value / 60) + DECORATOR_MIN;
								
								// Modify the original distance
								originLeg.distance.value = originLeg.distance.value - originStep.distance.value + tmpJson.routes.get(0).legs.get(0).distance.value;
								originLeg.distance.text = decimalFormat.format((float)originLeg.distance.value / 1000) + DECORATOR_KM;
								
								// Remove the original step
								originStep = null;
								originSteps.remove(j);
								
								// Append the new google walking steps
								originSteps.addAll(j, tmpJson.routes.get(0).legs.get(0).steps);
								
								// Modify the index of global step-list
								j += (tmpJson.routes.get(0).legs.get(0).steps.size() - 1);
							}
							else
								tmpJson.dataClean();	
						}
					}
					else
					{
						if(transitStepCount > 0)
							originRoute.summary += ",";
						
						originRoute.summary += originStep.travel_mode;
						transitStepCount++;
					}
				}
			}
		}
		else
		{
			if(responseJson.status.equals(ResponseJSON.ZERO_RESULTS))	// Special case: the distance between given start/end points is short enough that can be walked through
				responseJson.parseJSON(getDirectionFrGoogle(startLocation.latE6, startLocation.lonE6, endLocation.latE6, endLocation.lonE6), true, true);
			else
				responseJson.dataClean();
		}
	}
	
	/*
	 * Inner routing management - Patch the first step as Indoor step
	 */
	private static boolean patchIndoorFirst(ResponseJSON responseJson, int targetRouteIdx, int patchMode, RequestLocation startLocation)
	{
		RequestLocation tmpLocation;
		ResponseJSON.Legs originLeg = responseJson.routes.get(targetRouteIdx).legs.get(0);
		List<ResponseJSON.Steps> originSteps = originLeg.steps;
		
		switch(patchMode)	// Try to find the corresponding indoor location
		{
		case PATCH_MODE_GOOGLE:
			tmpLocation = getPoiExit(originLeg.start_location.latE6, originLeg.start_location.lngE6, 1, startLocation.buildingID);
			break;
		case PATCH_MODE_MASS:
			tmpLocation = getPoiPlatform(originSteps.get(1).stop_to_id, startLocation.buildingID);
			break;
		default:
			return false;	
		}
		
		if(tmpLocation.latE6 != 91000000 && tmpLocation.lonE6 != 181000000 && tmpLocation.floorID != 0 && tmpLocation.buildingID != null)
		{
			printLog(D_TAG, "Indoor parameters: Start - " + startLocation.latE6 + "," + startLocation.lonE6 + "," + startLocation.floorID +
					" End - " + tmpLocation.latE6 + "," + tmpLocation.lonE6 + "," + tmpLocation.floorID + " Build: " + startLocation.buildingID);
			ResponseJSON tmpJson = new ResponseJSON();
			tmpJson.parseJSON(JNI_StartIdrRoute(startLocation.latE6, 
					startLocation.lonE6, 
					startLocation.floorID, 
					tmpLocation.latE6, 
					tmpLocation.lonE6, 
					tmpLocation.floorID, 
					startLocation.buildingID), false, false);
			
			// Patch it!
			if(tmpJson.status.equals(ResponseJSON.SUCCESS) &&
					tmpJson.routes != null &&
					tmpJson.routes.size() > 0)
			{
				ResponseJSON.Legs tmpLeg = tmpJson.routes.get(0).legs.get(0);
				ResponseJSON.Steps originStep;
				
				switch(patchMode)
				{
				case PATCH_MODE_GOOGLE:
					// Modify the first "WALKING" step
					originStep = originSteps.get(0);
					originStep.start_location.latE6 = tmpLocation.latE6;
					originStep.start_location.lngE6 = tmpLocation.lonE6;
					originStep.start_location.z_index = tmpLeg.steps.get(0).end_location.z_index;
					originStep.start_location.attr = ATTR_EXIT;
					
					// Insert the new indoor virtual steps
					tmpLeg.steps.get(0).end_location.attr = ATTR_EXIT;
					originSteps.add(0, tmpLeg.steps.get(0));
					break;
				case PATCH_MODE_MASS:
					// Modify the first "MASS" step
					originStep = originSteps.get(1);
					originStep.start_location.latE6 = tmpLocation.latE6;
					originStep.start_location.lngE6 = tmpLocation.lonE6;
					originStep.start_location.z_index = tmpLeg.steps.get(0).end_location.z_index;
					originStep.start_location.attr = ATTR_STOP;
					
					// Insert the new indoor virtual steps
					tmpLeg.steps.get(0).end_location.attr = ATTR_STOP;
					originSteps.remove(0);
					originSteps.add(0, tmpLeg.steps.get(0));
					break;
				}

				// Modify the original start location
				originLeg.start_location.latE6 = startLocation.latE6;
				originLeg.start_location.lngE6 = startLocation.lonE6;
				originLeg.start_location.z_index = startLocation.floorID;
				originLeg.start_location.attr = tmpLeg.start_location.attr;
				
				// Modify the original duration
				originLeg.duration.value += tmpLeg.duration.value;
				
				// Modify the original distance
				originLeg.distance.value += tmpLeg.distance.value;
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Inner routing management - Patch the last step as Indoor step
	 */
	private static boolean patchIndoorLast(ResponseJSON responseJson, int targetRouteIdx, int patchMode, RequestLocation endLocation)
	{
		RequestLocation tmpLocation;
		ResponseJSON.Legs originLeg = responseJson.routes.get(targetRouteIdx).legs.get(0);
		List<ResponseJSON.Steps> originSteps = originLeg.steps;
		
		// Try to find the corresponding exit location
		
		
		switch(patchMode)	// Try to find the corresponding indoor location
		{
		case PATCH_MODE_GOOGLE:
			tmpLocation = getPoiExit(originLeg.end_location.latE6, originLeg.end_location.lngE6, 1, endLocation.buildingID);
			break;
		case PATCH_MODE_MASS:
			tmpLocation = getPoiPlatform(originSteps.get(originSteps.size() - 2).stop_from_id, endLocation.buildingID);
			break;
		default:
			return false;	
		}
		
		if(tmpLocation.latE6 != 91000000 && tmpLocation.lonE6 != 181000000 && tmpLocation.floorID != 0 && tmpLocation.buildingID != null)
		{
			printLog(D_TAG, "Indoor parameters: Start - " + tmpLocation.latE6 + "," + tmpLocation.lonE6 + "," + tmpLocation.floorID +
					" End - " + endLocation.latE6 + "," + endLocation.lonE6 + "," + endLocation.floorID + " Build: " + endLocation.buildingID);
			ResponseJSON tmpJson = new ResponseJSON();
			tmpJson.parseJSON(JNI_StartIdrRoute(tmpLocation.latE6, 
					tmpLocation.lonE6, 
					tmpLocation.floorID, 
					endLocation.latE6, 
					endLocation.lonE6, 
					endLocation.floorID, 
					endLocation.buildingID), false, false);
			
			// Patch it!
			if(tmpJson.status.equals(ResponseJSON.SUCCESS) &&
					tmpJson.routes != null &&
					tmpJson.routes.size() > 0)
			{
				ResponseJSON.Legs tmpLeg = tmpJson.routes.get(0).legs.get(0);
				ResponseJSON.Steps originStep;
				
				switch(patchMode)
				{
				case PATCH_MODE_GOOGLE:
					// Modify the last google walking step
					originStep = originSteps.get(originSteps.size() - 1);
					originStep.end_location.latE6 = tmpLocation.latE6;
					originStep.end_location.lngE6 = tmpLocation.lonE6;
					originStep.end_location.z_index = tmpLeg.steps.get(0).start_location.z_index;
					originStep.end_location.attr = ATTR_EXIT;
					originStep.html_instructions = originStep.html_instructions.replaceAll(HTML_INS_KEY_DEST, "-" + HTML_INS_KEY_EXIT);
					
					// Append the new indoor virtual steps
					tmpLeg.steps.get(0).start_location.attr = ATTR_EXIT;
					originSteps.add(tmpLeg.steps.get(0));
					break;
				case PATCH_MODE_MASS:
					// Modify the last "MASS" step
					originStep = originSteps.get(originSteps.size() - 2);
					originStep.end_location.latE6 = tmpLocation.latE6;
					originStep.end_location.lngE6 = tmpLocation.lonE6;
					originStep.end_location.z_index = tmpLeg.steps.get(0).start_location.z_index;
					originStep.end_location.attr = ATTR_STOP;
					
					// Append the new indoor virtual steps
					tmpLeg.steps.get(0).start_location.attr = ATTR_STOP;
					originSteps.remove(0);
					originSteps.add(tmpLeg.steps.get(0));
					break;
				}
				
				// Modify the original end location
				originLeg.end_location.latE6 = endLocation.latE6;
				originLeg.end_location.lngE6 = endLocation.lonE6;
				originLeg.end_location.z_index = endLocation.floorID;
				originLeg.end_location.attr = tmpLeg.end_location.attr;
				
				// Modify the original duration
				originLeg.duration.value += tmpLeg.duration.value;
				
				// Modify the original distance
				originLeg.distance.value += tmpLeg.distance.value;
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Local routing management
	 */
	public static String getDirection(String origin, String destination, String mode, String transit_avoid, boolean alternative) {
		// Parameters
		RequestLocation startLocation;
		RequestLocation endLocation;
		
		// Resources
		ResponseJSON responseJson = new ResponseJSON();
		
		// [LOG]
		System.out.println("\n[ Logged at " + new Date().toString() + " ]");
		System.out.println("[ I ] origin: " + origin);
		System.out.println("[ I ] destination: " + destination);
		System.out.println("[ I ] mode: " + mode);
		System.out.println("[ I ] transit_avoid: " + transit_avoid);
		System.out.println("[ I ] alternative: " + Boolean.toString(alternative));
		
		// Parameter processing: invoke the routing process only when valid parameter set existed
		if(origin != null && destination != null && mode != null &&
				(startLocation = RequestLocation.locationParser(origin)) != null &&
				(endLocation = RequestLocation.locationParser(destination)) != null &&
				(mode.equals(MODE_WALK_ONLY) || mode.equals(MODE_TRANSIT)))
		{
			try
			{
				/** Load-in required libraries */
				System.load(TARGET_MPLANNER);	// [Load-in the Mass planner library]
				System.load(TARGET_INDOOR);		// [Load-in the Indoor planner library]
				System.load(TARGET_JNI);		// [Load-in the JNI library]
				
				/** Start the management flow */
				if(startLocation.buildingID == null && endLocation.buildingID == null)	// [0. Without INDOOR] 
				{
					if(mode.equals(MODE_WALK_ONLY))	// [1. WALK-ONLY mode]
					{
						// Request google direction, get the JSON response
						// Parse the original response(this will: (a.) filter out HTML tags from the html_instruction, (b.) translate double-angle into intE6 format)
						responseJson.parseJSON(getDirectionFrGoogle(startLocation.latE6, startLocation.lonE6, endLocation.latE6, endLocation.lonE6), true, true);
					}
					else							// [1. MASS_TRANSIT mode]
					{
						mergeMassGoogle(responseJson, startLocation, endLocation, transit_avoid);
					}
				}
				else																		// [0. With INDOOR] 
				{
					if(mode.equals(MODE_WALK_ONLY))	// [1. WALK-ONLY mode]
					{
						if(startLocation.buildingID != null &&
								endLocation.buildingID != null &&
								startLocation.buildingID.equals(endLocation.buildingID))	// Routes within the same building
						{
							// Request indoor plans, get the JSON response
							responseJson.parseJSON(JNI_StartIdrRoute(startLocation.latE6, 
									startLocation.lonE6, 
									startLocation.floorID, 
									endLocation.latE6, 
									endLocation.lonE6, 
									endLocation.floorID, 
									endLocation.buildingID), false, false);
							
							// Request google plans if there is no indoor plan
							if(!responseJson.status.equals(ResponseJSON.SUCCESS))	
							{
								responseJson.parseJSON(getDirectionFrGoogle(startLocation.latE6, startLocation.lonE6, endLocation.latE6, endLocation.lonE6), true, true);
							}
						}
						else
						{
							// Request google plans as the base result
							responseJson.parseJSON(getDirectionFrGoogle(startLocation.latE6, startLocation.lonE6, endLocation.latE6, endLocation.lonE6), true, true);
							
							// If the base result existed
							if(responseJson.status.equals(ResponseJSON.SUCCESS) &&
									responseJson.routes != null &&
									responseJson.routes.size() > 0)
							{
								// Try to patch the start step if indoor location is required
								if(startLocation.buildingID != null)
								{
									patchIndoorFirst(responseJson, 0, PATCH_MODE_GOOGLE, startLocation);	
								}
								
								// Try to patch the end step if indoor location is required
								if(endLocation.buildingID != null)
								{
									patchIndoorLast(responseJson, 0, PATCH_MODE_GOOGLE, endLocation);
								}
							}
						}
					}
					else							// [1. MASS_TRANSIT mode]
					{
						// Request Mass+Google plans as the base result
						mergeMassGoogle(responseJson, startLocation, endLocation, transit_avoid);
						
						// If the base result existed
						if(responseJson.status.equals(ResponseJSON.SUCCESS) &&
								responseJson.routes != null &&
								responseJson.routes.size() > 0)
						{
							for(int i = 0; i < responseJson.routes.size(); i++)
							{
								// Try to patch the start step if indoor location is required
								if(startLocation.buildingID != null)
								{
									patchIndoorFirst(responseJson, i, PATCH_MODE_GOOGLE, startLocation);
								}
								
								// Try to patch the end step if indoor location is required
								if(endLocation.buildingID != null)
								{
									patchIndoorLast(responseJson, i, PATCH_MODE_GOOGLE, endLocation);
								}
							}
						}
					}
				}
			}
			catch(UnsatisfiedLinkError e)
			{
				printErr(D_TAG, "UnsatisfiedLinkError:" + e.toString());
			}
			catch(Exception e) 
			{
				printErr(D_TAG, "Exception: " + e.toString());
			}
		}
		
		// [LOG]
		System.out.println("[ O ] response: \n" + responseJson.toString());
		
		return responseJson.toString();		// Compose the response into JSON string
	}
	
	/** Public static methods */
	private static boolean debugMsgEnabled = false;
	
	public static void setDebugMsgEnabled(boolean enabled)
	{
		debugMsgEnabled = enabled;
	}
	
	public static void printErr(String tag, String message)
	{
		if(debugMsgEnabled)
		{
			if(tag != null)
				System.out.println("[ ERROR ] " + tag + ": " + message);
			else
				System.out.println("[ ERROR ] " + message);	
		}
	}
	
	public static void printLog(String tag, String message)
	{
		if(debugMsgEnabled)
		{
			if(tag != null)
				System.out.println("[ LOG ] " + tag + ": " + message);
			else
				System.out.println("[ LOG ] " + message);	
		}
	}
}
