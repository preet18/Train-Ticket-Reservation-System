package com.train.ticket.service;

import com.train.ticket.model.Ticket;
import com.train.ticket.model.Train;

public class TrainTicketReservationService {

	public Ticket bookTicket(Train train, char src, char dest) {
		Ticket ticket = null;
		int s = src-'A';
		int d = dest-'A';
		for(int i=0; i<train.getSeats().length; i++) {
			if(checkSeatAvailability(train, i, s, d)) {
				bookSeat(train, i, s, d);
				ticket = generateTicket(src, dest, train, i);
				train.getTcktList().add(ticket);
				return ticket;
			}
		}
		ticket = addToWaitingList(train, src, dest);
		train.getTcktList().add(ticket);
		return ticket;
	}

	private Ticket addToWaitingList(Train train, char src, char dest) {
		int tcktNo = train.generateTicketNo();
		Ticket ticket = new Ticket(tcktNo);
		ticket.setSrc(src);
		ticket.setDest(dest);
		ticket.setReservationStatus("Waiting");
		return ticket;
	}

	private Ticket generateTicket(char src, char dest, Train train, int i) {
		int tcktNo = train.generateTicketNo();
		Ticket ticket = new Ticket(tcktNo);
		ticket.setSrc(src);
		ticket.setDest(dest);
		ticket.setReservationStatus("Confirmed");
		ticket.setSeatNo(i+1);
		return ticket;
	}

	private void bookSeat(Train train, int i, int s, int d) {
		for(int j=s; j<d; j++) {
			train.getSeats()[i][j] = true;
		}
		
	}

	private boolean checkSeatAvailability(Train train, int i, int s, int d) {
		for(int j=s; j<d; j++) {
			if(train.getSeats()[i][j]) {
				return false;
			}
		}
		return true;
	}

	public boolean cancelTicket(int ticketNo, Train train) {
		for(Ticket tckt: train.getTcktList()) {
			if(tckt.getTicketNo()==ticketNo) {
				System.out.println(tckt.getReservationStatus());
				if(tckt.getReservationStatus().equals("Confirmed")) {
					System.out.println("Its confirmed...");
					cancelSeats(tckt, train);
				}
				tckt.setReservationStatus("Cancelled");
				checkForWaitingList(train, tckt);
				return true;
			}
		}
		return false;
	}

	private void checkForWaitingList(Train train, Ticket ticket) {
		char src = ticket.getSrc();
		char dest = ticket.getDest();
		
		for(Ticket tckt: train.getTcktList()) {
			if(tckt.getReservationStatus().equals("Waiting") && src<=tckt.getSrc() && dest>=tckt.getDest()) {
				System.out.println("Waiting Tickets found");
				confirmSeat(train, tckt.getSrc()-'A', tckt.getDest()-'A', ticket.getSeatNo());
			}
		}
		
	}

	private void confirmSeat(Train train, int s, int d, int seatNo) {
		for(int j=s; j<d; j++) {
			train.getSeats()[seatNo-1][j] = true;
		}
		
	}

	private void cancelSeats(Ticket tckt, Train train) {
		int src = tckt.getSrc()-'A';
		int dest = tckt.getDest()-'A';
		System.out.println("Src :: " + src + " Dest :: " + dest);
		int seatNo = tckt.getSeatNo();
		for(int j=src; j<dest; j++) {
			train.getSeats()[seatNo-1][j] = false;
		}
	}

	public void displaySeats(Train train) {
		for(int i=0; i<train.getSeats().length; i++) {
			for(int j=0; j<train.getSeats()[0].length-1; j++) {
				if(train.getSeats()[i][j]) {
					System.out.print("X|");
				}else {
					System.out.print("_|");
				}
			}
			System.out.println();
		}
		
	}

	public String getReservationStatus(int ticketNo, Train train) {
		for(Ticket tckt: train.getTcktList()) {
			if(tckt.getTicketNo()==ticketNo) {
				return "Reservation Status is :: " + tckt.getReservationStatus();
			}
		}
		return "No ticket found with the given ticket No";
	}

}
