package com.bank.service;

import com.bank.exception.CompteIntrouvableException;
import com.bank.model.Compte;
import com.bank.model.Transaction;
import com.bank.repository.CompteRepository;
import com.bank.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ServiceBancaire {
    private final CompteRepository compteRepository;
    private final TransactionRepository transactionRepository;

    public ServiceBancaire(CompteRepository compteRepository, TransactionRepository transactionRepository) {
        this.compteRepository = compteRepository;
        this.transactionRepository = transactionRepository;
    }

    public void ajouterCompte(Compte compte) { compteRepository.sauvegarder(compte); }

    public Compte rechercherCompte(String numeroCompte) {
        return compteRepository.rechercherParNumero(numeroCompte);
    }

    public List<Transaction> historique(String numeroCompte) {
        return transactionRepository.trouverParCompte(rechercherCompteOuErreur(numeroCompte));
    }

    public void effectuerVirement(String numeroSource, String numeroDestinataire, BigDecimal montant) {
        Compte source = rechercherCompteOuErreur(numeroSource);
        Compte destinataire = rechercherCompteOuErreur(numeroDestinataire);
        Transaction transaction = source.virerVers(destinataire, montant);
        if (transaction != null) transactionRepository.sauvegarder(transaction);
    }

    private Compte rechercherCompteOuErreur(String numeroCompte) {
        Compte compte = rechercherCompte(numeroCompte);
        if (compte == null) throw new CompteIntrouvableException("Compte introuvable : " + numeroCompte);
        return compte;
    }
}
