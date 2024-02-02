package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.HttpResponse;

/**
 * Unit test for simple App.
 */
class AppTest {
    /**
     * Rigorous Test.
     */
    @Test
    void testApp() {
        System.out.println("HAAAAALOOOO");

        try {
            System.out.println("testApp");
            HttpClient httpClient = HttpClients.createDefault();
            HttpPost postRequest = new HttpPost("http://localhost:7070/beitrag/create/");

            // String payload
            StringEntity input = new StringEntity("{\"text\":\"Hallo\",\"absender\":\"Max\"}");
            input.setContentType("application/json");
            postRequest.setEntity(input);

            // Execute the request
            HttpResponse response = httpClient.execute(postRequest);

            // Convert the response to a String format
            String result = EntityUtils.toString(response.getEntity());
            int status = response.getStatusLine().getStatusCode();
            assertEquals(200, status);
            // Print result
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
