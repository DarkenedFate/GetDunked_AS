
package com.jt.getdunked.summonerdata;

public class FellowPlayers{
   	private Integer championId;
   	private Integer summonerId;
   	private Integer teamId;

 	public Integer getChampionId(){
		return this.championId;
	}
	public void setChampionId(Integer championId){
		this.championId = championId;
	}
 	public Integer getSummonerId(){
		return this.summonerId;
	}
	public void setSummonerId(Integer summonerId){
		this.summonerId = summonerId;
	}
 	public Integer getTeamId(){
		return this.teamId;
	}
	public void setTeamId(Integer teamId){
		this.teamId = teamId;
	}
}
