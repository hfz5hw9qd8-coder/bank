package com.bank.controller;

import com.bank.Compte;
import com.bank.ServiceBancaire;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comptes")
public class CompteController {

    private final ServiceBancaire serviceBancaire;

    public CompteController(ServiceBancaire serviceBancaire) {
        this.serviceBancaire = serviceBancaire;
    }

    @GetMapping("/{numeroCompte}")
    public ResponseEntity<Compte> rechercherCompte(@PathVariable String numeroCompte) {
        Compte compte = serviceBancaire.rechercherCompte(numeroCompte);

        if (compte == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(compte);
    }
}
