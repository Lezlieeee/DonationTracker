import java.util.PriorityQueue;
import java.util.Scanner;

class Donation implements Comparable<Donation> {
    private String donorName;
    private double amount;

    public Donation(String donorName, double amount) {
        this.donorName = donorName;
        this.amount = amount;
    }

    public String getDonorName() {
        return donorName;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public int compareTo(Donation other) {
        return Double.compare(other.amount, this.amount); // Max-Heap: Higher donations come first
    }

    @Override
    public String toString() {
        return "Donor: " + donorName + " | Amount: $" + amount;
    }
}

public class DonationTracker {
    private static PriorityQueue<Donation> donationQueue = new PriorityQueue<>();
    private static double totalDonations = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Community Donation Tracker ---");
            System.out.println("1. Add a Donation");
            System.out.println("2. View Top Donors");
            System.out.println("3. View Total Donations");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addDonation(scanner);
                    break;
                case 2:
                    viewTopDonors(scanner);
                    break;
                case 3:
                    viewTotalDonations();
                    break;
                case 4:
                    running = false;
                    System.out.println("Exiting... Thank you for tracking donations!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    private static void addDonation(Scanner scanner) {
        System.out.print("Enter donor's name: ");
        String donorName = scanner.nextLine();

        System.out.print("Enter donation amount: $");
        double amount = scanner.nextDouble();

        donationQueue.add(new Donation(donorName, amount));
        totalDonations += amount;

        System.out.println("Donation added successfully!");
    }

    private static void viewTopDonors(Scanner scanner) {
        if (donationQueue.isEmpty()) {
            System.out.println("No donations yet.");
            return;
        }

        System.out.print("Enter the number of top donors to view: ");
        int n = scanner.nextInt();

        System.out.println("--- Top Donors ---");
        PriorityQueue<Donation> tempQueue = new PriorityQueue<>(donationQueue); // Copy queue to preserve original
        for (int i = 0; i < n && !tempQueue.isEmpty(); i++) {
            System.out.println(tempQueue.poll());
        }
    }

    private static void viewTotalDonations() {
        System.out.printf("Total Donations Collected: $%.2f\n", totalDonations);
    }
}