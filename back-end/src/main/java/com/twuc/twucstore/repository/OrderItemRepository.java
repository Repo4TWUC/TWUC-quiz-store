package com.twuc.twucstore.repository;

import com.twuc.twucstore.dto.OrderItemDto;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemRepository extends CrudRepository<OrderItemDto, Integer> {
}
