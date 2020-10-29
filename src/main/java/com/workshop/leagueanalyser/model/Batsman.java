package com.workshop.leagueanalyser.model;

import com.opencsv.bean.CsvBindByName;

public class Batsman {
	@CsvBindByName(column = "POS", required = true)
	public String position;

	@CsvBindByName(column = "PLAYER", required = true)
	public String player;

	@CsvBindByName(column = "MAT")
	public int noOfMatches;

	@CsvBindByName(column = "Inns")
	public int innings;

	@CsvBindByName(column = "NO")
	public int notOut;

	@CsvBindByName(column = "Runs")
	public int runs;

	@CsvBindByName(column = "HS")
	public String highestScore;

	@CsvBindByName(column = "Avg")
	public String average;

	@CsvBindByName(column = "BF")
	public int ballFaced;

	@CsvBindByName(column = "SR")
	public double strikeRate;

	@CsvBindByName(column = "100")
	public int centuries;

	@CsvBindByName(column = "50")
	public int halfCenturies;

	@CsvBindByName(column = "4s")
	public int fours;

	@CsvBindByName(column = "6s")
	public int sixes;

	@Override
	public String toString() {
		return "Batsman [position=" + position + ", player=" + player + ", noOfMatches=" + noOfMatches + ", innings="
				+ innings + ", notOut=" + notOut + ", runs=" + runs + ", highestScore=" + highestScore + ", average="
				+ average + ", ballFaced=" + ballFaced + ", strikeRate=" + strikeRate + ", centuries=" + centuries
				+ ", halfCenturies=" + halfCenturies + ", fours=" + fours + ", sixes=" + sixes + "]";
	}

	public double getAverage() {
		if (average.equals("-"))
			return 0.0;
		return Double.parseDouble(average);
	}

	public double getStrikeRate() {
		return strikeRate;
	}

	public int getBoundaries() {
		return sixes + fours;
	}

	public int getRuns() {
		return runs;
	}

	public int getCenturies() {
		return centuries;
	}
}
