
package com.synchrony.usermanagement.service;

import com.synchrony.usermanagement.config.SynchronyAppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;


@Service
public class ImgurApiService {
    @Autowired
    SynchronyAppConfig config;
    @Autowired
    HttpClient httpClient;


    public HttpResponse<String> uploadToImgur(byte[] data) throws IOException, InterruptedException {
        String uuid = java.util.UUID.randomUUID().toString();
        HttpRequest.BodyPublisher body = oneFileBody(data, uuid);
        URI uri = URI.create(config.getImgurapi()+"/upload/");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Content-Type", "multipart/form-data; boundary=" + uuid)
                .header("Authorization", "Client-ID " + config.getClientId())
                .POST(body)
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());

    }


    public static HttpRequest.BodyPublisher oneFileBody(byte[] data, String uuid) throws IOException {
        String base64_image = Base64.getEncoder().encodeToString(data);
        String content = String.format("--%1$s\nContent-Disposition: form-data; name=\"type\"\nContent-Type: text/plain\n\nbase64\n--%1$s\nContent-Disposition: form-data; name=\"image\"\n\n%2$s\n--%1$s", uuid, base64_image);
        return HttpRequest.BodyPublishers.ofString(content);
    }

    public HttpResponse<String> getUploadedImage(String id) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.imgur.com/3/image/" + id))
                .header("Authorization", "Client-ID " + config.getClientId())
                .GET()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response;

    }

    public HttpResponse<String> deleteUploadedImage(String id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.imgur.com/3/image/" + id))
                .header("Authorization", "Client-ID " + config.getClientId())
                .DELETE()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

}

