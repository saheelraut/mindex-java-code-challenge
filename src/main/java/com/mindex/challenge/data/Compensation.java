package com.mindex.challenge.data;

import java.util.Date;

public class Compensation {
    private String employeeId;
    private Employee employee;
    private Date effectiveDate;

    private Double Salary;

    public Double getSalary() {
        return Salary;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public void setSalary(Double salary) {
        Salary = salary;
    }
}
