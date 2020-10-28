package com.workshop.leagueanalyser.model;

import com.opencsv.bean.CsvBindByName;

public class Bowler {
	@CsvBindByName(column = "POS")
	public int position;

	@CsvBindByName(column = "PLAYER")
	public String player;

	@CsvBindByName(column = "MAT")
	public int noOfMatches;

	@CsvBindByName(column = "Inns")
	public int innings;

	@CsvBindByName(column = "Ov")
	public double overs;

	@CsvBindByName(column = "Runs")
	public int runs;

	@CsvBindByName(column = "Wkts")
	public int wickets;

	@CsvBindByName(column = "BBI")
	public int bbi;

	@CsvBindByName(column = "Avg")
	public String average;

	@CsvBindByName(column = "Econ")
	public double economy;

	@CsvBindByName(column = "SR")
	public String strikeRate;

	@CsvBindByName(column = "4w")
	public int fourWickets;

	@CsvBindByName(column = "5w")
	public int fiveWickets;

	@Override
	public String toString() {
		return "Bowler [position=" + position + ", player=" + player + ", noOfMatches=" + noOfMatches + ", innings="
				+ innings + ", overs=" + overs + ", runs=" + runs + ", wickets=" + wickets + ", bbi=" + bbi
				+ ", average=" + average + ", economy=" + economy + ", strikeRate=" + strikeRate + ", fourWickets="
				+ fourWickets + ", fiveWickets=" + fiveWickets + "]";
	}

	public double getAverage() {
		if (average.equals("-"))
			return 0.0;
		return Double.parseDouble(average);
	}

	public double getStrikeRate() {
		if (strikeRate.equals("-"))
			return 0.0;
		return Double.parseDouble(strikeRate);
	}

	public double getEconomy() {
		return economy;
	}

}
