package com.example.aeroporti.service;

import com.example.aeroporti.dao.AeroportoRepository;
import com.example.aeroporti.model.Aeroporto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AeroportoService {

	@Autowired
	AeroportoRepository aeroportoRepository;
	
	public List<Aeroporto> cercaAeroporti () {
		return aeroportoRepository.findValidAirports();
	}
	
	public Optional<Aeroporto> cercaAeroportoPerId (int id) {
		return aeroportoRepository.findById(id);
	}
}
