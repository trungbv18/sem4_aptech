package com.example.demo.service;

import com.example.demo.model.OrderProduct;
import com.example.demo.model.Product;
import com.example.demo.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{
    private final OrderRepo orderRepo;
    @Autowired
    public OrderServiceImpl(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }
    @Override
    public List<OrderProduct> getAll() {
        return orderRepo.findAll();
    }

    @Override
    public void add(OrderProduct order) {
//        OrderProduct orderProduct = new OrderProduct();
//        orderProduct.setCode(order.getCode());
//        orderProduct.setProducts(order.getProducts());
//        orderProduct.setCustomer(order.getCustomer());
        orderRepo.save(order);
    }

    @Override
    public boolean delete(Long id) {
        Optional<OrderProduct> orderProduct = orderRepo.findById(id);
        if (orderProduct.isPresent()) {
            orderRepo.deleteById(id);
            return true;
        }
        return false;
    }
    @Override
    public OrderProduct update(Long id, OrderProduct orderInput) {
        Optional<OrderProduct> orderProduct = orderRepo.findById(id);
        if (orderProduct.isPresent()){
            OrderProduct _orderProduct = orderProduct.get();
            _orderProduct.setCode(orderInput.getCode());
//            _orderProduct.setProducts(orderInput.getProducts());
            return orderRepo.save(_orderProduct);
        }
        return null;
    }

    @Override
    public Optional<OrderProduct> getById(Long id) {
        return orderRepo.findById(id);
    }
}
