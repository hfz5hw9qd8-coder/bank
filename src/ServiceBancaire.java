import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ServiceBancaire {

    private CompteRepository compteRepository;

    public ServiceBancaire(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }

    public void ajouterCompte(Compte compte) {
        compteRepository.sauvegarder(compte);
    }


    public Compte rechercherCompte(String numeroCompte) {
        return compteRepository.rechercherParNumero(numeroCompte);
    }

    public void effectuerVirement(
            String numeroSource,
            String numeroDestinataire,
            BigDecimal montant
    ) {

        Compte source = rechercherCompte(numeroSource);
        Compte destinataire = rechercherCompte(numeroDestinataire);

        if (source == null) {
            throw new CompteIntrouvableException(
                    "Compte source introuvable : " + numeroSource
            );
        }

        if (destinataire == null) {
            throw new CompteIntrouvableException(
                    "Compte destinataire introuvable : " + numeroDestinataire
            );
        }

        source.virerVers(destinataire, montant);
    }
}