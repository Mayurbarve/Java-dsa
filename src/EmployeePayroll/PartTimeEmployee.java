package EmployeePayroll;

public class PartTimeEmployee extends Employee{
    private int hoursWorked;
    private double hourlyRate;

    public PartTimeEmployee(String empName, int empId, int  hoursWorked, double hourlyRate){
        super(empName, empId, 0);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculatePay(){
        return hoursWorked * hourlyRate;
    }

    @Override
    public void displayInfo(){
        System.out.println("Part Time Employee");
        System.out.println("Employee Name:" + getEmpName());
        System.out.println("Employee Id:" + getEmpId());
        System.out.println("Hourly Rate: " + hourlyRate);
        System.out.println("Hours Worked: " + hoursWorked);
        System.out.println("Pay:" + calculatePay());
        System.out.println();
    }
}
