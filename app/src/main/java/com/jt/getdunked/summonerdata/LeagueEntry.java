
package com.jt.getdunked.summonerdata;


public class LeagueEntry{
   	private boolean isFreshBlood;
   	private boolean isHotStreak;
   	private boolean isInactive;
   	private boolean isVeteran;
   	private Number lastPlayed;
   	private String leagueName;
   	private Number leaguePoints;
   	private String playerOrTeamId;
   	private String playerOrTeamName;
   	private String queueType;
   	private String rank;
   	private String tier;
   	private Number wins;

 	public boolean getIsFreshBlood(){
		return this.isFreshBlood;
	}
	public void setIsFreshBlood(boolean isFreshBlood){
		this.isFreshBlood = isFreshBlood;
	}
 	public boolean getIsHotStreak(){
		return this.isHotStreak;
	}
	public void setIsHotStreak(boolean isHotStreak){
		this.isHotStreak = isHotStreak;
	}
 	public boolean getIsInactive(){
		return this.isInactive;
	}
	public void setIsInactive(boolean isInactive){
		this.isInactive = isInactive;
	}
 	public boolean getIsVeteran(){
		return this.isVeteran;
	}
	public void setIsVeteran(boolean isVeteran){
		this.isVeteran = isVeteran;
	}
 	public Number getLastPlayed(){
		return this.lastPlayed;
	}
	public void setLastPlayed(Number lastPlayed){
		this.lastPlayed = lastPlayed;
	}
 	public String getLeagueName(){
		return this.leagueName;
	}
	public void setLeagueName(String leagueName){
		this.leagueName = leagueName;
	}
 	public Number getLeaguePoints(){
		return this.leaguePoints;
	}
	public void setLeaguePoints(Number leaguePoints){
		this.leaguePoints = leaguePoints;
	}
 	public String getPlayerOrTeamId(){
		return this.playerOrTeamId;
	}
	public void setPlayerOrTeamId(String playerOrTeamId){
		this.playerOrTeamId = playerOrTeamId;
	}
 	public String getPlayerOrTeamName(){
		return this.playerOrTeamName;
	}
	public void setPlayerOrTeamName(String playerOrTeamName){
		this.playerOrTeamName = playerOrTeamName;
	}
 	public String getQueueType(){
		return this.queueType;
	}
	public void setQueueType(String queueType){
		this.queueType = queueType;
	}
 	public String getRank(){
		return this.rank;
	}
	public void setRank(String rank){
		this.rank = rank;
	}
 	public String getTier(){
		return this.tier;
	}
	public void setTier(String tier){
		this.tier = tier;
	}
 	public Number getWins(){
		return this.wins;
	}
	public void setWins(Number wins){
		this.wins = wins;
	}
}
