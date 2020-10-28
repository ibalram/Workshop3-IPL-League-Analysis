package com.workshop.leagueanalyser;

import org.junit.Test;

import com.google.gson.Gson;
import com.workshop.leagueanalyser.Executor;
import com.workshop.leagueanalyser.model.Batsman;
import com.workshop.leagueanalyser.service.LeagueAnalyserException;
import com.workshop.leagueanalyser.service.LeagueAnalyserService;

import static org.junit.Assert.*;

import org.junit.Before;

public class LeagueAnalyserTest {
	static String BATTING_DATA_FILE = "./IPL2019FactsheetMostRuns.csv";
	static String BOWLING_DATA_FILE = "./IPL2019FactsheetMostWkts.csv";
	LeagueAnalyserService analyser = null;

	@Before
	public void initialise() {
		analyser = new LeagueAnalyserService();
	}

	@Test
	public void givenBattingCsvFileWhenload_ShouldReturnCount() {
		int count = 0;
		try {
			count = analyser.loadBattingCSV(BATTING_DATA_FILE);
		} catch (LeagueAnalyserException e) {
			e.printStackTrace();
		}
		assertEquals(100, count);
	}

	@Test
	public void givenBowlingCsvFileWhenload_ShouldReturnCount() {
		int count = 0;
		try {
			count = analyser.loadBowlingCSV(BOWLING_DATA_FILE);
		} catch (LeagueAnalyserException e) {
			e.printStackTrace();
		}
		assertEquals(99, count);
	}

	@Test
	public void givenBattingCsvFileWhenSortedByAverage_ShouldReturnSorted() {
		String sorted = null;
		try {
			analyser.loadBattingCSV(BATTING_DATA_FILE);
			sorted = analyser.getAverageWiseSortedBattingData();
		} catch (LeagueAnalyserException e) {
			e.printStackTrace();
		}
		Batsman[] sortedList = new Gson().fromJson(sorted, Batsman[].class);
		assertEquals("MS Dhoni", sortedList[0].player);
	}

	@Test
	public void givenBattingCsvFileWhenSortedByStrikeRate_ShouldReturnSorted() {
		String sorted = null;
		try {
			analyser.loadBattingCSV(BATTING_DATA_FILE);
			sorted = analyser.getStrikeRateWiseSortedBattingData();
		} catch (LeagueAnalyserException e) {
			e.printStackTrace();
		}
		Batsman[] sortedList = new Gson().fromJson(sorted, Batsman[].class);
		assertEquals("Ishant Sharma", sortedList[0].player);
	}

	@Test
	public void givenBattingCsvFileWhenSortedBySixesAndFours_ShouldReturnSorted() {
		String sorted = null;
		try {
			analyser.loadBattingCSV(BATTING_DATA_FILE);
			sorted = analyser.getBoundariesWiseSortedBattingData();
		} catch (LeagueAnalyserException e) {
			e.printStackTrace();
		}
		Batsman[] sortedList = new Gson().fromJson(sorted, Batsman[].class);
		assertEquals("Andre Russell", sortedList[0].player);
	}

	@Test
	public void givenBattingCsvFileWhenSortedBySixesAndFoursThenStrikeRate_ShouldReturnSorted() {
		String sorted = null;
		try {
			analyser.loadBattingCSV(BATTING_DATA_FILE);
			sorted = analyser.getBoundariesThenStrikeRateWiseSortedBattingData();
		} catch (LeagueAnalyserException e) {
			e.printStackTrace();
		}
		// System.out.println(sorted);
		Batsman[] sortedList = new Gson().fromJson(sorted, Batsman[].class);
		assertEquals("Andre Russell", sortedList[0].player);
	}
	
	@Test
	public void givenBattingCsvFileWhenSortedByStrikeRateThenAverage_ShouldReturnSorted() {
		String sorted = null;
		try {
			analyser.loadBattingCSV(BATTING_DATA_FILE);
			sorted = analyser.getStrikeRateThenAverageWiseSortedBattingData();
		} catch (LeagueAnalyserException e) {
			e.printStackTrace();
		}
//		System.out.println(sorted);
		Batsman[] sortedList = new Gson().fromJson(sorted, Batsman[].class);
		assertEquals("Ishant Sharma", sortedList[0].player);
	}
	
	@Test
	public void givenBattingCsvFileWhenSortedByRunsThenAverage_ShouldReturnSorted() {
		String sorted = null;
		try {
			analyser.loadBattingCSV(BATTING_DATA_FILE);
			sorted = analyser.getRunsThenAverageWiseSortedBattingData();
		} catch (LeagueAnalyserException e) {
			e.printStackTrace();
		}
		System.out.println(sorted);
		Batsman[] sortedList = new Gson().fromJson(sorted, Batsman[].class);
		assertEquals("David Warner ", sortedList[0].player);
	}
}
