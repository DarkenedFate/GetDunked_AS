
package com.jt.getdunked.summonerdata;

import java.util.List;

public class Games{
   	private Number championId;
   	private Number createDate;
   	private List<FellowPlayers> fellowPlayers;
   	private Number gameId;
   	private String gameMode;
   	private String gameType;
   	private boolean invalid;
   	private Number ipEarned;
   	private Number level;
   	private Number mapId;
   	private Number spell1;
   	private Number spell2;
   	private Stats stats;
   	private String subType;
   	private Number teamId;

 	public Number getChampionId(){
		return this.championId;
	}
	public void setChampionId(Number championId){
		this.championId = championId;
	}
 	public Number getCreateDate(){
		return this.createDate;
	}
	public void setCreateDate(Number createDate){
		this.createDate = createDate;
	}
 	public List<FellowPlayers> getFellowPlayers(){
		return this.fellowPlayers;
	}
	public void setFellowPlayers(List<FellowPlayers> fellowPlayers){
		this.fellowPlayers = fellowPlayers;
	}
 	public Number getGameId(){
		return this.gameId;
	}
	public void setGameId(Number gameId){
		this.gameId = gameId;
	}
 	public String getGameMode(){
		return this.gameMode;
	}
	public void setGameMode(String gameMode){
		this.gameMode = gameMode;
	}
 	public String getGameType(){
		return this.gameType;
	}
	public void setGameType(String gameType){
		this.gameType = gameType;
	}
 	public boolean getInvalid(){
		return this.invalid;
	}
	public void setInvalid(boolean invalid){
		this.invalid = invalid;
	}
 	public Number getIpEarned(){
		return this.ipEarned;
	}
	public void setIpEarned(Number ipEarned){
		this.ipEarned = ipEarned;
	}
 	public Number getLevel(){
		return this.level;
	}
	public void setLevel(Number level){
		this.level = level;
	}
 	public Number getMapId(){
		return this.mapId;
	}
	public void setMapId(Number mapId){
		this.mapId = mapId;
	}
 	public Number getSpell1(){
		return this.spell1;
	}
	public void setSpell1(Number spell1){
		this.spell1 = spell1;
	}
 	public Number getSpell2(){
		return this.spell2;
	}
	public void setSpell2(Number spell2){
		this.spell2 = spell2;
	}
 	public Stats getStats(){
		return this.stats;
	}
	public void setStats(Stats stats){
		this.stats = stats;
	}
 	public String getSubType(){
		return this.subType;
	}
	public void setSubType(String subType){
		this.subType = subType;
	}
 	public Number getTeamId(){
		return this.teamId;
	}
	public void setTeamId(Number teamId){
		this.teamId = teamId;
	}
}
