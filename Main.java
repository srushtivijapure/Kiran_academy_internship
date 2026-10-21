import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        while (true) {
            System.out.println("\n===== CAB BOOKING CHATBOT =====");
            System.out.println("1. Book Ride");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");

            int choice = readChoice(sc);

            if (choice == 2) {
                System.out.println("Thank you for using Cab Booking Chatbot!");
                System.out.println("Goodbye!");
                break;
            }

            if (choice != 1) {
                System.out.println("Invalid choice! Please select 1 or 2.");
                continue;
            }

            System.out.println("\nSelect Cab Type:");
            System.out.println("1. Auto - Rs. 12/km");
            System.out.println("2. Sedan - Rs. 18/km");
            System.out.println("3. SUV - Rs. 25/km");
            System.out.print("Enter your choice: ");

            int cabChoice = readChoice(sc);
            String cabType;
            int rate;

            if (cabChoice == 1) {
                cabType = "Auto";
                rate = 12;
            } else if (cabChoice == 2) {
                cabType = "Sedan";
                rate = 18;
            } else if (cabChoice == 3) {
                cabType = "SUV";
                rate = 25;
            } else {
                System.out.println("Invalid cab choice! Returning to main menu.");
                continue;
            }

            System.out.println("\nSelect Route:");
            System.out.println("1. Pune Station to Shivajinagar (5 km)");
            System.out.println("2. Swargate to Kothrud (8 km)");
            System.out.println("3. Hinjawadi to Wakad (10 km)");
            System.out.print("Enter your choice: ");

            int routeChoice = readChoice(sc);
            String source;
            String destination;
            int distance;

            if (routeChoice == 1) {
                source = "Pune Station";
                destination = "Shivajinagar";
                distance = 5;
            } else if (routeChoice == 2) {
                source = "Swargate";
                destination = "Kothrud";
                distance = 8;
            } else if (routeChoice == 3) {
                source = "Hinjawadi";
                destination = "Wakad";
                distance = 10;
            } else {
                System.out.println("Invalid route choice! Returning to main menu.");
                continue;
            }

            int fare = distance * rate;

            System.out.println("\n===== BOOKING DETAILS =====");
            System.out.println("Cab Type: " + cabType);
            System.out.println("Source: " + source);
            System.out.println("Destination: " + destination);
            System.out.println("Distance: " + distance + " km");
            System.out.println("Rate: Rs. " + rate + "/km");
            System.out.println("Total Fare: Rs. " + fare);

            System.out.println("\nConfirm your booking:");
            System.out.println("1. Confirm & Share OTP");
            System.out.println("2. Cancel Ride");
            System.out.print("Enter your choice: ");

            int confirmChoice = readChoice(sc);

            if (confirmChoice == 2) {
                System.out.println("Ride cancelled successfully.");
                System.out.println("No OTP has been shared.");
                continue;
            }

            if (confirmChoice != 1) {
                System.out.println("Invalid confirmation choice. Ride cancelled.");
                System.out.println("No OTP has been shared.");
                continue;
            }

            int otp = 1000 + random.nextInt(9000);

            System.out.println("\nYour OTP is: " + otp);
            System.out.println("Booking confirmed successfully!");
            System.out.println("IMPORTANT: Once OTP is shared, ride cancellation is NOT allowed.");
            System.out.println("Returning to main menu...");
        }

        sc.close();
    }

    private static int readChoice(Scanner sc) {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}