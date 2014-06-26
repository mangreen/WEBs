package com.spider;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

import org.htmlparser.filters.*;
import org.htmlparser.*;
import org.htmlparser.nodes.*;
import org.htmlparser.tags.*;
import org.htmlparser.util.*;
import org.htmlparser.visitors.*;

/** *//**
 * 測試HTMLParser的使用.
 * @author <a href="mailto:xiexingxing1121@126.com">AmigoXie</a>
 * Creation date: 2008-1-18 - 上午11:44:22
 */
public class HTMLParserTest {
    /** *//**
     * 入口方法.
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
    	// 設置proxy伺服器
    	String proxy = "proxy.iii.org.tw",//你的防火牆伺服器
    	port = "3128";//你的防火牆port
    	Properties systemProperties = System.getProperties();
    	systemProperties.setProperty("http.proxyHost",proxy);
    	systemProperties.setProperty("http.proxyPort",port);
    	
    	String path = "http://www.mojotaiwan.com.tw/wiki/wiki.aspx?lfor=100200020003";
        URL url = new URL(path);
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        //設置User-Agent,讓網站伺服器誤認user端的瀏覽器為google,baidu,BorderSpider,firefox,GoogleReader...
        conn.setRequestProperty("User-Agent", "BorderSpider ( http://www.mojotaiwan.com.tw/wiki/wiki.aspx?lfor=100200020003)");
        
        InputStream inputStream = conn.getInputStream();
        InputStreamReader isr = new InputStreamReader(inputStream, "utf8");
        StringBuffer sb = new StringBuffer();
        BufferedReader in = new BufferedReader(isr);
        String inputLine;
        
        while ((inputLine = in.readLine()) != null) {
            sb.append(inputLine);
            sb.append("\n");
        }
        
        String result = sb.toString();

        //readByHtml(result);
        readTextAndLinkAndTitle(result);
    }
    
    /** *//**
     * 按页面方式处理.解析标准的html页面
     * @param content 网页的内容
     * @throws Exception
     */
    public static void readByHtml(String content) throws Exception {
        Parser myParser;
        myParser = Parser.createParser(content, "utf8");
        HtmlPage visitor = new HtmlPage(myParser);
        myParser.visitAllNodesWith(visitor);

        String textInPage = visitor.getTitle();
        System.out.println(textInPage);
        NodeList nodelist;
        nodelist = visitor.getBody();
        
        System.out.print(nodelist.asString().trim());
    }

    /** *//**
     * 分别读纯文本和链接.
     * @param result 网页的内容
     * @throws Exception
     */
    public static void readTextAndLinkAndTitle(String result) throws Exception {
        Parser parser;
        NodeList nodelist;
        parser = Parser.createParser(result, "utf8");
        
        NodeFilter textFilter = new NodeClassFilter(TextNode.class);
        NodeFilter linkFilter = new NodeClassFilter(LinkTag.class);
        NodeFilter titleFilter = new NodeClassFilter(TitleTag.class);
        
        OrFilter lastFilter = new OrFilter();
        lastFilter.setPredicates(new NodeFilter[] { textFilter, linkFilter, titleFilter });
        nodelist = parser.parse(lastFilter);
        Node[] nodes = nodelist.toNodeArray();
        String line = "";
        
        for (int i = 0; i < nodes.length; i++) {
            Node node = nodes[i];
            if (node instanceof TextNode) {
                TextNode textnode = (TextNode) node;
                line = textnode.getText();
            } else if (node instanceof LinkTag) {
                LinkTag link = (LinkTag) node;
                line = link.getLink();
            } else if (node instanceof TitleTag) {
                TitleTag titlenode = (TitleTag) node;
                line = titlenode.getTitle();
            }
            
            if (isTrimEmpty(line))
                continue;
            System.out.println(line);
        }
    }
    
    /** *//**
     * 去掉左右空格后字符串是否为空
     */
    public static boolean isTrimEmpty(String astr) {
        if ((null == astr) || (astr.length() == 0)) {
            return true;
        }
        if (isBlank(astr.trim())) {
            return true;
        }
        return false;
    }

    /** *//**
     * 字符串是否为空:null或者长度为0.
     */
    public static boolean isBlank(String astr) {
        if ((null == astr) || (astr.length() == 0)) {
            return true;
        } else {
            return false;
        }
    }
}

