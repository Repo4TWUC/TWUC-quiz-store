package com.twuc.twucstore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author gaarahan
 */

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_order")
public class OrderDto {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private Integer count;

  private String unit;

  @ManyToOne
  private ProductDto productDto;
}

