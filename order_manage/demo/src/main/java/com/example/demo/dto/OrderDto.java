package com.example.demo.dto;

import com.example.demo.model.Customer;
import com.example.demo.model.Product;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class OrderDto {
  private long id;
  private String code;
  private Customer customer;
  private List<Product> product;
  private double totalPrice;

}
