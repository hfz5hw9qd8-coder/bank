import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {

    private final BigDecimal montant;
    private final TypeTransaction type;
    private final Compte source;
    private final Compte destinataire;
    private final LocalDateTime date;

    private static int compteur = 1;

    private final int id;

    public Transaction(
            BigDecimal montant,
            TypeTransaction type,
            Compte source,
            Compte destinataire
    ) {
        this.id = compteur++;
        this.montant = montant;
        this.type = type;
        this.source = source;
        this.destinataire = destinataire;
        this.date = LocalDateTime.now();
    }

    public Compte getSource() {
        return source;
    }

    public Compte getDestinataire() {
        return destinataire;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public TypeTransaction getType() {
        return type;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void afficher() {
        System.out.println("----- TRANSACTION -----");
        System.out.println("ID : TX-" + String.format("%06d", id));
        System.out.println("Type : " + type);
        System.out.println("Montant : " + montant + " €");

        if (source == null) {
            System.out.println("Source : BANQUE");
        } else {
            System.out.println(
                    "Source : " + source.getNumeroCompte()
                            + " (" + source.getTitulaire() + ")"
            );
        }

        if (destinataire == null) {
            System.out.println("Destinataire : BANQUE");
        } else {
            System.out.println(
                    "Destinataire : " + destinataire.getNumeroCompte()
                            + " (" + destinataire.getTitulaire() + ")"
            );
        }

        System.out.println("Date : " + date);
        System.out.println("-----------------------");
    }
}
