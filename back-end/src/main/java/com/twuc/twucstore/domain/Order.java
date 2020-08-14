package com.twuc.twucstore.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
  private Integer id;
  private Integer productId;
  private Integer count;

  public Order(Integer productId, Integer count) {
    this.productId = productId;
    this.count = count;
  }
}
