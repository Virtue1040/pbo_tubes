package controllers;

import com.google.gson.JsonObject;
import module.HttpClientWrapper;

abstract class Controller {
    public HttpClientWrapper httpClient;
    public String httpHost;
    public UserController userController;

    public Controller(Boolean isUserController) {
        this.httpClient = new HttpClientWrapper();
        this.httpHost = System.getProperty("httpHost");
        if (!isUserController) { this.userController = new UserController(); }
    }

    public abstract JsonObject get();
    public abstract boolean delete(Integer id);
}
