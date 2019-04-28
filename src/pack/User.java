package pack;

import java.util.ArrayList;
import javafx.util.Pair;

/**
 * User class, stores his wallet of bitcoins and balance of money
 *
 * @author Victtor da Silva Mendes
 */
public class User extends Person {

    public ArrayList<Pair<Bitcoin, Integer>> wallet;

    /**
     *
     * @param firstName first name of the user
     * @param lastName last name of the user
     * @param balance initial value of the balance
     */
    public User(String firstName, String lastName, double balance) {
        super(firstName, lastName);
        setBalance(balance);
        wallet = new ArrayList<>();
    }

    /**
     * Prints the name and the balance of the user
     */
    public void print() {
        System.out.println("Usuario: " + this);
        printBalance();
    }

    /**
     * Prints the balance of the user
     */
    public void printBalance() {
        System.out.printf("Saldo: R$ %.2f\n", getBalance());
    }

    /**
     * Getter balance
     *
     * @return balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Setter balance
     *
     * @param balance the new balance value
     */
    public void setBalance(double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("balance should not be a negative number");
        }
        this.balance = balance;
    }

    /**
     * Prints all the coins in the wallet and its value
     */
    public void printWallet() {
        System.out.println("Coins na carteira:");
        for (Pair<Bitcoin, Integer> coins : wallet) {
            System.out.println(coins.getValue() + " coins de id: " + coins.getKey().getId());
        }
        System.out.printf("Valor da carteira: R$ %.2f\n", getTotalValueOfBitcoins());
    }

    /**
     * Calculates the total value of the bitcoins in the wallet
     *
     * @return the total value of the wallet
     */
    public double getTotalValueOfBitcoins() {
        double sum = 0;
        for (Pair<Bitcoin, Integer> coins : wallet) {
            Bitcoin coin = coins.getKey();
            int quantity = coins.getValue();
            sum += coin.getValue() * quantity;
        }
        return sum;
    }

    /**
     * Buys some coins
     *
     * @param coin the Bitcoin that it's trying to buy
     * @param quantity the quantity of bitcoins that it's trying to buy
     * @return if it was sucessful in the transaction
     */
    public boolean buyCoins(Bitcoin coin, int quantity) {
        if (coin.getValue() * quantity > balance) {
            return false;
        }
        balance -= (coin.getValue() * quantity);
        int index = indexOfCoin(coin);
        if (index == -1) {
            wallet.add(new Pair<>(coin, quantity));
        } else {
            int previousQuantity = wallet.get(index).getValue();
            wallet.remove(index);
            wallet.add(new Pair<>(coin, quantity + previousQuantity));
        }
        return true;
    }

    /**
     * Sells some coins
     *
     * @param coin the Bitcoin that it's trying to sell
     * @param quantity the quantity of bitcoins that it's trying to sell
     * @return if it was sucessful in the transaction
     */
    public boolean sellCoins(Bitcoin coin, int quantity) {
        int index = indexOfCoin(coin);
        if (index == -1 || wallet.get(index).getValue() < quantity) {
            return false;
        }
        int quantityNow = wallet.get(index).getValue();
        balance += coin.getValue() * quantity;
        if (wallet.get(index).getValue() != quantity) {
            wallet.add(new Pair<>(coin, quantityNow - quantity));
        }
        wallet.remove(index);
        return true;
    }

    /**
     * Returns the index of the coin
     *
     * @param coin the coin
     * @return if it finds returns the index of the coin, else returns -1
     */
    private int indexOfCoin(Bitcoin coin) {
        for (int i = 0; i < wallet.size(); i++) {
            if (coin.equals(wallet.get(i).getKey())) {
                return i;
            }
        }
        return -1;
    }

    private double balance;
}
