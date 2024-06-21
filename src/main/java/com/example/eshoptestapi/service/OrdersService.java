package com.example.eshoptestapi.service;

import com.example.eshoptestapi.dto.Orders.OrdersDTO;
import com.example.eshoptestapi.entity.Orders;
import com.example.eshoptestapi.entity.User;
import com.example.eshoptestapi.repository.OrdersRepository;
import com.example.eshoptestapi.repository.ProductRepository;
import com.example.eshoptestapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    public List<OrdersDTO> getAllOrders() {
        return ordersRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<OrdersDTO> getOrdersByUserId(Long userId) {
        List<Orders> orders = ordersRepository.findByUserId(userId);
        return orders.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<OrdersDTO> getOrderById(Long id) {
        Optional<Orders> orders = ordersRepository.findById(id);
        return orders.map(this::convertToDTO);
    }

    public OrdersDTO addOrder(OrdersDTO ordersDTO) {
        Orders order = convertToEntity(ordersDTO);
        order.setStatus("Processing"); // Đặt giá trị mặc định cho status
        Orders savedOrder = ordersRepository.save(order);
        return convertToDTO(savedOrder);
    }

    public OrdersDTO updateOrderStatus(Long id, String status) {
        Optional<Orders> orderOptional = ordersRepository.findById(id);
        if (orderOptional.isPresent()) {
            Orders order = orderOptional.get();
            order.setStatus(status);
            Orders updatedOrder = ordersRepository.save(order);
            return convertToDTO(updatedOrder);
        } else {
            throw new RuntimeException("Order not found");
        }
    }

    public void deleteOrder(Long id) {
        ordersRepository.deleteById(id);
    }

    private OrdersDTO convertToDTO(Orders order) {
        OrdersDTO ordersDTO = new OrdersDTO();
        ordersDTO.setId(order.getId());
        ordersDTO.setOrderNumber(order.getOrderNumber());
        ordersDTO.setUserId(order.getUser().getId());
        ordersDTO.setSubtotal(order.getSubtotal());
        ordersDTO.setTotalAmount(order.getTotalAmount());
        ordersDTO.setTotalQuantity(order.getTotalQuantity());
        ordersDTO.setPaymentMethod(order.getPaymentMethod());
        ordersDTO.setStatus(order.getStatus());
        ordersDTO.setTel(order.getTel());
        ordersDTO.setAddress(order.getAddress());
        ordersDTO.setCreatedAt(order.getCreatedAt());
        return ordersDTO;
    }

    private Orders convertToEntity(OrdersDTO ordersDTO) {
        Orders order = new Orders();
        if(ordersDTO.getId()!= null){
            order.setId(ordersDTO.getId());
        }
        order.setOrderNumber(ordersDTO.getOrderNumber());
        User user = userRepository.findById(ordersDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        order.setUser(user);
        order.setSubtotal(ordersDTO.getSubtotal());
        order.setTotalAmount(ordersDTO.getTotalAmount());
        order.setTotalQuantity(ordersDTO.getTotalQuantity());
        order.setPaymentMethod(ordersDTO.getPaymentMethod());
        order.setTel(ordersDTO.getTel());
        order.setAddress(ordersDTO.getAddress());
        order.setCreatedAt(ordersDTO.getCreatedAt());
        return order;
    }
}
