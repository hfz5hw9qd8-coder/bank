import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        System.out.println("Bienvenue dans votre système bancaire !");

        Client mathieu = new Client(
                "CLI-000001",
                "Mathieu",
                "mathieu@example.com"
        );

        Client paul = new Client(
                "CLI-000002",
                "Paul",
                "paul@example.com"
        );

        mathieu.afficherClient();
        paul.afficherClient();

        Compte compteMathieu = new Compte(
                "FR 1234 5678 9101 12",
                mathieu,
                new BigDecimal("1000.00")
        );

        Compte epargneMathieu = new Compte(
                "FR 5555 5555 5555 55",
                mathieu,
                new BigDecimal("5000.00")
        );

        Compte comptePaul = new Compte(
                "FR 9876 5432 1098 76",
                paul,
                new BigDecimal("500.00")
        );

        mathieu.ajouterCompte(compteMathieu);
        mathieu.ajouterCompte(epargneMathieu);
        paul.ajouterCompte(comptePaul);

        CompteRepository compteRepository = new CompteRepository();
        TransactionRepository transactionRepository = new TransactionRepository();

        ServiceBancaire banque = new ServiceBancaire(
                compteRepository,
                transactionRepository
        );

        banque.ajouterCompte(compteMathieu);
        banque.ajouterCompte(epargneMathieu);
        banque.ajouterCompte(comptePaul);

        ConsoleBancaire console = new ConsoleBancaire(banque);

        console.afficherCompte("FR 1234 5678 9101 12");
        console.afficherCompte("FR 9876 5432 1098 76");

        System.out.println("\n--- VIREMENT ---");

        console.effectuerVirement(
                "FR 1234 5678 9101 12",
                "FR 9876 5432 1098 76",
                new BigDecimal("200.00")
        );

        banque.retirer(
                "FR 1234 5678 9101 12",
                new BigDecimal("5000.00")
        );

        System.out.println("\n--- TEST COMPTE INCONNU ---");

        try {
            console.effectuerVirement(
                    "FR 1111 1111 1111 11",
                    "FR 9876 5432 1098 76",
                    new BigDecimal("50.00")
            );
        } catch (CompteIntrouvableException e) {
            console.afficherErreur(e.getMessage());
        }

        try {
            banque.retirer(
                    "FR 1234 5678 9101 12",
                    new BigDecimal("-100.00")
            );
        } catch (MontantInvalideException e) {
            console.afficherErreur(e.getMessage());
        }

        System.out.println("\n--- TRANSFERT VERS ÉPARGNE ---");

        console.effectuerVirement(
                "FR 1234 5678 9101 12",
                "FR 5555 5555 5555 55",
                new BigDecimal("300.00")
        );

        System.out.println("\n--- APRES LES OPERATIONS ---");

        console.afficherCompte("FR 1234 5678 9101 12");
        console.afficherCompte("FR 5555 5555 5555 55");
        console.afficherCompte("FR 9876 5432 1098 76");

        console.afficherHistorique("FR 1234 5678 9101 12");
        console.afficherHistorique("FR 5555 5555 5555 55");
        console.afficherHistorique("FR 9876 5432 1098 76");
    }
}
