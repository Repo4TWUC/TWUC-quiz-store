package com.twuc.twucstore.api;

import com.twuc.twucstore.domain.Product;
import com.twuc.twucstore.repository.ProductRepository;
import com.twuc.twucstore.service.ProductService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class ProductsController {
  ProductService productService;

  ProductsController (ProductRepository productRepository) {
    this.productService = new ProductService(productRepository);
  }

  @GetMapping("/ts/product")
  public List<Product> getProductsList (@RequestParam(required = false) Integer id) {
    return this.productService.getList(id);
  }

  @PostMapping("/ts/product")
  public ResponseEntity<List<Product>> addNewProduct (@RequestBody Product product) {
    Integer id = this.productService.add(product);
    return ResponseEntity.created(URI.create("/ts/product/" + id))
        .body(this.productService.getList(null));
  }
}
