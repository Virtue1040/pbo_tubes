package controllers;

import com.google.gson.JsonObject;
import module.JsonConverter;

import java.io.File;
import java.nio.file.Files;
import java.util.Base64;


public class MovieController extends Controller {
    public MovieController() {
        super(false);
    }

    public boolean addMovie(String title, String sinopsis, String genre, String duration, double price, String expire, File cover) {
        String base64Cover = "null";
        if (cover != null) {
            try {
                byte[] fileContent = Files.readAllBytes(cover.toPath());
                base64Cover = Base64.getEncoder().encodeToString(fileContent);
            } catch (Exception ex) {

            }
        }
        final String data = String.format("{\"title\":\"%s\", \"sinopsis\":\"%s\", \"genre\":\"%s\", \"duration\":\"%s\", \"price\":\"%s\", \"expire\":\"%s\", \"cover\":\"%s\"}", title, sinopsis, genre, duration, price, expire, base64Cover);
        try {
            String response = httpClient.sendPostRequest(httpHost + "/api/movie", data, userController.getToken());
            System.out.println(response);
            JsonObject jsonResponse = JsonConverter.convertToJson(response);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public boolean updateMovie(Integer id, String title, String sinopsis, String genre, String duration, Double price, String expire, File cover) {
        String base64Cover = "null";
        if (cover != null) {
            try {
                byte[] fileContent = Files.readAllBytes(cover.toPath());
                base64Cover = Base64.getEncoder().encodeToString(fileContent);
            } catch (Exception ex) {

            }
        }
        final String data = String.format("{\"_method\":\"%s\", \"title\":\"%s\", \"sinopsis\":\"%s\", \"genre\":\"%s\", \"duration\":\"%s\", \"price\":\"%s\", \"expire\":\"%s\", \"cover\":\"%s\"}","PUT", title, sinopsis, genre, duration, price, expire, base64Cover);
        try {
            String response = httpClient.sendPostRequest(httpHost + "/api/movie/" + id, data, userController.getToken());
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
            String response = httpClient.sendPostRequest(httpHost + "/api/movie/" + id, data, userController.getToken());
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
            String response = httpClient.sendGetRequest(httpHost + "/api/movie", null);
            System.out.println(response);
            JsonObject jsonResponse = JsonConverter.convertToJson(response);
            return jsonResponse;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
