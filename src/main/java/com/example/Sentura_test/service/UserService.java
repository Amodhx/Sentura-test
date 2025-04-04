package com.example.Sentura_test.service;

import com.example.Sentura_test.dto.UserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserService {

    @Autowired
    private OkHttpClient client;
    @Autowired
    private ObjectMapper objectMapper;

    @Value("${weavy.api.url}")
    private String weavyApiUrl;

    @Value("${weavy.api.key}")
    private String weavyApiToken;


    public UserDTO saveUser(UserDTO userDTO) {
        String url = weavyApiUrl + "/api/users";
        try {

            String jsonBody = objectMapper.writeValueAsString(userDTO);

            RequestBody body = okhttp3.RequestBody.create(
                    jsonBody,
                    MediaType.parse("application/json")
            );

            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .addHeader("Authorization", "Bearer " + weavyApiToken)
                    .addHeader("Content-Type", "application/json")
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful() && response.body() != null) {
                    String responseBody = response.body().string();
                    return objectMapper.readValue(responseBody, UserDTO.class);
                } else {
                    throw new RuntimeException("Failed to save user: " + response);
                }
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting user to JSON", e);
        } catch (IOException e) {
            throw new RuntimeException("Error making HTTP request to Weavy API", e);
        }
    }
    public UserDTO getUser(String userId, Boolean trashed) {
        String url = weavyApiUrl + "/api/users/" + userId;
        if (trashed != null) {
            url += "?trashed=" + trashed;
        }

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("Authorization", "Bearer " + weavyApiToken)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();
                return objectMapper.readValue(responseBody, UserDTO.class);
            } else {
                throw new RuntimeException("Cant get User " + response.message());
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException("response to UserDTO", e);
        } catch (IOException e) {
            throw new RuntimeException("request to Weavy API", e);
        }
    }
    public void deleteUser(String userId) {
        String url = weavyApiUrl + "/api/users/" + userId+"/trash";

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(new byte[0]))
                .addHeader("Authorization", "Bearer " + weavyApiToken)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("Cant delete User" + response.message());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
