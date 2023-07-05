package model;

import controller.CompanyRepository;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.function.Predicate;
import model.Customer;

/**
 *
 * @author Admin
 */
public class Company {

    public static ArrayList<Customer> customerList = new ArrayList<>();
    public static CompanyRepository cp = new CompanyRepository();
    private static final String BASE_PATH = new File("").getAbsolutePath();
    private static final String READ_PATH = "\\src\\data\\customer.txt";
    private static final String WRITE_PATH = "\\src\\data\\customer1.txt";

    public void config() {
        try {
            cp.readFile(BASE_PATH + READ_PATH);

        } catch (ParseException e) {
            e.printStackTrace();

        }
    }
    public boolean saveFile(){
        return cp.savefile(BASE_PATH+WRITE_PATH,customerList,(c)->c.toSaveString());
    }
    public void addCustomer(String id, String name, String phone, Calendar date) {
        customerList.add(new Customer(id, name, phone, date));
    }

    public void displayListCustomer() {
        customerList.forEach((t) -> {
            System.out.println(t);
        });
    }

    public ArrayList<Customer> search(Predicate<Customer> p) {
        ArrayList<Customer> rs = new ArrayList<>();
        for (Customer s : customerList) {
            if (p.test(s)) {
                rs.add(s);
            }
        }
        return rs;

    }

    public int totalCustomer() {
        return customerList.size();
    }

    public void displayCustomerList(ArrayList<Customer> list) {
        for (Customer c : list) {
            System.out.println(c);
        }

    }
    public void deleteCustomer(Customer c){
        customerList.remove(c);
    }
    public boolean update(Customer up, String data, String type){
        switch (type) {
            case "NAME":
                up.setName(data);
                return true;
            case "PHONE":
                up.setPhone(data);
                return true;
            case "BIRTHDAY":
                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                try {
                Date birthDay = formatter.parse(data);
                Calendar date = Calendar.getInstance();
                date.setTime(birthDay);
                   up.setDateOfBirth(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return true;
                
            default:
                 return false;
                
        }
    }
}

