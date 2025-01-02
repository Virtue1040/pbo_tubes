import controllers.UserController;
import models.User;
import views.Movies;
import views.Splash;

import static module.Storage.getToken;

public class Main {
    private static UserController userController;

    public static void main(String[] args) {
        System.setProperty("httpHost", "http://127.0.0.1:8000");
        userController = new UserController();

        final String getRememberToken = getToken();
        User getUser = null;
        if (getRememberToken != null) {
            System.out.println(getRememberToken);
            userController.setToken(getRememberToken);
            getUser = userController.getUser(userController.getToken());
        }

        /* start form */
        Splash splash = new Splash(new Movies(getUser));
        splash.setVisible(true);
    }
}