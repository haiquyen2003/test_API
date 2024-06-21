package com.example.eshoptestapi.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address", length = 50)
    private String address;

    @Column(name = "avatar", columnDefinition = "TEXT")
    private String avatar;

    @Column(name = "email", unique = true, nullable = false, length = 50)
    private String email;

    @Column(name = "name_user", length = 50)
    private String nameUser;

    @Column(name = "password", nullable = false, length = 200)
    private String password;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "username", unique = true, nullable = false, length = 50)
    private String username;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    // Enum for Role
    public enum Role {
        CUSTOMER,
        ADMIN
    }
    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
