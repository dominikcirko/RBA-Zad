package com.example.RBAZadatak.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    @Column(nullable = false)
    private String name;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String oib;
    @Column(nullable = false)
    private Boolean status;

    public User(Long userId, String name, String lastName, String oib, Boolean status) {
        this.userId = userId;
        this.name = name;
        this.lastName = lastName;
        this.oib = oib;
        this.status = status;
    }

    public User() {
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOib() {
        return oib;
    }

    public void setOib(String oib) {
        this.oib = oib;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
