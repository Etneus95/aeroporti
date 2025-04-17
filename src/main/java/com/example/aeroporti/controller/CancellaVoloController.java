package com.example.aeroporti.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.aeroporti.model.Volo;
import com.example.aeroporti.service.VoloService;

@Controller
@RequestMapping("/admin")
public class CancellaVoloController {

    @Autowired
    private VoloService voloService;
    
    @GetMapping("/cancella-volo")
    public String mostraCancellaVoloPage(Model model) {
    	List<Volo> voliFuturi = voloService.getVoliFuturi();
    	
        // Aggiungi questi dati al modello
        model.addAttribute("voliFuturi", voliFuturi);

        return "cancella-volo"; // JSP per modificare un volo
    }
    
    @GetMapping("/cancella-volo/{idVolo}")
    public String mostraConfermaCancellazione(@PathVariable int idVolo, Model model) {
        // Ottieni il volo da cancellare
        Optional<Volo> voloOpt = voloService.cercaVoloPerId(idVolo);
        
        if (voloOpt.isPresent()) {
            Volo volo = voloOpt.get();
            
            // Passa il volo al modello per la visualizzazione
            model.addAttribute("volo", volo);
        }
        
        // Mostra la pagina di conferma cancellazione
        return "richiesta-conferma-cancellazione";
    }
    
    @PostMapping("/cancella-volo")
    public String cancellaVolo(@RequestParam int idVolo, Model model) {
        Optional<Volo> voloOpt = voloService.cercaVoloPerId(idVolo);
        if (voloOpt.isPresent()) {
            Volo volo = voloOpt.get();
            voloService.eliminaVolo(volo); // Metodo per eliminare il volo dal database

            // Aggiungi messaggio di successo
            model.addAttribute("messaggio", "Volo cancellato con successo!");
        } else {
            // Se il volo non è trovato, aggiungi un messaggio di errore
            model.addAttribute("errore", "Volo non trovato!");
        }

        // Ritorna alla pagina di gestione senza fare un redirect
        return "gestione"; // La vista che desideri mostrare
    }
}