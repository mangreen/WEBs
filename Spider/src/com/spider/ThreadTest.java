package com.spider;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ThreadTest {
	public static class Command implements Runnable {  
		private int id;  
		   
		public Command(int id) {  
			this.id = id;  
		}  
		   
		public void run() {  
			System.out.println(id + " Begin " + Thread.currentThread().getName());  
			try {  
				Thread.sleep(1000);  
			} catch (InterruptedException ex) {  
		        ex.printStackTrace();  
		    }  
		    System.out.println(id + " End   " + Thread.currentThread().getName());
		    return;
		}  
	}  
	   
	public static void main(String[] args) {  
	     Executor tp = Executors.newFixedThreadPool(3);  
	     tp.execute(new Command(1));  
	     tp.execute(new Command(2));  
	     tp.execute(new Command(3));  
	     tp.execute(new Command(4));  
	     tp.execute(new Command(5));
	     
	}    
}
