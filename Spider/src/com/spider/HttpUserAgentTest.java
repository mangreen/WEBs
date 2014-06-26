package com.spider;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpUserAgentTest {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

    	// 設置proxy伺服器
    	String proxy = "proxy.iii.org.tw",//你的防火牆伺服器
    	port = "3128";//你的防火牆port
    	Properties systemProperties = System.getProperties();
    	systemProperties.setProperty("http.proxyHost",proxy);
    	systemProperties.setProperty("http.proxyPort",port);
    	
    	System.out.println("start \n");
    	
        URL url = new URL("http://www.mojotaiwan.com.tw/");
        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();

        
        //設置User-Agent,讓網站伺服器誤認user端的瀏覽器為google,baidu,BorderSpider,firefox,GoogleReader...
        httpConnection.setRequestProperty("User-Agent", "BorderSpider ( http://www.mojotaiwan.com.tw/)");
        
        //獲得輸入流
        InputStream input = httpConnection.getInputStream();

        InputStreamReader inReader = new InputStreamReader(input, "utf-8");//獲得鏈接該類的流,指定網頁回傳結果的編碼utf-8
        BufferedReader reader = new BufferedReader(inReader);

        int retVal = 0;
        char[] cString = new char[1000];
        int len = 1000;
        String getString = "";
        StringBuffer sb = new StringBuffer();

        /* 將reader內容存入cString中。    off:0 開始存儲字符的偏移量。 len: 讀取的最大字符數
        while ((retVal = reader.read(cString, 0, len)) != -1) {
            //將 char 陣列 cString 中 由 cString[0] 開始取 count 個元素 轉換成字串, 加到getString
        	getString += String.valueOf(cString, 0, retVal);
        }
        */
        
        while( (getString=reader.readLine()) != null) {
            sb.append(getString);
        }
        Pattern pattern = Pattern.compile("ctl00_center"); 
        Matcher m = pattern.matcher(sb.toString());
        while(m.find()) {
            String str = m.group();
            System.out.println(str);
        }

        
        //System.out.println(getString);
        System.out.println("end \n");
    }
} 

