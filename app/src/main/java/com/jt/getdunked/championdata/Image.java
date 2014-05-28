
package com.jt.getdunked.championdata;

import java.io.Serializable;


public class Image implements Serializable {

	private static final long serialVersionUID = -7789156274368824828L;
	private String full;
   	private String group;
   	private Number h;
   	private String sprite;
   	private Number w;
   	private Number x;
   	private Number y;

 	public String getFull(){
		return this.full;
	}
	public void setFull(String full){
		this.full = full;
	}
 	public String getGroup(){
		return this.group;
	}
	public void setGroup(String group){
		this.group = group;
	}
 	public Number getH(){
		return this.h;
	}
	public void setH(Number h){
		this.h = h;
	}
 	public String getSprite(){
		return this.sprite;
	}
	public void setSprite(String sprite){
		this.sprite = sprite;
	}
 	public Number getW(){
		return this.w;
	}
	public void setW(Number w){
		this.w = w;
	}
 	public Number getX(){
		return this.x;
	}
	public void setX(Number x){
		this.x = x;
	}
 	public Number getY(){
		return this.y;
	}
	public void setY(Number y){
		this.y = y;
	}
}
