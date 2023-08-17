package com.example.Reto4SpringBootMongoDB.Repository;

import com.example.Reto4SpringBootMongoDB.Entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public interface OrderRepository extends MongoRepository<Order, Integer>{

    List<Order> findAll();

    List<Order> findAllBySalesMan_Id(int salesmanId);

    List<Order> findAllByStatusAndSalesMan_Id(String status, int salesmanId);



    List<Order> findAllByRegisterDay(String date);
}

