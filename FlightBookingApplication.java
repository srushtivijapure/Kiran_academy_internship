
import java.util.ArrayList;
import java.util.Scanner;

// Abstract class: Passenger
abstract class Passenger {
    private String name;
    private int age;

    public Passenger(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public abstract String getPassengerType();

    public abstract double getDiscountPercentage();
}

// Inheritance: RegularPassenger extends Passenger
class RegularPassenger extends Passenger {

    public RegularPassenger(String name, int age) {
        super(name, age);
    }

    @Override
    public String getPassengerType() {
        return "Regular";
    }

    @Override
    public double getDiscountPercentage() {
        return 0.0;
    }
}

// Inheritance: StudentPassenger extends Passenger
class StudentPassenger extends Passenger {

    public StudentPassenger(String name, int age) {
        super(name, age);
    }

    @Override
    public String getPassengerType() {
        return "Student";
    }

    @Override
    public double getDiscountPercentage() {
        return 0.10;
    }
}

// Flight class
class Flight {
    private String flightCode;
    private String airline;
    private String source;
    private String destination;
    private int availableSeats;
    private double baseFare;

    public Flight(String flightCode, String airline,
                  String source, String destination,
                  int availableSeats, double baseFare) {
        this.flightCode = flightCode;
        this.airline = airline;
        this.source = source;
        this.destination = destination;
        this.availableSeats = availableSeats;
        this.baseFare = baseFare;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public String getAirline() {
        return airline;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public double getBaseFare() {
        return baseFare;
    }

    public boolean hasEnoughSeats(int tickets) {
        return tickets > 0 && tickets <= availableSeats;
    }

    public void bookSeats(int tickets) {
        if (hasEnoughSeats(tickets)) {
            availableSeats -= tickets;
        }
    }

    public void displayFlight() {
        System.out.println("------------------------------------------");
        System.out.println("Flight Code: " + flightCode);
        System.out.println("Airline: " + airline);
        System.out.println("Route: " + source + " to " + destination);
        System.out.println("Available Seats: " + availableSeats);
        System.out.printf("Base Fare: Rs. %.2f%n", baseFare);
    }
}

// Booking class
class Booking {
    private String bookingId;
    private Flight flight;
    private Passenger passenger;
    private int numberOfTickets;
    private double subtotal;
    private double discount;
    private double totalFare;

    public Booking(String bookingId, Flight flight,
                   Passenger passenger, int numberOfTickets) {
        this.bookingId = bookingId;
        this.flight = flight;
        this.passenger = passenger;
        this.numberOfTickets = numberOfTickets;

        // Calculate fare
        subtotal = flight.getBaseFare() * numberOfTickets;

        // Polymorphism: uses the actual passenger's discount
        discount = subtotal * passenger.getDiscountPercentage();

        totalFare = subtotal - discount;
    }

    public void displayBookingSummary() {
        System.out.println("\n========== BOOKING SUMMARY ==========");
        System.out.println("Booking ID: " + bookingId);
        System.out.println("Flight Code: " + flight.getFlightCode());
        System.out.println("Airline: " + flight.getAirline());
        System.out.println("Route: " + flight.getSource()
                           + " to " + flight.getDestination());

        System.out.println("Passenger Name: " + passenger.getName());
        System.out.println("Passenger Age: " + passenger.getAge());
        System.out.println("Passenger Type: "
                           + passenger.getPassengerType());
        System.out.println("Number of Tickets: " + numberOfTickets);

        System.out.printf("Base Fare per Ticket: Rs. %.2f%n",
                          flight.getBaseFare());
        System.out.printf("Subtotal: Rs. %.2f%n", subtotal);
        System.out.printf("Discount (%.0f%%): Rs. %.2f%n",
                          passenger.getDiscountPercentage() * 100,
                          discount);
        System.out.printf("Final Amount: Rs. %.2f%n", totalFare);

        System.out.println("=====================================");
    }
}

// Main application class
public class FlightBookingApplication {
    private Scanner sc = new Scanner(System.in);
    private ArrayList<Flight> flights = new ArrayList<>();
    private ArrayList<Booking> bookings = new ArrayList<>();
    private int nextBookingNumber = 1001;

    // Add predefined flights
    public FlightBookingApplication() {
        flights.add(new Flight(
            "PN101", "Air India", "Pune", "Delhi", 20, 4000));

        flights.add(new Flight(
            "MB202", "IndiGo", "Mumbai", "Bengaluru", 25, 3500));

        flights.add(new Flight(
            "PN303", "SpiceJet", "Pune", "Hyderabad", 15, 3000));
    }

    // Display all available flights
    public void displayFlights() {
        System.out.println("\n========== AVAILABLE FLIGHTS ==========");

        for (Flight flight : flights) {
            flight.displayFlight();
        }

        System.out.println("------------------------------------------");
    }

    // Find a flight using its code
    public Flight findFlightByCode(String code) {
        for (Flight flight : flights) {
            if (flight.getFlightCode().equalsIgnoreCase(code)) {
                return flight;
            }
        }

        return null;
    }

    // Read an integer safely
    private int readInteger(String message) {
        System.out.print(message);
        String input = sc.nextLine().trim();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // Book a flight
    public void bookFlight() {
        System.out.println("\n========== BOOK A FLIGHT ==========");

        System.out.print("Enter flight code: ");
        String code = sc.nextLine().trim();

        Flight selectedFlight = findFlightByCode(code);

        if (selectedFlight == null) {
            System.out.println("Booking failed: Invalid flight code.");
            return;
        }

        System.out.print("Enter passenger name: ");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Booking failed: Passenger name cannot be blank.");
            return;
        }

        int age = readInteger("Enter age: ");

        if (age <= 0) {
            System.out.println("Booking failed: Age must be greater than zero.");
            return;
        }

        System.out.print("Passenger type (Regular/Student): ");
        String type = sc.nextLine().trim();

        Passenger passenger;

        if (type.equalsIgnoreCase("Regular")) {
            passenger = new RegularPassenger(name, age);
        } else if (type.equalsIgnoreCase("Student")) {
            passenger = new StudentPassenger(name, age);
        } else {
            System.out.println(
                "Booking failed: Enter Regular or Student.");
            return;
        }

        int tickets = readInteger("Enter number of tickets: ");

        if (tickets <= 0) {
            System.out.println(
                "Booking failed: Ticket count must be greater than zero.");
            return;
        }

        // Validate seat availability before confirming
        if (!selectedFlight.hasEnoughSeats(tickets)) {
            System.out.println(
                "Booking failed: Not enough seats available.");
            System.out.println("Available seats: "
                               + selectedFlight.getAvailableSeats());
            System.out.println("No booking was created.");
            return;
        }

        // Create booking only after all validations succeed
        String bookingId = "BK" + nextBookingNumber;

        Booking booking = new Booking(
            bookingId, selectedFlight, passenger, tickets);

        // Update seats and save the confirmed booking
        selectedFlight.bookSeats(tickets);
        bookings.add(booking);
        nextBookingNumber++;

        System.out.println("\nBooking confirmed!");
        booking.displayBookingSummary();
    }

    // Display the latest confirmed booking
    public void viewBooking() {
        if (bookings.isEmpty()) {
            System.out.println("\nNo bookings available yet.");
            return;
        }

        Booking latestBooking = bookings.get(bookings.size() - 1);
        latestBooking.displayBookingSummary();
    }

    // Main menu loop
    public void start() {
        System.out.println("Welcome to Kiran Air Booking!");

        while (true) {
            System.out.println("\n========== MAIN MENU ==========");
            System.out.println("1. View Flights");
            System.out.println("2. Book Flight");
            System.out.println("3. View Booking");
            System.out.println("4. Exit");

            int choice = readInteger("Enter choice: ");

            if (choice == 1) {
                displayFlights();
            } else if (choice == 2) {
                bookFlight();
            } else if (choice == 3) {
                viewBooking();
            } else if (choice == 4) {
                System.out.println(
                    "Thank you for using Kiran Air Booking!");
                break;
            } else {
                System.out.println(
                    "Invalid choice. Please select 1, 2, 3, or 4.");
            }
        }

        sc.close();
    }

    public static void main(String[] args) {
        FlightBookingApplication app = new FlightBookingApplication();
        app.start();
    }
}
