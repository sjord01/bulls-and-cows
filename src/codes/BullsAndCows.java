import java.util.Random;
import java.util.Scanner;

public class BullsAndCows
{
    private static final int MIN_RANGE = 1;
    private static final int MAX_RANGE = 10;
    private static final int COUNTING_RULE = 1;
    private static final int MAX_CODE_LENGTH = 4;
    private static final int MAX_TURNS = 10;
    private static final int FIRST_TURN = 1;

    public static void main(String[] args)
    {
        playBullsAndCowsGame();
    }

    public static void playBullsAndCowsGame()
    {

        System.out.println("Okay, let's start a game!");
        final String secretCode;
        final Scanner scanner;

        secretCode = generateSecretCode();
        scanner = new Scanner(System.in);

        int turn = FIRST_TURN;
        while (turn <= MAX_TURNS)
        {
            System.out.println("Turn " + turn + ":");

            String guess = scanner.nextLine();

            if (!isValidGuess(guess))
            {
                System.out.println("Invalid guess. Please enter a 4-digit number.");
                continue;
            }

            if (guess.equals(secretCode))
            {
                System.out.println("Grade: 4 bulls");
                System.out.println("Congratulations! You guessed the secret code.");
                break;
            }
            else
            {
                int bulls = countBulls(secretCode, guess);
                int cows = countCows(secretCode, guess);
                System.out.println("Grade: " + bulls + " bull(s) and " + cows + " cow(s)");
                turn++;
            }
        }

        if (turn > MAX_TURNS) {
            System.out.println("Sorry, you've run out of turns. The secret code was: " + secretCode);
        }
            scanner.close();
        }

    /**
     * Generate a 4-digit secret code for the player to guess
     * @return  a 4-digit secret code for the player to guess
     */
    public static String generateSecretCode()
    {
        final Random random = new Random();
        final StringBuilder sb;

        sb = new StringBuilder();

        while (sb.length() < MAX_CODE_LENGTH)
        {
            int digit = random.nextInt(MIN_RANGE + MAX_RANGE - COUNTING_RULE) + MIN_RANGE;

            if (sb.indexOf(String.valueOf(digit)) == -1)
            {
                sb.append(digit);
            }
        }
        return sb.toString();
    }

    /**
     * Validates if the user-input guess is 4-digit
     * @param   guess the user-input
     * @return  True if user input is a 4-digit number, else False
     */
    public static boolean isValidGuess(final String guess)
    {
        return guess.matches("\\d{4}");
    }


    public static int countBulls(String secretCode, String guess)
    {
        int bulls = 0;

        for (int i = 0; i < secretCode.length(); i++) {
            if (secretCode.charAt(i) == guess.charAt(i)) {
                bulls++;
            }
        }
        return bulls;
    }

    public static int countCows(final String secretCode,
                                final String guess)
    {
        int cows = 0;

        for (int i = 0; i < secretCode.length(); i++)
        {
            char currentDigit = guess.charAt(i);
            if (secretCode.contains(String.valueOf(currentDigit)) &&
                    secretCode.charAt(i) != currentDigit)
            {
                cows++;
            }
        }
        return cows;
    }
}