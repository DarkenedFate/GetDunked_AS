
package com.jt.getdunked.championdata;

import java.util.List;

public class EnemyTips{
   	private List<String> enemytips;
   	private Number id;
   	private String key;
   	private String name;
   	private String title;

 	public List<String> getEnemytips(){
		return this.enemytips;
	}
	public void setEnemytips(List<String> enemytips){
		this.enemytips = enemytips;
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
