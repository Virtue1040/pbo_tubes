package models;

import javax.swing.*;

import static module.ComponentMod.savePanelAsPDF;

public class Ticket implements Printable {
    private Integer id;
    private Integer id_user;
    private Showtime showtime;
    private String seatNumber;
    private Double price;
    private String status;

    public Ticket(Integer id, Showtime showtime, Integer id_user, Double price, String seatNumber) {
        this.showtime = showtime;
        this.id_user = id_user;
        this.id = id;
        this.seatNumber = seatNumber;
        this.price = price;
    }

    @Override
    public void printTicket(JPanel panel, String filePath) {
        savePanelAsPDF(panel, filePath);
    }

    public Integer getId_user() {
        return id_user;
    }

    public Showtime getShowtime() {
        return showtime;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public double getPrice() {
        return price;
    }
}
