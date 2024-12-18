package models;

public class Ticket extends Showtime implements Printable {
    private String seatNumber;
    private double price;

    public Ticket(String title, String time, String seatNumber, double price) {
        super(title, time);
        this.seatNumber = seatNumber;
        this.price = price;
    }

    @Override
    public void printTicket() {
        System.out.println("========== TICKET ==========");
        System.out.println("Movie: " + getTitle());
        System.out.println("Time: " + getTime());
        System.out.println("Seat: " + seatNumber);
        System.out.println("Price: $" + price);
        System.out.println("============================");
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public double getPrice() {
        return price;
    }
}
