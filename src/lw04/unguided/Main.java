package lw04.unguided;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        
        Scanner input = new Scanner (Main.class.getResourceAsStream("cards.txt"));

        
        int N = input.nextInt();

        int[] cards = new int[N];

        
        for (int i = 0; i < N; i++) {
            cards[i] = input.nextInt();
        }

        
        int M = input.nextInt();

        int[] targets = new int[M];

        
        for (int i = 0; i < M; i++) {
            targets[i] = input.nextInt();
        }

        input.close();

        
        sortCards(cards, 2);

        
        for (int i = 0; i < N; i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(cards[i]);
        }

        System.out.println();

        
        for (int i = 0; i < M; i++) {

            int foundIndex = findCard(cards, targets[i]);

            if (foundIndex != -1) {
                System.out.println(targets[i] +
                        ": FOUND at index " + foundIndex);
            } else {
                System.out.println(targets[i] + ": NOT FOUND");
            }
        }
    }

    
    public static void sortCards(int[] arr, int passes) {

        for (int i = 0; i < passes; i++) {

            int minIndex = i;

            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    
    public static int findCard(int[] arr, int target) {

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }

        return -1;
    }
}
    
