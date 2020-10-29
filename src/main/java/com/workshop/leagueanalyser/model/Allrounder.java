package com.workshop.leagueanalyser.model;

import com.opencsv.bean.CsvBindByName;

public class Allrounder {
	public String player;
	public int runs;
	public double battingAverage;
	public int wickets;
	public double bowlingAverage;

	public Allrounder(String player, int runs, double battingAverage, int wickets, double bowlingAverage) {
		super();
		this.player = player;
		this.runs = runs;
		this.battingAverage = battingAverage;
		this.wickets = wickets;
		this.bowlingAverage = bowlingAverage;
	}

	public int getRuns() {
		return runs;
	}

	public double getBattingAverage() {
		return battingAverage;
	}

	public int getWickets() {
		return wickets;
	}

	public double getBowlingAverage() {
		return bowlingAverage;
	}
}
