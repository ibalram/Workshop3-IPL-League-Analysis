package com.workshop.leagueanalyser.service;

public class LeagueAnalyserException extends Exception {
	enum ExceptionType {
		INCORRECT_CSV, INCORRECT_DELIMITER, INCORRECT_HEADER, RUNTIME_EXCEPTION;
	}

	ExceptionType type;

	public LeagueAnalyserException(ExceptionType type, String message) {
		super(message);
		this.type = type;
	}
}
