package com.example.demo.controller;

import com.example.demo.model.OrderProduct;
import com.example.demo.model.Product;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order")
public class OrderController {
  private final OrderService orderService;
  @Autowired
  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }
  @GetMapping("/get-all")
  public ResponseEntity<List<OrderProduct>> getAll() {
    List<OrderProduct> listOrder = orderService.getAll();
    if(listOrder.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(listOrder, HttpStatus.OK);
  }
  @PostMapping("/add")
  public ResponseEntity<OrderProduct> add (@RequestBody OrderProduct orderProduct) {
    try {
      orderService.add(orderProduct);
      return ResponseEntity.ok(orderProduct);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @GetMapping("/get-by-id/{id}")
  public ResponseEntity<OrderProduct> getProductById(@PathVariable("id") Long id){
    Optional<OrderProduct> orderProduct = orderService.getById(id);
    return orderProduct.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

}
