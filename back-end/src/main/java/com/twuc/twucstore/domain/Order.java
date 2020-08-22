package com.twuc.twucstore.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
  private Integer id;
  private List<OrderItem> orderItems;
}
