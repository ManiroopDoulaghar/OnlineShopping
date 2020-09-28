package com.onlineshopping.onlineshopping.controller;
import com.onlineshopping.onlineshopping.model.Category;
import com.onlineshopping.onlineshopping.model.Order;
import com.onlineshopping.onlineshopping.model.Product;
import com.onlineshopping.onlineshopping.repository.OrderRepository;
import com.onlineshopping.onlineshopping.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    private OrderRepository orderRepository;
    private OrderService orderService;

    public OrderController(OrderRepository orderRepository, OrderService orderService) {
        this.orderRepository = orderRepository;
        this.orderService = orderService;
    }

    @PostMapping("/order/create")
    public ResponseEntity<Object> createOrder(@RequestBody Order order) {
        return  orderService.addOrder(order);
    }

    @GetMapping("/order/details/{id}")
    public Order getOrders(@PathVariable Long id) {
        if(orderRepository.findById(id).isPresent())
            return orderRepository.findById(id).get();
        else return  null;
    }
}
