<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.sql.*" import="java.util.*" import="java.io.*" %>
<%@ page import="org.json.simple.parser.*" %>
<%@ page import="org.json.simple.*" %>
<%@ page import="java.net.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
<META HTTP-EQUIV="expires" CONTENT="Wed, 26 Feb 1997 08:21:57 GMT">
<% 
	//禁止緩存
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	
	request.setCharacterEncoding("UTF-8");
	
	String user = request.getParameter("user");
	String password = request.getParameter("password");	
	String origin = request.getParameter("origin");
	String destination = request.getParameter("destination");
	
	//String urlParameters ="fName=" + URLEncoder.encode("???", "UTF-8") +"&lName=" + URLEncoder.encode("???", "UTF-8")
    //http://maps.google.com/maps/api/directions/xml?origin=25.05847,121.55486&destination=25.06344,121.56462&sensor=false
	//String urlParameters = "origin=25.05847,121.55486&destination=25.06344,121.56462&sensor=false";
	String urlParameters = "origin="+origin + "&destination="+destination + "&sensor=false";
	
	try {
		//URL url = new URL( "http", "localhost", 8800, "/TestServlet");
		URL url = new URL( "http://maps.google.com/maps/api/directions/json?"+urlParameters);
		//URL url = new URL( "http://maps.google.com/maps/api/directions/json");

		
	    URLConnection connection = url.openConnection();
	    /*
		此處的 urlConnection對象實際上是根據URL的,
		請求協議(此處是http)生成的URLConnection類的子類HttpURLConnection,
		故此處最好將其轉化 為HttpURLConnection類型的對象,
		以便用到 HttpURLConnection更多的API.如下: 
		*/
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
	    
	    /*
	    DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
    	wr.writeBytes (urlParameters);
   	 	wr.flush ();
    	wr.close ();
    	*/
    	
    	/*
    	OutputStream outStrm = connection.getOutputStream(); 
    	// 現在通過輸出流對象構建對象輸出流對象，以實現輸出可序列化的對象。 
    	ObjectOutputStream objOutputStrm = new ObjectOutputStream(outStrm); 
    	// 向對象輸出流寫出數據，這些數據將存到內存緩沖區中 
    	objOutputStrm.writeObject(urlParameters); 
    	// 刷新對象輸出流，將任何字節都寫入潛在的流中（些處為 ObjectOutputStream） 
    	objOutputStrm.flush(); 
    	// 關閉流對象。此時，不能再向對象輸出流寫入任何數據，先前寫入的數據存在于內存緩沖區中, 
    	// 在調用下邊的getInputStream()函數時才把準備好的http請求正式發送到服務器 
    	objOutputStrm.close();
    	*/
    	
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
	    //out.println(result);
	    
	    //新增一個JSON parser
	    JSONParser parser = new JSONParser();
	    Object obj = parser.parse(jsonResponse);
	    JSONObject jObj = (JSONObject)obj;
	    JSONArray routeArray=(JSONArray)jObj.get("routes");
	    JSONObject routeObj = (JSONObject)routeArray.get(0);
	    JSONArray legArray=(JSONArray)routeObj.get("legs");
	    JSONObject legObj = (JSONObject)legArray.get(0);
	    JSONArray stepArray=(JSONArray)legObj.get("steps");
	    
	    for(int i=0; i<stepArray.size(); i++){
	    	JSONObject stepObj = (JSONObject)stepArray.get(i);
	    	String mode = stepObj.get("travel_mode").toString();
	    	out.println("\nmode:"+mode);
	    	
	    	JSONObject startObj = (JSONObject)stepObj.get("start_location");
	    	String sLat = startObj.get("lat").toString();
	    	String sLng = startObj.get("lng").toString();
	    	out.println("\nslat:"+sLat+" slng:"+sLng);
	    	
	    	JSONObject endObj = (JSONObject)stepObj.get("end_location");
	    	String eLat = endObj.get("lat").toString();
	    	String eLng = endObj.get("lng").toString();
	    	out.println("\nelat:"+eLat+" elng:"+eLng);
	    	
	    	JSONObject durObj = (JSONObject)stepObj.get("duration");
	    	String durValue = durObj.get("value").toString();
	    	String durText = durObj.get("text").toString();
	    	out.println("\ndurvalue:"+durValue+" durtext:"+durText);
	    	
	    	String instruction = stepObj.get("html_instructions").toString();
	    	out.println("\ninstruction:"+instruction);
	    	
	    	JSONObject distObj = (JSONObject)stepObj.get("distance");
	    	String distValue = distObj.get("value").toString();
	    	String distText = distObj.get("text").toString();
	    	out.println("\ndistvalue:"+distValue+" disttext:"+distText);
	    }
	}
	catch(Exception e ){
		// handle the error here
		out.println("exception: "+ e);
	}

%>