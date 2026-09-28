package lw02.unguided;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {

        LinkedList<String[]> requests = new LinkedList<>();
        LinkedList<String[]> books = new LinkedList<>();
        LinkedList<String[]> members = new LinkedList<>();
        LinkedList<String[]> successful = new LinkedList<>();

        Scanner scanner = new Scanner(
            Main.class.getResourceAsStream("borrowing.txt")
        );

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] request = line.split(" ");

            requests.add(request);

            String memberName = request[0];
            boolean exists = false;

            for (String[] member : members) {
                if (member[0].equals(memberName)) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                members.add(new String[]{memberName, "0"});
            }
        }

        scanner.close();

        books.add(new String[]{"Kalkulus", "2"});
        books.add(new String[]{"Fisika", "1"});
        books.add(new String[]{"Statistika", "2"});

        Queue<String[]> queue = new LinkedList<>();

        while (!requests.isEmpty()) {
            queue.offer(requests.remove());
        }

        Stack<String[]> failed = new Stack<>();

        int MAX_BORROW = 2;

        while (!queue.isEmpty()) {
            String[] request = queue.poll();

            String name = request[0];
            String bookTitle = request[1];

            for (String[] book : books) {
                if (book[0].equals(bookTitle)) {

                    for (String[] member : members) {
                        if (member[0].equals(name)) {

                            int stock = Integer.parseInt(book[1]);
                            int borrowed = Integer.parseInt(member[1]);

                            if (stock > 0 && borrowed < MAX_BORROW) {

                                successful.add(request);

                                stock--;
                                borrowed++;

                                book[1] = String.valueOf(stock);
                                member[1] = String.valueOf(borrowed);

                            } else {
                                failed.push(request);
                            }

                            break;
                        }
                    }

                    break;
                }
            }
        }

        System.out.println("=== Successfully Processed Requests ===");

        for (String[] request : successful) {
            System.out.println(request[0] + " " + request[1]);
        }

        System.out.println("=== Remaining Book Stock ===");

        for (String[] book : books) {
            System.out.println(book[0] + " : " + book[1]);
        }

        System.out.println("=== Failed Requests ===");

        while (!failed.isEmpty()) {
            String[] request = failed.pop();

            System.out.println(
                request[0] + " " + request[1]
            );
        }
    }
}