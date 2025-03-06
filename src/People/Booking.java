package People;

import java.util.ArrayList;

public class Booking {
    private String bookingId;
    private Customer customer;
    private ArrayList<Room> rooms;
    private java.time.LocalDate checkInDate;
    private java.time.LocalDate checkOutDate;
    private double totalAmount;
    private boolean isPaid;
    private String status; // Confirmed, Pending, Cancelled, Completed
    private ArrayList<String> additionalServices;

    public Booking(String bookingId, Customer customer, java.time.LocalDate checkInDate,
                   java.time.LocalDate checkOutDate) {
        this.bookingId = bookingId;
        this.customer = customer;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.rooms = new ArrayList<Room>();
        this.additionalServices = new ArrayList<String>();
        this.isPaid = false;
        this.status = "Pending";
        this.totalAmount = 0.0;
    }

    public boolean addRoom(Room room) {
        if (room != null && room.isAvailable()) {
            rooms.add(room);
            room.setAvailable(false);
            calculateTotalAmount();
            return true;
        }
        return false;
    }


    public long calculateNights() {
        return java.time.temporal.ChronoUnit.DAYS.between(checkInDate, checkOutDate);
    }


    public void calculateTotalAmount() {
        double roomTotal = 0.0;
        // Enhanced for loop
        for (Room room : rooms) {
            roomTotal += room.getBasePrice() * calculateNights();
        }

        this.totalAmount = roomTotal;
    }

    public void addService(String service, double cost) {
        additionalServices.add(service + " ($" + cost + ")");
        totalAmount += cost;
    }

    public void setPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    /**
     * Gets the payment status of the booking
     *
     * @return true if the booking is paid, false otherwise
     */
    public boolean isPaid() {
        return isPaid;
    }

    /**
     * Sets the status of the booking
     *
     * @param status The new status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the status of the booking
     *
     * @return The booking status
     */
    public String getStatus() {
        return status;
    }

    public String getBookingId() {
        return bookingId;
    }

    public Customer getCustomer() {
        return customer;
    }

    /**
     * Gets the check-in date
     *
     * @return The check-in date
     */
    public java.time.LocalDate getCheckInDate() {
        return checkInDate;
    }

    public java.time.LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public ArrayList<String> getAdditionalServices() {
        return additionalServices;
    }

    public boolean cancelBooking() {
        if (!status.equals("Completed")) {
            for (Room room : rooms) {
                room.setAvailable(true);
            }
            status = "Cancelled";
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Booking ID: " + bookingId +
                "\nCustomer: " + customer.getName() +
                "\nCheck-in: " + checkInDate +
                "\nCheck-out: " + checkOutDate +
                "\nDuration: " + calculateNights() + " nights" +
                "\nRooms: " + rooms.size() +
                "\nTotal Amount: $" + String.format("%.2f", totalAmount) +
                "\nStatus: " + status +
                "\nPaid: " + (isPaid ? "Yes" : "No");
    }
}
