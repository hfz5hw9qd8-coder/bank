package com.bank;

import com.bank.model.Client;
import com.bank.model.Compte;
import com.bank.repository.CompteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class BankDataInitializer {
    @Bean
    CommandLineRunner initialiserComptes(CompteRepository compteRepository) {
        return args -> {
            Client mathieu = new Client("CLI-000001", "Mathieu", "mathieu@example.com");
            Client paul = new Client("CLI-000002", "Paul", "paul@example.com");

            Compte compteMathieu = new Compte(
                    "FR 1234 5678 9101 12", mathieu, new BigDecimal("1000.00")
            );
            Compte comptePaul = new Compte(
                    "FR 9876 5432 1098 76", paul, new BigDecimal("500.00")
            );

            mathieu.ajouterCompte(compteMathieu);
            paul.ajouterCompte(comptePaul);

            compteRepository.sauvegarder(compteMathieu);
            compteRepository.sauvegarder(comptePaul);
        };
    }
}
