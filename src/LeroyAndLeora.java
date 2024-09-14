import java.util.Scanner;
import java.util.Random;
// it was really annoying to create my own function to generate the new int every time, because changing the falses and trues to their real values and then returning it was weird, so i created another function that justs prints the array.

public class LeroyAndLeora {

    public static void main(String[] args) {
//        INITIAL SETUP
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

//        START GAME, COLLECT AND VALIDATE LEROY'S INPUT
        Scanner scanner = new Scanner(System.in);
        while (!allCookiesEaten(newArray)) {
            int Leroy_curr_turn = 0;
            while (true) {
                System.out.print("Leroy's Turn. What cookie should he eat: ");
                int cookie = scanner.nextInt();

//        checker to confirm cookie is in range (and possibly not already eater)
                if (cookie > 25) {
                    System.out.println("Cookie out of range, try again");
                } else if (newArray[cookie]) {
                    System.out.println("Cookie already eaten, try again");
                } else {
                    System.out.println("Leroy eats cookie " + cookie);
                    Leroy_curr_turn += cookie;
                    newArray[cookie] = true;
                    for (int i = 2; i <= 25; i++) {
                        if (Leroy_curr_turn % i == 0 && i != Leroy_curr_turn) {
                            System.out.println("Leora eats cookie " + i);
                            Leora += i;
                            newArray[i] = true;
                        }
                    }
                    System.out.println(Leroy);
                    break;
                }
            }

//        now need to find all multiples of Leroys score and feed them to that fat bitch Leora.
            Leroy += Leroy_curr_turn;
            System.out.println("\n\nLeroy's new score: " + Leroy);
            System.out.println("Leora's new score: " + Leora);
            System.out.println();
            System.out.println("\nUpdated Board:");
            printArray(newArray);
        }

    }


    // new array generation
    public static boolean[] createNewArray() {
        boolean[] start = new boolean[26];  // Array of size 26 to accommodate indices 1-25

//        STACK OVERFLOW https://stackoverflow.com/questions/22584244/how-to-generate-6-different-random-numbers-in-java
        final int[] remove = new Random().ints(1, 26).distinct().limit(4).toArray();

        // Set those 4 positions to true in the array. IntelliJ autocorrected to an 'enhanced for' loop
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
}




































































































