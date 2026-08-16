import java.util.ArrayList;
import java.util.List;

public class Client {
    private List<Compte> comptes = new ArrayList<>();
    public void ajouterCompte(Compte compte) {
        comptes.add(compte);
    }

    public void afficherComptes() {
        System.out.println("\n===== COMPTE DE " + nom + "=====");
        for (Compte compte : comptes) {
            compte.afficherCompte();
        }
    }

    private String id;
    private String nom;
    private String email;

    public Client(String id, String nom, String email) {
        this.id = id;
        this.nom = nom;
        this.email = email;
    }
    public String getId() {
        return id;
    }
    public String getNom() {
        return nom;
    }
    public String getEmail() {
        return email;
    }
    public void afficherClient() {
        System.out.println("----- CLIENT -----");
        System.out.println("ID : " + id);
        System.out.println("Nom : " + nom);
        System.out.println("Email : " + email);
    }
}
