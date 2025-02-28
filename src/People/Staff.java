package People;

import java.util.ArrayList;

public class Staff extends Person {
    private String employeeId;
    private String department;
    private double hourlySalary;
    private ArrayList<Integer> clockInTimes;
    private ArrayList<Integer> clockOutTimes;


    public Staff(String id, String firstName, String lastName, String email, String phone,
                 String employeeId, String department, double hourlySalary) {
        super(id, firstName, lastName, email, phone);
        this.employeeId = employeeId;
        this.department = department;
        this.hourlySalary = hourlySalary;
        this.clockInTimes = new ArrayList<>();
        this.clockOutTimes = new ArrayList<>();
    }


    @Override
    public String getRole() {
        return "Staff - " + department;
    }

    public void clockInt(int time) {
        clockInTimes.add(time);
    }


    public void clockOut(int time) {
        if (clockInTimes.size() > clockOutTimes.size()) {
            clockOutTimes.add(time);
        }
    }

    public double calculateHoursWorked() {
        double totalHours = 0.0;


        for (int i = 0; i < clockOutTimes.size(); i++) {
            totalHours = clockOutTimes.get(i) - clockInTimes.get(i);
        }

        return totalHours;
    }

    public double calculateSalary() {
        return calculateHoursWorked() * hourlySalary;
    }

    public String getEmployeeId() { return employeeId; }
    public String getDepartment() { return department; }
    public double getHourlySalary() { return hourlySalary; }
    public void setHourlySalary(double hourlySalary) { this.hourlySalary = hourlySalary; }

    @Override
    public String toString() {
        return super.toString() + "\n"
                + "Employee ID: " + employeeId + "\n"
                + "Department: " + department + "\n"
                + "Hourly Salary: $" + hourlySalary + "\n"
                + "Hours Worked: " + calculateHoursWorked();
    }
}
