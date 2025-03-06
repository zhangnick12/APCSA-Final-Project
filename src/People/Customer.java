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

    public void addLoyaltyPoints(int points) {
        if (points > 0) {
            this.loyaltyPoints += points;
            updateMembershipTier();
        }
    }

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

    public void addBooking(Booking booking) {
        bookingHistory.add(booking);
    }

    public ArrayList<Booking> getBookingHistory() {
        return bookingHistory;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public String getMembershipTier() {
        return membershipTier;
    }

    public ArrayList<String> getSpecialRequests() {
        return specialRequests;
    }

    @Override
    public String displayInfo() {
        return "Customer: " + getName() +
                "\nID: " + getId() +
                "\nContact: " + getPhoneNumber() + ", " + getEmail() +
                "\nLoyalty Status: " + membershipTier +
                "\nPoints: " + loyaltyPoints;
    }
}