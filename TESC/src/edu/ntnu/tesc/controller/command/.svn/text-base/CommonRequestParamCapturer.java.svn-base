package edu.ntnu.tesc.controller.command;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

public class CommonRequestParamCapturer {

	public static Object captureParam(Object obj,String[] fields,HttpServletRequest request) 
		throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException
		, InvocationTargetException, Exception{
		
		for(String field:fields){
			String value = request.getParameter(field);
			String upperfield = field.substring(0,1).toUpperCase()+field.substring(1);
			String set_method_name = String.format("set%s", upperfield);
			String get_method_name = String.format("get%s", upperfield);
			Method method = obj.getClass().getDeclaredMethod(get_method_name,null);
			Class returnClass = method.getReturnType();
			
			method = obj.getClass().getDeclaredMethod(set_method_name, returnClass);
			if(returnClass.equals(int.class)){
				int int_value = Integer.parseInt(value);
				method.invoke(obj, int_value);
			}
			else if(returnClass.equals(double.class)){
				double double_value = Double.parseDouble(value);
				method.invoke(obj,double_value);
			}
			else if(returnClass.equals(String.class)){
				method.invoke(obj, value);
			}
		}
		
		
		return obj;
	}
	
}
