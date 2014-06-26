package com.spider;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.imageio.ImageIO;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.Span;
import org.htmlparser.tags.TableColumn;
import org.htmlparser.tags.TableRow;
import org.htmlparser.tags.TableTag;
import org.htmlparser.tags.TitleTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.HtmlPage;

import com.spider.ThreadTest.Command;

public class MojoSpider {
	
	static double poiLon;
	static double poiLat;
	
	static String poiName;
	static String poiPhone;
	static String poiUrl;
	static String poiImg;
	static String poiAddress;
	static String poiIntro;
	
	static String[] alphabet = { "0","1","2","3","4","5","6","7","8","9",
								 "A","B","C","D","E","F","G","H","I","J",
								 "K","L","M","N","O","P","Q","R","S","T",
								 "U","V","W","X","Y","Z"};
	
    static String poiOrgID;
	static String townID;
    static String l3cataID;
    
    public static class threadSpider implements Runnable {  
	   
		public void run() {// 啟動Thread時會執行run  
			String orgPath;
	    	
	    	for(int k=1; k<=6; k++){//分類
	    		l3cataID = "9A0"+alphabet[k];
				for(int j=1; j<=25; j++){//地點
					for(int ii=0; ii<=1; ii++){
				    	for(int  i=1; i<=35; i++){//流水號
				    		orgPath = "http://www.mojotaiwan.com.tw/wiki/wiki.aspx?lfor="+l3cataID+"000"+alphabet[j]+"00"+alphabet[ii]+alphabet[i];
					        
				    		poiOrgID = l3cataID+"000"+alphabet[j]+"00"+alphabet[ii]+alphabet[i];
				    		townID = alphabet[j];
				    		
				    		System.out.println(poiOrgID);
				    		//System.out.println(townID);
				    		//System.out.println(l3cataID);
				    		connectHttp(orgPath);
				    		
				    		try {
				    			int mod = j%5;
								if(mod == 0){
									int sleepTime = (int)(Math.random()*3000);
									Thread.sleep(sleepTime);
								}		    			  			  
							} catch (InterruptedException ex) {  
								ex.printStackTrace();  
							}
							
				    	}
					}
		    	}
	    	}
		}  
	}
    
    /** *//**
     * 入口方法.
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
    	
    	setProxy();
    	Executor tp = Executors.newFixedThreadPool(3);  
		tp.execute(new threadSpider());
    	System.gc();
    	//connectHttp();
        
    }
    
    public static void setProxy(){
    	// 設置proxy伺服器
    	String proxy = "proxy.iii.org.tw",//你的防火牆伺服器
    	port = "3128";//你的防火牆port
    	Properties systemProperties = System.getProperties();
    	systemProperties.setProperty("http.proxyHost",proxy);
    	systemProperties.setProperty("http.proxyPort",port);
    }
    
    public static void connectHttp(String path){
    	try{
    		
	    	
	        URL url = new URL(path);
	        
	        HttpURLConnection.setFollowRedirects(false);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        //設置連接主機超時（單位：毫秒）
            conn.setConnectTimeout(30000);
            //設置從主機讀取數據超時（單位：毫秒）
            conn.setReadTimeout(30000);    
	        conn.setDoOutput(true);
	        
	        //設置User-Agent,讓網站伺服器誤認user端的瀏覽器為google,baidu,BorderSpider,firefox,GoogleReader...
	        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-TW; rv:1.9.1.5) Gecko/20091102 Firefox/3.5.5 GTB6");
	        //取得http資料串流
	        InputStream inputStream = conn.getInputStream();
	        
	        
	        InputStreamReader isr = new InputStreamReader(inputStream, "utf-8");
	        StringBuffer sb = new StringBuffer();
	        BufferedReader in = new BufferedReader(isr);
	        String inputLine;
	        
	        while ((inputLine = in.readLine()) != null) {
	            sb.append(inputLine);
	            sb.append("\n");
	            //System.out.print(inputLine);
	        }
	        
	        String result = sb.toString();
	
	        boolean flag = judgeRedirects(result);
	        if (flag == true){
	        	readByHtml(result);
	        }else{
	        	System.out.println("no this ID");
	        }
	        
	        isr.close();
	        in.close();
    	
    	}catch(Exception conne){

            conne.printStackTrace();

        }
    }
    
    public static boolean judgeRedirects(String resultPage){
    	try{
	    	Parser parserTitle = null;
	        NodeList nodeListTitle = null;
	        NodeFilter filterTitle = null;
	
	        //创建指定编码的Parser对象
	        parserTitle = Parser.createParser(resultPage, "UTF-8");
	        //创建一个接受标签A的过滤器
	        filterTitle = new TagNameFilter("TITLE");
	        //过滤节点内容
	        nodeListTitle = parserTitle.extractAllNodesThatMatch(filterTitle);
	           
	        TitleTag tagTitle = (TitleTag) nodeListTitle.elementAt(0);
	        	
	        if(tagTitle != null){
	        	//System.out.println(tagTitle.getTitle());
	        	if(tagTitle.getTitle().equals("Object moved")){
	        		return false;
	        	}else{
	        		return true;
	        	}
	        	
	        }else{
	        	//System.out.println("not hava url");
	        }
	        
    	}catch(Exception e){

            e.printStackTrace();

        }
    	return false;
    }
    
    /** *//**
     * 按頁面處理.解析標準的html頁面
     * @param content 網頁的内容
     * @throws Exception
     */
    public static void readByHtml(String content) throws Exception {
        try{
	    	Parser parser;
		    HtmlPage htmlPage = null;
		    //NodeList list = null;
		    
		    //將頁面傳給parser進行解析,傳回UTF-8
	        parser = Parser.createParser(content, "utf8");
	        htmlPage = new HtmlPage(parser);
	        //拜訪網頁中的每個節點
	        parser.visitAllNodesWith(htmlPage);
	        
	        /*
	        list = htmlPage.getBody();
	        for (NodeIterator iterator=list.elements(); iterator.hasMoreNodes();) {

	            Node node = iterator.nextNode();
	            //System.out.println(node.toHtml());

	        }*/

	        //將頁面中的Table分別存進陣列中
	        TableTag[] allTable = htmlPage.getTables();
	        
	        
	        //取得景點經緯度
	        getPoiCoordinate(allTable);     
	        //System.out.println(poiLon + "\n");
            //System.out.println(poiLat + "\n");

            //取得圖片
            getPoiImg(poiOrgID);
	        
            //取得景點名稱
	        getPoiName(allTable);
	        //System.out.println(poiName + "\n");   
            
	        //取得電話
	        getPoiPhone(allTable);
	        //System.out.println(poiPhone + "\n");
	        
	        //取得地址
	        getPoiAddress(allTable);
	        //System.out.println(poiAddress + "\n");
	        
	        //取得網址
	        getPoiUrl(allTable);
	        	
            //取得景點介紹
	        getPoiIntro(allTable);
	        //System.out.println(poiIntro + "\n");
	        

	        writeDBPoi(poiOrgID, poiName, poiAddress, poiPhone, poiUrl, poiImg, poiIntro, poiLat, poiLon, townID, l3cataID);
			

	    } catch (ParserException e) {
	        e.printStackTrace();
	    }
    }
    
    public static void getPoiImg(String poiID) throws IOException{
    	
    	poiImg = null;
    	
    	try {
           
        	String imgPath = "http://www.mojotaiwan.com.tw/AJAX/ImageForWiki.aspx?l4="+poiID;
           
        	URL imgUrl = new URL(imgPath);
            URLConnection connImg = imgUrl.openConnection();
            connImg.setDoOutput(true);
            //設置User-Agent,讓網站伺服器誤認user端的瀏覽器為google,baidu,BorderSpider,firefox,GoogleReader...
            connImg.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-TW; rv:1.9.1.5) Gecko/20091102 Firefox/3.5.5 GTB6");
            //設置連接主機超時（單位：毫秒）
            connImg.setConnectTimeout(30000);
            //設置從主機讀取數據超時（單位：毫秒）
            connImg.setReadTimeout(30000); 
            
            
            InputStream inputStreamImg = connImg.getInputStream();
            InputStreamReader isrImg = new InputStreamReader(inputStreamImg, "utf-8");
            BufferedReader inImg = new BufferedReader(isrImg);
            String inputLine;
            
            while ((inputLine = inImg.readLine()) != null) {
            	
            	if(inputLine.equals("../../images/NoImage.jpg")){
            		poiImg = null;
            		System.out.println("no Image");
            	}else{
            		BufferedImage bufferImg = ImageIO.read(new URL(inputLine));
                	if(bufferImg != null){
                		//建立資料夾
                		File imgDir = new File("d:/mojo/img/"+l3cataID);
                		if(!imgDir.exists()){
                			imgDir.mkdir(); 
                		} 
                		// 建立BufferedWriter的輸出串流物件
	                	ImageIO.write(bufferImg,"jpeg",new File("d:/mojo/img/"+l3cataID+"/"+poiID+".jpg"));
	                	poiImg = poiID+".jpg";
            		}else{
            			poiImg = null;
            			System.out.println("no Image");
            		}
                	bufferImg.flush();
            	}
            	
            }
            //System.out.println("connect over");
            inputStreamImg.close();
            isrImg.close();
            inImg.close();
           
        } catch (IOException e) {
           e.printStackTrace();
        }
    }
    
    public static void getPoiUrl(TableTag[] tables){
    		
    		poiUrl = null;
        	TableRow[] rows = tables[7].getRows();

        	TableColumn[] cols = rows[2].getColumns();

            Parser parserSpan = null;
            NodeList nodeList = null;
            NodeFilter filter = null;

            try {
            	//创建指定编码的Parser对象
            	parserSpan = Parser.createParser(cols[0].getStringText(), "UTF-8");
            	//创建一个接受标签A的过滤器
            	filter = new TagNameFilter("A");
            	//过滤节点内容
            	nodeList = parserSpan.extractAllNodesThatMatch(filter);
               
            	LinkTag tag = (LinkTag) nodeList.elementAt(0);
            	
            	if(tag != null){
            		poiUrl = tag.getLink();
            	}else{
            		System.out.println("not hava url");
            	}
            	
            	
            } catch (ParserException e) {
            	
            	e.printStackTrace();
            	
            }
    }
    
    public static void getPoiCoordinate(TableTag[] tables) throws IOException{
    	
    	poiLon = 0;
    	poiLat = 0;
    	
    	TableRow[] rows = tables[7].getRows();
    		
        TableColumn[] cols = rows[0].getColumns();

        Parser parserSpan = null;
        NodeList nodeList = null;
        NodeFilter filter = null;

        try {
        	//创建指定编码的Parser对象
        	parserSpan = Parser.createParser(cols[1].getStringText(), "UTF-8");
        	//创建一个接受标签A的过滤器
        	filter = new TagNameFilter("SPAN");
        	//过滤节点内容
        	nodeList = parserSpan.extractAllNodesThatMatch(filter);
           
        	/*或自行实现NodeList接口，处理过滤
        	 * nodeList = parser.extractAllNodesThatMatch(new NodeFilter() {
               // 实现方法,用以过滤标签
               public boolean accept(Node node) {
                  if (node instanceof LinkTag)// 标记
                      return true;
                  return false;
               }
           	});*/
           
        	Span tag = (Span) nodeList.elementAt(1);
        	String[] tokens = tag.getStringText().split("'");
               
        	poiLon = Double.parseDouble(tokens[3]);
        	poiLat = Double.parseDouble(tokens[5]);
           
        } catch (ParserException e) {
           e.printStackTrace();
        }
    }
    

    public static String getPoiName(TableTag[] tables){
    	poiName = null;
    	//取得Table中第6個Row跟第1個Column的值去掉空白指定給poiName
    	TableRow[] rows = tables[5].getRows();
        TableColumn[] cols = rows[0].getColumns();

        poiName = cols[0].toPlainTextString().trim();
 
    	return poiName;
    }
    
    public static String getPoiPhone(TableTag[] tables){
    	poiPhone = null;
    	
    	TableRow[] rows = tables[7].getRows();
    	
    	//System.out.println(rows.length);
    	TableColumn[] cols = rows[0].getColumns();
    	//System.out.println(cols.length);
    	//將取得的String去掉空白,並以enter分開
    	String[] tokens = cols[1].toPlainTextString().trim().split("\n");

    	if(tokens.length == 2 ){
    		poiPhone = tokens[1].trim();
    	}else{
    		poiPhone = null;
    	}
    	//System.out.println(poiPhone);
    	
	
    	return poiPhone;
    }
    
    public static String getPoiAddress(TableTag[] tables) {
    	
    	poiAddress = null;
    	//處理方法同poiPhone
        TableRow[] rows = tables[7].getRows();
        
        TableColumn[] cols = rows[1].getColumns();
        String[] tokens = cols[0].toPlainTextString().trim().split("\n");

        if(tokens.length == 2 ){
        	poiAddress = tokens[1].trim();
    	}else{
    		poiAddress = null;
    	}
        
        return poiAddress;

    }
    
    public static String getPoiIntro(TableTag[] tables){
    	poiIntro = null;
    	//同poiName
    	TableRow[] rows = tables[5].getRows();

        TableColumn[] cols = rows[4].getColumns();
        
        poiIntro = cols[0].toPlainTextString();
    	
    	return poiIntro;
    }
    
    public static void writeDBPoi(String poi_org_id, String poi_name, String poi_address, String poi_tel, String poi_url, String poi_img, String poi_desc, double poi_lat, double poi_lon, String town_id, String l3cata_id)throws Exception{
    	String user = "root";
		String pass = "";
        String database = "test";
        String url = "jdbc:mysql://127.0.0.1:3306/" + database + "?useUnicode=true&characterEncoding=UTF-8";

        try{

        	//定義驅動程式與資料來源之間的連結
			Class.forName("org.gjt.mm.mysql.Driver");
			//建立一個聯結物件
			Connection conn = DriverManager.getConnection(url,user,pass);
			//建立Statement物件
			
			//ResultSet rs;
	        //建立Statement物件，建立陳述式物件
			Statement stmt;

            //stmt = conn.createStatement();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            //System.out.println("Connect SUCCESS!!");
			//stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
          	//顯示資料
            String query = "INSERT INTO poi (poi_org_id, poi_name, poi_address, poi_tel, poi_url, poi_img, poi_desc, poi_lat, poi_lon, poi_ele, town_id, l3cata_id)"+
            				"VALUES ('"+ poi_org_id +"','" + poi_name + "', '" + poi_address + "', '" + poi_tel + "', '" + poi_url + "', '" +
            				poi_img + "', '" + poi_desc + "', " + poi_lat + ", " + poi_lon + ", NULL,'"+ town_id +"','"+ l3cata_id +"')";
            
            String query1 = "DELETE FROM poi WHERE poi_id IN('65','66','67','68','69','70')"; 
            stmt.executeUpdate(query);       
            
            conn.close();	

        }catch(Exception sqle){

            System.out.println("SQL Exception : " + sqle);

        }
    }
}
