package com.bank.repository;

import com.bank.model.Compte;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CompteRepository {
    private final List<Compte> comptes = new ArrayList<>();

    public void sauvegarder(Compte compte) { comptes.add(compte); }

    public Compte rechercherParNumero(String numeroCompte) {
        String recherche = numeroCompte.replace(" ", "");
        return comptes.stream()
                .filter(c -> c.getNumeroCompte().replace(" ", "").equals(recherche))
                .findFirst()
                .orElse(null);
    }
}
