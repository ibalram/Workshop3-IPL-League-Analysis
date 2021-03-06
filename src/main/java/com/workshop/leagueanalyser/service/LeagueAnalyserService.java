package com.workshop.leagueanalyser.service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.cg.opencsvbuilder.CSVBuilderException;
import com.cg.opencsvbuilder.CSVBuilderFactory;
import com.cg.opencsvbuilder.ICSVBuilder;
import com.google.gson.Gson;
import com.workshop.leagueanalyser.model.*;
import com.workshop.leagueanalyser.service.LeagueAnalyserException.ExceptionType;

public class LeagueAnalyserService {
	List<Batsman> batsmanList = null;
	List<Bowler> bowlerList = null;
	List<Allrounder> allrounderList = null;

	public int loadBattingCSV(String filePath) throws LeagueAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
			ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
			batsmanList = icsvBuilder.getCSVFileList(reader, Batsman.class);
			return batsmanList.size();
		} catch (IOException e) {
			throw new LeagueAnalyserException(LeagueAnalyserException.ExceptionType.INCORRECT_CSV,
					"Incorrect csv file");
		} catch (CSVBuilderException e) {
			throw new LeagueAnalyserException(LeagueAnalyserException.ExceptionType.INCORRECT_CSV, e.getMessage());
		}
	}

	public int loadBowlingCSV(String filePath) throws LeagueAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
			ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
			bowlerList = icsvBuilder.getCSVFileList(reader, Bowler.class);
			return bowlerList.size();
		} catch (IOException e) {
			throw new LeagueAnalyserException(LeagueAnalyserException.ExceptionType.INCORRECT_CSV,
					"Incorrect csv file");
		} catch (CSVBuilderException e) {
			throw new LeagueAnalyserException(LeagueAnalyserException.ExceptionType.INCORRECT_CSV, e.getMessage());
		}
	}

	public void loadAllroundersData() {
		allrounderList = new ArrayList<Allrounder>();
		batsmanList.stream().forEach(batsman -> {
			bowlerList.stream().forEach(bowler -> {
				if (bowler.player.equals(batsman.player))
					allrounderList.add(new Allrounder(batsman.player, batsman.getRuns(), batsman.getAverage(),
							bowler.getWickets(), bowler.getAverage()));
			});
		});
	}

	public String getAverageWiseSortedBattingData() throws LeagueAnalyserException {
		if (batsmanList.size() == 0) {
			throw new LeagueAnalyserException(LeagueAnalyserException.ExceptionType.INCORRECT_CSV, "No csv data");
		}
		Comparator<Batsman> comparator = Comparator.comparing(data -> data.getAverage());
		this.sortDesc(comparator, batsmanList);
		String json = new Gson().toJson(batsmanList);
		return json;
	}

	public String getStrikeRateWiseSortedBattingData() throws LeagueAnalyserException {
		if (batsmanList.size() == 0) {
			throw new LeagueAnalyserException(LeagueAnalyserException.ExceptionType.INCORRECT_CSV, "No csv data");
		}
		Comparator<Batsman> comparator = Comparator.comparing(data -> data.getStrikeRate());
		this.sortDesc(comparator, batsmanList);
		String json = new Gson().toJson(batsmanList);
		return json;
	}

	public String getBoundariesWiseSortedBattingData() throws LeagueAnalyserException {
		if (batsmanList.size() == 0) {
			throw new LeagueAnalyserException(LeagueAnalyserException.ExceptionType.INCORRECT_CSV, "No csv data");
		}
		Comparator<Batsman> comparator = Comparator.comparing(data -> data.getBoundaries());
		this.sortDesc(comparator, batsmanList);
		String json = new Gson().toJson(batsmanList);
		return json;
	}

	public String getBoundariesThenStrikeRateWiseSortedBattingData() throws LeagueAnalyserException {
		if (batsmanList.size() == 0) {
			throw new LeagueAnalyserException(LeagueAnalyserException.ExceptionType.INCORRECT_CSV, "No csv data");
		}
		Comparator<Batsman> comparator = Comparator.comparing(Batsman::getBoundaries)
				.thenComparing(Batsman::getStrikeRate);
		this.sortDesc(comparator, batsmanList);
		String json = new Gson().toJson(batsmanList);
		return json;
	}

	public String getStrikeRateThenAverageWiseSortedBattingData() throws LeagueAnalyserException {
		if (batsmanList.size() == 0) {
			throw new LeagueAnalyserException(LeagueAnalyserException.ExceptionType.INCORRECT_CSV, "No csv data");
		}
		Comparator<Batsman> comparator = Comparator.comparing(Batsman::getStrikeRate)
				.thenComparing(Batsman::getAverage);
		this.sortDesc(comparator, batsmanList);
		String json = new Gson().toJson(batsmanList);
		return json;
	}

	public String getRunsThenAverageWiseSortedBattingData() throws LeagueAnalyserException {
		if (batsmanList.size() == 0) {
			throw new LeagueAnalyserException(LeagueAnalyserException.ExceptionType.INCORRECT_CSV, "No csv data");
		}
		Comparator<Batsman> comparator = Comparator.comparing(Batsman::getRuns).thenComparing(Batsman::getAverage);
		this.sortDesc(comparator, batsmanList);
		String json = new Gson().toJson(batsmanList);
		return json;
	}

	public String getAverageWiseSortedBowlingData() throws LeagueAnalyserException {
		if (bowlerList.size() == 0) {
			throw new LeagueAnalyserException(LeagueAnalyserException.ExceptionType.INCORRECT_CSV, "No csv data");
		}
		Comparator<Bowler> comparator = Comparator.comparing(Bowler::getAverage);
		this.sortDesc(comparator, bowlerList);
		String json = new Gson().toJson(bowlerList);
		return json;
	}

	public String getStrikeRateWiseSortedBowlingData() throws LeagueAnalyserException {
		if (bowlerList.size() == 0) {
			throw new LeagueAnalyserException(LeagueAnalyserException.ExceptionType.INCORRECT_CSV, "No csv data");
		}
		Comparator<Bowler> comparator = Comparator.comparing(Bowler::getStrikeRate);
		this.sortDesc(comparator, bowlerList);
		String json = new Gson().toJson(bowlerList);
		return json;
	}

	public String getEconomyWiseSortedBowlingData() throws LeagueAnalyserException {
		if (bowlerList.size() == 0) {
			throw new LeagueAnalyserException(LeagueAnalyserException.ExceptionType.INCORRECT_CSV, "No csv data");
		}
		Comparator<Bowler> comparator = Comparator.comparing(Bowler::getEconomy);
		this.sortDesc(comparator, bowlerList);
		String json = new Gson().toJson(bowlerList);
		return json;
	}

	public String get5W4WThenStrikeRateWiseSortedBowlingData() throws LeagueAnalyserException {
		if (bowlerList.size() == 0) {
			throw new LeagueAnalyserException(LeagueAnalyserException.ExceptionType.INCORRECT_CSV, "No csv data");
		}
		Comparator<Bowler> comparator = Comparator.comparing(Bowler::getFiveAndFourWickets)
				.thenComparing(Bowler::getStrikeRate);
		this.sortDesc(comparator, bowlerList);
		String json = new Gson().toJson(bowlerList);
		return json;
	}

	public String getStrikeRateThenAverageWiseSortedBowlingData() throws LeagueAnalyserException {
		if (bowlerList.size() == 0) {
			throw new LeagueAnalyserException(LeagueAnalyserException.ExceptionType.INCORRECT_CSV, "No csv data");
		}
		Comparator<Bowler> comparator = Comparator.comparing(Bowler::getStrikeRate).thenComparing(Bowler::getAverage);
		this.sortDesc(comparator, bowlerList);
		String json = new Gson().toJson(bowlerList);
		return json;
	}

	public String getWicketsThenAverageWiseSortedBowlingData() throws LeagueAnalyserException {
		if (bowlerList.size() == 0) {
			throw new LeagueAnalyserException(LeagueAnalyserException.ExceptionType.INCORRECT_CSV, "No csv data");
		}
		Comparator<Bowler> comparator = Comparator.comparing(Bowler::getWickets).thenComparing(Bowler::getAverage);
		this.sortDesc(comparator, bowlerList);
		String json = new Gson().toJson(bowlerList);
		return json;
	}

	public String getBattingThenBowlingAverageWiseSortedAllrounderData() throws LeagueAnalyserException {
		if (allrounderList.size() == 0) {
			throw new LeagueAnalyserException(LeagueAnalyserException.ExceptionType.INCORRECT_CSV, "No allrounders");
		}
		Comparator<Allrounder> comparator = Comparator.comparing(Allrounder::getBattingAverage)
				.thenComparing(Allrounder::getBowlingAverage);
		this.sortDesc(comparator, allrounderList);
		String json = new Gson().toJson(allrounderList);
		return json;
	}

	public String getRunsThenWicketsWiseSortedAllrounderData() throws LeagueAnalyserException {
		if (allrounderList.size() == 0) {
			throw new LeagueAnalyserException(LeagueAnalyserException.ExceptionType.INCORRECT_CSV, "No allrounders");
		}
		Comparator<Allrounder> comparator = Comparator.comparing(Allrounder::getRuns)
				.thenComparing(Allrounder::getWickets);
		this.sortDesc(comparator, allrounderList);
		String json = new Gson().toJson(allrounderList);
		return json;
	}

	public String getHundredsThenAverageWiseSortedBattingData() throws LeagueAnalyserException {
		if (batsmanList.size() == 0) {
			throw new LeagueAnalyserException(LeagueAnalyserException.ExceptionType.INCORRECT_CSV, "No csv data");
		}
		Comparator<Batsman> comparator = Comparator.comparing(Batsman::getCenturies).thenComparing(Batsman::getAverage);
		this.sortDesc(comparator, batsmanList);
		String json = new Gson().toJson(batsmanList);
		return json;
	}

	public String getAverageWiseSortedBattingDataWithZeroHundredsFifties() throws LeagueAnalyserException {
		if (batsmanList.size() == 0) {
			throw new LeagueAnalyserException(LeagueAnalyserException.ExceptionType.INCORRECT_CSV, "No csv data");
		}
		Comparator<Batsman> comparator = Comparator.comparing(Batsman::getAverage);
		List<Batsman> list = batsmanList.stream()
				.filter(batsman -> batsman.getCenturies() == 0 && batsman.getHalfCenturies() == 0)
				.collect(Collectors.toList());
		this.sortDesc(comparator, list);
		String json = new Gson().toJson(list);
		return json;
	}

	private <E> void sortDesc(Comparator<E> comparator, List<E> list) {
		for (int i = 0; i < list.size() - 1; ++i) {
			for (int j = 0; j < list.size() - i - 1; ++j) {
				E obj1 = list.get(j);
				E obj2 = list.get(j + 1);
				if (comparator.compare(obj1, obj2) < 0) {
					list.set(j, obj2);
					list.set(j + 1, obj1);
				}
			}
		}
	}

}
