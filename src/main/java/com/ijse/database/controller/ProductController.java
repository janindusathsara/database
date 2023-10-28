package com.ijse.database.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.database.dto.ProductDto;
import com.ijse.database.entity.Product;
import com.ijse.database.service.ProductService;

@RestController
@CrossOrigin(origins = "*") //Allowing cross origin to all
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products") 
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.status(200).body(productService.getAllProducts());
    }

    @PostMapping("/products")
    public ResponseEntity<?> createProduct(@RequestBody ProductDto productDTO) {
        try {
            return ResponseEntity.status(201).body(productService.createProduct(productDTO));
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Failed to create the Product");
        }
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);

        if(product != null) {
            return ResponseEntity.status(HttpStatus.OK).body(product); //Return 200 with Body
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); //Return 404
        }
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @GetMapping("/categories/{id}/products") 
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable Long id) {
        return ResponseEntity.ok().body(productService.getProductsByCategory(id));
    }
}
