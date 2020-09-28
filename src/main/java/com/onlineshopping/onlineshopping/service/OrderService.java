package com.onlineshopping.onlineshopping.service;

import com.onlineshopping.onlineshopping.model.Category;
import com.onlineshopping.onlineshopping.model.Order;
import com.onlineshopping.onlineshopping.model.Product;
import com.onlineshopping.onlineshopping.repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    public OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /*Create a New Order */

   @Transactional
   public ResponseEntity<Object> addOrder(Order order){
       Order newOrder= new Order();
       newOrder.setProductId(order.getProductId());
       newOrder.setCustomerId(order.getCustomerId());
       newOrder.setOrderDate(order.getOrderDate());
       List<Order> orderList = new ArrayList<>();
       orderList.add(newOrder);
       Order savedOrder = orderRepository.save(newOrder);
       if(! orderRepository.findById(savedOrder.getId()).isPresent()){
           return ResponseEntity.unprocessableEntity().body("Category Creation Failed");    }
       else{
           return ResponseEntity.ok("Successfully Created");
       }
   }
}
