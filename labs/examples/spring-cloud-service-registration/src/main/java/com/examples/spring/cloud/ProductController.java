package com.examples.spring.cloud;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @GetMapping("/products")
    public ResponseEntity<Object> getProducts() {
        return ResponseEntity.ok().body("Products to be listed...");
    }
}
