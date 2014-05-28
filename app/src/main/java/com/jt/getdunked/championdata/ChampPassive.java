
package com.jt.getdunked.championdata;


public class ChampPassive{
   	private Number id;
   	private String key;
   	private String name;
   	private Passive passive;
   	private String title;

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
 	public Passive getPassive(){
		return this.passive;
	}
	public void setPassive(Passive passive){
		this.passive = passive;
	}
 	public String getTitle(){
		return this.title;
	}
	public void setTitle(String title){
		this.title = title;
	}
}
