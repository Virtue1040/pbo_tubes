package controllers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import models.Cart;
import module.HttpClientWrapper;
import module.JsonConverter;

import java.awt.*;
import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;

public class BookingController extends Controller {
    public BookingController() {
        super(false);
    }

    public String[] bookTicket(JsonObject cart) {
        try {
            String response = httpClient.sendPostRequest(httpHost + "/api/order",
                    cart.toString(), System.getProperty("token"));
            System.out.println(response);
            JsonObject jsonResponse = JsonConverter.convertToJson(response);
            JsonObject getData = jsonResponse.get("data").getAsJsonObject();
            JsonObject getToken = getData.get("token").getAsJsonObject();
            String getRedirect = getToken.get("redirect_url").getAsString();
            String id_order = getData.get("id_order").getAsString();

            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) { Desktop.getDesktop().browse(new URI(getRedirect)); }
            return new String[]{getRedirect, id_order};
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String getStatus(String id) {
        try {
            String response = httpClient.sendGetRequest(httpHost + "/api/midtrans/status/" + id, null);
            JsonObject jsonResponse = JsonConverter.convertToJson(response);
            return jsonResponse.get("transaction_status").getAsString();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public HashMap<String, String[]> getSeatStatus(Integer id_showtime) {
        HashMap<String, String[]> occupy = new HashMap<>();
        try {
            String response = httpClient.sendGetRequest(httpHost + "/api/showtime/getoccupy/" + id_showtime, null);
            JsonObject jsonResponse = JsonConverter.convertToJson(response);
            JsonArray jsonData = jsonResponse.getAsJsonArray("data");
            for (JsonElement element : jsonData) {
                JsonObject obj = element.getAsJsonObject();
                String seatNumber = obj.get("seatNumber").getAsString();
                String status = obj.get("status").getAsString();
                occupy.put(seatNumber, new String[]{seatNumber, status});
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return occupy;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public JsonObject get() {
        return null;
    }
}
