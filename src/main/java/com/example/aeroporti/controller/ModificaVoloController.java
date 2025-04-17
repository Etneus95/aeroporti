package com.example.aeroporti.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.aeroporti.model.Aereo;
import com.example.aeroporti.model.Volo;
import com.example.aeroporti.service.AereoService;
import com.example.aeroporti.service.VoloService;


@Controller
@RequestMapping("/admin")
public class ModificaVoloController {

    @Autowired
    private VoloService voloService;
    @Autowired
    private AereoService aereoService;


    @GetMapping("/modifica-aereo")
    public String mostraModificaVoloPage(Model model) {
    	List<Volo> voliFuturi = voloService.getVoliFuturi();
    	
        // Aggiungi questi dati al modello
        model.addAttribute("voliFuturi", voliFuturi);

        return "modifica-volo"; // JSP per modificare un volo
    }
    
    @GetMapping("/modifica-aereo/{idVolo}")
    public String mostraAereiDisponibili(@PathVariable int idVolo, Model model) {
        // Ottieni il volo selezionato
        Optional<Volo> voloOpt = voloService.cercaVoloPerId(idVolo);
        
        if (voloOpt.isPresent()) {
            Volo volo = voloOpt.get();

            // Ottieni l'elenco degli aerei con capienza sufficiente
            List<Aereo> aereiDisponibili = aereoService.getAereiCapienti(volo.getPasseggeri(), volo.getMerci());

            // Passa al modello
            model.addAttribute("volo", volo);
            model.addAttribute("aereiDisponibili", aereiDisponibili);
        }

        // Mostra la pagina per la selezione dell'aereo
        return "cambia-aereo";
    }
    
    @PostMapping("/salva-modifica-aereo")
    public String salvaModificaAereo(@RequestParam int idVolo, @RequestParam String aereoId, Model model) {
        Optional<Volo> voloOpt = voloService.cercaVoloPerId(idVolo);
        if (voloOpt.isPresent()) {
            Volo volo = voloOpt.get();
            Optional<Aereo> aereoOpt = aereoService.cercaAereoPerId(aereoId);
            if (aereoOpt.isPresent()) {
                Aereo aereo = aereoOpt.get();
                volo.setTipoAereo(aereo);
                voloService.salvaVolo(volo);

             // Usa il model per aggiungere il messaggio di successo
                model.addAttribute("messaggio", "Aereo modificato con successo!");
            } else {
                // Se l'aereo non è trovato, aggiungi un errore al model
                model.addAttribute("errore", "Aereo non trovato!");
            }
        } else {
            // Se il volo non è trovato, aggiungi un errore al model
            model.addAttribute("errore", "Volo non trovato!");
        }

        // Ritorna alla pagina di gestione senza fare un redirect
        return "gestione"; // La vista che desideri mostrare
    }

}