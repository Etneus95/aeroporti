package com.example.aeroporti.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.aeroporti.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Optional<Admin> findByUsernameAndPassword(String username, String password);
}