import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {

    private List<Transaction> transactions = new ArrayList<>();

    public void sauvegarder(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> trouverToutes() {
        return transactions;
    }
}
