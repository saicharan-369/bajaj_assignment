package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Task2SubmitSolution implements CommandLineRunner {

    private final RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        SpringApplication.run(Task2SubmitSolution.class, args);
    }

    @Override
    public void run(String... args) {
        // Use your token here
        String accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJyZWdObyI6IjIyQkNFMjAzODMiLCJuYW1lIjoiQVlJVEhBIFZFTktBVEEgU0FJIENIQVJBTiIsImVtYWlsIjoiQ2hhcmFuLjIyYmNlMjAzODNAdml0YXBzdHVkZW50LmFjLmluIiwic3ViIjoid2ViaG9vay11c2VyIiwiaWF0IjoxNzU2NDQ1Njk3LCJleHAiOjE3NTY0NDY1OTd9.UqTeX4tUKNKSYmRjm-mhp5eqDscTH3cEpMWf02QAlWY";

        // Final SQL Query
        String finalQuery = "SELECT p.AMOUNT AS SALARY, " +
                "CONCAT(e.FIRST_NAME, ' ', e.LAST_NAME) AS NAME, " +
                "TIMESTAMPDIFF(YEAR, e.DOB, CURDATE()) - " +
                "(DATE_FORMAT(CURDATE(), '%m%d') < DATE_FORMAT(e.DOB, '%m%d')) AS AGE, " +
                "d.DEPARTMENT_NAME " +
                "FROM PAYMENTS p " +
                "JOIN EMPLOYEE e ON p.EMP_ID = e.EMP_ID " +
                "JOIN DEPARTMENT d ON e.DEPARTMENT = d.DEPARTMENT_ID " +
                "WHERE DAY(p.PAYMENT_TIME) <> 1 " +
                "ORDER BY p.AMOUNT DESC " +
                "LIMIT 1;";

        String submitUrl = "https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(accessToken);  // JWT

        String body = "{ \"finalQuery\": \"" + finalQuery.replace("\"", "\\\"") + "\" }";

        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                submitUrl, HttpMethod.POST, entity, String.class
        );

        System.out.println("✅ Submit Response: " + response.getBody());
    }
}
