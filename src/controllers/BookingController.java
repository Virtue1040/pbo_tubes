package controllers;

import models.Showtime;
import models.Ticket;

public class BookingController {
    public Ticket bookTicket(Showtime showtime, String seat, double price) {
        System.out.println("Booking ticket for " + showtime.getTitle() + " at " + showtime.getTime() + "...");
        return new Ticket(showtime.getTitle(), showtime.getTime(), seat, price);
    }
}
