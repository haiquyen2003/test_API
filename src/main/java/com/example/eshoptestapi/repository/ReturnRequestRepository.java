package com.example.eshoptestapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.eshoptestapi.entity.ReturnRequest;

@Repository
public interface ReturnRequestRepository extends JpaRepository<ReturnRequest, Long>{
    @Query(value = "SELECT r FROM ReturnRequest r WHERE r.id = :id")
    ReturnRequest getReturnRequestbyID(@Param("id") Long id);
    @Query(value = "SELECT r FROM ReturnRequest r WHERE r.order.id = :orderId")
    List<ReturnRequest> findByOrderId(@Param("orderId") Long orderId); 
    @Query(value = "SELECT r FROM ReturnRequest r WHERE r.product.id = :productId")
    List<ReturnRequest> findByProductId(@Param("productId") Long productId);
    @Query(value = "SELECT r FROM ReturnRequest r WHERE r.order.id = :orderId AND r.product.id = :productId")
    List<ReturnRequest> findByOrderIdAndProductId(@Param("orderId") Long orderId, @Param("productId") Long productId);
    @Query(value = "SELECT r FROM ReturnRequest r WHERE r.status = :status")
    List<ReturnRequest> findByStatus(@Param("status") String status);
    @Query(value = "SELECT r FROM ReturnRequest r WHERE r.reason = :reason")
    List<ReturnRequest> findByReason(@Param("reason") String reason);
    @Query(value = "SELECT r FROM ReturnRequest r WHERE r.status = :status AND r.reason = :reason")
    List<ReturnRequest> findByStatusAndReason(@Param("status") String status, @Param("reason") String reason);


}
