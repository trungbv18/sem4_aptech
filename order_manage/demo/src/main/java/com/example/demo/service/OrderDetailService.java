package com.example.demo.service;

import com.example.demo.model.OrderDetail;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OrderDetailService {
//  List<OrderProduct> getAll();
  void add(OrderDetail order);
//  boolean delete (Long id);
//  OrderProduct update (Long id, OrderProduct order);
  Optional<OrderDetail> getById(Long id);
  Optional<List<OrderDetail>> getByOrderId(Long orderId);

}
