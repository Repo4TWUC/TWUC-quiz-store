package com.twuc.twucstore.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
  private Integer id;
  private Integer productId;
  private Integer count;
}
