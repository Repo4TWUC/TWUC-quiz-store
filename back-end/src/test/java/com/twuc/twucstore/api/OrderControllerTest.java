package com.twuc.twucstore.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twuc.twucstore.domain.Order;
import com.twuc.twucstore.domain.OrderItem;
import com.twuc.twucstore.domain.Product;
import com.twuc.twucstore.dto.OrderDto;
import com.twuc.twucstore.dto.ProductDto;
import com.twuc.twucstore.exception.TsExceptionHandler;
import com.twuc.twucstore.repository.OrderItemRepository;
import com.twuc.twucstore.repository.OrderRepository;
import com.twuc.twucstore.repository.ProductRepository;
import com.twuc.twucstore.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {
  MockMvc mockMvc;
  @Autowired
  OrderRepository orderRepository;
  @Autowired
  ProductRepository productRepository;
  @Autowired
  OrderItemRepository orderItemRepository;
  @Autowired
  OrderService orderService;

  ModelMapper modelMapper;
  ObjectMapper objectMapper;

  private Order initOrder;
  private OrderDto initOrderDto;

  @BeforeEach
  void setUp() {
    this.orderRepository.deleteAll();
    this.orderItemRepository.deleteAll();
    this.mockMvc = MockMvcBuilders.standaloneSetup(new OrderController(this.orderRepository, this.productRepository, orderItemRepository))
        .build();

    this.modelMapper = new ModelMapper();
    this.objectMapper = new ObjectMapper();

    Product product = new Product(null, "可乐", 100, "瓶");
    ProductDto productDto = modelMapper.map(product, ProductDto.class);
    productDto = this.productRepository.save(productDto);

    OrderItem orderItem = new OrderItem(null, productDto.getId(), 100);
    initOrder = new Order(null, List.of(orderItem));
    initOrderDto = modelMapper.map(initOrder, OrderDto.class);
    initOrderDto = this.orderRepository.save(initOrderDto);
  }

  @Test
  void couldGetOrderList() throws Exception {
    this.mockMvc.perform(get("/ts/order"))
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(status().isOk());
  }

  @Test
  void couldAddNewOrder() throws Exception {
    this.orderItemRepository.deleteAll();
    this.orderRepository.deleteAll();
    String userJson = objectMapper.writeValueAsString(initOrder);

    this.mockMvc.perform(post("/ts/order").contentType(MediaType.APPLICATION_JSON)
        .characterEncoding("utf-8")
        .content(userJson)
    )
        .andExpect(status().isCreated());

    assertEquals(this.orderRepository.findAll().size(), 1);
  }

  @Test
  void couldDeleteOrderById() throws Exception {
    this.mockMvc.perform(
        delete("/ts/order/" + this.initOrderDto.getId())
    )
        .andExpect(status().isOk());

    assertEquals(this.orderRepository.findAll().size(), 0);
  }
}
