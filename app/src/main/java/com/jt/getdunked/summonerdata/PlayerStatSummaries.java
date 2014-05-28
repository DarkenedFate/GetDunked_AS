
package com.jt.getdunked.summonerdata;


public class PlayerStatSummaries{
   	private AggregatedStats aggregatedStats;
   	private Number modifyDate;
   	private String playerStatSummaryType;
   	private Number wins;
   	private Number losses;

 	public AggregatedStats getAggregatedStats(){
		return this.aggregatedStats;
	}
	public void setAggregatedStats(AggregatedStats aggregatedStats){
		this.aggregatedStats = aggregatedStats;
	}
 	public Number getModifyDate(){
		return this.modifyDate;
	}
	public void setModifyDate(Number modifyDate){
		this.modifyDate = modifyDate;
	}
 	public String getPlayerStatSummaryType(){
		return this.playerStatSummaryType;
	}
	public void setPlayerStatSummaryType(String playerStatSummaryType){
		this.playerStatSummaryType = playerStatSummaryType;
	}
 	public Number getWins(){
		return this.wins;
	}
	public void setWins(Number wins){
		this.wins = wins;
	}
	public Number getLosses() {
		return losses;
	}
	public void setLosses(Number losses) {
		this.losses = losses;
	}
}
