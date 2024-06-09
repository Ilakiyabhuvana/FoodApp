package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Payment;

public interface Payrepo extends JpaRepository<Payment, Integer> {

}
