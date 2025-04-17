package com.example.aeroporti.service;

import com.example.aeroporti.dao.VoloRepository;
import com.example.aeroporti.model.Volo;
import com.example.aeroporti.utils.DateTimeFormatUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class VoloService {

	@Autowired
	VoloRepository voloRepository;
	
	public List<Volo> cercaVoliDisponibili (String aeroportoPartenza, String aeroportoArrivo, String dataPartenza, int qtaBagagli) {
		
		// Converto la data nel formato 'yyyy-MM-dd' prima di passarla al servizio
        DateTimeFormatter formatterInput = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatterOutput = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(dataPartenza, formatterInput);
        String dataFormattata = localDate.format(formatterOutput);
		
		return voloRepository.cercaVoliDisponibili(aeroportoPartenza, aeroportoArrivo, dataFormattata, qtaBagagli);
	}
	
	public Optional<Volo> cercaVoloPerId (int id) {
		return voloRepository.findById(id);
	}
	
	public void salvaVolo(Volo volo) {
        voloRepository.save(volo);  // Questo metodo salva il volo, o lo aggiorna se già esistente
    }
	
	public void eliminaVolo(Volo volo) {
		voloRepository.delete(volo);
	}
	
	public boolean prenota(int idVolo, int pesoBagaglio) {
	    Optional<Volo> optionalVolo = voloRepository.findById(idVolo);

	    if (optionalVolo.isPresent()) {
	        Volo volo = optionalVolo.get();

	        int posti_liberi = volo.getTipoAereo().getNumPass() - volo.getPasseggeri();
	        int peso_libero = volo.getTipoAereo().getQtaMerci() - volo.getMerci();
	        if (posti_liberi > 0 && peso_libero > pesoBagaglio) {
	            volo.setPasseggeri(volo.getPasseggeri() + 1);
	            volo.setMerci(volo.getMerci() + pesoBagaglio);

	            voloRepository.save(volo);
	            return true;
	        }
	    }

	    return false;
	}
	
	
	public List<Volo> getVoliFuturi() {
		LocalDateTime now = LocalDateTime.now();
		// Define the format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		// Format the current date and time
		String data = now.format(formatter);
		
		data = "2019-01-01 12:00:00"; // DATA DI PROVA
		List<Volo> result =  voloRepository.getVoliFuturi(data);
		result.forEach(volo -> {
            String formatted = DateTimeFormatUtil.formatVoloDate(volo.getOraPartenza());
            volo.setOraPartenza(formatted);
            formatted = DateTimeFormatUtil.formatVoloDate(volo.getOraArrivo());
            volo.setOraArrivo(formatted);
			});
		return result;
	}
}
