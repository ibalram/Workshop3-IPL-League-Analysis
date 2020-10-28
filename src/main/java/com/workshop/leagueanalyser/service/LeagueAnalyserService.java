package com.workshop.leagueanalyser.service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

import com.cg.opencsvbuilder.CSVBuilderException;
import com.cg.opencsvbuilder.CSVBuilderFactory;
import com.cg.opencsvbuilder.ICSVBuilder;
import com.google.gson.Gson;
import com.workshop.leagueanalyser.model.*;
import com.workshop.leagueanalyser.service.LeagueAnalyserException.ExceptionType;

public class LeagueAnalyserService {
	List<Batsman> batsmanList = null;
	List<Bowler> bowlerList = null;

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

	public String getAverageWiseSortedBattingData() throws LeagueAnalyserException {
		if (batsmanList.size() == 0) {
			throw new LeagueAnalyserException(LeagueAnalyserException.ExceptionType.INCORRECT_CSV, "No csv data");
		}
		Comparator<Batsman> comparator = Comparator.comparing(data -> data.getAverage());
		this.sortDesc(comparator, batsmanList);
		String json = new Gson().toJson(batsmanList);
		return json;
	}

	private <E> void sortDesc(Comparator<E> censusComparator, List<E> batsmanList) {
		for (int i = 0; i < batsmanList.size() - 1; ++i) {
			for (int j = 0; j < batsmanList.size() - i - 1; ++j) {
				E obj1 = batsmanList.get(j);
				E obj2 = batsmanList.get(j + 1);
				if (censusComparator.compare(obj1, obj2) < 0) {
					batsmanList.set(j, obj2);
					batsmanList.set(j + 1, obj1);
				}
			}
		}
	}

}
