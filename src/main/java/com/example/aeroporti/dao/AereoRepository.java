package com.example.aeroporti.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.aeroporti.model.Aereo;

@Repository
public interface AereoRepository extends JpaRepository<Aereo, String> {
	
	List<Aereo> findAll();

}