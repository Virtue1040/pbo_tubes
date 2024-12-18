package models;

public class Showtime extends Movie {
    private String time;

    public Showtime(String title, String time) {
        super(title); // memanggil constructor Movie
        this.time = time;
    }

    public String getTime() {
        return time;
    }
}
