package org.iii.esti;

import java.io.IOException;
import java.util.Timer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TimeServlet extends HttpServlet{     
    
	private static final long serialVersionUID = 1L;  
      
    public TimeServlet(){  
        super();  
    }  
      
    public void init() throws ServletException{  
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
        }  
    }  
  
    //具體執行的任務  
    class MyTask extends java.util.TimerTask{  
        public void run(){  
            System.out.println("____ok____");  
        }  
    }
    
    class MyTask2 extends java.util.TimerTask{  
        public void run(){  
            System.out.println("ok______ok");  
        }  
    }
      
    public void destroy(){  
        super.destroy();   
    }  
  
      
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{  
          
    }  
  
      
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{  
  
    }  
}
