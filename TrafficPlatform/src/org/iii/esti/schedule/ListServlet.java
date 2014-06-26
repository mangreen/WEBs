package org.iii.esti.schedule;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TreeMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;


public class ListServlet  extends HttpServlet{
	private String jsonData="";
	private ScheduleDAO sDAO = new ScheduleDAO();
    public ArrayList<Schedule> list;
    public TreeMap<String, Timer> map;
	
	public void init(ServletConfig conf)throws ServletException{
		super.init(conf);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		//System.out.println(req.getParameter("query")+"  dddddddddddddddddd  "+req.getParameter("name"));  
		res.setContentType("text/html;charset=UTF-8");  
        res.setHeader("Cache-Control","no-cache");
        
        String query = req.getParameter("query");
        String result="";
        
        if(query.equals("getlog")){
        	System.out.println(req.getParameter("id"));
        	String filename = Integer.parseInt(req.getParameter("id"))+".txt";
		    String directory = ScheduleServlet.logdir;
			File file = new File(directory, filename);
			String bur="";
			BufferedReader bufReader = null;
			 
			if(file.exists()){//檔案存在, 在就印出檔案資料
				 
				try {
					bufReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
					
					while ((bur=bufReader.readLine())!=null){
						result += bur+"<br/>";
						
					}				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				result = "This log file is not exist !!!";
			}
			
			System.out.println(result);
			res.getWriter().print(result);
	        res.getWriter().close();
	        bufReader.close();
        }else{
	        if(query.equals("selectAll")){
	        	list = sDAO.selectAll();
	            
	            JSONArray array=new JSONArray();
	            
	            //jsonData = "[";
	            for(int i=0; i<list.size(); i++){
	            	jsonData += "{id:"+list.get(i).getID()+",name:'"+list.get(i).getName()+"'},";
	            	array.add(new ListJSON(list.get(i).getID(), 
	            			               list.get(i).getName(), 
	            			               list.get(i).getProgram(), 
	            			               list.get(i).getStatus(), 
	            			               list.get(i).getStartString(), 
	            			               list.get(i).getEndString(), 
	            			               list.get(i).getLastString(), 
	            			               list.get(i).getNextString(), 
	            			               list.get(i).getInterval(), 
	            			               list.get(i).getWeekday(), 
	            			               list.get(i).getDate()));
	            	  
	            }
	            //System.out.println(array.toString());
	            result = array.toString();
	        }else if(query.equals("select")){
	        	list = sDAO.queryList(req.getParameter("name"));
	            
	            JSONArray array=new JSONArray();
	            
	            //jsonData = "[";
	            for(int i=0; i<list.size(); i++){
	            	jsonData += "{id:"+list.get(i).getID()+",name:'"+list.get(i).getName()+"'},";
	            	array.add(new ListJSON(list.get(i).getID(), 
	            			               list.get(i).getName(), 
	            			               list.get(i).getProgram(), 
	            			               list.get(i).getStatus(), 
	            			               list.get(i).getStartString(), 
	            			               list.get(i).getEndString(), 
	            			               list.get(i).getLastString(), 
	            			               list.get(i).getNextString(), 
	            			               list.get(i).getInterval(), 
	            			               list.get(i).getWeekday(), 
	            			               list.get(i).getDate()));
	            	  
	            }
	            System.out.println(array.toString());
	            result = array.toString();
	        }else if(query.equals("update")){
	        	result = String.valueOf(sDAO.updateList(Integer.parseInt(req.getParameter("id")), 
							        					req.getParameter("name"), 
							        					req.getParameter("program"), 
							        					new Boolean(req.getParameter("status")), 
							        					req.getParameter("start"), 
							        					req.getParameter("end"), 
							        					req.getParameter("interval"), 
							        					Integer.parseInt(req.getParameter("weekday")), 
							        					Integer.parseInt(req.getParameter("date")
			        					)));
	        	if(result.equals("true")){
	        		list = sDAO.queryList(req.getParameter("name"));
					Schedule newSchedule = new Schedule(list.get(0).getID(),
														list.get(0).getName(),
														list.get(0).getProgram(),
														list.get(0).getStatus(),
														list.get(0).getStartString(),
														list.get(0).getEndString(),
														list.get(0).getLastString(),
														list.get(0).getNextString(),
														list.get(0).getInterval(),
														list.get(0).getWeekday(),
														list.get(0).getDate()
											);
					
	        		ScheduleServlet.modifyTimer(newSchedule);
	        	}
	        }else if(query.equals("delete")){
	        	
	        	if(true == ScheduleServlet.dropTimer(Integer.parseInt(req.getParameter("id")))){
	        		result = String.valueOf(sDAO.deleteList(Integer.parseInt(req.getParameter("id"))));
	        	}
	        	
			}else if(query.equals("add")){
				result = String.valueOf(sDAO.addList(req.getParameter("name"), 
													 req.getParameter("program"), 
													 new Boolean(req.getParameter("status")), 
													 req.getParameter("start"), 
													 req.getParameter("end"), 
													 req.getParameter("interval"), 
													 Integer.parseInt(req.getParameter("weekday")), 
													 Integer.parseInt(req.getParameter("date")
										)));
				
				if(result.equals("true")){
					if(new Boolean(req.getParameter("status"))){
						list = sDAO.queryList(req.getParameter("name"));
						Schedule newSchedule = new Schedule(list.get(0).getID(),
															list.get(0).getName(),
															list.get(0).getProgram(),
															list.get(0).getStatus(),
															list.get(0).getStartString(),
															list.get(0).getEndString(),
															list.get(0).getLastString(),
															list.get(0).getNextString(),
															list.get(0).getInterval(),
															list.get(0).getWeekday(),
															list.get(0).getDate()
												);
						
						ScheduleServlet.scheduleTimer(newSchedule);
					}
				}
			}
	        
	        
	        res.getWriter().print(result);
	        res.getWriter().close();
		}
	}
}
