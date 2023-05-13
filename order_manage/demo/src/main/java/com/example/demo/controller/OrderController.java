package com.example.demo.controller;

import com.example.demo.dto.OrderDto;
import com.example.demo.dto.OrderProductDTO;
import com.example.demo.model.OrderDetail;
import com.example.demo.model.OrderProduct;
import com.example.demo.model.Product;
import com.example.demo.service.CustomerService;
import com.example.demo.service.OrderDetailService;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/api/order")
@CrossOrigin(value = "*", maxAge = 3600)
public class OrderController {
  private final OrderService orderService;
  private final OrderDetailService orderDetailService;
  private final CustomerService customerService;
  private final ProductService productService;
  @Autowired
  public OrderController(OrderService orderService, OrderDetailService orderDetailService,
                         CustomerService customerService, ProductService productService) {
    this.orderDetailService = orderDetailService;
    this.orderService = orderService;
    this.customerService = customerService;
    this.productService = productService;
  }
  @GetMapping("/get-all")
  public ResponseEntity<List<OrderDto>> getAll() {
    List<OrderProduct> listOrder = orderService.getAll();
    List<OrderDto> orderDtos = new ArrayList<>();
    if(listOrder.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    else {
      listOrder.forEach( item -> {
        AtomicReference<Double> totalAll = new AtomicReference<>(0.0);
        OrderDto orderDto = new OrderDto();
        orderDto.setCode(item.getCode());
        orderDto.setId(item.getId());
        //c1 : neu ko tim thay se chet code tai day do dong nay chua check null
//        orderDto.setCustomer(customerService.getById(item.getCustomerId()).get());
        //c2
        customerService.getById(item.getCustomerId())
                .ifPresent(orderDto::setCustomer);

        //c1 : ko dung if present
//        Optional<List<OrderDetail>> orderDetail = orderDetailService.getByOrderId(item.getId());
//        if (orderDetail.isPresent()) {
//          List<Product> products = new ArrayList<>();
//          orderDetail.get().forEach(itemDetail -> {
//          Optional<Product> product = productService.getProductById(itemDetail.getProductId());
//          if (product.isPresent()) {
//            product.get().setQuantity(itemDetail.getQuantity());
//            products.add(product.get());
//          }
//          });
//          orderDto.setProduct(products);
//        }

        //c2 : dung if present

        Optional<List<OrderDetail>> orderDetail = orderDetailService.getByOrderId(item.getId());
        orderDetail.ifPresent(orderDetails -> {
          List<Product> products = new ArrayList<>();
          orderDetails.forEach(itemDetail -> {
//            BigDecimal total = new BigDecimal(0);
//            double total = 0;
            Optional<Product> product = productService.getProductById(itemDetail.getProductId());
//          if (product.isPresent()) {
//            product.get().setQuantity(itemDetail.getQuantity());
//            products.add(product.get());
//          }
            product.ifPresent(p -> {
              double total = p.getPrice() * itemDetail.getQuantity();
              totalAll.updateAndGet(currentTotal -> currentTotal + total);
              p.setQuantity(itemDetail.getQuantity());
              products.add(p);
            });
          });
          orderDto.setProduct(products);
          orderDto.setTotalPrice(totalAll.get());
        });


//        orderDetail.ifPresent(orderDetailList -> orderDetailList.forEach(orderDetailItem -> {
//          Optional<Product> product = productService.getProductById(orderDetailItem.getProductId());
//          product.ifPresent(orderDto::setProduct);
//        }) );
        orderDtos.add(orderDto);
      });
    }
    return new ResponseEntity<>(orderDtos, HttpStatus.OK);
  }

  @PostMapping("/add")
  public ResponseEntity<OrderProduct> add (@RequestBody OrderProductDTO orderProductDTO) {
    try {
      OrderProduct orderProduct = new OrderProduct();
      orderProduct.setCustomerId(orderProductDTO.getOrderProduct().getCustomerId());
      orderProduct.setCode(orderProductDTO.getOrderProduct().getCode());
      orderService.add(orderProduct);
      long orderProductId = orderProduct.getId();

      orderProductDTO.getProductOrder().forEach( item -> {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId(item.getId());
        orderDetail.setQuantity(item.getQuantity());
        orderDetail.setOrderId(orderProductId);
        orderDetailService.add(orderDetail);
      });
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

  @DeleteMapping("/delete")
  public ResponseEntity<OrderProduct> delete(@RequestParam("id") long id) {
    boolean bl = orderService.delete(id);
    if (bl) {
      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

}
