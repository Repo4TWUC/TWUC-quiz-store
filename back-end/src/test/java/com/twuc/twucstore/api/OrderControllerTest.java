package com.twuc.twucstore.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twuc.twucstore.domain.Order;
import com.twuc.twucstore.domain.Product;
import com.twuc.twucstore.dto.OrderDto;
import com.twuc.twucstore.dto.ProductDto;
import com.twuc.twucstore.exception.TsExceptionHandler;
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
  OrderService orderService;

  ModelMapper modelMapper;
  ObjectMapper objectMapper;

  private Order initOrder;
  private OrderDto initOrderDto;

  @BeforeEach
  void setUp() {
    this.mockMvc = MockMvcBuilders.standaloneSetup(new OrderController(this.orderRepository, this.productRepository))
        .setControllerAdvice(new TsExceptionHandler())
        .build();

    this.modelMapper = new ModelMapper();
    this.objectMapper = new ObjectMapper();

    Product product = new Product(0, "可乐", 100, "瓶");
    ProductDto productDto = modelMapper.map(product, ProductDto.class);
    productDto = this.productRepository.save(productDto);

    initOrder = new Order(1, productDto.getId(), 100);
    this.initOrderDto = this.modelMapper.map(initOrder, OrderDto.class);
    this.initOrderDto.setProductDto(productDto);
    this.initOrderDto = this.orderRepository.save(this.initOrderDto);
  }

  @Test
  void couldGetOrderList() throws Exception {
    this.mockMvc.perform(get("/ts/order"))
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].count", is(this.initOrder.getCount())))
        .andExpect(jsonPath("$[0].productId", is(this.initOrder.getProductId())))
        .andExpect(status().isOk());
  }

  @Test
  void couldAddNewOrder() throws Exception {
    this.orderRepository.deleteAll();
    String userJson = objectMapper.writeValueAsString(initOrder);

    this.mockMvc.perform(post("/ts/order").contentType(MediaType.APPLICATION_JSON)
        .characterEncoding("utf-8")
        .content(userJson)
    )
        .andDo(print())
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
