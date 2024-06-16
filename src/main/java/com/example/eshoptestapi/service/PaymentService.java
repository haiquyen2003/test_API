package com.example.eshoptestapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eshoptestapi.entity.Payment;
import com.example.eshoptestapi.repository.PaymentRepository;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Payment updatePayment(Long id, Payment paymentDetails) {
        Payment payment = paymentRepository.findById(id).orElse(null);
        if (payment != null) {
            payment.setPaymentType(paymentDetails.getPaymentType());
            payment.setPaymentStatus(paymentDetails.getPaymentStatus());
            return paymentRepository.save(payment);
        } else {
            return null;
        }
    }

    public void deletePayment(Long id) {
        if (paymentRepository.existsById(id)) {
            paymentRepository.deleteById(id);
        }
    }

    
    
}
