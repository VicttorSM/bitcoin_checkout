package pack;

import java.util.Scanner;

/**
 * Main class
 *
 * @author Victtor da Silva Mendes
 */
public class Main {

    /**
     * Main function
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean sucess = false;
        Controller control = null;
        Scanner input = new Scanner(System.in);
        do {
            try {
                /* Gets the info about the user */
                System.out.println("Digite seu primeiro nome:");
                String firstName = input.next();
                input.nextLine();
                System.out.println("Digite seu ultimo nome:");
                String lastName = input.nextLine();
                System.out.print("Digite o valor de saldo que pretende começar:\nR$ ");
                double balance = Double.parseDouble(input.next().replace(',', '.'));
                System.out.println();

                control = new Controller(new User(firstName, lastName, balance));
                sucess = true;
            } catch (Exception e) {
                System.out.println(e.getMessage() + "\n");
            }
        } while (!sucess);

        /* Starts the program loop */
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
