package lw02.prelab;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;


public class Main {
    public static void main(String[] args) {

        LinkedList<String[]> transactions = new LinkedList<>();
        LinkedList<String[]> customers = new LinkedList<>();

        InputStream input = Main.class.getResourceAsStream("transactions.txt");
        Scanner scanner = new Scanner(input);

        // Read transactions
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] transaction = line.split(" ");

            transactions.add(transaction);

            // Add customer if not already exists
            String customerName = transaction[0];
            boolean exists = false;

            for (String[] customer : customers) {
                if (customer[0].equals(customerName)) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                customers.add(new String[]{customerName, "0"});
            }
        }

        // Move transactions to Queue
        Queue<String[]> queue = new LinkedList<>();

        while (!transactions.isEmpty()) {
            queue.offer(transactions.remove());
        }

        // Stack for failed withdrawals
        Stack<String[]> failedTransactions = new Stack<>();

        // Process transactions
        while (!queue.isEmpty()) {
            String[] transaction = queue.poll();

            String name = transaction[0];
            String type = transaction[1];
            int amount = Integer.parseInt(transaction[2]);

            for (String[] customer : customers) {
                if (customer[0].equals(name)) {

                    int balance = Integer.parseInt(customer[1]);

                    if (type.equals("DEPOSIT")) {
                        balance += amount;
                        customer[1] = String.valueOf(balance);

                    } else if (type.equals("WITHDRAW")) {

                        if (amount > balance) {
                            failedTransactions.push(transaction);
                        } else {
                            balance -= amount;
                            customer[1] = String.valueOf(balance);
                        }
                    }

                    break;
                }
            }
        }

        // Display final balances
        System.out.println("=== Final Balances ===");

        for (String[] customer : customers) {
            System.out.println(customer[0] + " : " + customer[1]);
        }

        // Display failed transactions
        System.out.println("=== Failed Transactions ===");

        while (!failedTransactions.isEmpty()) {
            String[] transaction = failedTransactions.pop();

            System.out.println(
                transaction[0] + " "
                + transaction[1] + " "
                + transaction[2]
            );
        }

        scanner.close();
    }
}