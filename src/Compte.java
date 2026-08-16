import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class Compte {
    private List<Transaction> historique = new ArrayList<>();

    private String numeroCompte;
    private Client titulaire;
    private BigDecimal solde;

    public String getNumeroCompte() {
        return numeroCompte;
    }

    public String getTitulaire() {
        return titulaire.getNom();
    }

    public BigDecimal getSolde() {
        return solde;
    }


    public Compte(String numeroCompte, Client titulaire, BigDecimal soldeInitial) {
        this.numeroCompte = numeroCompte;
        this.titulaire = titulaire;
        this.solde = soldeInitial;
    }

    public void afficherCompte() {
        System.out.println("------ COMPTE BANCAIRE -----");
        System.out.println("Numéro : " + numeroCompte);
        System.out.println("Titulaire : " + titulaire.getNom());
        System.out.println("Solde : " + solde + "€");
    }

    public void deposer(BigDecimal montant) {

        if (montant.compareTo(BigDecimal.ZERO) <= 0) {
            throw new MontantInvalideException(
                    "Le montant du dépôt doit être supérieur à 0."
            );
        }

        solde = solde.add(montant);

        Transaction transaction = new Transaction(
                montant,
                TypeTransaction.DEPOT,
                "BANQUE",
                titulaire.getNom()
        );

        historique.add(transaction);

        System.out.println("Dépôt effectué : " + montant + " €");
    }

    public void retirer(BigDecimal montant) {

        if (montant.compareTo(BigDecimal.ZERO) <= 0) {
            throw new MontantInvalideException(
                    "Le montant du retrait doit être supérieur à 0."
            );
        }

        if (montant.compareTo(solde) > 0) {
            System.out.println("Solde insuffisant !");
            return;
        }

        solde = solde.subtract(montant);

        Transaction transaction = new Transaction(
                montant,
                TypeTransaction.RETRAIT,
                titulaire.getNom(),
                "BANQUE"
        );

        historique.add(transaction);

        System.out.println("Retrait effectué : " + montant + " €");
    }

    public void virerVers(Compte destinataire, BigDecimal montant) {

        if (montant.compareTo(BigDecimal.ZERO) <= 0) {
            throw new MontantInvalideException(
                    "Le montant du virement doit être supérieur à 0."
            );
        }

        if (montant.compareTo(solde) > 0) {
            System.out.println("Virement refusé : solde insuffisant !");
            return;
        }

        this.solde = this.solde.subtract(montant);
        destinataire.solde = destinataire.solde.add(montant);

        Transaction transaction = new Transaction(
                montant,
                TypeTransaction.VIREMENT,
                this.titulaire.getNom(),
                destinataire.titulaire.getNom()
        );

        historique.add(transaction);
        destinataire.historique.add(transaction);

        System.out.println("Virement effectué : " + montant + " €");
        System.out.println("De : " + this.titulaire.getNom());
        System.out.println("Vers : " + destinataire.titulaire.getNom());
    }
    public void afficherHistorique() {
        System.out.println(
                "\n===== HISTORIQUE DE " + titulaire.getNom() + " ====="
        );

        for (Transaction transaction : historique) {
            transaction.afficher();
        }

        System.out.println("=================================");
    }
}