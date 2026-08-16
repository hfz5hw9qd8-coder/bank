import java.math.BigDecimal;

public class ConsoleBancaire {

    private final ServiceBancaire banque;

    public ConsoleBancaire(ServiceBancaire banque) {
        this.banque = banque;
    }

    public void afficherCompte(String numeroCompte) {
        Compte compte = banque.rechercherCompte(numeroCompte);
        if (compte == null) {
            throw new CompteIntrouvableException(
                    "Compte introuvable : " + numeroCompte
            );
        }

        compte.afficherCompte();
    }

    public void effectuerVirement(
            String numeroSource,
            String numeroDestinataire,
            BigDecimal montant
    ) {
        banque.effectuerVirement(numeroSource, numeroDestinataire, montant);
    }

    public void afficherHistorique(String numeroCompte) {
        banque.afficherHistorique(numeroCompte);
    }

    public void afficherErreur(String message) {
        System.out.println("ERREUR : " + message);
    }
}
