package com.examples.scart.product.service;

import com.examples.scart.product.controller.ProductServiceController;
import com.examples.scart.product.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    private static Map<String, Product> productRepo = new HashMap<>();

    public Collection<Product> getProducts() {
        return productRepo.values();
    }

    public void createProduct(Product product) {
        if(product.getId() == null || product.getId().isEmpty()) {
            throw new RuntimeException("Product Id mandatory");
        }
        productRepo.put(product.getId(), product);
    }

    public void updateProduct(String id, Product product) {
        productRepo.remove(id);
        product.setId(id);
        productRepo.put(id, product);
    }

    public void deleteProduct(String id) {
        productRepo.remove(id);
    }

    public Product getProduct(String id) {
        return productRepo.get(id);
    }

    public void clear() {
        productRepo.clear();
    }

}
