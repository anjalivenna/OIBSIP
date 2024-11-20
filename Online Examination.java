import java.util.*;
import java.util.concurrent.TimeUnit;

class OnlineExaminationSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExaminationSystem examSystem = new ExaminationSystem();
        
        // Login functionality
        if (examSystem.login(scanner)) {
            examSystem.showMenu(scanner);
        }
    }

    // User class to represent individual user data
    public static class User {
        private String userId;
        private String password;
        private String name;
        private String email;
        
        public User(String userId, String password, String name, String email) {
            this.userId = userId;
            this.password = password;
            this.name = name;
            this.email = email;
        }

        // Getters and setters for user profile
        public String getUserId() {
            return userId;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    // MCQManager class to handle the MCQ functionality
    public static class MCQManager {
        private Map<Integer, String> questions;
        private Map<Integer, String[]> options;
        private Map<Integer, String> correctAnswers;

        public MCQManager() {
            questions = new HashMap<>();
            options = new HashMap<>();
            correctAnswers = new HashMap<>();

            // Sample questions, options, and correct answers
            questions.put(1, "What is the capital of France?");
            options.put(1, new String[] {"A. Berlin", "B. Madrid", "C. Paris", "D. Rome"});
            correctAnswers.put(1, "C");

            questions.put(2, "Which planet is known as the Red Planet?");
            options.put(2, new String[] {"A. Earth", "B. Mars", "C. Jupiter", "D. Venus"});
            correctAnswers.put(2, "B");
        }

        public void startQuiz(Scanner scanner) {
            int score = 0;
            for (int i = 1; i <= questions.size(); i++) {
                System.out.println("Q" + i + ": " + questions.get(i));
                String[] opts = options.get(i);
                for (String opt : opts) {
                    System.out.println(opt);
                }

                System.out.print("Enter your answer: ");
                String answer = scanner.nextLine().toUpperCase();

                if (answer.equals(correctAnswers.get(i))) {
                    score++;
                }
            }

            System.out.println("Quiz Finished! Your score: " + score + "/" + questions.size());
        }

        public void startQuizWithTimer(Scanner scanner, long timeLimitInSeconds) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("\nTime's up! Auto-submitting your answers.");
                }
            }, timeLimitInSeconds * 1000);

            startQuiz(scanner);
            timer.cancel();
        }
    }

    // ExaminationSystem class to handle the main functionalities
    public static class ExaminationSystem {
        private User currentUser;
        private User user1 = new User("user1", "1234", "John Doe", "john@example.com");
        private User user2 = new User("user2", "5678", "Jane Doe", "jane@example.com");
        private MCQManager mcqManager = new MCQManager();
        private Scanner scanner = new Scanner(System.in);

        // Method to handle login functionality
        public boolean login(Scanner scanner) {
            System.out.println("Welcome to the Online Examination System!");
            System.out.print("Enter User ID: ");
            String userId = scanner.nextLine();
            System.out.print("Enter Password: ");
            String password = scanner.nextLine();

            if ((userId.equals(user1.getUserId()) && password.equals(user1.getPassword()))) {
                currentUser = user1;
                System.out.println("Login successful.");
                return true;
            } else if (userId.equals(user2.getUserId()) && password.equals(user2.getPassword())) {
                currentUser = user2;
                System.out.println("Login successful.");
                return true;
            } else {
                System.out.println("Invalid credentials. Please try again.");
                return false;
            }
        }

        // Method to show the menu options
        public void showMenu(Scanner scanner) {
            while (true) {
                System.out.println("\n--- Exam Menu ---");
                System.out.println("1. Update Profile");
                System.out.println("2. Take MCQ Quiz");
                System.out.println("3. Logout");

                System.out.print("Select an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                switch (choice) {
                    case 1:
                        updateProfile(scanner);
                        break;
                    case 2:
                        System.out.println("You have 30 seconds to complete the quiz.");
                        mcqManager.startQuizWithTimer(scanner, 30); // Timer set to 30 seconds
                        break;
                    case 3:
                        System.out.println("Logging out...");
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        }

        // Method to handle profile update
        private void updateProfile(Scanner scanner) {
            System.out.println("\n--- Update Profile ---");
            System.out.print("Enter new name: ");
            String newName = scanner.nextLine();
            currentUser.setName(newName);

            System.out.print("Enter new email: ");
            String newEmail = scanner.nextLine();
            currentUser.setEmail(newEmail);

            System.out.print("Enter new password: ");
            String newPassword = scanner.nextLine();
            currentUser.setPassword(newPassword);

            System.out.println("Profile updated successfully!");
        }
    }
}
