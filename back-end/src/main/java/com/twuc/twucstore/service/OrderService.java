package com.twuc.twucstore.service;

import com.twuc.twucstore.domain.Order;
import com.twuc.twucstore.dto.OrderDto;
import com.twuc.twucstore.repository.OrderRepository;
import com.twuc.twucstore.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
  OrderRepository orderRepository;
  ProductRepository productRepository;
  ModelMapper modelMapper;

  public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
    this.orderRepository = orderRepository;
    this.productRepository = productRepository;
  }

  public List<Order> getList() {
    return this.orderRepository.findAll()
        .stream().map(orderDto -> new Order(orderDto.getId(), orderDto.getProductDto().getId(), orderDto.getCount()))
        .collect(Collectors.toList());
  }

  public Integer add(Order order) {
    OrderDto newOrder = modelMapper.map(order, OrderDto.class);
    newOrder.setProductDto(this.productRepository.findById(order.getProductId()).get());
    newOrder = this.orderRepository.save(newOrder);
    return newOrder.getId();
  }

  public void deleteById(Integer id) {
    if (id == null) {
      throw new RuntimeException("invalid id");
    }
    this.orderRepository.deleteById(id);
  }
}
