package com.examples.scart.product.service;

import com.examples.scart.product.model.Product;
import com.examples.scart.product.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProduceServiceTests {

    @Autowired
    ProductService productService;

//    @MockBean
//    ProductRepository productRepo;

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
    public void shouldCreateProductWhenPassingMandatoryDetails() {
        productService.createProduct(products.get(0));

        assertNotNull(productService.getProduct("1"));
        assertEquals("1", productService.getProduct("1").getId());
    }

    @Test
    public void shouldShowErrorWhenNotPassingMandatoryDetails() {
        try {
            productService.createProduct(new Product());
        }
        catch(Exception e) {
            assertEquals("Product Id mandatory", e.getMessage());
        }
    }

    @Test
    public void shouldUpdateProductForGivenProductId() {
        productService.createProduct(products.get(1));

        Product laptop = new Product();
        laptop.setName("Lenovo Thinkpad E490");
        laptop.setCategory("Laptops");
        laptop.setManufacturer("Lenovo");

        productService.updateProduct("2", laptop);

        assertNotNull(productService.getProduct("2"));
        assertEquals("Lenovo",productService.getProduct("2").getManufacturer());
    }

    @Test
    public void shouldDeleteProductWhenPassingValidProductId() {
        productService.createProduct(products.get(0));
        productService.createProduct(products.get(1));
        assertEquals(2, productService.getProducts().size());

        productService.deleteProduct("2");
        assertEquals(1, productService.getProducts().size());
    }

    @Test
    public void shouldReturnProductForGivenProductId() {
        productService.createProduct(products.get(0));
        assertNotNull(productService.getProduct("1"));
        assertEquals("1",productService.getProduct("1").getId());
    }

    @Test
    public void shouldReturnAllProductsWhenDontSpecifyProductId() {
//        Mockito.when(productRepo.findAll()).thenReturn(products);

        productService.createProduct(products.get(0));
        productService.createProduct(products.get(1));

        assertEquals(2, productService.getProducts().size());
    }

}
