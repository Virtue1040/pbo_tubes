package module;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpHeaders;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.time.Duration;

public class HttpClientWrapper {

    private final HttpClient client;

    public HttpClientWrapper() {
        this.client = HttpClient.newHttpClient(); // Default HttpClient
    }

    public HttpClientWrapper(Duration timeout) {
        this.client = HttpClient.newBuilder()
                .connectTimeout(timeout)
                .build();
    }

    public String sendGetRequest(String url, String token) throws Exception {
        if (token == null) {
            token = "";
        }
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", token)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return handleResponse(response);
    }

    public CompletableFuture<String> sendGetRequestAsync(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(this::handleResponse);
    }

    public String sendPostRequest(String url, String json, String token) throws Exception {
        if (token == null) {
            token = "";
        }
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", token)
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return handleResponse(response);
    }

    public CompletableFuture<String> sendPostRequestAsync(String url, String json, String token) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Bearer " + token)
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(this::handleResponse);
    }

    private void addHeaders(HttpRequest.Builder requestBuilder, Map<String, String> headers) {
        if (headers != null) {
            headers.forEach(requestBuilder::header);
        }
    }

    public String sendDeleteRequest(String url, Map<String, String> headers) throws Exception {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(new URI(url))
                .DELETE();

        addHeaders(requestBuilder, headers);
        HttpResponse<String> response = client.send(requestBuilder.build(), HttpResponse.BodyHandlers.ofString());

        return handleResponse(response);
    }

    private String handleResponse(HttpResponse<String> response) {
        if (response.statusCode() == 200) {
            return response.body();
        } else {
            return "Error: " + response.statusCode() + " - " + response.body();
        }
    }

    public void close() {
        // client.close();
    }
}
