package People;

import java.util.ArrayList;

public class Customer extends Person {
    private String loyaltyId;
    private int loyaltyPoints;
    private String membershipTier; // "Standard", "Silver", "Gold", "Platinum"
    private ArrayList<Reservation> bookingHistory;
    private ArrayList<String> preferences;

    public Customer(String id, String firstName, String lastName, String email, String phone,
                    String loyaltyId) {
        super(id, firstName, lastName, email, phone);
        this.loyaltyId = loyaltyId;
        this.loyaltyPoints = 0;
        this.membershipTier = "Standard";
        this.bookingHistory = new ArrayList<>();
        this.preferences = new ArrayList<>();
    }


    @Override
    public String getRole() {
        return "Customer";
    }

    public void addReservation(Reservation reservation) {
        bookingHistory.add(reservation);
    }

    public void addPreference(String preference) {
        preferences.add(preference);
    }

    public void addLoyaltyPoints(int points) {
        this.loyaltyPoints += points;
        updateMembershipTier();
    }

    private void updateMembershipTier() {
        if (loyaltyPoints >= 50000) {
            membershipTier = "Platinum";
        } else if (loyaltyPoints >= 25000) {
            membershipTier = "Gold";
        } else if (loyaltyPoints >= 10000) {
            membershipTier = "Silver";
        } else {
            membershipTier = "Standard";
        }
    }

    public String getLoyaltyId() { return loyaltyId; }
    public int getLoyaltyPoints() { return loyaltyPoints; }
    public String getMembershipTier() { return membershipTier; }
    public ArrayList<Reservation> getBookingHistory() { return bookingHistory; }
    public ArrayList<String> getPreferences() { return preferences; }

    @Override
    public String toString() {
        return super.toString() + "\n"
                + "Loyalty ID: " + loyaltyId + "\n"
                + "Points: " + loyaltyPoints + "\n"
                + "Tier: " + membershipTier + "\n"
                + "Preferences: " + String.join(", ", preferences) + "\n"
                + "Number of Past Bookings: " + bookingHistory.size();
    }
}
