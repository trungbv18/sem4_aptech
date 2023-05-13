package com.example.demo.repository;

import com.example.demo.model.OrderDetail;
import com.example.demo.model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDetailRepo extends JpaRepository<OrderDetail, Long> {
  Optional<List<OrderDetail>> findOrderDetailByOrderId(Long id);

//  @Query("Select * from OrderDetail od " +
//          " join OrderProduct op on od.orderId = op.id " +
//          " where op.id = :id ")
//  Optional<OrderDetail> getListOrderDetailByOrderId(Long id);
}
