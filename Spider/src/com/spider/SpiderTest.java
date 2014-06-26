package com.spider;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.tags.TableColumn;
import org.htmlparser.tags.TableRow;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.HtmlPage;

public class SpiderTest {
	
	public static void main(String args[]) throws Exception{
		String ahtml = "<table><tr><td style=\"line-height: 25px;\"><p>當紅偶像劇《這裡發現愛》戲中，男女主角王傳一和關穎的定情之吻，就在《台北101大樓》燦爛的煙火前完成，也為這座即將被「杜拜塔」(Burj Dubai)打敗的「世界第一高樓」更添幾許浪漫。<br />在這座台灣最亮眼的地標大樓內部，有占地廣達2300坪購物中心，空間規劃概念以「全球」著眼，將紐約的第五大道、巴黎的香榭麗舍大道、羅馬的西班牙大道等都搬進賣場，時尚感十足。至於高樓景觀台，則分為89樓的室內觀景台和91樓的戶外觀景台兩處，遊客於5樓櫃台買票後，即可搭乘高速恆壓電梯直抵觀景台，並以360度的視野盡情眺覽大台北景致，尤其是在入夜之後，璀璨的夜景更加浪漫，無怪乎這裡能成為台灣人氣最旺的景點，不論本國人還是外國人都不得不去朝聖！<br /><br />營業時間：B1~3F、5F週日至週四11:00~21:30，4F週一至週日11:00~22:00，B1-5F週五、週六、例假日前及當天11:00~22:00。觀景台營業時間為每日10:00~22:00，最後購票及入場時間為21:15。<br />費用：89樓觀景台全票為350元、優惠票為320元、110公分以下孩童免購票，至91樓戶外觀景台全票為100元、130公分以下孩童免購票。<br /><br /><img src=\"http://2.share.photo.xuite.net/kktravel/1243273/2482496/91892019_m.jpg\" alt= width=\"335\" height=\"500\" />&nbsp;<img src=\"http://2.share.photo.xuite.net/kktravel/1243271/2482496/91892017_m.jpg\" width=\"333\" height=\"500\" /></p></td></tr></table>";

		Parser parser = null;
	    HtmlPage htmlPage = null;
	    NodeList list = null;

	      

	    try {

	        parser = new Parser();

	        String inputHTML = "<html>" + "<head>" +

	                     "<title>Welcome to the HTMLParser website</title>" +

	                     "</head><body>Welcome to HTMLParser" +

	                     "<table id=’table1′ >" +

	                     	"<tr><td>1-11</td><td>1-12</td><td>1-13</td>" +
	                     	
	                     	"<table id=’table1′ >" +
	                     	
	                     	"</table>"+

	                     	"<tr><td>1-21</td><td>1-22</td><td>1-23</td>" +

	                     	"<tr><td>1-31</td><td>1-32</td><td>1-33</td></table>" +

	                     "<table id=’table2′ >" +

	                     	"<tr><td>2-11</td><td>2-12</td><td>2-13</td>" +

	                     	"<tr><td>2-21</td><td>2-22</td><td>2-23</td>" +

	                     	"<tr><td>2-31</td><td>2-32</td><td>2-33</td></table>" +

	                     "</body></html>";

	        parser.setInputHTML(inputHTML);
	        htmlPage = new HtmlPage(parser);
	        parser.visitAllNodesWith(htmlPage);
	        System.out.println("Title:" + htmlPage.getTitle());
	        list = htmlPage.getBody();


	        for (NodeIterator iterator=list.elements(); iterator.hasMoreNodes();) {

	            Node node = iterator.nextNode();
	            //System.out.println(node.toHtml());

	        }

	        TableTag[] tables = htmlPage.getTables();
	        System.out.println(tables.length);
	        
	        for (int i=0; i<tables.length; i++) {

	        	TableRow[] rows = tables[i].getRows();

	        	for (int r=0; r<rows.length; r++) {

	            	TableColumn[] cols = rows[r].getColumns();
	                for (int c=0; c<cols.length; c++) {
	                   System.out.print(cols[c].toPlainTextString() + " ");
	                }
	                System.out.println();
	            }
	        }	          

	    } catch (ParserException e) {
	        e.printStackTrace();
	    }
	}
}