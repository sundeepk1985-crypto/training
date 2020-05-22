package com.examples.scart.product.service;

import com.examples.scart.product.model.Product;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProduceServiceTest {

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

        Assertions.assertThat(productService.getProduct("3")).isNotNull();
        Assertions.assertThat(productService.getProduct("3").getId()).isEqualTo("3");
        Assertions.assertThat(productService.getProducts().size()).isEqualTo(3);
    }

    @Test
    public void shouldShowErrorWhenNotPassingMandatoryDetails() {
        Product product = new Product();
        try {
            productService.createProduct(product);
        }
        catch(Exception e) {
            Assertions.assertThatExceptionOfType(RuntimeException.class);
            Assertions.assertThat(e.getMessage()).isEqualTo("Product Id mandatory");
        }
    }

    @Test
    public void shouldDeleteProductWhenPassingValidProductId() {
        productService.deleteProduct("2");
        Assertions.assertThat(productService.getProduct("2")).isNull();
        Assertions.assertThat(productService.getProducts().size()).isEqualTo(1);
    }

    @Test
    public void shouldReturnAllProductsWhenDontSpecifyProductId() {
        System.out.println("Product count: " + productService.getProducts().size());
        Assertions.assertThat(productService.getProducts().size()).isEqualTo(2);
    }

}
