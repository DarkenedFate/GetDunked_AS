package com.jt.getdunked.summonerdata;

import java.util.List;

public class PlayerStats {
	private List<PlayerStatSummaries> playerStatSummaries;
	private Number summonerId;

	public List<PlayerStatSummaries> getPlayerStatSummaries() {
		return this.playerStatSummaries;
	}

	public void setPlayerStatSummaries(
			List<PlayerStatSummaries> playerStatSummaries) {
		this.playerStatSummaries = playerStatSummaries;
	}

	public Number getSummonerId() {
		return this.summonerId;
	}

	public void setSummonerId(Number summonerId) {
		this.summonerId = summonerId;
	}
}
