package models;

import java.util.ArrayList;

public class Showtime {
    private Integer id;
    private Movie movie;
    private Studio studio;
    private String time;
    private ArrayList<Ticket> listTicket;

    public Showtime(Integer id, Movie movie, Studio studio, String time) {
        this.id = id;
        this.movie = movie;
        this.studio = studio;
        this.time = time;
        this.listTicket = new ArrayList<>();
    }

    public void addTicket(Ticket ticket) {
        listTicket.add(ticket);
    }

    public ArrayList<Ticket> getTicket() {
        return listTicket;
    }

    public Ticket getTicket(int index) {
        return listTicket.get(index);
    }

    public ArrayList<Ticket> getTicket(User user) {
        ArrayList<Ticket> getUsertickets = new ArrayList<>();
        listTicket.forEach(ticket -> {
            if (ticket.getId_user().equals(user.getId())) {
                getUsertickets.add(ticket);
            }
        });

        return getUsertickets;
    }

    public Integer getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }

    public Studio getStudio() {
        return studio;
    }

    public String getTime() {
        return time;
    }
}
