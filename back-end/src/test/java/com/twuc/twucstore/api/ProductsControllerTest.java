package com.twuc.twucstore.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twuc.twucstore.domain.Product;
import com.twuc.twucstore.dto.ProductDto;
import com.twuc.twucstore.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@SpringBootTest
@AutoConfigureMockMvc
class ProductsControllerTest {

  @Autowired
  MockMvc mockMvc;
  @Autowired
  ProductRepository productRepository;

  ModelMapper modelMapper;
  ObjectMapper objectMapper;

  Product initProduct;
  private ProductDto initProductDto;

  @BeforeEach
  void setUp() {
    this.modelMapper = new ModelMapper();
    this.objectMapper = new ObjectMapper();

    initProduct = new Product();
    this.initProductDto = this.modelMapper.map(initProduct, ProductDto.class);
    this.initProductDto = this.productRepository.save(this.initProductDto);
  }

  @Test
  void getProductsList() throws Exception {
    this.mockMvc.perform(get("/ts/product"))
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].id", is(this.initProductDto.getId())))
        .andExpect(status().isOk());
  }

  @Test
  void addNewProduct() {
  }
}