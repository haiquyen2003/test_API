package com.example.eshoptestapi.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.eshoptestapi.entity.Orders;


@Repository
public interface OrderRepository extends JpaRepository<Orders, Long>{
    @Query(value = "SELECT o FROM Orders o WHERE o.id = :id")
    Orders getOrderbyID(Long id);
    @Query(value = "SELECT o FROM Orders o WHERE o.user.id = :userId")
    Orders findByUserId(Long userId);
    @Query(value = "SELECT o FROM Orders o WHERE o.orderNumber = :orderNumber")
    Orders findByOrderNumber(String orderNumber);
    @Query(value = "SELECT o FROM Orders o WHERE o.status = :status")
    Orders findByStatus(String status);
    @Modifying
    @Query(value = "DELETE FROM Orders o WHERE o.id = :id")
    void deleteById(Long id);
    @Modifying
    @Query(value = "UPDATE Orders o SET o.status = :status WHERE o.id = :id")
    void changeStatus(String status, Long id);
    @Query(value = "SELECT o.subtotal FROM Orders o WHERE o.id = :id")
    Double getSubtotal(Long id);
    @Query(value = "SELECT o.totalAmount FROM Orders o WHERE o.id = :id")
    Double getTotalAmount(Long id);
    @Query(value = "SELECT o.totalQuantity FROM Orders o WHERE o.id = :id")
    Integer getTotalQuantity(Long id);
    @Modifying
    @Query(value = "UPDATE Orders o SET o.totalQuantity = :totalQuantity WHERE o.id = :id")
    void changeTotalQuantity(Integer totalQuantity, Long id);
    

}