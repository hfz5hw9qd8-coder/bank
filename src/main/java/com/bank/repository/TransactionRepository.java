package com.bank.repository;

import com.bank.model.Compte;
import com.bank.model.Transaction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionRepository {
    private final List<Transaction> transactions = new ArrayList<>();

    public void sauvegarder(Transaction transaction) { transactions.add(transaction); }

    public List<Transaction> trouverParCompte(Compte compte) {
        return transactions.stream()
                .filter(t -> t.getSource() == compte || t.getDestinataire() == compte)
                .toList();
    }
}
