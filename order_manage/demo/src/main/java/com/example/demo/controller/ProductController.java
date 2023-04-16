package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepo;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {
  private final ProductService productService;
  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/get-all")
  public ResponseEntity<List<Product>> getAllProduct() {
    List<Product> productList = productService.getAllProduct();
    if(productList.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(productList, HttpStatus.OK);
  }
  @PostMapping("/add")
  public ResponseEntity<Product> addProduct (@RequestBody Product product) {
    try {
      productService.addProduct(product);
      return ResponseEntity.ok(product);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @PutMapping ("/update")
  public ResponseEntity<Product> updateProduct (@RequestParam("id") Long id,@RequestBody Product product) {
    try {
      productService.updateProduct(id,product);
      return ResponseEntity.ok(product);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
  }
  @GetMapping("/get-by-id/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable("id") Long id){
    Optional<Product> product = productService.getProductById(id);
    if (product.isPresent()){
      return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
