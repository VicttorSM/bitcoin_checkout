package pack;

import java.util.ArrayList;

/**
 * Controller class, where all the logic of the program is computed
 *
 * @author Victtor da Silva Mendes
 */
public class Controller {

    /**
     *
     * @param user the user that is going to use the program
     */
    public Controller(User user) {
        coins = new ArrayList<>();
        this.user = user;
        printWelcomeMessage();
        printMenu();
    }

    /**
     * Computes and executes the command that is given
     *
     * @param command the line that the user typed
     * @return if it was able to execute the command
     */
    public boolean executeCommand(String command) {
        String[] arr = parseCommand(command);
        if (arr[0] == null) {
            return false;
        }

        switch (arr[0]) {
            case "quit":
                System.exit(0);
            case "status":
                if (arr[1] == null) {
                    throw new NullPointerException("O comando não foi digitado corretamente");
                }
                switch (arr[1]) {
                    case "user":
                        user.print();
                        break;
                    case "wallet":
                        if (user.wallet.isEmpty()) {
                            System.out.println("A carteira está vazia");
                        } else {
                            user.printWallet();
                        }
                        break;
                    case "coins":
                        if (coins.isEmpty()) {
                            System.out.println("Não foi adicionado nenhum bitcoin");
                        } else {
                            printCoins();
                        }
                        break;
                    default:
                        throw new IllegalArgumentException("O comando '" + arr[0]
                                + " " + arr[1] + "' não é reconhecido");
                }
                break;
            case "help":
                printMenu();
                break;
            case "bitcoin":
                if (arr[1] == null) {
                    throw new NullPointerException("O comando não foi digitado corretamente");
                }
                switch (arr[1]) {
                    case "add":
                        if (arr[2] == null || arr[3] == null) {
                            throw new NullPointerException("O comando é '" + arr[0]
                                    + " " + arr[1] + " [id] [preço]'");
                        }
                        if (getCoin(arr[2]) != null) {
                            throw new IllegalArgumentException("O bitcoin " + arr[2]
                                    + " ja foi cadastrado");
                        }
                        coins.add(new Bitcoin(arr[2], Double.valueOf(arr[3])));
                        System.out.println(arr[2] + " foi adicionado");
                        break;
                    case "buy":
                    case "sell":
                        if (arr[2] == null) {
                            throw new NullPointerException("O comando  é '" + arr[0]
                                    + " " + arr[1] + " [id] [quantidade]'");
                        }
                        if (arr[3] == null) {
                            arr[3] = "1";
                        }
                        Bitcoin coin = getCoin(arr[2]);
                        if (coin == null) {
                            throw new NullPointerException("A moeda " + arr[2] + " não existe");
                        }
                        if (arr[1].equals("buy")) {
                            tryOperation(user.buyCoins(coin, Integer.valueOf(arr[3])));
                        } else if (arr[1].equals("sell")) {
                            tryOperation(user.sellCoins(coin, Integer.valueOf(arr[3])));
                        }
                        user.printBalance();
                        break;
                    default:
                        throw new NullPointerException("O comando '" + arr[0]
                                + " " + arr[1] + "' não é reconhecido");
                }
                break;
            default:
                throw new IllegalArgumentException("O comando '" + arr[0] + "' não é reconhecido");
        }
        return true;
    }

    /**
     * Prints a welcome message to the user
     */
    public void printWelcomeMessage() {
        System.out.println("Olá " + user.getFirstName() + "!!");
        System.out.println("Seja bem vindo!");
        System.out.println("digite 'help' a qualquer momento "
                + "para obter informações sobre os comandos\n");
    }

    /**
     * Prints the possible commands
     */
    public void printMenu() {
        System.out.println("Digite um comando:");
        System.out.println("- bitcoin buy [id] [quantidade]");
        System.out.println("- bitcoin sell [id] [quantidade]");
        System.out.println("- bitcoin add [id] [preço]");
        System.out.println("- status coins");
        System.out.println("- status user");
        System.out.println("- status wallet");
        System.out.println("- quit\n");
    }

    /**
     * Prints all the coins that were added
     */
    public void printCoins() {
        System.out.println("Coins:");
        for (Bitcoin coin : coins) {
            System.out.printf("id: %s valor: R$ %.2f\n", coin.getId(), coin.getValue());
        }
    }

    /**
     * Splits a line of command in 4
     *
     * @param command a line of command
     * @return a String[4] array
     */
    private String[] parseCommand(String command) {
        command = command.trim();
        String[] arr = command.split(" ");
        String[] subcommands = new String[4];
        int index = 0;
        for (String str : arr) {
            if (str != null && !str.equals("")) {
                subcommands[index] = str;
                index++;
                if (index >= subcommands.length) {
                    break;
                }
            }
        }
        if (subcommands[0] != null) {
            subcommands[0] = subcommands[0].toLowerCase();
        }
        if (subcommands[1] != null) {
            subcommands[1] = subcommands[1].toLowerCase();
        }
        if (subcommands[3] != null) {
            subcommands[3] = subcommands[3].replace(',', '.');
        }
        return subcommands;
    }

    /**
     * Gets a coin by id
     *
     * @param id the id of the coin that it's searching
     * @return if it is able to find then it returns the coin, else it returns
     * null
     */
    private Bitcoin getCoin(String id) {
        for (Bitcoin coin : coins) {
            if (coin.getId().equals(id)) {
                return coin;
            }
        }
        return null;
    }

    /**
     * Verifies if an operation was sucessful and prints the result
     *
     * @param sucess the return of the function that it is trying to run
     */
    private void tryOperation(boolean sucess) {
        if (sucess) {
            System.out.println("Operação realizada com sucesso!!");
        } else {
            System.out.println("Não foi possivel realizar a operação");
        }
    }

    private final ArrayList<Bitcoin> coins;
    private final User user;
}
