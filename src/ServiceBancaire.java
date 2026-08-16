import java.math.BigDecimal;
import java.util.List;

public class ServiceBancaire {

    private final CompteRepository compteRepository;
    private final TransactionRepository transactionRepository;

    public ServiceBancaire(
            CompteRepository compteRepository,
            TransactionRepository transactionRepository
    ) {
        this.compteRepository = compteRepository;
        this.transactionRepository = transactionRepository;
    }

    public void ajouterCompte(Compte compte) {
        compteRepository.sauvegarder(compte);
    }

    public Compte rechercherCompte(String numeroCompte) {
        return compteRepository.rechercherParNumero(numeroCompte);
    }

    public void deposer(String numeroCompte, BigDecimal montant) {
        Compte compte = rechercherCompteOuErreur(numeroCompte);
        Transaction transaction = compte.deposer(montant);
        transactionRepository.sauvegarder(transaction);

        System.out.println("Dépôt effectué : " + montant + " €");
    }

    public void retirer(String numeroCompte, BigDecimal montant) {
        Compte compte = rechercherCompteOuErreur(numeroCompte);
        Transaction transaction = compte.retirer(montant);

        if (transaction != null) {
            transactionRepository.sauvegarder(transaction);
            System.out.println("Retrait effectué : " + montant + " €");
        }
    }

    public void effectuerVirement(
            String numeroSource,
            String numeroDestinataire,
            BigDecimal montant
    ) {
        Compte source = rechercherCompteOuErreur(numeroSource);
        Compte destinataire = rechercherCompteOuErreur(numeroDestinataire);

        Transaction transaction = source.virerVers(destinataire, montant);

        if (transaction != null) {
            transactionRepository.sauvegarder(transaction);

            System.out.println("Virement effectué : " + montant + " €");
            System.out.println("De : " + source.getTitulaire());
            System.out.println("Vers : " + destinataire.getTitulaire());
        }
    }

    public void afficherHistorique(String numeroCompte) {
        Compte compte = rechercherCompteOuErreur(numeroCompte);
        List<Transaction> transactions =
                transactionRepository.trouverParCompte(compte);

        System.out.println(
                "\n===== HISTORIQUE DE " + compte.getTitulaire() + " ====="
        );

        for (Transaction transaction : transactions) {
            transaction.afficher();
        }

        System.out.println("=================================");
    }

    private Compte rechercherCompteOuErreur(String numeroCompte) {
        Compte compte = rechercherCompte(numeroCompte);

        if (compte == null) {
            throw new CompteIntrouvableException(
                    "Compte introuvable : " + numeroCompte
            );
        }

        return compte;
    }
}
