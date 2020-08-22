package com.twuc.twucstore.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.twuc.twucstore.domain.Order;
import com.twuc.twucstore.domain.OrderItem;
import com.twuc.twucstore.dto.OrderDto;
import com.twuc.twucstore.dto.OrderItemDto;
import com.twuc.twucstore.repository.OrderItemRepository;
import com.twuc.twucstore.repository.OrderRepository;
import com.twuc.twucstore.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
  OrderRepository orderRepository;
  ProductRepository productRepository;
  ModelMapper modelMapper;

  public OrderService(OrderRepository orderRepository, ProductRepository productRepository, OrderItemRepository orderItemRepository) {
    this.orderRepository = orderRepository;
    this.productRepository = productRepository;
    this.modelMapper = new ModelMapper();
  }

  public List<Order> getList() {
    List<Order> list = new ArrayList<>();
    for (OrderDto orderDto : this.orderRepository.findAll()) {
      List<OrderItem> orderItems = new ArrayList<>();
      for (OrderItemDto item : orderDto.getOrderItems()) {
        orderItems.add(new OrderItem(item.getId(), item.getProductId(), item.getCount()));
      }
      Order newOrder = new Order(orderDto.getId(), orderItems);
      list.add(newOrder);
    }
    return list;
  }

  public Integer add(Order order) {
    OrderDto newOrder = modelMapper.map(order, OrderDto.class);
    return this.orderRepository.save(newOrder).getId();
  }

  public void deleteById(Integer id) {
    if (id == null) {
      throw new RuntimeException("invalid id");
    }
    this.orderRepository.deleteById(id);
  }
}
