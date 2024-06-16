package com.example.eshoptestapi.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "return_request")
public class ReturnRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long returnRequestID;

    @Column(name = "request_date")
    private Timestamp requestDate;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "reason", columnDefinition = "TEXT")
    private String reason;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;
    @ManyToOne
@JoinColumn(name = "product_id") 
private Product product;

    // Getters and Setters

    public Long getReturnRequestID() {
        return returnRequestID;
    }

    public void setReturnRequestID(Long returnRequestID) {
        this.returnRequestID = returnRequestID;
    }

    public Timestamp getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Timestamp requestDate) {
        this.requestDate = requestDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }
    
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
}
