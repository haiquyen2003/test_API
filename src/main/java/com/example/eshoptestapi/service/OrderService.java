package com.example.eshoptestapi.service;
//order service
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.eshoptestapi.entity.Orders;
import com.example.eshoptestapi.repository.OrderRepository;

@Service
public class OrderService{
    @Autowired
    private OrderRepository orderRepository;
    public Orders getOrderById(Long id) {
        return orderRepository.getOrderbyID(id);
    }
    public Orders getOrderByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }
    public Orders getOrderByOrderNumber(String orderNumber) {
        return orderRepository.findByOrderNumber(orderNumber);
    }
    public Orders getOrderByStatus(String status) {
        return orderRepository.findByStatus(status);
    }
    public Orders createOrder(Orders order) {
        return orderRepository.save(order);
    }
    public Orders updateOrder(Orders order) {
        return orderRepository.save(order);
    }
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }
    public void changeStatus(String status, Long id) {
        orderRepository.changeStatus(status, id);
    }
    public Double getSubtotal(Long id) {
        return orderRepository.getSubtotal(id);
    }
    public Double getTotalAmount(Long id) {
        return orderRepository.getTotalAmount(id);
    }
    public Integer getTotalQuantity(Long id) {
        return orderRepository.getTotalQuantity(id);
    }
    public void changeTotalQuantity(Integer totalQuantity, Long id) {
        orderRepository.changeTotalQuantity(totalQuantity, id);
    }
    
}