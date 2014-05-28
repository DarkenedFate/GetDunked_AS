
package com.jt.getdunked.championdata;

import java.util.List;

public class ChampSkins {
   	private Number id;
   	private String key;
   	private String name;
   	private List<Skins> skins;
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
 	public List<Skins> getSkins(){
		return this.skins;
	}
	public void setSkins(List<Skins> skins){
		this.skins = skins;
	}
 	public String getTitle(){
		return this.title;
	}
	public void setTitle(String title){
		this.title = title;
	}
}
