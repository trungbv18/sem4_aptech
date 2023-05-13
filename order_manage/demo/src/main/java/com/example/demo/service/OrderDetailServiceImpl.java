package com.example.demo.service;

import com.example.demo.model.OrderDetail;
import com.example.demo.repository.OrderDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{
    private final OrderDetailRepo orderDetailRepo;
    @Autowired
    public OrderDetailServiceImpl(OrderDetailRepo orderDetailRepo) {
        this.orderDetailRepo = orderDetailRepo;
    }

    @Override
    public void add(OrderDetail order) {
        orderDetailRepo.save(order);
    }

    @Override
    public Optional<OrderDetail> getById(Long id) {
        return orderDetailRepo.findById(id);
    }

    @Override
    public Optional<List<OrderDetail>> getByOrderId(Long orderId) {
        return orderDetailRepo.findOrderDetailByOrderId(orderId);
    }
}
