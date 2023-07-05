/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Calendar;

/**
 *
 * @author Admin
 */
public class Customer {

    private String customerID;
    private String name;
    private String phone;
    private Calendar dateOfBirth;

    public Customer(String customerID, String name, String phone, Calendar dateOfBirth) {
        this.customerID = customerID;
        this.name = name;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDateOfBirth() {
        return String.format("%d/%d/%d", dateOfBirth.get(Calendar.DATE), dateOfBirth.get(Calendar.MONTH) + 1, dateOfBirth.get(Calendar.YEAR));
    
    }

    public void setDateOfBirth(Calendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public String toSaveString(){
        return customerID + "," + name + "," + phone +","+ getDateOfBirth();
    
    }
    @Override
    public String toString() {
        return "Customer ID = " + customerID + "\tName = " + name + "\tPhone = " + phone +"\t Date Of Birth: "+ getDateOfBirth();
    }
    
}
