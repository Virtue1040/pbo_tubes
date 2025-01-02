package models;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import controllers.BookingController;

import java.util.ArrayList;

public class Cart implements Payable {
    private Integer id_showtime;
    private ArrayList<SeatCart> listSeats;
    private BookingController bookingController;
    private JsonObject cart;

    public Cart(Integer id_showtime) {
        this.id_showtime = id_showtime;
        this.listSeats = new ArrayList<>();
        this.bookingController = new BookingController();
    }

    public void addSeatCart(SeatCart seatCart) {
        this.listSeats.add(seatCart);
    }

    public void deleteSeatCart(SeatCart seatCart) {
        this.listSeats.remove(seatCart);
    }

    public void deleteSeatCart(int index) {
        this.listSeats.remove(index);
    }

    public void clearSeatCart() {
        this.listSeats.clear();
    }

    public ArrayList<SeatCart> getListSeats() {
        return listSeats;
    }

    public Integer getTotalCart() {
        return listSeats.size();
    }

    public Integer getId_showtime() {
        return id_showtime;
    }

    public void bungkusJson() {
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonData = new JsonArray();
        this.getListSeats().forEach(seatCart -> {
            String getSeatNumber = seatCart.getSeatNumber();
            Integer getIdShow = this.getId_showtime();
            JsonObject obj = new JsonObject();
            obj.addProperty("seatNumber", getSeatNumber);
            obj.addProperty("id_showtime", getIdShow);
            jsonData.add(obj);
        });
        jsonObject.add("ticket", jsonData);
        this.cart = jsonObject;
    }

    @Override
    public String[] processPayment() {
        if (cart != null) { return this.bookingController.bookTicket(cart); }
        return null;
    }
}
