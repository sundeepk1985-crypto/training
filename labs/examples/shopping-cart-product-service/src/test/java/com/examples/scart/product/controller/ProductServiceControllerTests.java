package com.examples.scart.product.controller;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.net.URISyntaxException;

// API Test / Integration test
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductServiceControllerTests {

    @Autowired
    TestRestTemplate restTemp;

    // SpringMockMVC
    // Rest Assured

    @Test
    public void shouldCreateProduct() throws URISyntaxException
    {
        // POST /products

        String reqBody = "{\"id\":\"1\",\"name\":\"HP Deskjet 5600\",\"category\":\"Printer\",\"manufacturer\":\"Manager\"}";

        // Step 1: Create Request
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RequestEntity request = new RequestEntity(reqBody, headers, HttpMethod.POST, new URI("/products"));

        // Step 2: Send Request to Endpoint
        // Step 3: Receive the Response

        ResponseEntity<Object> response = restTemp.exchange(request, Object.class);

        System.out.println("Response: " + response.getBody());

        // Step 4: Validate the Response
        Assertions.assertThat(response).isNotNull();
    }


    @Test
    public void shouldReturnAllProducts() {

        // REST Template
        // Step 1: Create Request
        // Step 2: Send Request to Endpoint
        // Step 3: Receive the Response
        Object response = restTemp.getForEntity("/products", Object.class);

//        log.info("Response: {}", response);

        // Step 4: Validate the Response
        Assertions.assertThat(response).isNotNull();
    }

}
