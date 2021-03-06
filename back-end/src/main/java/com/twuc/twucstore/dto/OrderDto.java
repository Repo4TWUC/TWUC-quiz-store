package com.twuc.twucstore.dto;

import com.twuc.twucstore.domain.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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
  private Integer id;

  @OneToMany(mappedBy = "orderDto", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<OrderItemDto> orderItems;
}
