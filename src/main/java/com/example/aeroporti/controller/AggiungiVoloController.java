package com.example.aeroporti.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.aeroporti.model.Aereo;
import com.example.aeroporti.model.Aeroporto;
import com.example.aeroporti.model.Volo;
import com.example.aeroporti.service.AereoService;
import com.example.aeroporti.service.AeroportoService;
import com.example.aeroporti.service.VoloService;

@Controller
@RequestMapping("/admin")
public class AggiungiVoloController {

    @Autowired
    private AeroportoService aeroportoService;
    @Autowired
    private AereoService aereoService;
    @Autowired
    private VoloService voloService;

    // Visualizza il form per aggiungere un volo
    @GetMapping("/aggiungi-volo")
    public String mostraFormAggiungiVolo(Model model) {
    	
        model.addAttribute("aeroporti", aeroportoService.cercaAeroporti()); // Elenco aeroporti
        model.addAttribute("aerei", aereoService.cercaAerei()); // Elenco aerei
        model.addAttribute("volo", new Volo()); // Oggetto volo vuoto per il form
        return "aggiungi-volo"; // Nome della JSP per il form
    }

    // Processa il form per aggiungere un nuovo volo
    @PostMapping("/aggiungi-volo")
    public String aggiungiVolo(@ModelAttribute Volo volo, 
                               @RequestParam("idAeroportoPartenza") int idAeroportoPartenza,
                               @RequestParam("idAeroportoArrivo") int idAeroportoArrivo,
                               @RequestParam("idAereo") String idAereo,
                               @RequestParam("dataPartenza") String dataPartenzaStr,
                               @RequestParam("dataArrivo") String dataArrivoStr,
                               Model model) {
        
        DateTimeFormatter formatterInput = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime dataPartenza = LocalDateTime.parse(dataPartenzaStr, formatterInput);
        LocalDateTime dataArrivo = LocalDateTime.parse(dataArrivoStr, formatterInput);

        // Formatter per giorno
        DateTimeFormatter giornoFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        // Formatter per ora con frazioni
        DateTimeFormatter oraFormatter = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm:ss,nnnnnnnnn");

        volo.setGiorno(dataPartenza.format(giornoFormatter));
        volo.setOraPartenza(dataPartenza.format(oraFormatter));
        volo.setOraArrivo(dataArrivo.format(oraFormatter));

        Optional<Aeroporto> aeroportoPartenzaOpt = aeroportoService.cercaAeroportoPerId(idAeroportoPartenza);
        Optional<Aeroporto> aeroportoArrivoOpt = aeroportoService.cercaAeroportoPerId(idAeroportoArrivo);
        Optional<Aereo> aereoOpt = aereoService.cercaAereoPerId(idAereo);

        if (aeroportoPartenzaOpt.isPresent() && aeroportoArrivoOpt.isPresent() && aereoOpt.isPresent()) {
            volo.setCittaPartenza(aeroportoPartenzaOpt.get().getCitta());
            volo.setCittaArrivo(aeroportoArrivoOpt.get().getCitta());
            volo.setTipoAereo(aereoOpt.get());

            volo.setPasseggeri(0);
            volo.setMerci(0);

            voloService.salvaVolo(volo); // Salviamo il volo nel database


            // Invece di redirect, restituiamo direttamente la pagina di gestione con il messaggio
            model.addAttribute("messaggio", "Volo aggiunto con successo!");
            return "gestione"; // Restituisce la stessa pagina gestione.jsp
        } else {
            model.addAttribute("errore", "Dati non validi per aeroporto o aereo.");
            return "aggiungi-volo"; // Ritorna al form di aggiunta volo
        }
    }
}