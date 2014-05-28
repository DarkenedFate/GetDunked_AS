
package com.jt.getdunked.championdata;

import java.util.List;

public class Blocks{
   	private List<Item> items;
   	private String type;

 	public List<Item> getItems(){
		return this.items;
	}
	public void setItems(List<Item> items){
		this.items = items;
	}
 	public String getType(){
		return this.type;
	}
	public void setType(String type){
		this.type = type;
	}
}
