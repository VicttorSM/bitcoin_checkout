package pack;

import java.util.Scanner;

/**
 *
 * @author Victtor da Silva Mendes
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean end = false;
        Controller control = null;
        do {
            try {
                Scanner input = new Scanner(System.in);
                System.out.println("Digite seu primeiro nome:");
                String firstName = input.next();
                input.nextLine();
                System.out.println("Digite seu ultimo nome:");
                String lastName = input.nextLine();
                System.out.print("Digite o valor de saldo que pretende começar:\nR$ ");
                double balance = Double.parseDouble(input.next().replace(',', '.'));
                System.out.println();
                control = new Controller(new User(firstName, lastName, balance));
                end = true;
            } catch (Exception e) {
                System.out.println(e.getMessage() + "\n");
            }
        } while (!end);
        Scanner input = new Scanner(System.in);
        while (true) {
            try {
                control.executeCommand(input.nextLine());
                System.out.println();
            } catch (Exception e) {
                System.out.println(e.getMessage() + "\n");
            }
        }
    }

}
