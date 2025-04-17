package com.example.aeroporti.controller;

import com.example.aeroporti.model.Volo;
import com.example.aeroporti.service.AeroportoService;
import com.example.aeroporti.service.VoloService;
import com.example.aeroporti.utils.DateTimeFormatUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class UserController {
	
	@Autowired
    private VoloService voloService;
	@Autowired
    private AeroportoService aeroportoService;


    // Mostra il form di ricerca voli
    @GetMapping("/cerca")
    public String mostraFormRicerca(Model model) {
        System.out.println("+++++++++++++++++++");
        System.out.println("GET /cerca chiamato");
        System.out.println("+++++++++++++++++++");
        model.addAttribute("aeroporti", aeroportoService.cercaAeroporti());
        return "cerca"; // JSP: cerca.jsp
    }

    
    
    // Elabora la ricerca e reindirizza alla pagina dei risultati
    @PostMapping("/cerca")
    public String elaboraFormRicerca(
            @RequestParam("cittaPartenza") String cittaPartenza,
            @RequestParam("cittaArrivo") String cittaArrivo,
            @RequestParam("data") String data,  // Data in formato "dd-MM-yyyy"
            @RequestParam("pesoBagaglio") int pesoBagaglio,
            Model model) {
    
        // Ricerca voli
        List<Volo> risultati = voloService.cercaVoliDisponibili(cittaPartenza, cittaArrivo, data, pesoBagaglio);
        System.out.println("+++ VOLO TROVATI: " + risultati.size());
        risultati.forEach(v -> System.out.println("   -> " + v));

        // Riformatta l'ora di partenza di ogni volo trovato
        for (Volo volo : risultati) {
            try {
                // Formattazione dell'ora di partenza
            	String formattedOra = DateTimeFormatUtil.formatVoloDate(volo.getOraPartenza());
        	    volo.setOraPartenza(formattedOra); // Sovrascrive con la stringa più leggibile
            } catch (Exception e) {
                System.err.println("Errore parsing oraPartenza per volo: " + volo);
                e.printStackTrace();
            }
        }

        // Passa i risultati e i parametri alla view
        model.addAttribute("risultati", risultati);
        model.addAttribute("cittaPartenza", cittaPartenza);
        model.addAttribute("cittaArrivo", cittaArrivo);
        model.addAttribute("data", data);
        model.addAttribute("pesoBagaglio", pesoBagaglio);

        return "risultati";  // Non un redirect, ma una forward alla pagina dei risultati
    }
    
    // Visualizza la pagina con i risultati
    @GetMapping("/risultati")
    public String mostraRisultatiRicerca(@ModelAttribute("risultati") List<Volo> risultati) {
    	if (risultati == null || risultati.isEmpty()) {
            System.out.println("+++ NESSUN VOLO TROVATO o FLASHATTRIBUTES MANCANTI.");
        } else {
            System.out.println("+++ FLASHATTRIBUTES OK. Dimensione lista: " + risultati.size());
        }
        return "risultati";
    }
    
    @PostMapping("/confermaPrenotazione")
    public String mostraConfermaPrenotazione(
            @RequestParam("idVolo") int idVolo,
            @RequestParam("pesoBagaglio") int pesoBagaglio,
            Model model) {

    	Optional<Volo> voloOpt = voloService.cercaVoloPerId(idVolo);
    	if (voloOpt.isPresent()) {
    	    Volo volo = voloOpt.get();
    	    String formattedOra = DateTimeFormatUtil.formatVoloDate(volo.getOraPartenza());
    	    volo.setOraPartenza(formattedOra); // Sovrascrive con la stringa più leggibile
            model.addAttribute("volo", volo); // Passi direttamente l'oggetto, non l'Optional
    	    model.addAttribute("pesoBagaglio", pesoBagaglio);
    	    return "richiesta-conferma-prenotazione";
    	} else {
    	    // in caso di volo non trovato, potresti reindirizzare a una pagina d’errore
    	    return "erroreVoloNonTrovato"; // o redirect:/qualcosa
    	}	
    }
    
    @PostMapping("/prenota")
    public String prenotaVolo(
            @RequestParam int idVolo,
            @RequestParam int pesoBagaglio,
            RedirectAttributes redirectAttributes) {

        boolean successo = voloService.prenota(idVolo, pesoBagaglio);

        if (successo) {
            String codicePrenotazione = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            redirectAttributes.addFlashAttribute("codicePrenotazione", codicePrenotazione);
            redirectAttributes.addFlashAttribute("messaggio", "Prenotazione effettuata con successo!");
        } else {
            redirectAttributes.addFlashAttribute("messaggio", "Prenotazione fallita.");
        }

        redirectAttributes.addFlashAttribute("idVolo", idVolo);
        redirectAttributes.addFlashAttribute("pesoBagaglio", pesoBagaglio);

        return "redirect:/conferma-prenotazione";
    }
    
    @GetMapping("/conferma-prenotazione")
    public String mostraConfermaSuccesso(@ModelAttribute("idVolo") int idVolo, 
                                         @ModelAttribute("pesoBagaglio") int pesoBagaglio,
                                         Model model) {

        Optional<Volo> voloOpt = voloService.cercaVoloPerId(idVolo);
        voloOpt.ifPresent(volo -> {
            String formattedOra = DateTimeFormatUtil.formatVoloDate(volo.getOraPartenza());
            volo.setOraPartenza(formattedOra);
            model.addAttribute("volo", volo);
        });

        return "conferma-prenotazione";
    }
}
    
