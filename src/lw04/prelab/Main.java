package lw04.prelab;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) {

        
        Scanner input = new Scanner (Main.class.getResourceAsStream("branches.txt"));

        
        int N = input.nextInt();

        int[] branches = new int[N];

        
        for (int i = 0; i < N; i++) {
            branches[i] = input.nextInt();
        }

        
        int M = input.nextInt();

        int[] targets = new int[M];

        
        for (int i = 0; i < M; i++) {
            targets[i] = input.nextInt();
        }

        input.close();

        
        for (int i = 1; i <= 3; i++) {

            int key = branches[i];
            int j = i - 1;

            while (j >= 0 && branches[j] < key) {
                branches[j + 1] = branches[j];
                j--;
            }

            branches[j + 1] = key;
        }

        
        for (int i = 0; i < N; i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(branches[i]);
        }

        System.out.println();

       
        for (int i = 0; i < M; i++) {

            int foundIndex = -1;

            for (int j = 0; j < N; j++) {
                if (branches[j] == targets[i]) {
                    foundIndex = j;
                    break;
                }
            }

            if (foundIndex != -1) {
                System.out.println(targets[i] +
                        ": FOUND at index " + foundIndex);
            } else {
                System.out.println(targets[i] + ": NOT FOUND");
            }
        }
    }
}
