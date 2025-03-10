package Hotel;
import Hotel.Functions.Booking;
import Hotel.Functions.Room;
import Hotel.People.Customer;
import Hotel.People.Staff;

import java.util.ArrayList;
import java.util.Iterator;

public class Hotel {
    private String name;
    private String address;
    private ArrayList<Room> rooms;
    private ArrayList<Staff> staff;
    private ArrayList<Booking> bookings;
    private ArrayList<Customer> customers;
    private ArrayList<Booking> waitingList;

    public Hotel(String name, String address) {
        this.name = name;
        this.address = address;
        this.rooms = new ArrayList<Room>();
        this.staff = new ArrayList<Staff>();
        this.bookings = new ArrayList<Booking>();
        this.customers = new ArrayList<Customer>();
        this.waitingList = new ArrayList<Booking>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void addStaff(Staff staffMember) {
        staff.add(staffMember);
    }

    public void registerCustomer(Customer customer) {
        customers.add(customer);
    }

    public ArrayList<Room> findAvailableRooms(java.time.LocalDate checkIn, java.time.LocalDate checkOut) {
        ArrayList<Room> availableRooms = new ArrayList<Room>();

        Iterator<Room> roomIterator = rooms.iterator();
        while (roomIterator.hasNext()) {
            Room room = roomIterator.next();
            if (room.isAvailable() && !isRoomBooked(room, checkIn, checkOut)) {
                availableRooms.add(room);
            }
        }

        return availableRooms;
    }

    private boolean isRoomBooked(Room room, java.time.LocalDate checkIn, java.time.LocalDate checkOut) {
        for (Booking booking : bookings) {
            if (booking.getStatus().equals("Cancelled")) {
                continue;
            }

            ArrayList<Room> bookedRooms = booking.getRooms();
            if (bookedRooms.contains(room)) {
                if (!(checkOut.isBefore(booking.getCheckInDate()) ||
                        checkIn.isAfter(booking.getCheckOutDate()))) {
                    return true;
                }
            }
        }
        return false;
    }

    public Booking createBooking(Customer customer, java.time.LocalDate checkIn, java.time.LocalDate checkOut) {
        String bookingId = generateBookingId();
        Booking newBooking = new Booking(bookingId, customer, checkIn, checkOut);
        return newBooking;
    }

    public boolean confirmBooking(Booking booking) {
        if (booking != null) {
            booking.setStatus("Confirmed");
            bookings.add(booking);

            booking.getCustomer().addBooking(booking);

            double amount = booking.getTotalAmount();
            int points = (int) (amount / 10);
            booking.getCustomer().addLoyaltyPoints(points);

            return true;
        }
        return false;
    }

    public void addToWaitingList(Booking booking) {
        waitingList.add(booking);
        booking.setStatus("Waiting");
    }

    public boolean processCheckout(String bookingId) {
        Booking booking = findBookingById(bookingId);
        if (booking != null && booking.getStatus().equals("Confirmed")) {
            booking.setStatus("Completed");

            for (Room room : booking.getRooms()) {
                room.setAvailable(true);
            }

            processWaitingList();

            return true;
        }
        return false;
    }

    private void processWaitingList() {
        if (!waitingList.isEmpty()) {
            Iterator<Booking> waitingIterator = waitingList.iterator();
            while (waitingIterator.hasNext()) {
                Booking waitingBooking = waitingIterator.next();
                ArrayList<Room> availableRooms = findAvailableRooms(
                        waitingBooking.getCheckInDate(),
                        waitingBooking.getCheckOutDate()
                );

                if (availableRooms.size() >= waitingBooking.getRooms().size()) {
                    waitingBooking.setStatus("Confirmed");
                    bookings.add(waitingBooking);
                    waitingIterator.remove();
                }
            }
        }
    }

    public Booking findBookingById(String bookingId) {
        for (Booking booking : bookings) {
            if (booking.getBookingId().equals(bookingId)) {
                return booking;
            }
        }
        return null;
    }

    private String generateBookingId() {
        String timeStamp = Long.toString(System.currentTimeMillis());
        String bookingId = "BK" + timeStamp.substring(timeStamp.length() - 6);
        return bookingId;
    }

    public String getStatistics() {
        int totalRooms = rooms.size();
        int occupiedRooms = 0;
        int totalBookings = bookings.size();
        double totalRevenue = 0.0;

        for (Room room : rooms) {
            if (!room.isAvailable()) {
                occupiedRooms++;
            }
        }

        for (Booking booking : bookings) {
            if (booking.isPaid()) {
                totalRevenue += booking.getTotalAmount();
            }
        }

        return
        "Hotel Statistics\n" +
        "---------------\n" +
        "Total Rooms: " + totalRooms + "\n" +
        "Occupied Rooms: " + occupiedRooms + "\n" +
        "Occupancy Rate: " + (totalRooms > 0 ? (occupiedRooms * 100 / totalRooms) : 0) + "%\n" +
        "Total Bookings: " + totalBookings + "\n" +
        "Revenue: $" + String.format("%.2f", totalRevenue);

    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public ArrayList<Room> getAllRooms() {
        return rooms;
    }

    public ArrayList<Staff> getAllStaff() {
        return staff;
    }

    public ArrayList<Booking> getAllBookings() {
        return bookings;
    }

    public ArrayList<Customer> getAllCustomers() {
        return customers;
    }
}