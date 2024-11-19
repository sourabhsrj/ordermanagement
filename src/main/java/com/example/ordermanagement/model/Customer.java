package com.example.ordermanagement.model;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String email;
    private String address;
    private String phone;
    
    // Getters and Setters
}
