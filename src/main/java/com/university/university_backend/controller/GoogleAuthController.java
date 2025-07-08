package com.university.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.auth.oauth2.GooglePublicKeysManager;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;

import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class GoogleAuthController {

    private final GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
            new NetHttpTransport(),
            GsonFactory.getDefaultInstance())
            .setAudience(Collections.singletonList("548600634405-pjlmjeig84pjn7c1rpoh19c9nt5ji4vu.apps.googleusercontent.com"))
            .build();

    @PostMapping("/google")
    public String googleLogin(@RequestBody TokenRequest tokenRequest) throws Exception {
        GoogleIdToken idToken = verifier.verify(tokenRequest.getToken());

        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();

            String email = payload.getEmail();
            String name = (String) payload.get("name");

            // ✅ Here, you would check if user exists in DB or register them.
            // Then generate your own JWT and return it.

            return "✅ Logged in user: " + email;
        } else {
            throw new Exception("Invalid Google token");
        }
    }

    public static class TokenRequest {
        private String token;

        public String getToken() { return token; }
        public void setToken(String token) { this.token = token; }
    }
}
