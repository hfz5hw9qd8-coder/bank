import java.util.ArrayList;
import java.util.List;

public class CompteRepository {

    private List<Compte> comptes = new ArrayList<>();

    public void sauvegarder(Compte compte) {
        comptes.add(compte);
    }

    public Compte rechercherParNumero(String numeroCompte) {

        for (Compte compte : comptes) {

            if (compte.getNumeroCompte().equals(numeroCompte)) {
                return compte;
            }
        }

        return null;
    }
}