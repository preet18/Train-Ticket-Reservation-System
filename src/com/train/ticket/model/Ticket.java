package com.train.ticket.model;

public class Ticket {

	private int ticketNo;
	private char src;
	private char dest;
	private int seatNo;
	private String reservationStatus;
	
	public Ticket(int ticketNo) {
		this.ticketNo = ticketNo;
	}
	
	public int getTicketNo() {
		return ticketNo;
	}
	public void setTicketNo(int ticketNo) {
		this.ticketNo = ticketNo;
	}
	public char getSrc() {
		return src;
	}
	public void setSrc(char src) {
		this.src = src;
	}
	public char getDest() {
		return dest;
	}
	public void setDest(char dest) {
		this.dest = dest;
	}
	public String getReservationStatus() {
		return reservationStatus;
	}
	public void setReservationStatus(String reservationStatus) {
		this.reservationStatus = reservationStatus;
	}
	public int getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}
	
}
