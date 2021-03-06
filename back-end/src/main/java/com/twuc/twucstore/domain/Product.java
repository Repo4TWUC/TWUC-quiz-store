package com.twuc.twucstore.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
  private Integer id;
  private String name;
  private int price;
  private String unit;
}
