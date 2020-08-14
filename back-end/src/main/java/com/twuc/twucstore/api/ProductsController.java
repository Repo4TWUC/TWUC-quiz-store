package com.twuc.twucstore.api;

import com.twuc.twucstore.domain.Product;
import com.twuc.twucstore.service.ProductService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;

@RestController
public class ProductsController {
  ProductService productService;

  ProductsController (ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/ts/product")
  public List<Product> getProductsList (@RequestParam(required = false) Integer id) {
    return this.productService.getList(id);
  }

  @PostMapping("/ts/product")
  public ResponseEntity<Object> addNewProduct (@RequestBody Product product) {
    Integer id = this.productService.add(product);
    return ResponseEntity.created(URI.create("/ts/product/" + id)).build();
  }
}
