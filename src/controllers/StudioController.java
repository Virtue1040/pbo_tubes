package controllers;

import com.google.gson.JsonObject;
import module.JsonConverter;


public class StudioController extends Controller {
    public StudioController() {
        super(false);
    }

    public boolean addStudio(String studioName, Integer capacity) {
        final String data = String.format("{\"studioName\":\"%s\", \"capacity\":\"%s\"}", studioName, capacity);
        try {
            System.out.println(userController.getToken());
            String response = httpClient.sendPostRequest(httpHost + "/api/studio", data, userController.getToken());
            System.out.println(response);
            JsonObject jsonResponse = JsonConverter.convertToJson(response);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public boolean updateStudio(Integer id, String studioName, Integer capacity) {
        final String data = String.format("{\"_method\":\"%s\", \"studioName\":\"%s\", \"capacity\":\"%s\"}", "PUT", studioName, capacity);
        try {
            System.out.println(userController.getToken());
            String response = httpClient.sendPostRequest(httpHost + "/api/studio/" + id, data, userController.getToken());
            System.out.println(response);
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
            System.out.println(userController.getToken());
            String response = httpClient.sendPostRequest(httpHost + "/api/studio/" + id, data, userController.getToken());
            System.out.println(response);
            JsonObject jsonResponse = JsonConverter.convertToJson(response);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public JsonObject get() {
        try {
            String response = httpClient.sendGetRequest(httpHost + "/api/studio", null);
            JsonObject jsonResponse = JsonConverter.convertToJson(response);
            return jsonResponse;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
