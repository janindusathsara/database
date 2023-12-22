package com.ijse.database.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.database.dto.ProductDto;
import com.ijse.database.entity.Category;
import com.ijse.database.entity.Product;
import com.ijse.database.repository.CategoryRepository;
import com.ijse.database.repository.ProductRepository;
import com.ijse.database.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(ProductDto productDto) {
        Category entity = categoryRepository.findById(productDto.getCategoryId()).orElse(null);

        if (entity != null) {
            Product productEntity = new Product();
            productEntity.setName(productDto.getName());
            productEntity.setCatagory(entity);
            productEntity.setPrice(productDto.getPrice());
            productEntity.setQty(productDto.getQty());

            return productRepository.save(productEntity);
        } else {
            return null;
        }
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product existingEntity = productRepository.findById(id).orElse(null);

        if (existingEntity != null) {
            existingEntity.setCatagory(product.getCatagory());
            existingEntity.setName(product.getName());
            existingEntity.setQty(product.getQty());
            existingEntity.setPrice(product.getPrice());

            return productRepository.save(existingEntity);
        } else {
            return null;
        }
    }

    @Override
    public List<Product> getProductsByCategory(Long id) {
        Category categoryEntity = categoryRepository.findById(id).orElse(null);

        if (categoryEntity != null) {
            return null; //productRepository.findProductByCategory(categoryEntity);
        } else {
            return null;
        }
    }

}
