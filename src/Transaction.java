import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Transaction {

    private BigDecimal montant;
    private TypeTransaction type;
    private String source;
    private String destinataire;
    private LocalDateTime date;

    private static int compteur = 1;

    private int id;

    public Transaction(
            BigDecimal montant,
            TypeTransaction type,
            String source,
            String destinataire
    ) {
        this.id = compteur ++;
        this.montant = montant;
        this.type = type;
        this.source = source;
        this.destinataire = destinataire;
        this.date = LocalDateTime.now();
    }

    public void afficher() {
        System.out.println("----- TRANSACTION -----");
        System.out.println("ID : TX-" + String.format("%06d", id));
        System.out.println("Type : " + type);
        System.out.println("Montant : " + montant + " €");
        System.out.println("Source : " + source);
        System.out.println("Destinataire : " + destinataire);
        System.out.println("Date : " + date);
        System.out.println("-----------------------");
    }
}
