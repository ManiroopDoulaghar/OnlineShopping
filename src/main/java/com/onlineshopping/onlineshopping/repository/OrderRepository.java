package com.onlineshopping.onlineshopping.repository;

import com.onlineshopping.onlineshopping.model.Order;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByOrderDateBetweenOrderByProductId(Date startDate, Date endDate);
    List<Order> findAllByOrderDateOrderByProductId(Date date);
    List<Order> findAllByCustomerIdOrderByCustomerId(Long id);

}
