package Testing;
import People.*;

import java.time.LocalDate;

public class Test1 {
    public static void main(String[] args) {
        Hotel grandHotel = new Hotel("Peninsula Inn", "27118 Silver Spur Rd, Palos Verdes");
        System.out.println("Created hotel: " + grandHotel.getName() + " at " + grandHotel.getAddress());

        Room standardRoom = new Room("101", "Standard", 99.99, 2);
        standardRoom.addAmenity("Wi-Fi");
        standardRoom.addAmenity("TV");

        Room deluxeRoom = new Room("201", "Deluxe", 159.99, 4);
        deluxeRoom.addAmenity("Wi-Fi");
        deluxeRoom.addAmenity("TV");
        deluxeRoom.addAmenity("Mini Bar");

        Room suiteRoom = new Room("301", "Suite", 299.99, 6);
        suiteRoom.addAmenity("Wi-Fi");
        suiteRoom.addAmenity("Kitchen");
        suiteRoom.addAmenity("Jacuzzi");

        grandHotel.addRoom(standardRoom);
        grandHotel.addRoom(deluxeRoom);
        grandHotel.addRoom(suiteRoom);

        System.out.println("\nAdded rooms to hotel:");
        for (Room room : grandHotel.getAllRooms()) {
            System.out.println(room);
            System.out.println();
        }

        Staff receptionist = new Staff("Alex Yoon", "S001", "123-456", "alex@hotel.com",
                "Receptionist", 35000.0, "Front Desk");
        Staff housekeeper = new Staff("Daniel Weiss", "S002", "123-456", "daniel@hotel.com",
                "Housekeeper", 30000.0, "Housekeeping");

        grandHotel.addStaff(receptionist);
        grandHotel.addStaff(housekeeper);

        System.out.println("\nAdded staff to hotel:");
        for (Staff staffMember : grandHotel.getAllStaff()) {
            System.out.println(staffMember.displayInfo());
            System.out.println();
        }
        Customer customer = new Customer("Sam Lin", "C001", "654 - 321", "same@gmail.com");
        grandHotel.registerCustomer(customer);

        System.out.println("\nRegistered customer: " + customer.displayInfo());
        System.out.println("\n" + grandHotel.getStatistics());
    }
}