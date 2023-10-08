package com.example.employeemanager.dto;

public class EmployeeSearchDTO {
    private  String name;
    private String fromBirthDate;
    private String toBirthDate;
    private String gender;
    private String salary;
    private String phoneNumber;
    private String departmentId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFromBirthDate() {
        return fromBirthDate;
    }

    public void setFromBirthDate(String fromBirthDate) {
        this.fromBirthDate = fromBirthDate;
    }

    public String getToBirthDate() {
        return toBirthDate;
    }

    public void setToBirthDate(String toBirthDate) {
        this.toBirthDate = toBirthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
}
