package Features;
import java.util.ArrayList;

public class Room implements Billable {

    public enum RoomType {
        SINGLE(1, 100.0),
        DOUBLE(2, 150.0),
        TRIPLE(3, 200.0),
        SUITE(4, 300.0);

        private final int capacity;
        private final double basePrice;

        RoomType(int capacity, double basePrice) {
            this.capacity = capacity;
            this.basePrice = basePrice;
        }

        public int getCapacity() { return capacity; }
        public double getBasePrice() { return basePrice; }
    }

    public enum RoomStatus {
        AVAILABLE, OCCUPIED, MAINTENANCE, RESERVED
    }

    private String roomNumber;
    private int floor;
    private RoomType type;
    private RoomStatus status;
    private boolean cleaned;
    private ArrayList<String> amenities;

    public Room(String roomNumber, int floor, RoomType type) {
        this.roomNumber = roomNumber;
        this.floor = floor;
        this.type = type;
        this.status = RoomStatus.AVAILABLE;
        this.cleaned = true;
        this.amenities = new ArrayList<>();

        // Add default amenities based on room type
        amenities.add("Wi-Fi");
        amenities.add("TV");

        if (type == RoomType.SUITE) {
            amenities.add("Mini Bar");
            amenities.add("Jacuzzi");
        }
    }

    @Override
    public double calculateCost() {
        return type.getBasePrice();
    }

    @Override
    public String getDescription() {
        return type.name() + " Room " + roomNumber + " on Floor " + floor;
    }

    public String getRoomNumber() { return roomNumber; }
    public int getFloor() { return floor; }
    public RoomType getType() { return type; }

    public RoomStatus getStatus() { return status; }
    public void setStatus(RoomStatus status) { this.status = status; }

    public boolean isCleaned() { return cleaned; }
    public void setCleaned(boolean cleaned) { this.cleaned = cleaned; }

    public ArrayList<String> getAmenities() { return amenities; }
    public void addAmenity(String amenity) { amenities.add(amenity); }


    @Override
    public String toString() {
        return "Room: " + roomNumber + "\n"
                + "Floor: " + floor + "\n"
                + "Type: " + type + "\n"
                + "Status: " + status + "\n"
                + "Cleaned: " + (cleaned ? "Yes" : "No") + "\n"
                + "Amenities: " + String.join(", ", amenities) + "\n"
                + "Price per night: $" + calculateCost();
    }
}
