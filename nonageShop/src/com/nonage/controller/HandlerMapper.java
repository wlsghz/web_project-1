package com.nonage.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import com.nonage.action.Action;

public class HandlerMapper {
	private static HandlerMapper instance=new HandlerMapper();
	private HandlerMapper(){}
	public static HandlerMapper getInstance(){
		return instance;
	}
	
	private static Map<String,Action> commandMap =
								new HashMap<String,Action>();
	
	static{
		String path="com/nonage/properties/Command";
		ResourceBundle rbHome=ResourceBundle.getBundle(path);
		
		Set<String> actionSetHome=rbHome.keySet();
		
		Iterator<String> it=actionSetHome.iterator();
		
		while(it.hasNext()){
			String command = it.next();			
			String className=rbHome.getString(command);
			
			try{			
			Class actionClass = Class.forName(className);
			Action commandAction = (Action)actionClass.newInstance();
			commandMap.put(command, commandAction);
			}catch(ClassNotFoundException e){
				e.printStackTrace();
				continue;
			}catch(InstantiationException | IllegalAccessException e){
				e.printStackTrace();
			}
			
		}		
	}
	
	public Map<String,Action> getMap(){
		return commandMap;
	}
}









