package com.bank.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private final String id;
    private final BigDecimal montant;
    private final TypeTransaction type;
    private final Compte source;
    private final Compte destinataire;
    private final LocalDateTime date;
    private static int compteur = 1;

    public Transaction(BigDecimal montant, TypeTransaction type, Compte source, Compte destinataire) {
        this.id = String.format("TX-%06d", compteur++);
        this.montant = montant;
        this.type = type;
        this.source = source;
        this.destinataire = destinataire;
        this.date = LocalDateTime.now();
    }

    public String getId() { return id; }
    public BigDecimal getMontant() { return montant; }
    public TypeTransaction getType() { return type; }
    public Compte getSource() { return source; }
    public Compte getDestinataire() { return destinataire; }
    public LocalDateTime getDate() { return date; }
}
