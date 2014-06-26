package bean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

class Point{
	int int_lat;
	double db_lat;
	int int_lon;
	double db_lon;
	int floor;
	String building;
	String address;
	
	Point(String str_lat, String str_lon, int floor, String build){
        this.int_lat = Step.convertStringToInt(str_lat);
        this.db_lat = Double.parseDouble(str_lat);
        this.int_lon = Step.convertStringToInt(str_lon);
        this.db_lon = Double.parseDouble(str_lon);;
        this.floor = floor;
        this.building = build;
    }

	public Point(int lat, int lon, int floor, String build) {
		this.int_lat = lat;
        this.db_lat = Double.parseDouble("25."+String.valueOf(lat).substring(2, 8));
        this.int_lon = lon;
        this.db_lon = Double.parseDouble("121."+String.valueOf(lon).substring(3, 9));
        this.floor = floor;
        this.building = build;
	}
}

class RouteParameter{
	Point ori_start;
	Point ori_end;
	String transit_avoid;
	String alternative;
	
	JSONObject start_location;
	JSONObject end_location;
	JSONObject duration;
	JSONObject distance;
	String start_address;
	String end_address;
	
	RouteParameter(Point ori_start, Point ori_end, String transit_avoid, String alternative){
        this.ori_start = ori_start;
        this.ori_end = ori_end;
        this.transit_avoid = transit_avoid;
        this.alternative = alternative;
    }
	
	public int setLegJSON(JSONObject start_location, JSONObject end_location, JSONObject duration, JSONObject distance){
		
		return 0;
	}
}

class Step implements JSONAware{
	String travel_mode;
	//Map<String,Object> start_location;
	//Map<String,Object> end_location;
	JSONObject start_location;
	JSONObject end_location;
	JSONObject polyline;
	JSONObject duration;
	String html_instructions;
	JSONObject distance;
	JSONArray sub_Steps;
	
	Step(String travel_mode, JSONObject start_location, JSONObject end_location, JSONObject polyline, JSONObject duration, String html_instructions, JSONObject distance, JSONArray sub_Steps){
		
		this.travel_mode = travel_mode;
		this.start_location = setLocationJSON(start_location);
		this.end_location = setLocationJSON(end_location);
		/*
		this.start_location = new LinkedHashMap<String,Object>();
		this.start_location.put("lat", SubStep.convertStringToInt(start_location.get("lat").toString()));
		this.start_location.put("lng", SubStep.convertStringToInt(start_location.get("lng").toString()));
		this.start_location.put("z_index", "");
		this.start_location.put("attr", "");
		
		this.end_location = new LinkedHashMap<String,Object>();
		this.end_location.put("lat", SubStep.convertStringToInt(end_location.get("lat").toString()));
		this.end_location.put("lng", SubStep.convertStringToInt(end_location.get("lng").toString()));
		this.end_location.put("z_index", "");
		this.end_location.put("attr", "");
		*/
		
		this.polyline = polyline;
		this.duration = duration;
		this.html_instructions = html_instructions;
		this.distance = distance;
		
		this.sub_Steps = sub_Steps;
	}

	public static JSONObject setLocationJSON(JSONObject location){
		JSONObject newLocation = new JSONObject();
		//Map<String,Object> map_location = new LinkedHashMap<String,Object>();
		newLocation.put("lat", Step.convertStringToInt(location.get("lat").toString()));
		newLocation.put("lng", Step.convertStringToInt(location.get("lng").toString()));
		newLocation.put("z_index", "");
		newLocation.put("attr", "");
		
		//newLocation.putAll(map_location);
		
		return newLocation;
	}
	
	public static int convertStringToInt(String coordinate){
		int int_cdnt = 0;
		String[] tokens = coordinate.split("\\."); //"."this character has been defined in regular expression, should use "\\."

		if(2 == tokens.length){
			if(5 == tokens[1].length()){
				tokens[1] = tokens[1] + "0" ;
			}else if(4 == tokens[1].length()){
				tokens[1] = tokens[1] + "00" ;
			}else if(3 == tokens[1].length()){
				tokens[1] = tokens[1] + "000" ;
			}else if(2 == tokens[1].length()){
				tokens[1] = tokens[1] + "0000" ;
			}else if(1 == tokens[1].length()){
				tokens[1] = tokens[1] + "00000" ;
			}
		}else{
			int_cdnt = Integer.parseInt(tokens[0]+"000000");
			return int_cdnt;
		}
		
		int_cdnt = Integer.parseInt(tokens[0]+tokens[1]);
		
		return int_cdnt;
	}
	
	public String toJSONString() {
		StringBuffer sb = new StringBuffer();
		
        sb.append("{");
        
        sb.append("\""+ JSONObject.escape("travel_mode") +"\"");
        sb.append(":");
        sb.append("\""+ JSONObject.escape(travel_mode) +"\"");
        
        sb.append(",");
        
        sb.append("\""+ JSONObject.escape("start_location") +"\"");
        sb.append(":");
        sb.append(start_location);
        
        sb.append(",");
        
        sb.append("\""+ JSONObject.escape("end_location") +"\"");
        sb.append(":");
        sb.append(end_location);
        
        sb.append(",");
        
        sb.append("\""+ JSONObject.escape("polyline") +"\"");
        sb.append(":");
        sb.append(polyline);
        
        sb.append(",");
        
        sb.append("\""+ JSONObject.escape("duration") +"\"");
        sb.append(":");
        sb.append(duration);
        
        sb.append(",");
        
        sb.append("\""+ JSONObject.escape("html_instructions") +"\"");
        sb.append(":");
        sb.append("\""+ JSONObject.escape(html_instructions) +"\"");
        
        sb.append(",");
        
        sb.append("\""+ JSONObject.escape("distance") +"\"");
        sb.append(":");
        sb.append(distance);
        
        sb.append(",");
        
        sb.append("\""+ JSONObject.escape("sub_Steps") +"\"");
        sb.append(":");
        sb.append(sub_Steps.toJSONString());
        
        sb.append("}");
        
        return sb.toString();
	}
}

class Leg implements JSONAware{

	JSONArray Steps;
	JSONObject duration;
	JSONObject distance;
	JSONObject start_location;
	JSONObject end_location;
	String start_address;
	String end_address;
	
	Leg(JSONArray Steps, JSONObject duration, JSONObject distance, JSONObject start_location, JSONObject end_location, String start_address, String end_address){
		
		this.Steps = Steps;
		this.duration = duration;
		this.distance = distance;
		this.start_location = start_location;
		this.end_location = end_location;
		this.start_address = start_address;
		this.end_address = end_address;
	}
	
	public String toJSONString() {
		StringBuffer sb = new StringBuffer();
		
        sb.append("{");
        
        sb.append("\""+ JSONObject.escape("Steps") +"\"");
        sb.append(":");
        sb.append(Steps.toJSONString());
        
        sb.append(",");
   
        sb.append("\""+ JSONObject.escape("duration") +"\"");
        sb.append(":");
        sb.append(duration);
        
        sb.append(",");
        
        sb.append("\""+ JSONObject.escape("distance") +"\"");
        sb.append(":");
        sb.append(distance);
        
        sb.append(",");
        
        sb.append("\""+ JSONObject.escape("start_location") +"\"");
        sb.append(":");
        sb.append(start_location);
        
        sb.append(",");
        
        sb.append("\""+ JSONObject.escape("end_location") +"\"");
        sb.append(":");
        sb.append(end_location);
        
        sb.append(",");
        
        sb.append("\""+ JSONObject.escape("start_address") +"\"");
        sb.append(":");
        sb.append("\""+ JSONObject.escape(start_address) +"\"");
        
        sb.append(",");
        
        sb.append("\""+ JSONObject.escape("end_address") +"\"");
        sb.append(":");
        sb.append("\""+ JSONObject.escape(end_address) +"\"");
        
        sb.append(",");
        
        sb.append("\""+ JSONObject.escape("copyrights") +"\"");
        sb.append(":");
        sb.append("\"地圖資料 ©2010 III\"");
        
        sb.append("}");
        
        return sb.toString();
	}
}
/*
class SubStep implements JSONAware{
	Map<String,Object> sub_start_location;
	Map<String,Object> sub_end_location;
	JSONObject sub_polyline;
	JSONObject sub_duration;
	String sub_html_instructions;
	JSONObject sub_distance;
	
	SubStep(JSONObject sub_start_location, JSONObject sub_end_location, JSONObject sub_polyline, JSONObject sub_duration, String sub_html_instructions, JSONObject sub_distance){
		
		this.sub_start_location = new LinkedHashMap<String,Object>();
		this.sub_start_location.put("lat", convertStringToInt(sub_start_location.get("lat").toString()));
		this.sub_start_location.put("lng", convertStringToInt(sub_start_location.get("lng").toString()));
		this.sub_start_location.put("z_index", "");
		this.sub_start_location.put("attr", "");
		
		this.sub_end_location = new LinkedHashMap<String,Object>();
		this.sub_end_location.put("lat", convertStringToInt(sub_end_location.get("lat").toString()));
		this.sub_end_location.put("lng", convertStringToInt(sub_end_location.get("lng").toString()));
		this.sub_end_location.put("z_index", "");
		this.sub_end_location.put("attr", "");
		
		this.sub_polyline = sub_polyline;
		this.sub_duration = sub_duration;
		this.sub_html_instructions = sub_html_instructions;
		this.sub_distance = sub_distance;
	}

	public String toJSONString() {
		StringBuffer sb = new StringBuffer();
        
        sb.append("{");
        
        sb.append("\""+ JSONObject.escape("start_location") +"\"");
        sb.append(":");
        sb.append(JSONValue.toJSONString(sub_start_location));
        
        sb.append(",");
        
        sb.append("\""+ JSONObject.escape("end_location") +"\"");
        sb.append(":");
        sb.append(JSONValue.toJSONString(sub_end_location));
        
        sb.append(",");
        
        sb.append("\""+ JSONObject.escape("polyline") +"\"");
        sb.append(":");
        sb.append(sub_polyline);
        
        sb.append(",");
        
        sb.append("\""+ JSONObject.escape("duration") +"\"");
        sb.append(":");
        sb.append(sub_duration);
        
        sb.append(",");
        
        sb.append("\""+ JSONObject.escape("html_instructions") +"\"");
        sb.append(":");
        sb.append("\""+ JSONObject.escape(sub_html_instructions) +"\"");
        
        sb.append(",");
        
        sb.append("\""+ JSONObject.escape("distance") +"\"");
        sb.append(":");
        sb.append(sub_distance);
        
        sb.append("}");
        
        return sb.toString();
	}
}*/

public class RouteManager {
	RouteParameter routeParam;
	
	static{
        System.loadLibrary("JNIIdrRoute");
    }
	/*set string test code
	public static String message1="abc"; //Accessing Strings
	public native static GDATest setMessage1(String msg);
	public static String getMessage1(){
		return message1;
	}*/
	
	public static String assignJob(String origin, String destination, String mode, String transit_avoid, String alternative){
		String JSON_Result = "";
		
		String[] ori_Tokens = origin.split(",");
		String[] des_Tokens = destination.split(",");
		Point start, end;
		
		if(4 == ori_Tokens.length){
			start = new Point(ori_Tokens[0], ori_Tokens[1], Integer.parseInt(ori_Tokens[2]), ori_Tokens[3]);
		}else if(3 == ori_Tokens.length){
			start = new Point(ori_Tokens[0], ori_Tokens[1], Integer.parseInt(ori_Tokens[2]), null);
		}else{
			JSON_Result = "ERROR_Wrong_origin_Parameter";
			return JSON_Result;
		}
		
		if(4 == des_Tokens.length){
			end = new Point(des_Tokens[0], des_Tokens[1], Integer.parseInt(des_Tokens[2]), des_Tokens[3]);
		}else if(3 == des_Tokens.length){
			end = new Point(des_Tokens[0], des_Tokens[1], Integer.parseInt(des_Tokens[2]), null);
		}else{
			JSON_Result = "ERROR_Wrong_destination_Parameter";
			return JSON_Result;
		}
		
		RouteParameter routeParam = new RouteParameter(start, end, transit_avoid, alternative);
		
		if("walk" == mode){
			JSON_Result = getWalkMode(routeParam);
		}
		
		/*
		System.out.println(routeParam.start.int_lat+", "+routeParam.start.db_lon+", "+routeParam.start.floor+", "+routeParam.start.building);
		System.out.println(routeParam.end.int_lat+", "+routeParam.end.db_lon+", "+routeParam.end.floor+", "+routeParam.end.building);
		System.out.println(routeParam.transit_avoid);
		System.out.println(routeParam.alternative);
		*/
		
		//String lockFile="";
		//GDARoute(origin, destination);
		//result = result + startIdrRoute(slat, slon, sfloor, elat, elon, efloor, lockFile);
		
		return JSON_Result;
	}
	
	public static String getWalkMode(RouteParameter routeParam){
		String JSON_Walk = "Error_Get_Walk_Legs_Fail";
		
		//新增一個JSON parser
	    JSONParser parser = new JSONParser();
	    Object startObj = null;
	    JSONObject jSObj = null;
	    Object endObj = null;
	    JSONObject jEObj = null;
	    
		JSONObject walkJSONObj = new JSONObject();
		//routes
		JSONArray routesJSONArray = new JSONArray();
		JSONObject routesJSONObj = new JSONObject();
		//legs
		JSONArray legsJSONArray = new JSONArray();
		//steps
		JSONArray StepsJSONArray = new JSONArray();
		
		if(null != routeParam.ori_start.building && null != routeParam.ori_end.building && routeParam.ori_start.building.equals(routeParam.ori_end.building)){
			try {
				startObj = parser.parse(startIdrRoute(routeParam.ori_start.int_lat, routeParam.ori_start.int_lon, routeParam.ori_start.floor, routeParam.ori_end.int_lat, routeParam.ori_end.int_lon, routeParam.ori_end.floor, routeParam.ori_end.building));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jSObj = (JSONObject)startObj;
			StepsJSONArray.add(jSObj);
			
		}else{
			//judge start is in indoor or not
			if(null != routeParam.ori_start.building){
				Point idrEnd = getPoiExit(routeParam.ori_end.int_lat, routeParam.ori_end.int_lon, 1, routeParam.ori_start.building);
				String strTmp = startIdrRoute(routeParam.ori_start.int_lat, routeParam.ori_start.int_lon, routeParam.ori_start.floor, idrEnd.int_lat, idrEnd.int_lon, idrEnd.floor, idrEnd.building);
				if(-1 == strTmp.indexOf("ERROR")){	
					try {
						startObj = parser.parse(strTmp);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					jSObj = (JSONObject)startObj;
				}
			}
			
			//judge end is in indoor or not
			if(null != routeParam.ori_end.building){
				Point idrStart = getPoiExit(routeParam.ori_start.int_lat, routeParam.ori_start.int_lon, 1, routeParam.ori_end.building);
				String strTmp = startIdrRoute(idrStart.int_lat, idrStart.int_lon, idrStart.floor, routeParam.ori_end.int_lat, routeParam.ori_end.int_lon, routeParam.ori_end.floor, idrStart.building);
				if(-1 == strTmp.indexOf("ERROR")){
					try {
						endObj = parser.parse(strTmp);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					jEObj = (JSONObject)endObj;
				}
			}
			
			JSONArray JSON_GDA = startGDA(routeParam);
			
			if(null != jSObj){StepsJSONArray.add(jSObj);}
			StepsJSONArray.addAll(JSON_GDA);
			if(null != jEObj){StepsJSONArray.add(jEObj);}
		}
		
		//write routes array
		routesJSONObj.put("summary","WALK,VIRTUAL");
		legsJSONArray.add(new Leg(StepsJSONArray, routeParam.duration, routeParam.distance, routeParam.start_location, routeParam.end_location, routeParam.start_address, routeParam.end_address));
		routesJSONObj.put("legs", legsJSONArray);
		routesJSONObj.put("copyrights", "地圖資料 ©2010 III");
		routesJSONArray.add(routesJSONObj);
		
		//write routes array
		walkJSONObj.put("status", "OK");
		walkJSONObj.put("routes", routesJSONArray);
			
		JSON_Walk = walkJSONObj.toJSONString();
		
		//startGDA(routeParam);
		//System.out.println(startIdrRoute(25058780, 121555170, 1, 25058721, 121554735, 13, "III"));
		
		return JSON_Walk;
	}
	
	public native static String startIdrRoute(int slat, int slon, int sfloor, int elat, int elon, int efloor, String lockFile);
	public static JSONArray startGDA(RouteParameter routeParam){
		String result =  "";
		JSONArray resultPD =  new JSONArray();
		
		String origin = routeParam.ori_start.db_lat+","+routeParam.ori_start.db_lon;
		String destination = routeParam.ori_end.db_lat+","+routeParam.ori_end.db_lon;
		String urlParameters = "origin="+origin + "&destination="+destination + "&mode=walking&language=zh-TW&sensor=false";
		
		HttpClient client = new HttpClient();								// construct the HttpClient object
        //client.getHostConfiguration().setProxy("proxy.iii.org.tw", 3128);	// set proxyServer

        // 建立GetMethod實體, 並指派網址, GetMethod會自動處理該網轉址動作, 如果不想自動轉址請呼叫 setFollowRedirects(false).
        GetMethod method = new GetMethod("http://maps.google.com/maps/api/directions/json?"+urlParameters);

        // 這段代碼用意為連接不到時自動重試五次.
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(5, false));

        try {
            int statusCode = client.executeMethod(method);	// execute the method (HTTP_GET request)
            
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + method.getStatusLine());
            } 

            //result = streamToString(method.getResponseBodyAsStream());	// 儲存provider list
            
            resultPD = getGDAJSON(streamToString(method.getResponseBodyAsStream()), routeParam);
        } catch (HttpException httpexc) {	// unrecoverable
            System.err.println("Fatal protocol violation: " + httpexc.getMessage());
            httpexc.printStackTrace();
        } catch (IOException ioexc) {		// recoverable: through "HttpMethodRetryHandler()"
            System.err.println("Fatal transport error: " + ioexc.getMessage());
            ioexc.printStackTrace();
        } finally {
            method.releaseConnection();	// Release connection
        }
        return resultPD;
	}
	
	public static String streamToString(InputStream in) throws IOException // 串流轉字串
    {
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        for(int n; (n = in.read(b)) != -1;) 
        {
            out.append(new String(b, 0, n, "utf8"));
        }
        return out.toString();
    }
	
	public static JSONArray StartGDARoute(RouteParameter routeParam){
		JSONArray resultPD =  new JSONArray();
		String origin = routeParam.ori_start.db_lat+","+routeParam.ori_start.db_lon;
		String destination = routeParam.ori_end.db_lat+","+routeParam.ori_end.db_lon;
		
		String urlParameters = "origin="+origin + "&destination="+destination + "&mode=walking&language=zh-TW&sensor=false";
		
		try {
			//URL url = new URL( "http", "localhost", 8800, "/TestServlet");
			URL url = new URL( "http://maps.google.com/maps/api/directions/json?"+urlParameters);
			//URL url = new URL( "http://maps.google.com/maps/api/directions/json");

		    URLConnection connection = url.openConnection();		    
			//此處的 urlConnection對象實際上是根據URL的,
			//請求協議(此處是http)生成的URLConnection類的子類HttpURLConnection,
			//故此處最好將其轉化 為HttpURLConnection類型的對象,
			//以便用到 HttpURLConnection更多的API.如下: 		
		    ((HttpURLConnection)connection).setRequestMethod("POST");//設定請求的方法為"POST"，默認是GET 
		    
		 	//設置是否向 httpUrlConnection輸出，因為這個是post請求，參數要放在 http正文內，因此需要設為true, 默認情況下是false; 
		    connection.setDoOutput(true);
		 	//設置是否從 httpUrlConnection讀入，默認情況下是true; 
			connection.setDoInput(true);
			//Post請求不能使用緩存 
			connection.setUseCaches(false); 
			
			//設置連接主機超時（單位：毫秒）
	        connection.setConnectTimeout(30000);
	        //設置從主機讀取數據超時（單位：毫秒）
	        connection.setReadTimeout(30000);    
	        //設置User-Agent,讓網站伺服器誤認user端的瀏覽器為google,baidu,BorderSpider,firefox,GoogleReader...
	        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-TW; rv:1.9.1.5) Gecko/20091102 Firefox/3.5.5 GTB6");
			
			//連接，從上述第2條中 url.openConnection()至此的配置必須要在connect之前完成， 
	        //connection.connect();

			//此處 getOutputStream會隱含的進行connect(即：如同調用上面的connect()方法， 所以在開發中不調用上述的connect()也可以)。
			
		    OutputStreamWriter os = new OutputStreamWriter(connection.getOutputStream());
		    os.write(urlParameters);
		    os.write( "\r\n");
		    os.flush();
		    os.close();
		    
		    
		    //DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
	    	//wr.writeBytes (urlParameters);
	   	 	//wr.flush ();
	    	//wr.close ();

	    	
	    	//OutputStream outStrm = connection.getOutputStream(); 
	    	// 現在通過輸出流對象構建對象輸出流對象，以實現輸出可序列化的對象。 
	    	//ObjectOutputStream objOutputStrm = new ObjectOutputStream(outStrm); 
	    	// 向對象輸出流寫出數據，這些數據將存到內存緩沖區中 
	    	//objOutputStrm.writeObject(urlParameters); 
	    	// 刷新對象輸出流，將任何字節都寫入潛在的流中（些處為 ObjectOutputStream） 
	    	//objOutputStrm.flush(); 
	    	// 關閉流對象。此時，不能再向對象輸出流寫入任何數據，先前寫入的數據存在于內存緩沖區中, 
	    	// 在調用下邊的getInputStream()函數時才把準備好的http請求正式發送到服務器 
	    	//objOutputStrm.close();
	    	
	    	
	    	InputStreamReader isr = new InputStreamReader(connection.getInputStream(), "utf-8");
		    BufferedReader br = new BufferedReader(isr);
	    	StringBuffer sb = new StringBuffer();

	    	
		    while ( br.ready()) {	
		    	//out.println( br.readLine());
		    	
		    	//將每行文字塞成一個字串
		    	sb.append(br.readLine());
	            sb.append("\n");
		    }
		    
		    String jsonResponse = sb.toString();
		    //System.out.println(jsonResponse);
		    
		    resultPD = getGDAJSON(jsonResponse, routeParam);
		    
		    isr.close();
		    br.close();
		    //System.out.println(routeParam.start_location.toString());
		}
		catch(Exception e ){
			// handle the error here
			System.out.println("exception: "+ e);
		}
		
		return resultPD;
	}
	
	public static JSONArray getGDAJSON(String GDAResult, RouteParameter routeParam){
		JSONArray steps = new JSONArray();
		JSONArray sub_steps = new JSONArray();
		
		try {
			//新增一個JSON parser
		    JSONParser gdaParser = new JSONParser();
		    Object gdaObj = gdaParser.parse(GDAResult);
		    JSONObject jsonGdaObj = (JSONObject)gdaObj;
		    
		    JSONArray routeArray=(JSONArray)jsonGdaObj.get("routes");
		    JSONObject routeObj = (JSONObject)routeArray.get(0);
		    
		    String summary = routeObj.get("summary").toString();
		   
		    JSONArray legArray=(JSONArray)routeObj.get("legs");
		    JSONObject legObj = (JSONObject)legArray.get(0);
		    
		    JSONArray stepArray=(JSONArray)legObj.get("steps");
		    
		    for(int i=0; i<stepArray.size(); i++){
		    	JSONObject GDAStepObj = (JSONObject)stepArray.get(i);
		    	//String mode = stepObj.get("travel_mode").toString();
		    	//System.out.println("\nmode:"+mode);
		    	
		    	String travel_mode = GDAStepObj.get("travel_mode").toString();
		    	
		    	JSONObject startObj = (JSONObject)GDAStepObj.get("start_location");
		    	//String sLat = startObj.get("lat").toString();
		    	//String sLng = startObj.get("lng").toString();
		    	//System.out.println("\nslat:"+sLat+" slng:"+sLng);
		    	
		    	JSONObject endObj = (JSONObject)GDAStepObj.get("end_location");
		    	//String eLat = endObj.get("lat").toString();
		    	//String eLng = endObj.get("lng").toString();
		    	//System.out.println("\nelat:"+eLat+" elng:"+eLng);
		    	
		    	JSONObject polyObj = (JSONObject)GDAStepObj.get("polyline");
		    	
		    	JSONObject durObj = (JSONObject)GDAStepObj.get("duration");
		    	//String durValue = durObj.get("value").toString();
		    	//String durText = durObj.get("text").toString();
		    	//System.out.println("\ndurvalue:"+durValue+" durtext:"+durText);
		    	
		    	String html = GDAStepObj.get("html_instructions").toString();
		    	//String clearHtml = html.replaceAll("<", "");
		    	//clearHtml = clearHtml.replaceAll("b", "");
		    	//clearHtml = clearHtml.replaceAll(">", "");
		    	//clearHtml = clearHtml.replaceAll("\\/", "");
		    	//System.out.println("\ninstruction:"+sub_html);
		    	
		    	JSONObject distObj = (JSONObject)GDAStepObj.get("distance");
		    	//String distValue = sub_distObj.get("value").toString();
		    	//String distText = sub_distObj.get("text").toString();
		    	//System.out.println("\ndistvalue:"+distValue+" disttext:"+distText);
		    	steps.add(new Step(travel_mode, startObj, endObj, polyObj, durObj, html, distObj, sub_steps));
		    }
		    
		    JSONObject leg_durObj = (JSONObject)legObj.get("duration");
		    routeParam.duration = leg_durObj;
		    JSONObject leg_distObj = (JSONObject)legObj.get("distance");
		    routeParam.distance = leg_distObj;
		    JSONObject leg_startObj = (JSONObject)legObj.get("start_location");
		    routeParam.start_location = Step.setLocationJSON(leg_startObj);
		    JSONObject leg_endObj = (JSONObject)legObj.get("end_location");
		    routeParam.end_location = Step.setLocationJSON(leg_endObj);
		    String leg_start_address = legObj.get("start_address").toString();
		    routeParam.start_address = leg_start_address;
		    String leg_end_address = legObj.get("end_address").toString();
		    routeParam.end_address = leg_end_address;
		    
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return steps;
	}
	
	public static Point getPoiExit(int lat, int lon, int floor, String building){
		Point poi = null;
		
		int temp_lat=0, temp_lon=0 ,count=0;
		String table = "poi_"+building;
		
		//資料庫帳密位置
		String user = "root";
		String pass = "";
        String database = "test";
        String url = "jdbc:mysql://127.0.0.1:3306/" + database + "?useUnicode=true&characterEncoding=UTF-8";
        
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
			
			String query = "SELECT * FROM "+table+" WHERE floor = "+floor+" AND catalog LIKE 'exit';";
            //執行sql
            rs = stmt.executeQuery(query);       
            //顯示資料
            while(rs.next()){
            	//System.out.print(rs.getString(2)+", "+rs.getString(3)+", "+rs.getString(4)+", "+rs.getString(5)+", "+rs.getString(8));
            	if(count >= 1){
	            	if(Math.abs(lat - temp_lat)+ Math.abs(lon - temp_lon) > Math.abs(lat - Integer.parseInt(rs.getString(2)))+Math.abs(lon - Integer.parseInt(rs.getString(3)))){
	            		temp_lat = Integer.parseInt(rs.getString(2)); 
		            	temp_lon = Integer.parseInt(rs.getString(3));
	            	}	
            	}else{
            		temp_lat = Integer.parseInt(rs.getString(2)); 
	            	temp_lon = Integer.parseInt(rs.getString(3)); 
            	}
            	count ++;
            }
            
            poi = new Point(temp_lat, temp_lon, floor, building);
            System.out.print(poi.int_lat+", "+poi.db_lat+", "+poi.int_lon+", "+poi.db_lon+", "+poi.floor+", "+poi.building);
            
            if(stmt != null)
		    	stmt.close();
			if(conn != null)
				conn.close();	

        }catch(Exception sqle){

            System.out.println("SQL Exception : " + sqle);

        }
        
		return poi;
	}
}
