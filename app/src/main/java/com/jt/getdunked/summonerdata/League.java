
package com.jt.getdunked.summonerdata;

import java.util.List;

public class League{
   	private List<Entries> entries;
   	private String name;
   	private String participantId;
   	private String queue;
   	private String tier;

 	public List<Entries> getEntries(){
		return this.entries;
	}
	public void setEntries(List<Entries> entries){
		this.entries = entries;
	}
 	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
 	public String getParticipantId(){
		return this.participantId;
	}
	public void setParticipantId(String participantId){
		this.participantId = participantId;
	}
 	public String getQueue(){
		return this.queue;
	}
	public void setQueue(String queue){
		this.queue = queue;
	}
 	public String getTier(){
		return this.tier;
	}
	public void setTier(String tier){
		this.tier = tier;
	}
}
