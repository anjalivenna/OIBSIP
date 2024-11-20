import java.util.Scanner;

class OnlineReservationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User user = new User();

        // Login Form
        System.out.println("Welcome to the Online Reservation System");
        System.out.print("Enter Login ID: ");
        String loginId = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (user.login(loginId, password)) {
            System.out.println("Login Successful!");
            // Reservation System
            System.out.println("Fill in the Reservation Form:");
            System.out.print("Enter Your Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Train Number: ");
            String trainNumber = scanner.nextLine();
            System.out.print("Enter Class Type: ");
            String classType = scanner.nextLine();
            System.out.print("Enter Date of Journey (YYYY-MM-DD): ");
            String dateOfJourney = scanner.nextLine();
            System.out.print("From (Place): ");
            String fromPlace = scanner.nextLine();
            System.out.print("To (Destination): ");
            String toDestination = scanner.nextLine();

            Reservation reservation = new Reservation(name, trainNumber, classType, dateOfJourney, fromPlace, toDestination);
            reservation.insert();

            // Cancellation Form
            System.out.print("Enter PNR Number to Cancel: ");
            String pnrNumber = scanner.nextLine();
            reservation.cancel(pnrNumber);
        } else {
            System.out.println("Invalid Login Credentials.");
        }
        scanner.close();
    }
}

class User {
    public boolean login(String loginId, String password) {
        // Logic for validating user credentials
        return "user".equals(loginId) && "password".equals(password);
    }
}

class Reservation {
    private String name;
    private String trainNumber;
    private String classType;
    private String dateOfJourney;
    private String fromPlace;
    private String toDestination;

    public Reservation(String name, String trainNumber, String classType, String dateOfJourney, String fromPlace, String toDestination) {
        this.name = name;
        this.trainNumber = trainNumber;
        this.classType = classType;
        this.dateOfJourney = dateOfJourney;
        this.fromPlace = fromPlace;
        this.toDestination = toDestination;
    }

    public void insert() {
        // Logic to save reservation details to the database
        System.out.println("Reservation details saved successfully.");
    }

    public void cancel(String pnrNumber) {
        // Logic to cancel reservation using PNR number
        System.out.println("Cancellation details for PNR: " + pnrNumber);
        System.out.print("Confirm Cancellation? (OK to confirm): ");
        Scanner scanner = new Scanner(System.in);
        String confirmation = scanner.nextLine();
        if ("OK".equalsIgnoreCase(confirmation)) {
            System.out.println("Ticket cancelled successfully.");
        } else {
            System.out.println("Cancellation aborted.");
        }
    }
}
