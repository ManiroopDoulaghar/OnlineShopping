package com.onlineshopping.onlineshopping.controller;
import com.onlineshopping.onlineshopping.model.Category;
import com.onlineshopping.onlineshopping.model.Order;
import com.onlineshopping.onlineshopping.model.Product;
import com.onlineshopping.onlineshopping.repository.OrderRepository;
import com.onlineshopping.onlineshopping.service.OrderService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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
    public Order getOrderById(@PathVariable Long id) {
        if(orderRepository.findById(id).isPresent())
            return orderRepository.findById(id).get();
        else return  null;
    }

    //Get all orders
    @GetMapping("/order/all")
    public List<Order> getOrders() {
            return orderRepository.findAll();
    }

    //An API endpoint that returns all orders for a customer sorted by date.
    @GetMapping("/order/customer")
    public List<Order> getOrdersByCustomerIdSortedByDate(@RequestParam("customerId") Long id) {
        return orderRepository.findAllByCustomerIdOrderByCustomerId(id);
    }


    //find all orders with given date
    @GetMapping("/order")
    public List<Order> getOrdersByDate(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
        System.out.println(date);
        List<Order> orders =   orderRepository.findAllByOrderDateOrderByProductId(date);
        System.out.println(orders);
        return orders;
    }

    //find all order with range of dates
    @GetMapping("/order/range")
    public List<Order> getOrdersBetweenDates(@RequestParam("toDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate,
                                             @RequestParam("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate){
        List<Order> orders =   orderRepository.findAllByOrderDateBetweenOrderByProductId(toDate, fromDate);
        System.out.println(orders);
        return orders;
    }
}
