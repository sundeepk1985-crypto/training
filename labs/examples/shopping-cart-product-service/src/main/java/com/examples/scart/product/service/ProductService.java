package com.examples.scart.product.service;

import com.examples.scart.product.controller.ProductServiceController;
import com.examples.scart.product.model.Product;
import com.examples.scart.product.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

//    private static Map<String, Product> productRepo = new HashMap<>();

    @Autowired
    ProductRepository productRepo;

    public Collection<Product> getProducts() {
//        return productRepo.values();
        return productRepo.findAll();
    }

    public String createProduct(Product product) {
//        if(product.getId() == null || product.getId().isEmpty()) {
//            throw new RuntimeException("Product Id mandatory");
//        }
//        productRepo.put(product.getId(), product);
        productRepo.save(product);

        return "Product created";
    }

    public void updateProduct(String id, Product product) {
//        productRepo.remove(id);
//        product.setId(id);
//        productRepo.put(id, product);
        product.setId(id);
        productRepo.save(product);
    }

    public void deleteProduct(String id) {
//        productRepo.remove(id);
        productRepo.delete(productRepo.findById(id).get());
    }

    public Product getProduct(String id) {
//        return productRepo.get(id);
        return productRepo.findById(id).get();
    }

    public void clear() {
//        productRepo.clear();
        productRepo.deleteAll();
    }

}
