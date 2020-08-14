package com.twuc.twucstore.service;

import com.twuc.twucstore.domain.Product;
import com.twuc.twucstore.dto.ProductDto;
import com.twuc.twucstore.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
  ProductRepository productRepository;
  ModelMapper modelMapper;

  ProductService () {
    this.modelMapper = new ModelMapper();
  }

  public List<Product> getList(Integer id) {
    return this.productRepository.findAll()
        .stream().map(productDto -> modelMapper.map(productDto, Product.class))
        .collect(Collectors.toList());
  }

  public Integer add(Product product) {
    ProductDto newProduct = this.modelMapper.map(product, ProductDto.class);
    newProduct = this.productRepository.save(newProduct);
    return newProduct.getId();
  }
}
