package controllers;

import com.google.gson.JsonObject;
import module.JsonConverter;


public class ShowtimeController extends Controller {
    public ShowtimeController() {
        super(false);
    }

    public boolean addShowtime(Integer id_movie, Integer id_studio, String time, String times) {
        final String data = String.format("{\"id_movie\":\"%s\", \"id_studio\":\"%s\", \"time\":\"%s\"}", id_movie, id_studio, time + " " + times);
        try {
            System.out.println(userController.getToken());
            String response = httpClient.sendPostRequest(httpHost + "/api/showtime", data, userController.getToken());
            System.out.println(response);
            JsonObject jsonResponse = JsonConverter.convertToJson(response);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public boolean updateShowtime(Integer id, Integer id_movie, Integer id_studio, String time, String times) {
        final String data = String.format("{\"_method\":\"%s\", \"id_movie\":\"%s\", \"id_studio\":\"%s\", \"time\":\"%s\"}", "PUT", id_movie, id_studio, time + " " + times);
        try {
            String response = httpClient.sendPostRequest(httpHost + "/api/showtime/" + id, data, userController.getToken());
            JsonObject jsonResponse = JsonConverter.convertToJson(response);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(Integer id) {
        final String data = String.format("{\"_method\":\"%s\"}", "DELETE");
        try {
            String response = httpClient.sendPostRequest(httpHost + "/api/showtime/" + id, data, userController.getToken());
            JsonObject jsonResponse = JsonConverter.convertToJson(response);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public JsonObject getShowtimesFromMovie(Integer id_movie) {
        try {
            String response = httpClient.sendGetRequest(httpHost + "/api/showtime/getshowtime/" + id_movie, null);
            System.out.println(response);
            JsonObject jsonResponse = JsonConverter.convertToJson(response);
            return jsonResponse;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public JsonObject get() {
        try {
            String response = httpClient.sendGetRequest(httpHost + "/api/showtime", null);
            JsonObject jsonResponse = JsonConverter.convertToJson(response);
            return jsonResponse;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
