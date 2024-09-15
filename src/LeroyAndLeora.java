import java.util.Scanner;
import java.util.Random;

public class LeroyAndLeora {

    public static void main(String[] args) {
        // INITIAL SETUP
        System.out.println("Let's play Leroy and Leora!");
        System.out.println();
        int Leroy = 0;
        int Leora = 0;
        System.out.println("Leroy's score: " + Leroy);
        System.out.println("Leora's score: " + Leora);
        System.out.println();

        // Generate new array and print array
        boolean[] newArray = createNewArray();
        System.out.println("Board:");
        printArray(newArray);

        // START GAME, COLLECT AND VALIDATE LEROY'S INPUT
        Scanner scanner = new Scanner(System.in);
        while (!allCookiesEaten(newArray)) {
            int Leroy_curr_turn = 0;
            while (true) {
                System.out.print("Leroy's Turn. What cookie should he eat: ");
                int cookie = scanner.nextInt();

                // Check to confirm the cookie is in range (and possibly not already eaten)
                if (cookie > 25 || cookie < 1) {
                    System.out.println("Cookie out of range, try again");
                } else if (newArray[cookie]) {
                    System.out.println("Cookie already eaten, try again");
                } else {
                    System.out.println("Leroy eats cookie " + cookie);
                    Leroy_curr_turn += cookie;
                    newArray[cookie] = true;

                    // Check if there are factors for Leora to eat
                    boolean noFactors = hasfactors(newArray, Leroy_curr_turn, Leora);
                    if (noFactors) {
                        break;
                    }
                }
            }

            // Add Leroy's turn score to total score
            Leroy += Leroy_curr_turn;

            // Display updated scores and board
            System.out.println("\n\nLeroy's new score: " + Leroy);
            System.out.println("Leora's new score: " + Leora);
            System.out.println();
            System.out.println("\nUpdated Board:");
            printArray(newArray);
        }
    }

    // New array generation
    public static boolean[] createNewArray() {
        boolean[] start = new boolean[26];  // Array of size 26 to accommodate indices 1-25

        // Generate 4 random distinct numbers between 1 and 25
        final int[] remove = new Random().ints(1, 26).distinct().limit(4).toArray();

        // Set those 4 positions to true in the array
        for (int j : remove) {
            start[j] = true;
        }

        // Return the boolean array
        return start;
    }


    public static void printArray(boolean[] array) {
        for (int i = 2; i <= 25; i++) {
            if (array[i]) {
                System.out.print("- ");
            } else {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }


    public static boolean allCookiesEaten(boolean[] array) {
        for (int i = 2; i <= 25; i++) {
            if (!array[i]) {  // If there's at least one cookie not eaten
                return false;
            }
        }
        return true;  // All cookies are eaten
    }

    // Check for factors and feed Leora
    public static boolean hasfactors(boolean[] newArray, int Leroy_curr_turn, int Leora) {
        int occurrences = 0;
        for (int i = 2; i <= 25; i++) {
            System.out.println(Leroy_curr_turn);
            if (Leroy_curr_turn % i == 0 && i != Leroy_curr_turn && !newArray[i]) {
                occurrences += 1;
                System.out.println("Leora eats cookie " + i);
                Leora += i;
                newArray[i] = true;
            }
        }
        return occurrences == 0;  // Return true if no factors were found
    }
}
