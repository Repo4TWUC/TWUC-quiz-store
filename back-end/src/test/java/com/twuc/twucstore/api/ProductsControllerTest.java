package com.twuc.twucstore.api;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twuc.twucstore.domain.Product;
import com.twuc.twucstore.dto.ProductDto;
import com.twuc.twucstore.repository.ProductRepository;
import com.twuc.twucstore.service.ProductService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@SpringBootTest
@AutoConfigureMockMvc
class ProductsControllerTest {

  MockMvc mockMvc;
  @Autowired
  ProductRepository productRepository;
  @Autowired
  ProductService productService;

  ModelMapper modelMapper;
  ObjectMapper objectMapper;

  Product initProduct;
  private ProductDto initProductDto;

  @BeforeEach
  void setUp() {
    this.mockMvc = MockMvcBuilders.standaloneSetup(new ProductsController(this.productRepository))
        .build();

    this.modelMapper = new ModelMapper();
    this.objectMapper = new ObjectMapper();

    initProduct = new Product(0, "可乐", 100, "瓶");
    this.initProductDto = this.modelMapper.map(initProduct, ProductDto.class);
    this.initProductDto = this.productRepository.save(this.initProductDto);
  }

  @Test
  void couldGetProductsList() throws Exception {
    this.mockMvc.perform(get("/ts/product"))
        .andDo(print())
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].name", is(this.initProductDto.getName())))
        .andExpect(jsonPath("$[0].price", is(this.initProductDto.getPrice())))
        .andExpect(status().isOk());
  }

  @Test
  void couldAddNewProduct() throws Exception {
    this.productRepository.deleteAll();
    String userJson = objectMapper.writeValueAsString(initProduct);

    this.mockMvc.perform(
        post("/ts/product")
            .contentType(MediaType.APPLICATION_JSON)
            .content(userJson)
    )
        .andExpect(status().isCreated());

    assertEquals(this.productRepository.findAll().size(), 1);
  }
}