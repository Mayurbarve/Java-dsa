package EmployeePayroll;

public class FullTimeEmployee extends Employee{

    private double bouns;

    public FullTimeEmployee(String empName, int empId, double baseSalary, double bonus)
    {
        super(empName, empId, baseSalary);
        this.bouns = bonus;
    }

    @Override
    public double calculatePay(){
        return getBaseSalary() + bouns;
    }

    @Override
    public void displayInfo(){
        System.out.println("Full Time Employee");
        System.out.println("Employee Name:" + getEmpName());
        System.out.println("Employee Id:" + getEmpId());
        System.out.println("Base Salary:" + getBaseSalary());
        System.out.println("Bouns:" + bouns);
        System.out.println("Pay:" + calculatePay());
        System.out.println();
    }


    public void displayInfo(boolean bonus){
        System.out.println("Full Time Employee");
        System.out.println("Employee Name:" + getEmpName());
        System.out.println("Employee Id:" + getEmpId());
        System.out.println("Base Salary:" + getBaseSalary());
        if(bonus){
            System.out.println("Bouns: " + bouns);
        }
        System.out.println("Pay:" + calculatePay());
        System.out.println();
    }


}
