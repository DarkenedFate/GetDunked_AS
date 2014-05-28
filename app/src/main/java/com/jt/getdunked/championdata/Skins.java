
package com.jt.getdunked.championdata;

import java.io.Serializable;


public class Skins implements Serializable{

	private static final long serialVersionUID = 8017308285711288235L;
	private Number id;
   	private String name;
   	private Number num;

 	public Number getId(){
		return this.id;
	}
	public void setId(Number id){
		this.id = id;
	}
 	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
 	public Number getNum(){
		return this.num;
	}
	public void setNum(Number num){
		this.num = num;
	}
}
