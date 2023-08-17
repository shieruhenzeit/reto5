package com.example.Reto4SpringBootMongoDB.Services;

import com.example.Reto4SpringBootMongoDB.Entity.Order;
import com.example.Reto4SpringBootMongoDB.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersBySalesman(int salesmanId) {
        return orderRepository.findAllBySalesMan_Id(salesmanId);
    }

    public List<Order> getOrdersByStatusAndSalesman(String status, int salesmanId) {
        return orderRepository.findAllByStatusAndSalesMan_Id(status, salesmanId);
    }

    public List<Order> findAllByRegisterDayAndSalesMan_Id(String date, int salesmanId){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        org.springframework.data.mongodb.core.query.Query query = new org.springframework.data.mongodb.core.query.Query();
        Criteria dateCriteria = Criteria.where("registerDay")
                .gte(LocalDate.parse(date, dtf).minusDays(1).atStartOfDay())
                .lt(LocalDate.parse(date, dtf).plusDays(2).atStartOfDay())
                .and("salesMan.id").is(salesmanId);

        query.addCriteria(dateCriteria);
        List<Order> orders = mongoTemplate.find(query, Order.class);

        return orders;
    }

    public List<Order> getOrdersByDate(String date) {
        return orderRepository.findAllByRegisterDay(date);
    }


}
