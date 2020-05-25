package com.examples.scart.service;

import com.examples.scart.model.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductServiceTest {
    ProductService productService = new ProductService();

    @Before
    public void setup() {
        // Initialize Test data
        Product mobile = new Product();
        mobile.setId("1");
        mobile.setName("Samsung Galaxy Note10");
        mobile.setCategory("Mobiles");
        mobile.setManufacturer("Samsung");
        productService.createProduct(mobile);

        Product laptop = new Product();
        laptop.setId("2");
        laptop.setName("Lenovo Thinkpad E490");
        laptop.setCategory("Laptops");
        laptop.setManufacturer("Samsung");
        productService.createProduct(laptop);
    }

    @After
    public void cleanup() {
        productService.clear();
    }

    @Test
    public void shouldCreateProductWhenPassingMandatoryDetails() {
        Product product = new Product();
        product.setId("3");
        product.setName("Laptop");
        productService.createProduct(product);

        assertNotNull(productService.getProduct("3"));
        assertEquals("3", productService.getProduct("3").getId());
    }

    @Test
    public void shouldShowErrorWhenNotPassingMandatoryDetails() {
        Product product = new Product();
        try {
            productService.createProduct(product);
        }
        catch(Exception e) {
            assertEquals("Product Id mandatory", e.getMessage());
        }
    }

    @Test
    public void shouldUpdateProductForGivenProductId() {
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
        productService.deleteProduct("2");
        assertNull(productService.getProduct("2"));
        assertEquals(1, productService.getProducts().size());
    }

    @Test
    public void shouldReturnProductForGivenProductId() {
        assertNotNull(productService.getProduct("2"));
        assertEquals("2",productService.getProduct("2").getId());
    }

    @Test
    public void shouldReturnAllProductsWhenDontSpecifyProductId() {
          assertEquals(2, productService.getProducts().size());
    }
}