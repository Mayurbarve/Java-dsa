package EmployeePayroll;

public class Employee {
    private String empName;
    private int empId;
    private double baseSalary;

    public Employee(String empName, int empId, double baseSalary) {
        this.empName = empName;
        this.empId = empId;
        this.baseSalary = baseSalary;
    }

    public String getEmpName() {
        return empName;
    }

    public int getEmpId() {
        return empId;
    }
    public double getBaseSalary() {
        return baseSalary;
    }
    public double calculatePay(){
        return baseSalary;
    }


    public void displayInfo(){
        System.out.println("Employee Name:" + empName);
        System.out.println("Employee Id:" + empId);
        System.out.println("Base Salary:" + calculatePay());
        System.out.println();
    }

}

