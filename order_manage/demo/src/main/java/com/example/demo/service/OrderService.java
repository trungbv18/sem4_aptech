package com.example.demo.service;

import com.example.demo.model.OrderProduct;
import com.example.demo.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OrderService {
  List<OrderProduct> getAll();
  void add(OrderProduct order);
  boolean delete (Long id);
  OrderProduct update (Long id, OrderProduct order);
  Optional<OrderProduct> getById(Long id);

}
