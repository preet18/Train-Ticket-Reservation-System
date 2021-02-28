package com.train.ticket.test;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.train.ticket.model.Ticket;
import com.train.ticket.model.Train;
import com.train.ticket.service.TrainTicketReservationService;

public class TrainTicketTest {

	public static void main(String[] args) {
		try {
			TrainTicketReservationService service = new TrainTicketReservationService();
			Train train = new Train();
			Scanner sc = new Scanner(System.in);
			List<Character> stations = train.getStations();
			boolean exit = false;
			while(!exit) {
				System.out.println("1.Book a Ticket \n2.Cancel a Ticket \n3.Ticket Reservation Status "
						+ "\n4.Display Booked Tickets \n5.Exit");
				int option = sc.nextInt();
				sc.nextLine();
				if(option==1) {

					System.out.println("The different stations available are ::");
					for(Character station : stations) {
						System.out.print(station+" | ");
					}
					System.out.println("Enter source :: ");
					char src = sc.next().charAt(0);
					System.out.println("Enter destination :: ");
					char dest = sc.next().charAt(0);

					Ticket ticket = service.bookTicket(train, src, dest);
					if(ticket==null) {
						System.out.println("Seats are not available");
					}else {
						if(ticket.getReservationStatus().equals("Confirmed")) {
							System.out.println("Ticket booked successfully, ticket no is :: " + ticket.getTicketNo());
						}else {
							System.out.println("Your booking is in waiting state, ticket no is :: " + ticket.getTicketNo());
						}
					}

				}else if(option==2) {
					System.out.println("Enter the ticket no:: ");
					int ticketNo = sc.nextInt();
					sc.nextLine();
					boolean cancellationStatus = service.cancelTicket(ticketNo, train);
					if(cancellationStatus) {
						System.out.println("Ticket cancelled successfully");
					}else {
						System.out.println("No Ticket found with the given ticket no");
					}
				}else if(option==3) {
					System.out.println("Enter the ticket No ::");
					int ticketNo = sc.nextInt();
					sc.nextLine();
					String status = service.getReservationStatus(ticketNo, train);
					System.out.println(status);
				}else if(option==4) {
					service.displaySeats(train);
				}else {
					exit = true;
				}
			}
		}catch(Exception ex) {
			System.out.println("In Exception :: " + ex.getMessage());
		}
	}

	

	
}
