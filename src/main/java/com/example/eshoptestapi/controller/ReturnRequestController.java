package com.example.eshoptestapi.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.eshoptestapi.entity.ReturnRequest;
import com.example.eshoptestapi.service.ReturnRequestService;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/return")
public class ReturnRequestController {

    @Autowired
    private ReturnRequestService returnRequestService;

    // GET all return requests
    @GetMapping
    public List<ReturnRequest> getAllReturnRequests() {
        return returnRequestService.getAllReturnRequests();
    }

    // GET return request by ID
    @GetMapping("/{id}")
    public ResponseEntity<ReturnRequest> getReturnRequestById(@PathVariable Long id) {
        ReturnRequest returnRequest = returnRequestService.getReturnRequestById(id);
        if (returnRequest != null) {
            return ResponseEntity.ok(returnRequest);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // GET return request by order ID
    @GetMapping("/order/{orderId}")
    public List<ReturnRequest> getReturnRequestByOrderId(@PathVariable Long orderId) {
        return returnRequestService.getReturnRequestByOrderId(orderId);
    }

    // GET return request by product ID
    @GetMapping("/product/{productId}")
    public List<ReturnRequest> getReturnRequestByProductId(@PathVariable Long productId) {
        return returnRequestService.getReturnRequestByProductId(productId);
    }

    // GET return request by order ID and product ID
    @GetMapping("/order/{orderId}/product/{productId}")
    public List<ReturnRequest> getReturnRequestByOrderIdAndProductId(@PathVariable Long orderId, @PathVariable Long productId) {
        return returnRequestService.getReturnRequestByOrderIdAndProductId(orderId, productId);
    }

    // GET return request by status
    @GetMapping("/status/{status}")
    public List<ReturnRequest> getReturnRequestByStatus(@PathVariable String status) {
        return returnRequestService.getReturnRequestByStatus(status);
    }

    // GET return request by reason
    @GetMapping("/reason/{reason}")
    public List<ReturnRequest> getReturnRequestByReason(@PathVariable String reason) {
        return returnRequestService.getReturnRequestByReason(reason);
    }   
    
}
