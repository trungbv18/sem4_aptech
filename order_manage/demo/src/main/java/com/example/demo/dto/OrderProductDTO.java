package com.example.demo.dto;

import com.example.demo.model.Customer;
import com.example.demo.model.OrderDetail;
import com.example.demo.model.OrderProduct;
import com.example.demo.model.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderProductDTO {
  private OrderProduct orderProduct;
  private List<ProductOrderDto> productOrder;
}
