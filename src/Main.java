import java.math.BigDecimal;

public class Main {
    public static void main(String[] args){
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

        Compte compteMathieu= new Compte(
                "FR 1234 5678 9101 12",
                mathieu,
                new BigDecimal("1000.00")
        );

        mathieu.ajouterCompte(compteMathieu);
        Compte epargneMathieu = new Compte(
                "FR 5555 5555 5555 55",
                mathieu,
                new BigDecimal("5000.00")
        );
        mathieu.ajouterCompte(epargneMathieu);

        Compte comptePaul= new Compte(
                "FR 9876 5432 1098 76",
                paul,
                new BigDecimal("500.00")
        );

        CompteRepository compteRepository = new CompteRepository();

        ServiceBancaire banque = new ServiceBancaire(
                compteRepository
        );

        banque.ajouterCompte(compteMathieu);
        banque.ajouterCompte(comptePaul);

        compteMathieu.afficherCompte();
        comptePaul.afficherCompte();

        System.out.println("\n --- VIREMENT ---");

        banque.effectuerVirement(
                "FR 1234 5678 9101 12",
                "FR 9876 5432 1098 76",
                new BigDecimal("200")
        );
        compteMathieu.retirer(new BigDecimal("5000"));

        System.out.println("\n--- TEST COMPTE INCONNU ---");
        System.out.println("\n--- TRANSFERT VERS ÉPARGNE ---");

        compteMathieu.virerVers(
                epargneMathieu,
                new BigDecimal("300.00")
        );

        compteMathieu.afficherCompte();
        epargneMathieu.afficherCompte();
        try {

            banque.effectuerVirement(
                    "FR 1111 1111 1111 11",
                    "FR 9876 5432 1098 76",
                    new BigDecimal("50")
            );

        } catch (CompteIntrouvableException e) {

            System.out.println(
                    "ERREUR BANCAIRE : " + e.getMessage()
            );
        }

        try {

            compteMathieu.retirer(new BigDecimal("-100"));

        } catch (MontantInvalideException e) {

            System.out.println("ERREUR : " + e.getMessage());
        }


        System.out.println("\n--- APRES LE VIREMENT ---");



        compteMathieu.afficherCompte();
        comptePaul.afficherCompte();

        compteMathieu.afficherHistorique();
        comptePaul.afficherHistorique();


    }
}
