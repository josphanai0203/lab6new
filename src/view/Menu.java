/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.CompanyManagement;
import model.Company;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Menu {

    static CompanyManagement cm = new CompanyManagement();
    static Company c = new Company();
    String[] op = {"List All Customer", "Search Customer", "Add New Customer", "Delete Customer", "Save", "Update Customer", "Exit"};

    public void displayMenu() {
        c.config();

        MenuGeneric<String> m = new MenuGeneric<String>("Menu", op) {
            @Override
            public boolean execute(int choice) {
                switch (choice) {
                    case 1:
                        cm.display();
                        break;
                    case 2:
                        cm.search();
                        break;
                    case 3:
                        cm.add();
                        break;
                    case 4:
                        cm.deleteCustomer();
                        break;
                    case 5:
                        cm.saveFile();
                        break;
                    case 6:
                        cm.updateInfo();
                        break;
                    case 7:
                        return true;

                    default:
                        break;
                }
                return false;
            }

        };
        m.run(); 
    }
}
