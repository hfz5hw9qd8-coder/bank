package com.bank.controller;

import com.bank.model.Compte;

import java.math.BigDecimal;

public record CompteResponse(String numeroCompte, String titulaire, BigDecimal solde) {
    public static CompteResponse from(Compte compte) {
        return new CompteResponse(
                compte.getNumeroCompte(),
                compte.getTitulaire().getNom(),
                compte.getSolde()
        );
    }
}
