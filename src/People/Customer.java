package People;

import java.util.ArrayList;

public class Customer extends Person {
    private int loyaltyPoints;
    private String membershipTier; // Regular, Silver, Gold, Platinum
    private ArrayList<String> specialRequests;
    private ArrayList<Booking> bookingHistory;

    public Customer(String name, String id, String phoneNumber, String email) {
        super(name, id, phoneNumber, email);
        this.loyaltyPoints = 0;
        this.membershipTier = "Regular";
        this.specialRequests = new ArrayList<String>();
        this.bookingHistory = new ArrayList<Booking>();
    }

    /**
     * Adds loyalty points to the customer's account
     *
     * @param points Number of points to add
     */
    public void addLoyaltyPoints(int points) {
        if (points > 0) {
            this.loyaltyPoints += points;
            updateMembershipTier();
        }
    }

    /**
     * Updates the membership tier based on loyalty points
     * Satisfies requirement #3 - if-then-else statements
     */
    private void updateMembershipTier() {
        if (loyaltyPoints >= 10000) {
            membershipTier = "Platinum";
        } else if (loyaltyPoints >= 5000) {
            membershipTier = "Gold";
        } else if (loyaltyPoints >= 2000) {
            membershipTier = "Silver";
        } else {
            membershipTier = "Regular";
        }
    }

    /**
     * Adds a special request for the customer
     *
     * @param request The special request to add
     */
    public void addSpecialRequest(String request) {
        specialRequests.add(request);
    }

    /**
     * Adds a booking to the customer's history
     *
     * @param booking The booking to add
     */
    public void addBooking(Booking booking) {
        bookingHistory.add(booking);
    }

    /**
     * Gets the customer's booking history
     *
     * @return ArrayList of the customer's bookings
     */
    public ArrayList<Booking> getBookingHistory() {
        return bookingHistory;
    }

    /**
     * Gets the customer's loyalty points
     *
     * @return The customer's loyalty points
     */
    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    /**
     * Gets the customer's membership tier
     *
     * @return The customer's membership tier
     */
    public String getMembershipTier() {
        return membershipTier;
    }

    /**
     * Gets the customer's special requests
     *
     * @return ArrayList of the customer's special requests
     */
    public ArrayList<String> getSpecialRequests() {
        return specialRequests;
    }

    /**
     * Implementation of abstract method from Person class
     * Satisfies requirement #7 - Implementation of inherited method
     *
     * @return String representation of customer details
     */
    @Override
    public String displayInfo() {
        return "Customer: " + getName() +
                "\nID: " + getId() +
                "\nContact: " + getPhoneNumber() + ", " + getEmail() +
                "\nLoyalty Status: " + membershipTier +
                "\nPoints: " + loyaltyPoints;
    }
}