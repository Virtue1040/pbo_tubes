package controllers;

import com.google.gson.JsonObject;
import module.JsonConverter;

public class TicketController extends Controller {
    public TicketController() {
        super(false);
    }

    public boolean addTicket(String id_order, Integer id_user, Integer id_showtime, String seatNumber, Double price, String status) {
        return false;
    }

    public boolean updateTicket(Integer id, String id_order, Integer id_user, Integer id_showtime, String seatNumber, Double price, String status) {
        return false;
    }

    public boolean deleteTicket(Integer id) {
        return false;
    }

    @Override
    public JsonObject get() {
        try {
            String response = httpClient.sendGetRequest(httpHost + "/api/ticket/get", userController.getToken());
            JsonObject jsonResponse = JsonConverter.convertToJson(response);
            return jsonResponse;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }
}
