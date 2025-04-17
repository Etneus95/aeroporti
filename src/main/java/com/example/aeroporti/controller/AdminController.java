package com.example.aeroporti.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.aeroporti.dao.AdminRepository;
import com.example.aeroporti.model.Admin;
import com.example.aeroporti.model.Volo;
import com.example.aeroporti.service.VoloService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private VoloService voloService;

    @GetMapping("/login")
    public String mostraLoginForm() {
        return "admin-login";
    }

    @PostMapping("/login")
    public String processaLogin(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        Optional<Admin> adminOpt = adminRepository.findByUsernameAndPassword(username, password);

        if (adminOpt.isPresent()) {
            session.setAttribute("adminLoggato", true);
            return "redirect:/admin/gestione";
        } else {
            model.addAttribute("errore", "Credenziali non valide");
            return "admin-login";
        }
    }
    
    @GetMapping("/gestione")
    public String mostraGestionePage(HttpSession session, Model model) {
        // Verifica se l'admin è loggato
        Boolean isLoggedIn = (Boolean) session.getAttribute("adminLoggato");
        if (isLoggedIn == null || !isLoggedIn) {
            return "redirect:/login"; // Se non loggato, fai il redirect al login
        }

        // Aggiungi eventuali dati al model se necessario
        model.addAttribute("messaggio", "Benvenuto nella gestione voli!");
        
        return "gestione"; // Restituisce la pagina gestione.jsp
    }


    @GetMapping("/elenco")
    public String mostraElencoVoli(Model model) {
        List<Volo> voliFuturi = voloService.getVoliFuturi();

        model.addAttribute("voli", voliFuturi);
        return "elenco-voli";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Invalida la sessione
        return "redirect:/"; // Redirigi al login
    }
}