package Hotel.People;

public class Staff extends Person {
    private String position;
    private double salary;
    private String department;
    private String workSchedule;

    public Staff(String name, String id, String phoneNumber, String email,
                 String position, double salary, String department) {
        super(name, id, phoneNumber, email);
        this.position = position;
        this.salary = salary;
        this.department = department;
        this.workSchedule = "9AM-5PM, Mon-Fri"; // Default schedule
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary >= 0) {
            this.salary = salary;
        }
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getWorkSchedule() {
        return workSchedule;
    }

    public void setWorkSchedule(String workSchedule) {
        this.workSchedule = workSchedule;
    }

    @Override
    public String displayInfo() {
        return "Staff: " + getName() +
                "\nID: " + getId() +
                "\nContact: " + getPhoneNumber() + ", " + getEmail() +
                "\nPosition: " + position +
                "\nDepartment: " + department +
                "\nSchedule: " + workSchedule;
    }
}