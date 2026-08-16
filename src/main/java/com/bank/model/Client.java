package com.bank.model;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private final List<Compte> comptes = new ArrayList<>();
    private final String id;
    private final String nom;
    private final String email;

    public Client(String id, String nom, String email) {
        this.id = id;
        this.nom = nom;
        this.email = email;
    }

    public void ajouterCompte(Compte compte) {
        comptes.add(compte);
    }

    public String getId() { return id; }
    public String getNom() { return nom; }
    public String getEmail() { return email; }
    public List<Compte> getComptes() { return List.copyOf(comptes); }
}
