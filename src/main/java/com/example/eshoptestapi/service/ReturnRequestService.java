package com.example.eshoptestapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eshoptestapi.entity.ReturnRequest;
import com.example.eshoptestapi.repository.ReturnRequestRepository;

@Service
public class ReturnRequestService {
    @Autowired
    private ReturnRequestRepository returnRequestRepository;


    public List<ReturnRequest> getAllReturnRequests() {
        return returnRequestRepository.findAll();
    }
    
    public ReturnRequest getReturnRequestById(Long id) {
        return returnRequestRepository.getReturnRequestbyID(id);
    }

    public List<ReturnRequest> getReturnRequestByOrderId(Long orderId) {
        return returnRequestRepository.findByOrderId(orderId);
    }

    public List<ReturnRequest> getReturnRequestByProductId(Long productId) {
        return returnRequestRepository.findByProductId(productId);
    }

    public List<ReturnRequest> getReturnRequestByOrderIdAndProductId(Long orderId, Long productId) {
        return returnRequestRepository.findByOrderIdAndProductId(orderId, productId);
    }

    public List<ReturnRequest> getReturnRequestByStatus(String status) {
        return returnRequestRepository.findByStatus(status);
    }

    public List<ReturnRequest> getReturnRequestByReason(String reason) {
        return returnRequestRepository.findByReason(reason);
    }

    

}
