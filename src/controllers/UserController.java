package controllers;

import com.google.gson.JsonObject;
import models.User;
import module.HttpClientWrapper;
import module.JsonConverter;
import views.Movies;
import views.modal.MessageBox;

import javax.swing.*;

import static module.Storage.clearToken;
import static module.Storage.saveToken;


public class UserController extends Controller {
    public UserController() {
        super(true);
    }

    @Override
    public JsonObject get() {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    public JsonObject login(String email, String password) {
        final String data = String.format("{\"login\":\"%s\",\"password\":\"%s\"}", email, password);

        try {
            String response = httpClient.sendPostRequest(httpHost + "/api/login",
                    data, null);
            JsonObject jsonResponse = JsonConverter.convertToJson(response);
            return jsonResponse;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public JsonObject login(String token) {
        System.out.println(token);
        try {
            String response = httpClient.sendGetRequest(httpHost + "/api/user",
                    token);
            JsonObject jsonResponse = JsonConverter.convertToJson(response);
            return jsonResponse;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public User getUser(String email, String password) {
        JsonObject result = this.login(email, password);
        if (result == null) {return null;}
        if (result.get("success").getAsBoolean()) {
            JsonObject jsonData = result.getAsJsonObject("data");
            String token = jsonData.get("token").getAsString();
            JsonObject user = jsonData.getAsJsonObject("user");

            return new User(user.get("id").getAsInt(), user.get("name").getAsString(), user.get("email").getAsString(), user.get("role").getAsString());
        }
        return null;
    }

    public User getUser(String token) {
        JsonObject result = this.login(token);
        if (result == null) {return null;}
        if (result.get("success").getAsBoolean()) {
            JsonObject jsonData = result.getAsJsonObject("data");
            JsonObject user = jsonData.getAsJsonObject("user");

            return new User(user.get("id").getAsInt(), user.get("name").getAsString(), user.get("email").getAsString(), user.get("role").getAsString());
        }
        return null;
    }

    public JsonObject register(String name, String email, String password, String password_confirmation) {
        final String data = String.format("{\"name\":\"%s\", \"email\":\"%s\", \"password\":\"%s\", \"password_confirmation\":\"%s\"}", name, email, password, password_confirmation);
        try {
            String response = httpClient.sendPostRequest(httpHost + "/api/register",
                    data, null);
            JsonObject jsonResponse = JsonConverter.convertToJson(response);
            return jsonResponse;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void logout() {
        String token = getToken();
        clearToken();
        System.setProperty("token", "null");
    }

    public String getToken() {
        return System.getProperty("token");
    }

    public void setToken(String token) {
        System.setProperty("token", "Bearer " + token);
    }
}
