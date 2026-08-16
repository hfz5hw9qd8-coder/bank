import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {

    private final List<Transaction> transactions = new ArrayList<>();

    public void sauvegarder(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> trouverToutes() {
        return List.copyOf(transactions);
    }

    public List<Transaction> trouverParCompte(Compte compte) {
        List<Transaction> resultat = new ArrayList<>();

        for (Transaction transaction : transactions) {
            if (transaction.getSource() == compte
                    || transaction.getDestinataire() == compte) {
                resultat.add(transaction);
            }
        }

        return List.copyOf(resultat);
    }
}
