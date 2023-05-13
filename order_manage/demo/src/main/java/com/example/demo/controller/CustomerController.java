package com.example.demo.controller;

import com.example.demo.model.Customer;
import com.example.demo.model.Product;
import com.example.demo.service.CustomerService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin(value = "*", maxAge = 3600)
public class CustomerController {
  private final CustomerService customerService;
  @Autowired
  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }
  @GetMapping("/get-all")
  public ResponseEntity<List<Customer>> getAll() {
    List<Customer> lst = customerService.getAll();
    if(lst.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(lst, HttpStatus.OK);
  }
}
