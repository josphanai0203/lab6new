/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Company;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import model.Customer;
import util.Input;
import util.ValidatorUtils;
import view.MenuGeneric;

/**
 *
 * @author Admin
 */
public class CompanyManagement {

    static Company c = new Company();
    static Scanner sc = new Scanner(System.in);

    public void add() {
        String id = Input.enterString("Customer ID ", ValidatorUtils.REGEX_CUSTOMER_ID, (idStr) -> {
            for (Customer c : Company.customerList) {
                if (c.getCustomerID().equals(idStr)) {
                    return true;
                }
            }
            return false;
        });
        String name = Input.enterString("Name");
        String phone = Input.enterString("Phone Number (10 numbers)", ValidatorUtils.REGEX_PHONE_NUMBER);
        Calendar date = Input.enterDate("Date Of Birth (dd/mm/yyyy)", "dd/MM/yyyy");
        c.addCustomer(id, name, phone, date);
    }

    public void display() {
        System.out.println("-----------------------");
        c.displayListCustomer();
        System.out.println("-----------------------");
        System.out.println("Total : " + c.totalCustomer() + " customers");
    }

    public void search() {
        displayFindMenu();
    }

    public void displayFindMenu() {
        String[] op = {"Find By CustomerID", "Find By Name", "Find By Phone", "Find By Date", "Return"};
        MenuGeneric<String> findMenu;
        findMenu = new MenuGeneric<String>("Find Menu", op) {
            @Override
            public boolean execute(int choice) {
                ArrayList<Customer> searchArr;
                switch (choice) {
                    case 1:
                        String id = Input.enterString("Customer ID ", ValidatorUtils.REGEX_CUSTOMER_ID);
                        searchArr = c.search((c) -> c.getCustomerID().equals(id));
                        if (searchArr.isEmpty()) {
                            System.out.println("Customer not found ");
                        } else {
                            c.displayCustomerList(searchArr);
                        }
                        break;
                    case 2:
                        String name = Input.enterString("Name Search");
                        searchArr = c.search(customer -> customer.getName().equals(name));
                        if (searchArr.isEmpty()) {
                            System.out.println("Customer not found ");
                        } else {
                            c.displayCustomerList(searchArr);
                        }
                        break;
                    case 3:
                        String numStr = Input.enterString("Phone Number Start With ", ValidatorUtils.REGEX_NUMBER);
                        searchArr = c.search(c -> c.getPhone().startsWith(numStr));
                        if (searchArr.isEmpty()) {
                            System.out.println("No Customer found");
                        } else {
                            c.displayCustomerList(searchArr);
                        }
                        break;
                    case 4:
                        String date = Input.enterString("Date (dd/MM/yyyy)");
                        searchArr = c.search((c) -> c.getDateOfBirth().equals(date));
                        if (searchArr.isEmpty()) {
                            System.out.println("No Customer found");
                        } else {
                            c.displayCustomerList(searchArr);
                        }

                        break;
                    case 5:
                        return true;
                    default:
                        System.out.println("You must choice 1 to 5 ! Please re-enter");
                }
                return false;
            }
        };
        findMenu.run();
    }

    public void saveFile() {
        boolean isSave = c.saveFile();
        if (isSave) {
            System.out.println("Save File Success");
        } else {
            System.out.println("Error When Save File");
        }
    }

    public void deleteCustomer() {
        String id = Input.enterString("ID delete", ValidatorUtils.REGEX_CUSTOMER_ID);
        ArrayList<Customer> delete = c.search((c) -> c.getCustomerID().equals(id));
        if (delete.isEmpty()) {
            System.out.println("The Customer not exist");
        } else {
            c.deleteCustomer(delete.get(0));
            System.out.println("Detele Customer Success");
        }
    }

    public void updateInfo() {
        String id = Input.enterString("ID Update", ValidatorUtils.REGEX_CUSTOMER_ID);
        ArrayList<Customer> update = c.search((c) -> c.getCustomerID().equals(id));
        if (update.isEmpty()) {
            System.out.println("The Customer not exist");
        } else {
            displayMenuUpdate(update.get(0));
            System.out.println("Detele Customer Success");
        }
    }

    public void displayMenuUpdate(Customer updateCustomer) {
        String[] op = {"Update name", "Update phone", "Update birthday", "Return"};
        MenuGeneric<String> menuUpdate = new MenuGeneric<String>("Update Menu", op) {
            @Override
            public boolean execute(int choice) {
                switch (choice) {
                    case 1:
                        String name = Input.enterString("New Name");
                        if (c.update(updateCustomer, name, "NAME")) {
                            System.out.println("Update success");
                        } else {
                            System.out.println("Update fail");
                        }
                        break;
                    case 2:
                        String phone = Input.enterString("New Phone", ValidatorUtils.REGEX_PHONE_NUMBER);
                        if (c.update(updateCustomer, phone, "PHONE")) {
                            System.out.println("Update success");
                        } else {
                            System.out.println("Update fail");
                        }
                        break;
                    case 3:
                        String date = Input.enterDate("New BirthDay(dd/mm/yyyy)");
                        if (c.update(updateCustomer, date, "BIRTHDAY")) {
                            System.out.println("Update success");
                        } else {
                            System.out.println("Update fail");
                        }

                        break;
                    case 4:
                        return true;

                    default:
                        break;

                }
                return false;
            }
        };
        menuUpdate.run();
    }
}
