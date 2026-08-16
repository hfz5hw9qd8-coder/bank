import java.math.BigDecimal;

public class Compte {

    private final String numeroCompte;
    private final Client titulaire;
    private BigDecimal solde;

    public Compte(String numeroCompte, Client titulaire, BigDecimal soldeInitial) {
        this.numeroCompte = numeroCompte;
        this.titulaire = titulaire;
        this.solde = soldeInitial;
    }

    public String getNumeroCompte() {
        return numeroCompte;
    }

    public String getTitulaire() {
        return titulaire.getNom();
    }

    public BigDecimal getSolde() {
        return solde;
    }

    public void afficherCompte() {
        System.out.println("------ COMPTE BANCAIRE -----");
        System.out.println("Numéro : " + numeroCompte);
        System.out.println("Titulaire : " + titulaire.getNom());
        System.out.println("Solde : " + solde + "€");
    }

    public Transaction deposer(BigDecimal montant) {
        if (montant.compareTo(BigDecimal.ZERO) <= 0) {
            throw new MontantInvalideException(
                    "Le montant du dépôt doit être supérieur à 0."
            );
        }

        solde = solde.add(montant);

        return new Transaction(
                montant,
                TypeTransaction.DEPOT,
                null,
                this
        );
    }

    public Transaction retirer(BigDecimal montant) {
        if (montant.compareTo(BigDecimal.ZERO) <= 0) {
            throw new MontantInvalideException(
                    "Le montant du retrait doit être supérieur à 0."
            );
        }

        if (montant.compareTo(solde) > 0) {
            System.out.println("Solde insuffisant !");
            return null;
        }

        solde = solde.subtract(montant);

        return new Transaction(
                montant,
                TypeTransaction.RETRAIT,
                this,
                null
        );
    }

    public Transaction virerVers(Compte destinataire, BigDecimal montant) {
        if (montant.compareTo(BigDecimal.ZERO) <= 0) {
            throw new MontantInvalideException(
                    "Le montant du virement doit être supérieur à 0."
            );
        }

        if (montant.compareTo(solde) > 0) {
            System.out.println("Virement refusé : solde insuffisant !");
            return null;
        }

        solde = solde.subtract(montant);
        destinataire.solde = destinataire.solde.add(montant);

        return new Transaction(
                montant,
                TypeTransaction.VIREMENT,
                this,
                destinataire
        );
    }
}
