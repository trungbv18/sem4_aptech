package com.example.demo.service;

import com.example.demo.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface ProductService {
  List<Product> getAllProduct();
  void addProduct(Product product);
  boolean deleteProduct (Long id);
  Product updateProduct (Long id, Product product);
  Optional<Product> getProductById(Long id);

}
