import java.util.Scanner;
import java.util.Random;

public class LeroyAndLeora {

    public static void main(String[] args) {
        // INITIAL SETUP
        System.out.println("Let's play Leroy and Leora!");
        System.out.println();
        int Leroy = 0;
        int[] Leora = {0};  // Use an array to store Leora's score
        System.out.println("Leroy's score: " + Leroy);
        System.out.println("Leora's score: " + Leora[0]);
        System.out.println();

        // Generate new array and print array
        boolean[] Eaten = Eaten();
        System.out.println("Board:");
        printArray(Eaten);

        // START GAME, COLLECT AND VALIDATE LEROY'S INPUT
        Scanner scanner = new Scanner(System.in);

        while (!allCookiesEaten(Eaten)) { // makes sure at least one cookie is remaining. Lowkey useless because it will never reach this stage.
            int Leroy_curr_turn = 0; // a local variable
            if (checkUneatenCookiesForFactors(Eaten)) { // if there no remaining moves that Leroy can make when leave a factor for leora, break the loop. This is only the checker for the first generated array
                System.out.println("No valid moves left for Leroy.");
                break;
            }

            while (true) {
                if (checkUneatenCookiesForFactors(Eaten)) { // now checking at the beginning of each turn
                    break;}
                System.out.print("Leroy's Turn. What cookie should he eat (or 0 to stop): ");
                int cookie = scanner.nextInt();

                // Check if Leroy wants to stop
                if (cookie == 0) {
                    System.out.println("Leroy has stopped eating cookies.");
                    break;
                }

                // Validate the cookie choice
                if (cookie > 25 || cookie < 2) {
                    System.out.println("Cookie out of range, try again.");
                } else if (Eaten[cookie]) { // if true, the cookie is a dash as clarified in eaten
                    System.out.println("Cookie already eaten, try again.");
                } else if (!canLeroyEat(cookie, Eaten)) { // this individually checks if the cookie selected can be eaten.
                    System.out.println("Leroy cannot eat this cookie because it would leave no factors for Leora.");
                } else {
                    System.out.println("Leroy eats cookie " + cookie); // if checks pass, it adds the cookie to the score and continues
                    Leroy += cookie;
                    Eaten[cookie] = true;  // marks the cookie as eaten

                    // checks if there are factors for Leora to eat
                    hasfactors(Eaten, cookie, Leora);  // Let Leora eat factors

                    System.out.println("\nLeroy's new score: " + Leroy);
                    System.out.println("Leora's new score: " + Leora[0]);
                    System.out.println("\nUpdated Board:");
                    printArray(Eaten);
                }
            }
            // After Leroy's turn, the game continues if there are uneaten cookies
        }

        // After Leroy stops, Leora eats all remaining cookies
        for (int i = 2; i <= 25; i++) {
            if (!Eaten[i]) {
                System.out.println("Leora eats remaining cookie " + i);
                Leora[0] += i;
                Eaten[i] = true;  // Mark the cookie as eaten
            }
        }

        // Display final scores
        System.out.println("\nFinal Scores:");
        System.out.println("Leroy's total score: " + Leroy);
        System.out.println("Leora's total score: " + Leora[0]);

        // Determine and print the winner
        if (Leroy > Leora[0]) {
            System.out.println("Leroy wins!");
        } else if (Leora[0] > Leroy) {
            System.out.println("Leora wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    // New array generation
    public static boolean[] Eaten() {
        boolean[] start = new boolean[26];  // Array of size 26 to accommodate indices 2-25
        int generate = (int) (Math.random() * 24) + 1;  // Random number between 1 and 24
        System.out.println("There were " + generate + " cookies randomly removed.");

        // Generate 'generate' random distinct numbers between 2 and 25
        final int[] remove = new Random().ints(2, 26).distinct().limit(generate).toArray();

        // Set those positions to true in the array
        for (int j : remove) {
            start[j] = true;
        }

        // Return the boolean array
        return start;
    }

    // Function to print the boolean array
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

    // Check if Leroy can eat the cookie without violating the factor rule
    public static boolean canLeroyEat(int cookie, boolean[] Eaten) {
        for (int i = 2; i <= 25; i++) {
            if (!Eaten[i] && i != cookie && cookie % i == 0) {
                return true;  // If there's at least one factor left for Leora to eat
            }
        }
        return false;  // No factors left for Leora
    }

    // Check for factors and feed Leora
    public static void hasfactors(boolean[] newArray, int cookie, int[] Leora) {
        boolean factorsFound = false;
        for (int i = 2; i <= 25; i++) {
            // Check if `i` is a factor of `cookie` and is still available (not eaten)
            if (!newArray[i] && i != cookie && cookie % i == 0) {
                System.out.println("Leora eats cookie " + i);
                Leora[0] += i;  // Add the value of the cookie to Leora's score
                newArray[i] = true;  // Mark the cookie as eaten
                factorsFound = true;
            }
        }
        if (!factorsFound) {
            System.out.println("Leora has no factors to eat for this cookie.");
        }
    }

    // Check if any uneaten cookies have uneaten factors
    public static boolean checkUneatenCookiesForFactors(boolean[] Eaten) {
        for (int i = 2; i <= 25; i++) {
            if (!Eaten[i]) {  // Only consider uneaten cookies 'i'
                for (int j = 2; j <= 25; j++) {
                    if (!Eaten[j] && j != i && i % j == 0) {  // Check for uneaten factors 'j'
                        return true;  // Found an uneaten cookie with uneaten factors
                    }
                }
            }
        }
        return false;  // No uneaten cookies have uneaten factors
    }

}
