package com.train.ticket.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Train {

	boolean[][] seats = new boolean[2][6];
	List<Ticket> tcktList = new ArrayList<>();
	List<Character> stations = Arrays.asList('A','B','C','D','E','F');
	int ticketNo = 0;
	
	public boolean[][] getSeats() {
		return seats;
	}
	public void setSeats(boolean[][] seats) {
		this.seats = seats;
	}
	public List<Ticket> getTcktList() {
		return tcktList;
	}
	public void setTcktList(List<Ticket> tcktList) {
		this.tcktList = tcktList;
	}
	public List<Character> getStations() {
		return stations;
	}
	public void setStations(List<Character> stations) {
		this.stations = stations;
	}
	public int generateTicketNo() {
		return ++ticketNo;
	}
	
}
