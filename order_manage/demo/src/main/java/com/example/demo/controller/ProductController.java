package com.example.demo.controller;

import com.example.demo.error.ErrorMessage;
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
import java.util.stream.Collectors;

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
  public ResponseEntity<?> addProduct (@RequestBody Product product) {
      List<Product> productList = productService.getAllProduct().stream().filter(p ->p.getCode().equals(product.getCode())).collect(Collectors.toList());
      if (productList.isEmpty()) {
        productService.addProduct(product);
        return ResponseEntity.ok(product);
      }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ma san pham bi trung");
  }
  @PutMapping ("/update")
  public ResponseEntity<Object> updateProduct (@RequestParam("id") Long id,@RequestBody Product product) {
      Product productReturn = productService.updateProduct(id,product);
      if (productReturn != null) return ResponseEntity.ok(product);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("khong tim thay san pham");
  }
  @GetMapping("/get-by-id/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable("id") Long id){
    Optional<Product> product = productService.getProductById(id);
    return product.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }
}
