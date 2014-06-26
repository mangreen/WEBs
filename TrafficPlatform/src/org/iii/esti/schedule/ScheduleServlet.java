package org.iii.esti.schedule;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ScheduleServlet extends HttpServlet{
		
	private static final long serialVersionUID = 1L;
	private static final int daysOfMonth[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31, 29};
	private static final int weekMask[] = {1, 2, 4, 8, 16, 32, 64};
	private static ScheduleDAO sDAO = new ScheduleDAO();
    public ArrayList<Schedule> list;
    public static TreeMap<String, Timer> map;
    public static String logdir = "";
    public static String TYPE_WIRTE_LOG_UPDATE = "update";
    public static String TYPE_WIRTE_LOG_EDIT = "edit";
    
    public ScheduleServlet(){  
        super();  
    }  
      
    //servlet initial
    public void init() throws ServletException{  
        /*
    	Timer timer = new Timer();  
        timer.schedule(new MyTask(), 1000, 2000);// 在1秒後執行此任務,每次間隔2秒,如果傳遞一個Data參數,就可以在某個固定的時間執行這個任務.
        
        Timer timer2 = new Timer();  
        timer2.schedule(new MyTask2(), 1000, 1000);// 在1秒後執行此任務,每次間隔2秒,如果傳遞一個Data參數,就可以在某個固定的時間執行這個任務.  
  
        // 這個是用來停止此任務的,否則就一直循環執行此任務了  
        while(true){  
            try{  
            	int ch = System.in.read(); //輸入字符「c」，回車即停止執行  
                if (ch - 'c' == 0){  
                    timer.cancel();// 使用這個方法退出任務 
                    timer2.cancel();// 使用這個方法退出任務  
                }  
  
            }catch (IOException e){  
                e.printStackTrace();  
            }  
        }*/
    	
    	logdir = getClassLocation()+"/log";//TODO
    	//System.out.println(logdir);
    	//Class thisClass = this.getClass();
    	//System.out.println(Path.getPathFromClass(thisClass));
    	

    	
    	list = sDAO.selectAll();//抓出所有清單
    	map = new TreeMap<String, Timer>();
    	//Date d1 = Schedule.stringToDate("0000-00-00 00:00:00");
    	//Date d2 = Schedule.stringToDate("2011-05-09 03:30:30");
    	//System.out.println("gggggggg"+Schedule.dateToString(d1));
    	//System.out.println("afsdfasfasdfd"+d.getDay());
    	//updateLastAndNext(list.get(0));
    	//System.out.println("123456789"+sDAO.queryStatus(0));
    	//Date d = stringToDate(list.get(0).getEnd());
    	//setYears(d, setMonths(d, setDates(d, setHours(d, 24))));
    	//System.out.println("gggggggg"+dateToString(d));
    	for(int i=0; i<list.size(); i++){
    		//Calendar time=Calendar.getInstance();
    		//Date d = stringToDate(list.get(i).getStart());
    		//time.setTime(d);
    		//time.set(d.getYear(), d.getMonth(), d.getDate(), d.getHours(), d.getMinutes(), 59);
    		//System.out.println("kkkkkkkkkkkkkkkk");
    		
    		scheduleTimer(list.get(i));
    	}
    	
    }
    
    public static void scheduleTimer(Schedule schedule){
    	if(true == schedule.getStatus()){//執行狀態為true才執行
			if(null == schedule.getEndDate()){//確定有無終止時間
				Timer timer = new Timer();
				//timer.schedule(new MyTask(), 0, 1000);	
				//System.out.println("ccccccccccccccccc"+list.get(i).getStart());
				if(null != schedule.getNextString() && !schedule.getNextString().equals(Schedule.initialTime)){//判別下次執行時間是否為空
					//System.out.println("kkkkkkkkkkk111111111111"+list.get(i).getWeekday()+list.get(i).getDate());
					if(null != schedule.getInterval() && !schedule.getInterval().equals("0")){//判定是否為一般循環
	    				
	    				//System.out.println("ccccccccc111111111111"+getDelay(list.get(i)));
	    				//timer.schedule(new ExeTask(list.get(i)), stringToDate(list.get(i).getStart()), getDelay(list.get(i)));
	    				//timer.schedule(new ExeTask(list.get(i).getProgram()), stringToDate(list.get(i).getNext()), getDelay(list.get(i)));
						timer.schedule(new ExeTask(schedule, timer), schedule.getNextDate(), getDelay(schedule));
	    			}else if(0 < schedule.getWeekday() || 0 < schedule.getDate()){//判定是否為周循環或月循環
	    				//System.out.println("ccccccccc111111111111"+list.get(i).getNextDate());
	    				timer.schedule(new ExeTask(schedule, timer), schedule.getNextDate());
	    			}else{
	    				
	    			}
					//System.out.println("ccccccccc111111111111");
				}
				
				map.put(String.valueOf(schedule.getID()), timer);
			}else if(schedule.getEndDate().after(schedule.getNextDate())){//確定終止時間是否過期
				Timer timer = new Timer();
				//System.out.println("kkkkkkkkkkk22222222222");
				if(null != schedule.getNextString() && !schedule.getNextString().equals(Schedule.initialTime)){//判別下次執行時間是否為空
					
					if(null != schedule.getInterval() && !schedule.getInterval().equals("0")){//判定是否為一般循環
						//System.out.println("ccccccc222222222"+getDelay(list.get(i)));
						timer.schedule(new ExeTask(schedule, timer), schedule.getNextDate(), getDelay(schedule));
	    			}else if(0 != schedule.getWeekday() || 0 != schedule.getDate()){//判定是否為周循環或月循環
	    				timer.schedule(new ExeTask(schedule, timer), schedule.getNextDate());
	    			}
				}
				
				map.put(String.valueOf(schedule.getID()), timer);
			}
    	}
    }
    
    public static boolean dropTimer(int id){
    	System.out.println("drop timer ");
    	
    	try{ 
    		Timer timer = map.get(String.valueOf(id));
    		if(null != timer){
	    		timer.cancel();// 使用這個方法退出任務  
	    		map.remove(String.valueOf(id));
    		}
    		return true;
        }catch (Exception e){  
            e.printStackTrace();  
            return false;
        }
    }
    
    public static void modifyTimer(Schedule schedule){
    	System.out.println("modify timer ");
    	writeLogfile(schedule, TYPE_WIRTE_LOG_EDIT);//TODO
    	dropTimer(schedule.getID());
    	scheduleTimer(schedule);
    }
    
    //計算循環週期
    public static int getDelay(Schedule schedule){
    	int delay = 0;
    	//System.out.println("fffffffffffffff");
    	
    	char token = schedule.getInterval().charAt(0);
    	
    	int length = schedule.getInterval().length();
    	int interval = Integer.valueOf(schedule.getInterval().substring(1, length));
    	//System.out.println("interval:"+interval); 
    	
    	switch(token){
    	case 's':
    		//System.out.println("ssssssssssss"); 
    		delay = interval*1000;
    		break;
        case 'm': 
            //System.out.println("mmmmmmmmmmmm");
        	delay = interval*60*1000;
            break; 
        case 'h': 
            //System.out.println("hhhhhhhhhhhh");
        	delay = interval*60*60*1000;
            break; 
        case 'd': 
            //System.out.println("dddddddddddd");
            delay = interval*24*60*60*1000;
            break;
        case 'w': 
            //System.out.println("wwwwwwwwwwww");
            delay = interval*7*24*60*60*1000;
            break;
        case 'M': 
            System.out.println("MMMMMMMMMMMM");
            delay = interval*30*24*60*60*1000;
            break;
        case 'y': 
            System.out.println("yyyyyyyyyyyy");
            delay = interval*365*24*60*60*1000;
            break;
        default: 
            System.out.println("wrong interval character"); 
    	}
    	
    	return delay;
    }
    
    //測試執行的任務  
    class MyTask extends java.util.TimerTask{  
        public void run(){  
            System.out.println("____ok____");  
        }  
    }
    
    //具體執行的任務  
    public static class ExeTask extends java.util.TimerTask{  
        private String url;
        private Schedule schedule;
    	private Timer timer;
        
    	public ExeTask(Schedule schedule, Timer timer){
        	this.url = schedule.getProgram();
        	this.schedule = schedule;
        	System.out.println("排入schedule ID:"+schedule.getID());
        	this.timer = timer;
        }
    	
    	public void run(){  
        	Runtime runtime = Runtime.getRuntime();
            Process process = null;
            String line = null;
            InputStream is = null;
            InputStreamReader isr = null;
            BufferedReader br = null;
            //String ip = "127.0.0.1"; //要Ping 的IP位址
            try {
            	process = runtime.exec(url);
            	is = process.getInputStream();
            	isr = new InputStreamReader(is);
            	br = new BufferedReader(isr);
            	while ( (line = br.readLine()) != null) {
            		System.out.println(line);
            		System.out.flush();
            	}
            	is.close();
            	isr.close();
            	br.close();
              
            	System.out.println("Java 呼叫 ping 程式，執行完畢！ 所花時間:"+process.waitFor());
            	
            	updateLastAndNext(schedule, new Date());
            	
            	if(schedule.getStatus()){
            		if(null != schedule.getNextString() && !schedule.getNextString().equals(Schedule.initialTime)){//TODO
	            		if(0 != schedule.getWeekday() || 0 != schedule.getDate()){
	            			timer.schedule(new ExeTask(schedule, timer), schedule.getNextDate());
	      			  	}
            		}else if(schedule.getEndDate().after(schedule.getNextDate())){//TODO
            			if(0 != schedule.getWeekday() || 0 != schedule.getDate()){
	            			timer.schedule(new ExeTask(schedule, timer), schedule.getNextDate());
	      			  	}
            		}
            	}
            }catch (Exception e) {
              System.out.println("Execute Error:"+e);
              runtime.exit(0);
            }
        }  
    }
    
    //更新下次執行時間與上次執行時間
    public static void updateLastAndNext(Schedule schedule, Date current){
    	schedule.setLast(current);//TODO
    	Date newNextDate = current;//TODO

    	if(null != schedule.getInterval() && !schedule.getInterval().equals("0")){
    		//int delay = 0;
        	//System.out.println("fffffffffffffff");
        	
        	char token = schedule.getInterval().charAt(0);
        	
        	int length = schedule.getInterval().length();
        	int interval = Integer.valueOf(schedule.getInterval().substring(1, length));
        	//System.out.println("interval:"+interval);
        	
        	switch(token){
        	case 's':
        		//System.out.println("ssssssssssss"); 
        		//delay = interval*1000;
        		//int sec = nextDate.getSeconds()+interval;
        		//nextDate.setSeconds(sec%60);
        		//nextDate.setMinutes(nextDate.getMinutes()+sec/60);
        		setYears(newNextDate, setMonths(newNextDate, setDates(newNextDate, setHours(newNextDate, setMinutes(newNextDate, setSeconds(newNextDate, interval))))));
        		
        		break;
            case 'm': 
                //System.out.println("mmmmmmmmmmmm");
            	//delay = interval*60*1000;
            	setYears(newNextDate, setMonths(newNextDate, setDates(newNextDate, setHours(newNextDate, setMinutes(newNextDate, interval)))));
                break; 
            case 'h': 
                //System.out.println("hhhhhhhhhhhh");
            	//delay = interval*60*60*1000;
            	setYears(newNextDate, setMonths(newNextDate, setDates(newNextDate, setHours(newNextDate, interval))));
                break; 
            case 'd': 
                //System.out.println("dddddddddddd");
                //delay = interval*24*60*60*1000;
            	setYears(newNextDate, setMonths(newNextDate, setDates(newNextDate, interval)));
                break;
            case 'w': 
                //System.out.println("wwwwwwwwwwww");
                //delay = interval*7*24*60*60*1000;
            	setYears(newNextDate, setMonths(newNextDate, setDates(newNextDate, interval*7)));
                break;
            case 'M': 
                //System.out.println("MMMMMMMMMMMM");
            	setYears(newNextDate, setMonths(newNextDate, interval));
                break;
            case 'y': 
                //System.out.println("yyyyyyyyyyyy");
            	setYears(newNextDate, interval);
                break;
            default: 
                System.out.println("wrong interval char"); 
        	}
		}else if(0 != schedule.getWeekday()){
			int afterday=7;
			int tmp=0;
			for(int i=0; i<7; i++){
				if(weekMask[i] == (weekMask[i]&schedule.getWeekday())){
					/*
					if((tmp=i-newNextDate.getDay())>0){
						if(tmp<newNextDate.getDay()){
							afterday=tmp;
						}
					}else{
						if(tmp != 0 && (tmp+=7) < afterday){
							afterday=tmp;
						}
					}*/
					if(i>newNextDate.getDay()){
						if(afterday>(tmp=i-newNextDate.getDay())){
							afterday=tmp;
						}
					}else{
						if(afterday>(tmp=i+7-newNextDate.getDay())){
							afterday=tmp;
						}
					}
				}
			}
			//System.out.println("fdafdsfasfffff "+afterday);
			newNextDate.setDate(newNextDate.getDate()+afterday);
			newNextDate.setHours(schedule.getStartDate().getHours());
			newNextDate.setMinutes(schedule.getStartDate().getMinutes());
			newNextDate.setSeconds(schedule.getStartDate().getSeconds());
		}else if(0 != schedule.getDate()){
			//System.out.println("ffggggggggggggff");
			newNextDate.setDate(schedule.getDate());
			newNextDate.setMonth(newNextDate.getMonth()+1);
			newNextDate.setHours(schedule.getStartDate().getHours());
			newNextDate.setMinutes(schedule.getStartDate().getMinutes());
			newNextDate.setSeconds(schedule.getStartDate().getSeconds());
		}
    	
    	//判斷是否過期
    	if(null != schedule.getEndDate()){//TODO
	    	if(schedule.getEndDate().before(newNextDate)){
	    		schedule.setStatus(false);
	    	}
    	}
    	
    	schedule.setNext(newNextDate);
    	//TODO
    	if(sDAO.updateTime(schedule.getID(), schedule.getStatus(), schedule.getLastString(), schedule.getNextString())){
    		writeLogfile(schedule, TYPE_WIRTE_LOG_UPDATE);
    	}
    }
    
    //計算與設定schedule秒鐘
    public static int setSeconds(Date date, int seconds){
    	int sec = date.getSeconds()+seconds;
    	int min = sec/60;
		date.setSeconds(sec%60);
		return min;
    }
    
    //計算與設定schedule分鐘
    public static int setMinutes(Date date, int minutes){
    	int min = date.getMinutes()+minutes;
    	int hour = min/60;
    	date.setMinutes(min%60);
    	return hour;
    }
    
    //計算與設定schedule小時
    public static int setHours(Date date, int hours){
    	int hour = date.getHours()+hours;
    	int day = hour/24;
    	date.setHours(hour%24);
    	return day; 
    }
    
    //計算與設定schedule日期
    public static int setDates(Date date, int days){
    	int day = date.getDate()+days;
    	int month = 0;
    	if(2!=date.getMonth() || !judgeLeapYear(date.getYear())){
    		date.setDate(day%daysOfMonth[date.getMonth()]);
    		month = day/daysOfMonth[date.getMonth()];
    		//System.out.println(days+"dddddddd"+day+"dddddd"+daysOfMonth[date.getMonth()]);
    	}else{
    		date.setDate(day%daysOfMonth[12]);
    		month = day/daysOfMonth[12];
    		//System.out.println(days+"llllllll"+day+"llllllllll"+daysOfMonth[date.getMonth()]);
    	}
    	return month;
    }
     
    //判斷閏年
    public static boolean judgeLeapYear(int year){
    	if((year/4==0 && year/100!=0) || year/400 == 0){
    		return true;
    	}	
    	return false;
    }
    
    //計算與設定schedule月份
    public static int setMonths(Date date, int months){
    	int month = date.getMonth()+months;
    	date.setMonth(month%12);
    	int year = month/12;
    	return year;
    }
    
    //計算與設定schedule年份
    public static void setYears(Date date, int years){
    	date.setYear(date.getYear()+years);
    }
    
    public String getClassLocation(){//測試取得檔案資料位置
        Class runtimeClass = this.getClass();
        String runtimeClassName = runtimeClass.getName();
        String className = runtimeClassName.replace('.','/')+".class";
        String resourceName = "/"+className;
        java.net.URL classResourceURL = runtimeClass.getResource(resourceName);
        String classPath = classResourceURL.getPath();
        String dirPath = classPath.substring(0, classPath.lastIndexOf("/"));

        System.out.println("runtimeClassName: "+runtimeClassName);
        System.out.println("className: "+className);
        System.out.println("resourceName: "+resourceName);
        System.out.println("classPath: "+classPath);
        System.out.println("dirPath: "+dirPath);
        
        return dirPath;
	}
    
    public static boolean writeLogfile(Schedule schedule, String writetype) {//測試file I/O
        String filename = schedule.getID()+".txt";
        String directory = logdir;
        String text="";

        
        File file = new File(directory, filename);
        if(file.exists()){//檔案存在, 在就印出檔案資料
            System.out.println("文件名：" + file.getAbsolutePath());
            System.out.println("檔案長度：" + file.length());
            
            if(TYPE_WIRTE_LOG_EDIT == writetype){
            	text = "Info: "+new Date()+"\r\n"+
		        		"-ID: "+schedule.getID()+"\r\n"+
		        		"-Name: "+schedule.getName()+"\r\n"+
		        		"-Program URL: "+schedule.getProgram()+"\r\n"+
		        		"-Start: "+schedule.getStartString()+"\r\n"+
		        		"-End: "+schedule.getEndString()+"\r\n"+
		        		"-Interval: "+schedule.getInterval()+"\r\n"+
		        		"-Weekday: "+schedule.getWeekday()+"\r\n"+
		        		"-MonthDate: "+schedule.getDate()+"\r\n";
            }else{
            	text = "Execution Time: "+schedule.getLastString()+"\r\n";
            }
            
            if(!writeText(text, file, "utf8", true)){
            	return false;
            }
            
        } else {//檔案不存在, 就建立新檔
            file.getParentFile().mkdirs();
            try{
                file.createNewFile();
                
                text = "Info: "+new Date()+"\r\n"+
                		"-ID: "+schedule.getID()+"\r\n"+
                		"-Name: "+schedule.getName()+"\r\n"+
                		"-Program URL: "+schedule.getProgram()+"\r\n"+
                		"-Status: "+schedule.getStatus()+"\r\n"+
                		"-Start: "+schedule.getStartString()+"\r\n"+
                		"-End: "+schedule.getEndString()+"\r\n"+
                		"-Interval: "+schedule.getInterval()+"\r\n"+
                		"-Weekday: "+schedule.getWeekday()+"\r\n"+
                		"-MonthDate: "+schedule.getDate()+"\r\n"+
                		"Execution Time: "+schedule.getLastString()+"\r\n";
                if(!writeText(text, file, "utf8", true)){
                	return false;
                }
            } catch(IOException e){
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
    
    public static boolean writeText(String text, File file, String format, boolean append){
    	BufferedWriter bufWriter;
		try {
			bufWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,append),format));
			bufWriter.write(text);
            bufWriter.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(file + "寫檔發生錯誤");
			return false;
		}
        return true;
    }
    
    //servlet destroy
    public void destroy(){  
        super.destroy();   
    }  
  
    //servlet handle get
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{  
          
    }  
  
    //servlet handle post 
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{  
  
    }
}
