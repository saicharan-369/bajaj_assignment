package com.example.demo;

import com.example.demo.model.GenerateWebhookRequest;
import com.example.demo.model.GenerateWebhookResponse;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Task1GenerateWebhook implements CommandLineRunner {

    private final RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        SpringApplication.run(Task1GenerateWebhook.class, args);
    }

    @Override
    public void run(String... args) {
        // Step 1 → Call generateWebhook
        String url = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";

        GenerateWebhookRequest request = new GenerateWebhookRequest(
                "AYITHA VENKATA SAI CHARAN",
                "22BCE20383",
                "Charan.22bce20383@vitapstudent.ac.in"
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<GenerateWebhookRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<GenerateWebhookResponse> response =
                restTemplate.exchange(url, HttpMethod.POST, entity, GenerateWebhookResponse.class);

        GenerateWebhookResponse resp = response.getBody();
        if (resp != null) {
            System.out.println("✅ Webhook URL: " + resp.getWebhook());
            System.out.println("✅ Access Token: " + resp.getAccessToken());
        } else {
            System.out.println("❌ Failed to generate webhook");
        }
    }
}
