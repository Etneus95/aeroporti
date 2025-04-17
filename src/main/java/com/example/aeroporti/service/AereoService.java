package com.example.aeroporti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aeroporti.dao.AereoRepository;
import com.example.aeroporti.model.Aereo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AereoService {

    @Autowired
    private AereoRepository aereoRepository;
    
    
    public Optional<Aereo> cercaAereoPerId (String id) {
		return aereoRepository.findById(id);
	}
    
    public List<Aereo> cercaAerei() {
        // Recupera tutti gli aerei
        return aereoRepository.findAll();
    }
    
    // Ottieni gli aerei con capienza sufficiente per passeggeri e merci
    public List<Aereo> getAereiCapienti(int passeggeri, int merci) {
    // Recupera tutti gli aerei
    List<Aereo> aerei = aereoRepository.findAll();
    
    // Filtra quelli con capienza sufficiente
    return aerei.stream()
            .filter(aereo -> aereo.getNumPass() >= passeggeri && aereo.getQtaMerci() >= merci)
            .collect(Collectors.toList());
    }
}