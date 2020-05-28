package com.examples.scart.product.controller;

import com.examples.scart.product.model.Product;
import com.examples.scart.product.service.ProductService;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

// API Test / Integration test
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductServiceControllerTests {

    @Autowired
    TestRestTemplate restTemp;

    @MockBean
    ProductService productService;

    // SpringMockMVC
    // Rest Assured

    private static List<Product> products = new ArrayList<>();

    @Before
    public void setup() {
        // Initialize Test data
        Product mobile = new Product();
        mobile.setId("1");
        mobile.setName("Samsung Galaxy Note10");
        mobile.setCategory("Mobiles");
        mobile.setManufacturer("Samsung");
        products.add(mobile);

        Product laptop = new Product();
        laptop.setId("2");
        laptop.setName("Lenovo Thinkpad E490");
        laptop.setCategory("Laptops");
        laptop.setManufacturer("Samsung");
        products.add(laptop);
    }

    @After
    public void cleanup() {
        productService.clear();
    }

    @Test
    public void shouldCreateProduct() throws URISyntaxException
    {
        // POST /products

        Mockito.when(productService.createProduct(new Product())).thenReturn("Product created");

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
        Assertions.assertThat(response.getBody()).isEqualTo("Product created");
    }


    @Test
    public void shouldReturnAllProducts() {

        Mockito.when(productService.getProducts()).thenReturn(products);

        // REST Template
        // Step 1: Create Request
        // Step 2: Send Request to Endpoint
        // Step 3: Receive the Response
        ResponseEntity<Object> response = restTemp.getForEntity("/products", Object.class);

        List<Product> products = (List) response.getBody();

//        log.info("Response: {}", response);

        System.out.println("Response: " + products);

        // Step 4: Validate the Response
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(products.size()).isEqualTo(2);
    }

}
