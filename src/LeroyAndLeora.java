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
        boolean[] Eaten = Eaten(); //returns array with all randomly eaten cookies set to true
        while (true) {
            System.out.println("Board:");
            printArray(Eaten); // converts all false to the numbers and all true to dashes
            if (!checkUneatenCookiesForFactors(Eaten)) { // if this is true, factors exist for uneaten cookies.
                System.out.println("No valid moves for Leroy. Ending the game.");
                break;  // End the game immediately if no valid moves NEED TO HAVE AFTER BREAK SYSTEM. AT THE END OF ALL THIS, LEORA GETS REST OF THE COOKIES
            }
            Scanner scanner = new Scanner(System.in); // a scanner checker not part of logic
            int Leroy_curr_turn = 0; // a local variable
            System.out.print("Leroy's Turn. What cookie should he eat (or 0 to stop): ");
            int cookie = scanner.nextInt();
                // Check if Leroy wants to stop
            if (cookie == 0) {
                System.out.println("Leroy has stopped eating cookies.");
                break;
            }
                // Validate the cookie choice
            else if (cookie > 25 || cookie < 2) {
                System.out.println("Cookie out of range, game over");
                break;
                }
            else if (Eaten[cookie]) {
                System.out.println("Cookie already eaten, game over");
                break;
                }
            else if (!canLeroyEat(cookie, Eaten)) {
                System.out.println("Leroy cannot eat this cookie because it would leave no factors for Leora. Game Over");
                break;
                }
            else {
                System.out.println("Leroy eats cookie " + cookie);
                Leroy_curr_turn += cookie;
                Eaten[cookie] = true;  // marks this specific cookie as eaten
                if (!checkUneatenCookiesForFactors(Eaten)) { // if this is true, factors exist for uneaten cookies.
                    System.out.println("No valid moves for Leroy. Ending the game.");
                    break;  // End the game immediately if no valid moves NEED TO HAVE AFTER BREAK SYSTEM. AT THE END OF ALL THIS, LEORA GETS REST OF THE COOKIES
                }
                eatfactors(Eaten, cookie, Leora);  // Let Leora eat factors
                Leroy += Leroy_curr_turn;
                System.out.println("\nLeroy's new score: " + Leroy);
                System.out.println("Leora's new score: " + Leora[0]);

                }
            }
        // After Leroy stops or the game ends, Leora eats all remaining cookies
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
            return start;

        // Return the boolean array

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

    // Check if Leroy can eat the cookie without violating the factor rule
    public static boolean canLeroyEat(int cookie, boolean[] Eaten) {
        for (int i = 2; i <= 25; i++) {
            if (!Eaten[i] && i != cookie && cookie % i == 0) {
                return true;  // If there's at least one factor left for Leora to eat
            }
        }
        return false;  // No factors left for Leora to eat so must break the program
    }

    // Check for factors and feed Leora
    public static void eatfactors(boolean[] Eaten, int cookie, int[] Leora) {
        for (int i = 2; i <= 25; i++) { //for loop
            if (!Eaten[i] && i != cookie && cookie % i == 0) { // checks if current iteration of i is uneaten and not equal to the cookie and is a factor of cookie
                System.out.println("Leora eats cookie " + i); // prints that Leora eats each one of these
                Leora[0] += i;  // Adds each of those cookies to Leora's score
                Eaten[i] = true;  // Mark the cookie as eaten
            }
        }

    }

    // Check if any uneaten cookies have uneaten factors
    public static boolean checkUneatenCookiesForFactors(boolean[] Eaten) {
        for (int i = 2; i <= 25; i++) { // for loop
            if (!Eaten[i]) {  // returns all the uneaten cookies
                for (int j = 2; j <= 25; j++) { // another for loop
                    if (!Eaten[j] && j != i && i % j == 0) {  // checks if any cookie is uneaten and not equal to the current iteration of uneaten cookie and is a factor of the current iteration of the uneaten cookie
                        return true;  // if there ever is a factor that exists, the whole function returns true
                    }
                }
            }
        }
        return false;  // no factors are left for the uneaten cookies OR no uneaten cookies are left.
    }

}
