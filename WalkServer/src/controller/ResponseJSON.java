package controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class ResponseJSON {
	private static final String D_TAG = "ResponseJSON";
	public static final String SUCCESS = "OK";
	public static final String NOT_FOUND = "NOT_FOUND";
	public static final String ZERO_RESULTS = "ZERO_RESULTS";
	public static final String MAX_WAYPOINTS_EXCEEDED = "MAX_WAYPOINTS_EXCEEDED";
	public static final String INVALID_REQUEST = "INVALID_REQUEST";			// [NOTE] Default response status if data has not been initialized yet
	public static final String OVER_QUERY_LIMIT = "OVER_QUERY_LIMIT";
	public static final String REQUEST_DENIED = "REQUEST_DENIED";
	public static final String UNKNOWN_ERROR = "UNKNOWN_ERROR";
	
	public static final String TMODE_GOOGLE_WALK = "WALKING";
	public static final String TMODE_MT_WALK = "WALK";
	
	/* Direction basic data type */
	public class Location
	{
		public int latE6;
		public int lngE6;
		public int z_index;
		public String attr;
		
		public void appendToJSONStringer(JSONStringer json) throws JSONException
		{
			if(json != null)
			{
				json.object();					// {
				json.key(LAT).value(latE6);		// 		"lat":"---"
				json.key(LNG).value(lngE6);		// 		"lat":"---"
				json.key(ZIDX).value(z_index);	// 		"z_index":"---"
				json.key(ATTR).value(attr);		// 		"attr":"---"
				json.endObject();				// }
			}
		}
	}
	private static final String LAT = "lat";
	private static final String LNG = "lng";
	private static final String ZIDX = "z_index";
	private static final String ATTR = "attr";
	private Location parseLocation(JSONObject jLocation)
	{
		if(jLocation != null)
		{
			RoutingManager.printLog(D_TAG, "parseLocation");
			Location location = new Location();
			if(isDoubleAngle)
			{
				location.latE6 = (int)(jLocation.optDouble(LAT, 91.0) * 1E6);
				location.lngE6 = (int)(jLocation.optDouble(LNG, 181.0) * 1E6);
			}
			else
			{
				location.latE6 = jLocation.optInt(LAT, 91000000);
				location.lngE6 = jLocation.optInt(LNG, 181000000);
			}
			location.z_index = jLocation.optInt(ZIDX);
			location.attr = jLocation.optString(ATTR);
			
			return location;
		}
		else
		{
			RoutingManager.printLog(D_TAG, "parseLocation: NULL");
			return null;
		}
	}
	
	public class Polyline
	{
		public String points;
		public String levels;
		
		public void appendToJSONStringer(JSONStringer json) throws JSONException
		{
			if(json != null)
			{
				json.object();						// {
				json.key(POINTS).value(points);		// 		"points":"---"
				json.key(LEVELS).value(levels);		// 		"levels":"---"
				json.endObject();					// }
			}
		}
	}
	private static final String POINTS = "points";
	private static final String LEVELS = "levels";
	private Polyline parsePolyline(JSONObject jPolyline)
	{
		if(jPolyline != null)
		{
			RoutingManager.printLog(D_TAG, "parsePolyline");
			Polyline polyline = new Polyline();
			polyline.points = jPolyline.optString(POINTS);
			polyline.levels = jPolyline.optString(LEVELS);
			return polyline;
		}
		else
		{
			RoutingManager.printLog(D_TAG, "parsePolyline: NULL");
			return null;
		}
	}
	
	public class ValueText
	{
		public int value;
		public String text;
		
		public void appendToJSONStringer(JSONStringer json) throws JSONException
		{
			if(json != null)
			{
				json.object();						// {
				json.key(VALUE).value(value);		// 		"value":"---"
				json.key(TEXT).value(text);			// 		"text":"---"
				json.endObject();					// }
			}
		}
	}
	private static final String VALUE = "value";
	private static final String TEXT = "text";
	private ValueText parseValueText(JSONObject jValueText)
	{
		if(jValueText != null)
		{
			RoutingManager.printLog(D_TAG, "parseValueText");
			ValueText valueText = new ValueText();
			valueText.value = jValueText.optInt(VALUE, -1);
			valueText.text = jValueText.optString(TEXT);
			return valueText;
		}
		else
		{
			RoutingManager.printLog(D_TAG, "parseValueText: NULL");
			return null;
		}
	}
	
	/* Direction advanced data type */
	public class SubSteps
	{
		public Location start_location;
		public Location end_location;
		public Polyline polyline;
		public ValueText duration;
		public ValueText distance;
		public String html_instructions;
		
		public void appendToJSONStringer(JSONStringer json) throws JSONException
		{
			if(json != null)
			{
				json.object();											// {
				if(start_location != null)
				{
					json.key(START_LOCATION);								//		"start_location":
					start_location.appendToJSONStringer(json);
				}
				if(end_location != null)
				{
					json.key(END_LOCATION);									//		"end_location":
					end_location.appendToJSONStringer(json);
				}
				if(polyline != null)
				{
					json.key(POLYLINE);										//		"polyline":
					polyline.appendToJSONStringer(json);
				}
				if(duration != null)
				{
					json.key(DURATION);										//		"duration":
					duration.appendToJSONStringer(json);
				}
				if(distance != null)
				{
					json.key(DISTANCE);										//		"distance":
					distance.appendToJSONStringer(json);
				}
				json.key(HTML_INS).value(html_instructions);			// 		"html_instructions":"---"
				json.endObject();										// }
			}
		}
	}
	private static final String START_LOCATION = "start_location";
	private static final String END_LOCATION = "end_location";
	private static final String POLYLINE = "polyline";
	private static final String DURATION = "duration";
	private static final String DISTANCE = "distance";
	private static final String HTML_INS = "html_instructions";
	private SubSteps parseSubSteps(JSONObject jSubSteps)
	{
		if(jSubSteps != null)
		{
			RoutingManager.printLog(D_TAG, "parseSubSteps");
			SubSteps subSteps = new SubSteps();
			subSteps.start_location = parseLocation(jSubSteps.optJSONObject(START_LOCATION));
			subSteps.end_location = parseLocation(jSubSteps.optJSONObject(END_LOCATION));
			subSteps.polyline = parsePolyline(jSubSteps.optJSONObject(POLYLINE));
			subSteps.duration = parseValueText(jSubSteps.optJSONObject(DURATION));
			subSteps.distance = parseValueText(jSubSteps.optJSONObject(DISTANCE));
			subSteps.html_instructions = jSubSteps.optString(HTML_INS);
			return subSteps;
		}
		else
		{
			RoutingManager.printLog(D_TAG, "parseSubSteps: NULL");
			return null;
		}
	}
	private List<SubSteps> parseSubStepsList(JSONArray jSubStepsArray)
	{
		// [NOTE] You should check the ability of JSONArray before invoking "parseSomethingArray(JSONArray)"
		RoutingManager.printLog(D_TAG, "parseSubStepsArray");
		List<SubSteps> subStepsArray = new ArrayList<SubSteps>();
		for(int i = 0; i < jSubStepsArray.length(); i++)
			subStepsArray.add(parseSubSteps(jSubStepsArray.optJSONObject(i)));
		return subStepsArray;
	}
	
	public class Steps extends SubSteps
	{
		// [NOTE] The other members is inherited from SubSteps
		public String travel_mode;
		public String building_id;
		public int stop_to_id;
		public int stop_from_id;
		public List<SubSteps> sub_steps;
		
		public void appendToJSONStringer(JSONStringer json) throws JSONException
		{
			if(json != null)
			{
				json.object();											// {
				json.key(TRAVEL_MODE).value(travel_mode);				// 		"travel_mode":"---"
				json.key(BIDX).value(building_id);						// 		"building_id":"---"
				json.key(S_T_IDX).value(stop_to_id);						// 		"stop_to_id":"---"
				json.key(S_F_IDX).value(stop_from_id);						// 		"stop_from_id":"---"
				if(start_location != null)
				{
					json.key(START_LOCATION);								//		"start_location":
					start_location.appendToJSONStringer(json);
				}
				if(end_location != null)
				{
					json.key(END_LOCATION);									//		"end_location":
					end_location.appendToJSONStringer(json);
				}
				if(polyline != null)
				{
					json.key(POLYLINE);										//		"polyline":
					polyline.appendToJSONStringer(json);
				}
				if(duration != null)
				{
					json.key(DURATION);										//		"duration":
					duration.appendToJSONStringer(json);
				}
				if(distance != null)
				{
					json.key(DISTANCE);										//		"distance":
					distance.appendToJSONStringer(json);
				}
				json.key(HTML_INS).value(html_instructions);			// 		"html_instructions":"---"
				json.key(SUB_STEPS).array();							// 		"sub_steps":[
				if(sub_steps != null && sub_steps.size() > 0)
					for(SubSteps subStep : sub_steps)
						if(subStep != null)
							subStep.appendToJSONStringer(json);
				json.endArray();										// 		]
				json.endObject();										// }
			}
		}
	}
	private static final String TRAVEL_MODE = "travel_mode";
	private static final String BIDX = "building_id";
	private static final String S_T_IDX = "stop_to_id";
	private static final String S_F_IDX = "stop_from_id";
	private static final String SUB_STEPS = "sub_steps";
	private Steps parseSteps(JSONObject jSteps)
	{
		if(jSteps != null)
		{
			RoutingManager.printLog(D_TAG, "parseSteps");
			Steps steps = new Steps();
			steps.travel_mode = jSteps.optString(TRAVEL_MODE);
			steps.building_id = jSteps.optString(BIDX);
			steps.stop_to_id = jSteps.optInt(S_T_IDX, 0);
			steps.stop_from_id = jSteps.optInt(S_F_IDX, 0);
			steps.start_location = parseLocation(jSteps.optJSONObject(START_LOCATION));
			steps.end_location = parseLocation(jSteps.optJSONObject(END_LOCATION));
			steps.polyline = parsePolyline(jSteps.optJSONObject(POLYLINE));
			steps.duration = parseValueText(jSteps.optJSONObject(DURATION));
			steps.distance = parseValueText(jSteps.optJSONObject(DISTANCE));
			steps.html_instructions = jSteps.optString(HTML_INS);
			
			if(hasHTMLTags)
				steps.html_instructions = removeHTMLTags(steps.html_instructions);
				
			JSONArray jSubSteps = jSteps.optJSONArray(SUB_STEPS);
			if(jSubSteps != null && jSubSteps.length() > 0)
				steps.sub_steps = parseSubStepsList(jSubSteps);
			else
				steps.sub_steps = null;
			return steps;
		}
		else
		{
			RoutingManager.printLog(D_TAG, "parseSteps: NULL");
			return null;
		}
	}
	private List<Steps> parseStepsList(JSONArray jStepsArray)
	{
		// [NOTE] You should check the ability of JSONArray before invoking "parseSomethingArray(JSONArray)"
		RoutingManager.printLog(D_TAG, "parseStepsArray");
		List<Steps> stepsArray = new ArrayList<Steps>();
		for(int i = 0; i < jStepsArray.length(); i++)
			stepsArray.add(parseSteps(jStepsArray.optJSONObject(i)));
		return stepsArray;
	}
	
	public class Legs
	{
		public List<Steps> steps;
		public ValueText duration;
		public ValueText distance;
		public Location start_location;
		public Location end_location;
		public String start_address;
		public String end_address;
		
		public void appendToJSONStringer(JSONStringer json) throws JSONException
		{
			if(json != null)
			{
				json.object();											// {
				json.key(STEPS).array();								// 		"steps":[
				if(steps != null && steps.size() > 0)
					for(Steps step : steps)
						if(step != null)
							step.appendToJSONStringer(json);
				json.endArray();										// 		]
				if(duration != null)
				{
					json.key(DURATION);										//		"duration":
					duration.appendToJSONStringer(json);	
				}
				if(distance != null)
				{
					json.key(DISTANCE);										//		"distance":
					distance.appendToJSONStringer(json);	
				}
				if(start_location != null)
				{
					json.key(START_LOCATION);								//		"start_location":
					start_location.appendToJSONStringer(json);
				}
				if(end_location != null)
				{
					json.key(END_LOCATION);									//		"end_location":
					end_location.appendToJSONStringer(json);
				}
				json.key(START_ADDR).value(start_address);				// 		"start_address":"---"
				json.key(END_ADDR).value(end_address);					// 		"end_address":"---"
				json.endObject();										// }
			}
		}
	}
	private static final String STEPS = "steps";
	private static final String START_ADDR = "start_address";
	private static final String END_ADDR = "end_address";
	private Legs parseLegs(JSONObject jLegs)
	{
		if(jLegs != null)
		{
			JSONArray jSteps = jLegs.optJSONArray(STEPS);	// [WARRING] No "steps", no "legs"
			if(jSteps != null && jSteps.length() > 0)
			{
				RoutingManager.printLog(D_TAG, "parseLegs");
				Legs legs = new Legs();
				legs.steps = parseStepsList(jSteps);
				legs.duration = parseValueText(jLegs.optJSONObject(DURATION));
				legs.distance = parseValueText(jLegs.optJSONObject(DISTANCE));
				legs.start_location = parseLocation(jLegs.optJSONObject(START_LOCATION));
				legs.end_location = parseLocation(jLegs.optJSONObject(END_LOCATION));
				legs.start_address = jLegs.optString(START_ADDR);
				legs.end_address = jLegs.optString(END_ADDR);
				return legs;
			}
			else
			{
				RoutingManager.printLog(D_TAG, "parseLegs: NULL (\"steps\" is NULL)");
				return null;
			}
		}
		else
		{
			RoutingManager.printLog(D_TAG, "parseLegs: NULL");
			return null;
		}
	}
	private List<Legs> parseLegsList(JSONArray jLegsArray)
	{
		// [NOTE] You should check the ability of JSONArray before invoking "parseSomethingArray(JSONArray)"
		RoutingManager.printLog(D_TAG, "parseLegsArray");
		List<Legs> legsArray = new ArrayList<Legs>();
		for(int i = 0; i < jLegsArray.length(); i++)
			legsArray.add(parseLegs(jLegsArray.optJSONObject(i)));
		return legsArray;
	}

	public class Routes
	{
		public String summary;
		public List<Legs> legs;
		public String copyrights;
		public Polyline overview_polyline;
		
		public void appendToJSONStringer(JSONStringer json) throws JSONException
		{
			if(json != null)
			{				
				json.object();											// {
				json.key(SUMMARY).value(summary);						// 		"summary":"---"
				json.key(LEGS).array();									// 		"legs":[
				if(legs != null && legs.size() > 0)
					for(Legs leg : legs)
						if(leg != null)
							leg.appendToJSONStringer(json);
				json.endArray();										// 		]
				json.key(COPYRIGHTS).value(copyrights);					// 		"copyrights":"---"
				if(overview_polyline != null)
				{
					json.key(OVERVIEW_POLYLINE);							//		"overview_polyline":
					overview_polyline.appendToJSONStringer(json);	
				}

				json.endObject();										// }
			}
		}
	}
	private static final String SUMMARY = "summary";
	private static final String LEGS = "legs";
	private static final String COPYRIGHTS = "copyrights";
	private static final String OVERVIEW_POLYLINE = "overview_polyline";
	private Routes parseRoutes(JSONObject jRoutes)
	{
		if(jRoutes != null)
		{
			JSONArray jLegs = jRoutes.optJSONArray(LEGS);	// [WARRING] No "legs", no "routes"
			if(jLegs != null && jLegs.length() > 0)
			{
				RoutingManager.printLog(D_TAG, "parseRoutes");
				Routes routes = new Routes();
				routes.summary = jRoutes.optString(SUMMARY);
				routes.legs = parseLegsList(jLegs);
				routes.copyrights = jRoutes.optString(COPYRIGHTS);
				routes.overview_polyline = parsePolyline(jRoutes.optJSONObject(OVERVIEW_POLYLINE));
				return routes;
			}
			else
			{
				RoutingManager.printLog(D_TAG, "parseRoutes: NULL (\"legs\" is NULL)");
				return null;
			}
		}
		else
		{
			RoutingManager.printLog(D_TAG, "parseRoutes: NULL");
			return null;
		}
	}
	private List<Routes> parseRoutesList(JSONArray jRoutesArray)
	{
		// [NOTE] You should check the ability of JSONArray before invoking "parseSomethingArray(JSONArray)"
		RoutingManager.printLog(D_TAG, "parseRoutesArray");
		List<Routes> routesArray = new ArrayList<Routes>();
		for(int i = 0; i < jRoutesArray.length(); i++)
			routesArray.add(parseRoutes(jRoutesArray.optJSONObject(i)));
		return routesArray;
	}
	
	private static final String STATUS = "status";
	private static final String ROUTES = "routes";
	
	/** Data members and the manipulation methods */
	private boolean isDoubleAngle;	// True: treat the source angle as double and translate it into intE6 format
	private boolean hasHTMLTags;	// True: filter out the HTML tags contain in "html_instructions"
	public String status;
	public List<Routes> routes;
	
	private void dataInitialize(String statusCode)
	{
		status = statusCode;
		routes = null;
	}
	
	public void dataClean()
	{
		dataInitialize(ZERO_RESULTS);
	}
	
	private String removeHTMLTags(String string)
	{
		if(string != null && !string.equals(""))
		{
			string = string.replaceAll("\u003c[^(\u003e\u003c)]+\u003e", "");
		}
		return string;
	}
	
	/*
	 * Default constructor
	 */
	public ResponseJSON() {
		isDoubleAngle = false;
		hasHTMLTags = false;
		dataInitialize(INVALID_REQUEST);
	}
	
	/** Tool methods */
	/*
	 * Parse the given JSON response and store into the inner data member for manipulation later
	 * [WARRING] All the existing data contain in the inner data member will be erased
	 */
	public void parseJSON(String content, boolean containsDoubleAngle, boolean containsHTMLTags)
	{
		isDoubleAngle = containsDoubleAngle;
		hasHTMLTags = containsHTMLTags;
		dataInitialize(INVALID_REQUEST);
		try
		{
			if(content != null)
			{
				RoutingManager.printLog(D_TAG, "Parsing now");
				
				JSONObject jObj = new JSONObject(content);										// Get the whole package
				if((status = jObj.optString(STATUS, UNKNOWN_ERROR)).equals(SUCCESS))			// Get "status" first, parse the following data only when status is "OK"
				{
					JSONArray jRoutes;
					if((jRoutes = jObj.optJSONArray(ROUTES)) != null && jRoutes.length() > 0)	// Get "routes" array
						routes = parseRoutesList(jRoutes);
					else
						dataInitialize(ZERO_RESULTS);
				}
			}
			else
				RoutingManager.printErr(D_TAG, "The content is null");
		}
		catch(Exception e)
		{
			dataInitialize(INVALID_REQUEST);
			RoutingManager.printErr(D_TAG, e.toString());
		}
		RoutingManager.printLog(D_TAG, "Parsing complete");
	}
	
	/*
	 * Compile current data into JSON string
	 */
	@Override
	public String toString() {
		JSONStringer json = new JSONStringer();
		
		try
		{
			json.object();								// {
			json.key(STATUS).value(status);				// 		"status":"---"
			json.key(ROUTES).array();					//		"routes":[
			if(routes != null && routes.size() > 0)
				for(Routes route : routes)
					if(route != null)
						route.appendToJSONStringer(json);
			json.endArray();							// 		]
			json.endObject(); 							// }
			return json.toString();	
		}
		catch(JSONException e)
		{
			RoutingManager.printErr(D_TAG, e.toString());
			return "{\"status\":\"" + UNKNOWN_ERROR + "\"},\"routes\":\"[]\"";
		}
		catch(Exception e)
		{
			RoutingManager.printErr(D_TAG, e.toString());
			return "{\"status\":\"" + UNKNOWN_ERROR + "\"},\"routes\":\"[]\"";
		}
	}
}
