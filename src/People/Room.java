package People;

import java.util.ArrayList;

public class Room {
    private String roomNumber;
    private String roomType;
    private Double basePrice;
    private Integer maxOccupancy;
    private boolean isAvailable;
    private boolean needsMaintenance;
    private ArrayList<String> amenities;

    public Room(String roomNumber, String roomType, Double basePrice, Integer maxOccupancy) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.basePrice = basePrice;
        this.maxOccupancy = maxOccupancy;
        this.isAvailable = true;
        this.needsMaintenance = false;
        this.amenities = new ArrayList<String>();
    }

    public String getRoomNumber() {
        return roomNumber;
    }


    public String getRoomType() {
        return roomType;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public boolean setBasePrice(Double basePrice) {
        if (basePrice > 0.0) {
            this.basePrice = basePrice;
            return true;
        }
        return false;
    }


    public Integer getMaxOccupancy() {
        return maxOccupancy;
    }

    public boolean isAvailable() {
        return isAvailable && !needsMaintenance;
    }


    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public boolean needsMaintenance() {
        return needsMaintenance;
    }

    public void setNeedsMaintenance(boolean needsMaintenance) {
        this.needsMaintenance = needsMaintenance;
    }

    public void addAmenity(String amenity) {
        amenities.add(amenity);
    }

    public ArrayList<String> getAmenities() {
        return amenities;
    }

    public double calculatePrice(double discountPercentage) {
        if (discountPercentage < 0 || discountPercentage > 100) {
            return basePrice;
        }

        double discount = basePrice * discountPercentage / 100;
        return basePrice - discount;
    }

    @Override
    public String toString() {
        String status = isAvailable ? "Available" : "Occupied";
        if (needsMaintenance) {
            status = "Under Maintenance";
        }

        return "Room " + roomNumber +
                " (" + roomType + ")" +
                "\nPrice: $" + basePrice + " per night" +
                "\nMax Occupancy: " + maxOccupancy +
                "\nStatus: " + status +
                "\nAmenities: " + String.join(", ", amenities);
    }
}