package com.twuc.twucstore.api;

import com.twuc.twucstore.domain.Order;
import com.twuc.twucstore.repository.OrderItemRepository;
import com.twuc.twucstore.repository.OrderRepository;
import com.twuc.twucstore.repository.ProductRepository;
import com.twuc.twucstore.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class OrderController {
  OrderService orderService;

  OrderController(OrderRepository orderRepository, ProductRepository productRepository, OrderItemRepository orderItemRepository) {
    this.orderService = new OrderService(orderRepository, productRepository, orderItemRepository);
  }

  @GetMapping("/ts/order")
  public List<Order> getOrderList() {
    return this.orderService.getList();
  }

  @PostMapping("/ts/order")
  public ResponseEntity<Object> addNewOrder (@RequestBody Order order) {
    Integer id = this.orderService.add(order);
    return ResponseEntity.created(URI.create("/ts/order/" + id))
    .body(this.orderService.getList());
  }

  @DeleteMapping("/ts/order/{id}")
  public void deleteOrderById (@PathVariable Integer id) {
    this.orderService.deleteById(id);
  }
}
