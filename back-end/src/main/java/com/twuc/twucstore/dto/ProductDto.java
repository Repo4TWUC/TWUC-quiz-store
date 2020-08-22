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
@Table(name = "product")
public class ProductDto {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;

  private int price;

  private String unit;
}

