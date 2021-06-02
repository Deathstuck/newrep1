package com.cts.emp.dto;

import java.util.Date;

//DO NOT CHANGE THE NAMES OR DATA TYPES OR VISIBILITY OF THE BELOW MEMBER VARIABLES
//TODO add your code here
public class Employee {
    private String empId;
    private String empName;
    private String designation;
    private String empType;
    private double salary;
    private Date dob;

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEmpType() {
        return empType;
    }

    public void setEmpType(String empType) {
        this.empType = empType;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Employee() {
    }

    public Employee(String empId, String empName, String designation, String empType, double salary, Date dob) {
        super();
        this.empId = empId;
        this.empName = empName;
        this.designation = designation;
        this.empType = empType;
        this.salary = salary;
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Employee [empId=" + empId + ", empName=" + empName + ", designation=" + designation + ", empType="
                + empType + ", salary=" + salary + ", dob=" + dob + "]";
    }


}