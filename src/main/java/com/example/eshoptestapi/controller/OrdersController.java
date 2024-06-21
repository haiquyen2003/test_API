package com.example.eshoptestapi.controller;

import com.example.eshoptestapi.dto.Orders.OrdersDTO;
import com.example.eshoptestapi.entity.User;
import com.example.eshoptestapi.service.OrdersService;
import com.example.eshoptestapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<OrdersDTO>> getAllOrders() {
        List<OrdersDTO> orders = ordersService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/user")
    public ResponseEntity<List<OrdersDTO>> getOrdersByUser(Authentication authentication) {
        Long userId = userService.findByEmail(authentication.getName()).getId();
        List<OrdersDTO> order = ordersService.getOrdersByUserId(userId);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/{id}")
    public ResponseEntity<OrdersDTO> getOrderById(@PathVariable Long id, Authentication authentication) {
        String username = authentication.getName();
        User user = userService.findByEmail(username);

        Optional<OrdersDTO> order = ordersService.getOrderById(id);
        if (order.isPresent() && order.get().getUserId().equals(user.getId())) {
            return new ResponseEntity<>(order.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping
    public ResponseEntity<OrdersDTO> addOrder(@RequestBody OrdersDTO ordersDTO, Authentication authentication) {
        String username = authentication.getName();
        User user = userService.findByEmail(username);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        ordersDTO.setUserId(user.getId());
        OrdersDTO newOrder = ordersService.addOrder(ordersDTO);

        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<OrdersDTO> updateOrderStatus(@PathVariable Long id, @RequestBody OrdersDTO ordersDTO) {
        try {
            OrdersDTO updatedOrder = ordersService.updateOrderStatus(id, ordersDTO.getStatus());
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        ordersService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
