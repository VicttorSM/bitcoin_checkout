package pack;

import java.util.ArrayList;
import javafx.util.Pair;

/**
 * @author Victtor da Silva Mendes
 */
public class User extends Person {

    public ArrayList<Pair<Bitcoin, Integer>> wallet;

    public User(String firstName, String lastName, double balance) {
        super(firstName, lastName);
        setBalance(balance);
        wallet = new ArrayList<>();
    }

    public void print() {
        System.out.println("Usuario: " + this);
        printBalance();
    }

    public void printBalance() {
        System.out.printf("Saldo: R$ %.2f\n", getBalance());
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("balance should not be a negative number");
        }
        this.balance = balance;
    }

    public void printWallet() {
        System.out.println("Coins na carteira:");
        for (Pair<Bitcoin, Integer> coins : wallet) {
            System.out.println(coins.getValue() + " coins de id: " + coins.getKey().getId());
        }
        System.out.printf("Valor da carteira: R$ %.2f\n", getTotalValueOfBitcoins());
    }

    public double getTotalValueOfBitcoins() {
        double sum = 0;
        for (Pair<Bitcoin, Integer> coins : wallet) {
            Bitcoin coin = coins.getKey();
            int quantity = coins.getValue();
            sum += coin.getValue() * quantity;
        }
        return sum;
    }

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
