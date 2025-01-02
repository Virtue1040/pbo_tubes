package module;

import java.util.prefs.Preferences;

public class Storage {
    private static final String PREF_NODE = "TixEaseApp";
    private static final String TOKEN_KEY = "rememberMeToken";

    public static void saveToken(String token) {
        Preferences prefs = Preferences.userRoot().node(PREF_NODE);
        prefs.put(TOKEN_KEY, token);
    }

    public static String getToken() {
        Preferences prefs = Preferences.userRoot().node(PREF_NODE);
        return prefs.get(TOKEN_KEY, null);
    }

    public static void clearToken() {
        Preferences prefs = Preferences.userRoot().node(PREF_NODE);
        prefs.remove(TOKEN_KEY);
    }
}