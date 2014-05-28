
package com.jt.getdunked.summonerdata;


public class AggregatedStats{
   	private Number totalAssists;
   	private Number totalChampionKills;
   	private Number totalTurretsKilled;

 	public Number getTotalAssists(){
		return this.totalAssists;
	}
	public void setTotalAssists(Number totalAssists){
		this.totalAssists = totalAssists;
	}
 	public Number getTotalChampionKills(){
		return this.totalChampionKills;
	}
	public void setTotalChampionKills(Number totalChampionKills){
		this.totalChampionKills = totalChampionKills;
	}
 	public Number getTotalTurretsKilled(){
		return this.totalTurretsKilled;
	}
	public void setTotalTurretsKilled(Number totalTurretsKilled){
		this.totalTurretsKilled = totalTurretsKilled;
	}
}
