package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepo productRepo;
    @Autowired
    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }
    @Override
    public List<Product> getAllProduct() {
        return productRepo.findAll();
    }
    @Override
    public void addProduct(Product product) {
        productRepo.save(product);
    }

    @Override
    public boolean deleteProduct(Long id) {
        Optional<Product> product = productRepo.findById(id);
        if (product.isPresent()) {
            productRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Optional<Product> oldProduct = productRepo.findById(id);
        if (oldProduct.isPresent()){
            Product _oldProduct = oldProduct.get();
            _oldProduct.setName(product.getName());
            _oldProduct.setCode(product.getCode());
            _oldProduct.setDescription(product.getDescription());
            _oldProduct.setPrice(product.getPrice());
            _oldProduct.setQuantity(product.getQuantity());
            return productRepo.save(_oldProduct);
        }
        return null;
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepo.findById(id);
    }
}
