package com.twuc.twucstore.repository;

import com.twuc.twucstore.dto.OrderDto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<OrderDto, Integer> {
  @Override
  List<OrderDto> findAll();
}
