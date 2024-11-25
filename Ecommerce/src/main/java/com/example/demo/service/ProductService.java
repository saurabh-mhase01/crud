package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Product;
import com.example.demo.repository.ProductRepository;


@Service
public class ProductService {
	  @Autowired
	    private ProductRepository productRepository;

	    public Page<Product> getProducts(int page, int size) {
	        return productRepository.findAll(PageRequest.of(page, size));
	    }

	    public Optional<Product> getProductById(Long id) {
	        return productRepository.findById(id);
	    }

	    public Product createProduct(Product product) {
	        return productRepository.save(product);
	    }

	    public Product updateProduct(Long id, Product product) {
	        product.setId(id);
	        return productRepository.save(product);
	    }

	    public void deleteProduct(Long id) {
	        productRepository.deleteById(id);
	    }
}
