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

public class MojoFoodSpider {
	
	static double poiLon;
	static double poiLat;
	
	static int intPoiLon;
	static int intPoiLat;
	
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
	    	
			//for(int kk=7; kk>=2; kk--){
		    	for(int k=16; k<=16; k++){//分類
		    		l3cataID = "210"+alphabet[k];//
					for(int j=3; j<=25; j++){//縣市地點
				    	for(int iii=0; iii<=0; iii++){
							for(int ii=0; ii<=0; ii++){
								for(int  i=1; i<=35; i++){//流水號
						    		orgPath = "http://www.mojotaiwan.com.tw/foodwiki/Detail.aspx?id="+l3cataID+"000"+alphabet[j]+"0"+alphabet[iii]+alphabet[ii]+alphabet[i];
							        
						    		poiOrgID = l3cataID+"000"+alphabet[j]+"0"+alphabet[iii]+alphabet[ii]+alphabet[i];
						    		townID = alphabet[j];
						    		
						    		System.out.println(poiOrgID);
						    		//System.out.println(townID);
						    		//System.out.println(l3cataID);
						    		connectHttp(orgPath);
						    		
						    		try {
						    			int mod = j%5;
										if(mod == 0){
											int sleepTime = (int)(Math.random()*2000);
											Thread.sleep(sleepTime);
										}		    			  			  
									} catch (InterruptedException ex) {  
										ex.printStackTrace();  
									}
									
						    	}
					    	}
				    	}
			    	}
		    	//}
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
    	//connectHttp(orgPath);
        
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
	        
	        String htmlPage = sb.toString();
	
	        boolean flag = judgeRedirects(htmlPage);//以轉址判斷網址是否存在
	        if (flag == true){
	        	readByHtml(htmlPage);
	        	
	        }else{
	        	System.out.println("no this ID");
	        }
	        
	        
	        isr.close();
	        in.close();
    	
    	}catch(Exception conne){

            conne.printStackTrace();

        }
    }
    
    /*
    public static String getSubString(String str, String str1, String str2){

    	int startIndex = str.indexOf(str1) + str1.length();  
    	int endIndex = str.indexOf(str2);  
    	String sString = str.substring(startIndex, endIndex); 
    	
    	return sString;
    }*/
    
    //從母字串中抓出由start開頭,end結尾的子字串
    public static String strFiltrate(String str, String start, String end){  
    	StringBuffer buffer = new StringBuffer();  
    	int i = 0, leg = 0;  
    	boolean isStart = false;  
    	
    	while(i != -1){  
    		if(isStart){  
    			isStart = false;  
    			i = str.indexOf(end);  
    	    	
    	    	if(i == -1){  
    	    		leg = str.length();  
    	    	}else{  
    	    		leg = i;  
    	    	}  
    	    
    	    	buffer.append(str.substring(start.length()-1, leg).toCharArray());  
    		
    		}else{  
    			i = str.indexOf(start);  
    			if(i > -1){  
    				isStart = true;  
    			}  
    			str = str.substring(i + 1);  
    		}  
    	}  
    	return   buffer.toString();  
    }   
    
    //轉址判斷
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
    public static void readByHtml(String content) throws IOException{
	        
    	try{
    		//取得景點經緯度
	        getPoiCoordinate(content);     
	        //System.out.println(poiLon + "\n");
            //System.out.println(poiLat + "\n");
            
            //System.out.println(intPoiLon + "\n");
            //System.out.println(intPoiLat + "\n");

            //取得圖片
            //getPoiImg(poiOrgID);
	        
            //取得景點名稱
	        getPoiName(content);
	        //System.out.println(poiName + "\n");   
            
	        //取得電話
	        getPoiPhone(content);
	        //System.out.println(poiPhone + "\n");
	        
	        //取得地址
	        getPoiAddress(content);
	        //System.out.println(poiAddress + "\n");
	        
	        //取得網址
	        getPoiUrl(content);
	        //System.out.println(poiUrl + "\n");
	        	
            //取得景點介紹
	        getPoiIntro(content);
	        //System.out.println(poiIntro + "\n");
	        
	        if(poiName.indexOf("麥當勞")>=0){
	        	writeDBPoi(poiOrgID, poiName, poiAddress, poiPhone, poiUrl, poiImg, poiIntro, poiLat, poiLon, townID, l3cataID, intPoiLat, intPoiLon);
	        }
    	} catch (Exception e) {
	        e.printStackTrace();
	    }

    }
    
    public static void getPoiCoordinate(String htmlTag){
    	
    	poiLon = 0;
    	poiLat = 0;
    	
    	//處理同poiPhone
    	String str1 = strFiltrate(htmlTag, "ShowWikiGeoSMSWindow", "<img border");
           
        String[] tokens = str1.split("'");
               
        poiLon = Double.parseDouble(tokens[3]);
        poiLat = Double.parseDouble(tokens[5]);
        
        String[] lontokens = tokens[3].split("[.]");
        if(lontokens[1].length() < 6){
			for(int i=0; i<= (6- lontokens[1].length()); i++){				
				lontokens[1] = lontokens[1] + "0";
			}
		}else if(lontokens[1].length() > 6){
			lontokens[1] = lontokens[1].substring(0, 6);
			//System.out.println(Integer.parseInt(message));
		}
        intPoiLon = Integer.parseInt(lontokens[0]+lontokens[1]);
        
        String[] lattokens = tokens[5].split("[.]");
        if(lattokens[1].length() < 6){
			for(int i=0; i<= (6- lattokens[1].length()); i++){				
				lattokens[1] = lattokens[1] + "0";
			}
		}else if(lattokens[1].length() > 6){
			lattokens[1] = lattokens[1].substring(0, 6);
			//System.out.println(Integer.parseInt(message));
		}
        intPoiLat = Integer.parseInt(lattokens[0]+lattokens[1]);

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

    public static String getPoiName(String htmlTag){
    	poiName = null;
    	//處理同poiPhone
    	String str1 = strFiltrate(htmlTag, "font-size:Medium;font-weight:bold;\">", "</span>").trim();
    	//String str2 = strFiltrate(str1, "AddrLbl\">", "</span>");
    	
    	if(str1.length() == 0){
    		str1 = null;
    	}
    	
    	poiName = str1;
        
        return poiName;
    }
    
    public static String getPoiPhone(String htmlTag){
    	poiPhone = null;
    	//先取得"電話："到"</td>"的子字串,在取得電話號碼
    	String str1 = strFiltrate(htmlTag, "電話", "</td>");
    	String str2 = strFiltrate(str1, "<span id=\"ctl00_center_PhoneLbl\">", "</span>").trim();
    	
    	if(str2.length() == 0){
    		str2 = null;
    	}
    	
    	poiPhone = str2;
    	
    	return poiPhone;
    }
    
    public static String getPoiAddress(String htmlTag) {  	
    	poiAddress = null;
    	//處理同poiPhone
    	String str1 = strFiltrate(htmlTag, "地址", "</td>");
    	String str2 = strFiltrate(str1, "ctl00_center_AddrLbl\">", "</span>").trim();
    	
    	if(str2.length() == 0){
    		str2 = null;
    	}
    	
    	poiAddress = str2;
        
        return poiAddress;
    }
    
    public static String getPoiUrl(String htmlTag){
		
		poiUrl = null;
		//處理同poiPhone
    	String str1 = strFiltrate(htmlTag, "網址：", "</td>");
    	String str2 = strFiltrate(str1, "ctl00_center_WebsiteLbl\">", "</span>").trim();
    	
    	if(str2.length() == 0){
    		str2 = null;
    	}
    	
    	poiUrl = str2;
        
        return poiUrl;
    }
    
    public static String getPoiIntro(String htmlTag){
    	poiIntro = null;
    	//處理同poiPhone
    	String str1 = strFiltrate(htmlTag, "<dt>服務特色", "</dl>");
    	String str2 = strFiltrate(str1, "</dt><dd>", "</dd>").trim();
    	
    	if(str2.length() == 0){
    		str2 = null;
    	}
    	
    	poiIntro = null;
        
        return poiIntro;
    }
    
    //寫入資料庫
    public static void writeDBPoi(String poi_org_id, String poi_name, String poi_address, String poi_tel, String poi_url, String poi_img, String poi_desc, double poi_lat, double poi_lon, String town_id, String l3cata_id, int intPoiLat, int intPoiLon)throws Exception{
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
            String query = "INSERT INTO poi (poi_org_id, poi_name, poi_address, poi_tel, poi_url, poi_img, poi_desc, poi_lat, poi_lon, poi_ele, town_id, l3cata_id, poi_int_lat, poi_int_lon)"+
            				"VALUES ('"+ poi_org_id +"','" + poi_name + "', '" + poi_address + "', '" + poi_tel + "', '" + poi_url + "', '" +
            				poi_img + "', '" + poi_desc + "', " + poi_lat + ", " + poi_lon + ", NULL,'"+ town_id +"','"+ l3cata_id +"', '"+intPoiLat+"', '"+intPoiLon+"')";
            
            //String query1 = "DELETE FROM poi WHERE poi_id IN('65','66','67','68','69','70')"; 
            stmt.executeUpdate(query);       
            
            conn.close();	

        }catch(Exception sqle){

            System.out.println("SQL Exception : " + sqle);

        }
    }
}

