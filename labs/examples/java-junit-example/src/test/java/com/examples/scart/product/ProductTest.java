package com.examples.scart.product;

import com.examples.scart.model.Product;
import static org.junit.Assert.*;
import org.junit.Test;

public class ProductTest {

    @Test
    public void shouldSetProductId() {
        Product mobile = new Product();
        mobile.setId("100");

        assertEquals("100", mobile.getId());
    }
}
