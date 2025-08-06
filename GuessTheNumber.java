import java.util.*;

class Game {
    private int random_num;
    private int guess;
    private int noOfGuesses = 0;
    private List<Integer> previousGuesses = new ArrayList<>();
    private final int MAX_ATTEMPTS = 10;

    public int getNoOfGuesses() {
        return noOfGuesses;
    }

    Game() {
        Random rand = new Random();
        random_num = rand.nextInt(100) + 1; 
    }

    boolean TakeUserInput(Scanner sc) {
        System.out.print("Guess the number (1-100): ");
        try {
            guess = sc.nextInt();
            if (guess < 1 || guess > 100) {
                System.out.println("Please enter a number between 1 and 100.");
                return false;
            }
            previousGuesses.add(guess);
            return true;
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter an integer.");
            sc.next(); 
            return false;
        }
    }

    boolean IsCorrectNumber() {
        noOfGuesses++;
        if (guess == random_num) {
            System.out.format("Yes, you got it right! It was %d.\nYou guessed in %d attempts.\n", guess, noOfGuesses);
            return true;
        } else if (guess < random_num) {
            System.out.println("Too low...");
        } else {
            System.out.println("Too high...");
        }
        System.out.println("Previous guesses: " + previousGuesses);
        System.out.println("Attempts left: " + (MAX_ATTEMPTS - noOfGuesses));
        return false;
    }

    boolean hasAttemptsLeft() {
        return noOfGuesses < MAX_ATTEMPTS;
    }
}                                                                                                                                                                                                                   

public class GuessTheNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean playAgain;
        do {
            Game g = new Game();
            boolean b = false;
            System.out.println("Welcome to Guess The Number Game!");
            while (!b && g.hasAttemptsLeft()) {
                if (g.TakeUserInput(sc)) {
                    b = g.IsCorrectNumber();
                }
            }
            if (!b) {
                System.out.println("Sorry, you've used all attempts! Better luck next time.");
            }
            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = sc.next().equalsIgnoreCase("yes");
        } while (playAgain);
        System.out.println("Thank you for playing!");
        sc.close();
    }
}
