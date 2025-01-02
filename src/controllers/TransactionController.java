package controllers;

import com.google.gson.JsonObject;
import module.JsonConverter;

public class TransactionController extends Controller {

    public TransactionController() {
        super(false);
    }

    @Override
    public JsonObject get() {
        try {
            String response = httpClient.sendGetRequest(httpHost + "/api/transaction/get", userController.getToken());
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
