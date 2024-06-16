package com.example.eshoptestapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.eshoptestapi.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> 
{
    @Query(value = "SELECT p FROM Payment p")
    List<Payment> getAllPayments();
    @Query(value = "SELECT p FROM Payment p WHERE p.id = :id")
    Payment getPaymentById(@Param("id") Long id);
    @Modifying
    @Query(value = "DELETE FROM Payment p WHERE p.id = :id")
    void deleteById(@Param("id") Long id);
    
    

    
}

