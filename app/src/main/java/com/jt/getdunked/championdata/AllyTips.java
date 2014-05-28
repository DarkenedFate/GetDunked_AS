
package com.jt.getdunked.championdata;

import java.util.List;

public class AllyTips{
   	private List<String> allytips;
   	private Number id;
   	private String key;
   	private String name;
   	private String title;
   	
   	public AllyTips() {
   		
   	}
   	
   	public AllyTips(List<String> allytips, Number id, String key, String name, String title) {
   		this.allytips = allytips;
   		this.id = id;
   		this.key = key;
   		this.name = name;
   		this.title = title;
   	}

 	public List<String> getAllytips(){
		return this.allytips;
	}
	public void setAllytips(List<String> allytips){
		this.allytips = allytips;
	}
 	public Number getId(){
		return this.id;
	}
	public void setId(Number id){
		this.id = id;
	}
 	public String getKey(){
		return this.key;
	}
	public void setKey(String key){
		this.key = key;
	}
 	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
 	public String getTitle(){
		return this.title;
	}
	public void setTitle(String title){
		this.title = title;
	}
}
