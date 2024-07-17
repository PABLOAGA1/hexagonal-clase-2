package com.example.application;

import com.example.domain.model.Order;
import com.example.domain.model.OrderItem;
import com.example.adapters.out.persistent.OrderRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class OrderService {

    OrderRepository orderRepository;

    public OrderService() {
    }

    @Inject
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public Order createOrder(Order order){
        this.orderRepository.persist(order);
        return order;
    }
    @Transactional
    public void addItemToOrder(Long orderId,OrderItem orderItem){
        Order orderFind = this.orderRepository.findById(orderId);
        if (orderFind!=null) {
            orderFind.addItem(orderItem);
            orderRepository.persist(orderFind);
        }

    }

    @Transactional
    public void updateOrderSatatur(Long orderId,String statusNew){
        Order orderFind = this.orderRepository.findById(orderId);
        if (orderFind!=null) {
            orderFind.updateStatus(statusNew);
            orderRepository.persist(orderFind);
        }

    }

    @Transactional
    public List<Order> getAllOrders(){

        return orderRepository.listAll();
    }

    @Transactional
    public Order findOrderById(Long orderId){

        return this.orderRepository.findById(orderId);
    }


}
