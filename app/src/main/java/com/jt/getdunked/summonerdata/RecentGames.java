
package com.jt.getdunked.summonerdata;

import java.util.List;

public class RecentGames{
   	private List<Games> games;
   	private Number summonerId;

 	public List<Games> getGames(){
		return this.games;
	}
	public void setGames(List<Games> games){
		this.games = games;
	}
 	public Number getSummonerId(){
		return this.summonerId;
	}
	public void setSummonerId(Number summonerId){
		this.summonerId = summonerId;
	}
}
