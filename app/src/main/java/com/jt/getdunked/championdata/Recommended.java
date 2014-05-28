
package com.jt.getdunked.championdata;

import java.io.Serializable;
import java.util.List;

public class Recommended implements Serializable {

	private static final long serialVersionUID = 8555106046550418111L;
	private List<Blocks> blocks;
   	private String champion;
   	private String map;
   	private String mode;
   	private boolean priority;
   	private String title;
   	private String type;

 	public List<Blocks> getBlocks(){
		return this.blocks;
	}
	public void setBlocks(List<Blocks> blocks){
		this.blocks = blocks;
	}
 	public String getChampion(){
		return this.champion;
	}
	public void setChampion(String champion){
		this.champion = champion;
	}
 	public String getMap(){
		return this.map;
	}
	public void setMap(String map){
		this.map = map;
	}
 	public String getMode(){
		return this.mode;
	}
	public void setMode(String mode){
		this.mode = mode;
	}
 	public boolean getPriority(){
		return this.priority;
	}
	public void setPriority(boolean priority){
		this.priority = priority;
	}
 	public String getTitle(){
		return this.title;
	}
	public void setTitle(String title){
		this.title = title;
	}
 	public String getType(){
		return this.type;
	}
	public void setType(String type){
		this.type = type;
	}
}
