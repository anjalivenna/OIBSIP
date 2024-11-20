import javax.swing.JOptionPane;
import java.util.Random;

 class GuessTheNumberGame {

    // Method to play one round of the game and return the number of attempts
    public static int playRound(int round, int maxAttempts) {
        // Generate a random number between 1 and 100
        Random random = new Random();
        int numberToGuess = random.nextInt(100) + 0;
        
        int attempts = 0;
        boolean guessedCorrectly = false;

        // Game loop: Keep prompting the user until they guess correctly or run out of attempts
        while (attempts < maxAttempts && !guessedCorrectly) {
            String userInput = JOptionPane.showInputDialog(
                    null, 
                    "Round " + round + "\nGuess the number (1 to 100):", 
                    "Guess the Number", 
                    JOptionPane.QUESTION_MESSAGE);

            // If user cancels the dialog, break the loop
            if (userInput == null) {
                JOptionPane.showMessageDialog(null, "Game canceled!");
                return attempts;  // Return the attempts made so far
            }

            // Try to parse the user input to an integer
            try {
                int userGuess = Integer.parseInt(userInput);
                attempts++;
                
                // Check if the guess is correct, higher, or lower
                if (userGuess == numberToGuess) {
                    guessedCorrectly = true;
                    JOptionPane.showMessageDialog(null, "Correct! You guessed the number in " + attempts + " attempts.");
                } else if (userGuess < numberToGuess) {
                    JOptionPane.showMessageDialog(null, "Too low! Try again.");
                } else {
                    JOptionPane.showMessageDialog(null, "Too high! Try again.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number.");
            }
        }
        
        // If the user runs out of attempts
        if (!guessedCorrectly) {
            JOptionPane.showMessageDialog(null, "Sorry, you've run out of attempts. The number was " + numberToGuess + ".");
        }

        return attempts;  // Return the number of attempts used
    }

    // Main method to start the game
    public static void main(String[] args) {
        int rounds = 5; // Set the number of rounds to play
        int maxAttempts = 10; // Set the max attempts per round
        int score = 0; // Player's score

        // Play multiple rounds
        for (int round = 1; round <= rounds; round++) {
            // Get the number of attempts for this round
            int attempts = playRound(round, maxAttempts);
            
            // Calculate the score: for simplicity, the score is just the number of attempts taken
            // Lower attempts = higher score
            int roundScore = maxAttempts - Math.min(maxAttempts, attempts);
            score += roundScore;

            // Ask the player if they want to continue to the next round
            int option = JOptionPane.showConfirmDialog(null, 
                "Your score for this round: " + roundScore + "\nTotal Score: " + score + "\nDo you want to continue to the next round?", 
                "Next Round", 
                JOptionPane.YES_NO_OPTION);

            // If the user selects 'No', exit the game
            if (option == JOptionPane.NO_OPTION) {
                break;
            }
        }

        // Display final score after all rounds
        JOptionPane.showMessageDialog(null, "Game over! Your final score is: " + score);
    }
}
