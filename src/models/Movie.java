package models;

import javax.swing.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class Movie {
    private Integer id;
    private String title;
    private String sinopsis;
    private String genre;
    private Double price;
    private String expire;
    private String duration;
    private String cover;
    private ArrayList<Showtime> listShowtimes;

    public Movie(Integer id, String title, String sinopsis, String genre, Double price, String duration, String expire, String cover) {
        this.title = title;
        this.sinopsis = sinopsis;
        this.genre = genre;
        this.price = price;
        this.expire = expire;
        this.id = id;
        this.cover = cover;
        this.duration = duration;
        this.listShowtimes = new ArrayList<>();
    }

    public void addShowtimes(Showtime showtime) {
        listShowtimes.add(showtime);
    }

    public void deleteShowtimes(Showtime showtime) {
        listShowtimes.remove(showtime);
    }

    public ArrayList<Showtime> getShowtimes() {
        return listShowtimes;
    }

    public ArrayList<Showtime> getActiveShowtimes() {
        ArrayList<Showtime> activeShowtimes = new ArrayList<>();
        listShowtimes.forEach(showtimes -> {
            if (Timestamp.valueOf(showtimes.getTime()).after(Timestamp.valueOf(LocalDateTime.now()))) {
                activeShowtimes.add(showtimes);
            }
        });

        return activeShowtimes;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getCover() {
        return cover;
    }

    public String getSinopsis(){
        return sinopsis;
    }

    public double getPrice() {
        return price;
    }

    public String getExpire() {
        return expire;
    }

    public String getDuration() {
        return duration;
    }
}
