/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Company;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer;
import util.ValidatorUtils;

/**
 *
 * @author Admin
 */
public class CompanyRepository {

    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";

    public <T> boolean savefile(String path, List<T> list, Function<T, String> f) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(path));
            for (T t : list) {
                bw.write(f.apply(t));
                bw.write(NEW_LINE_SEPARATOR);
            }

        } catch (IOException e) {
            System.out.println("Error When save file !!!");
            return false;
        } finally {
            try {
                bw.flush();
                bw.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                return false;
            }
        }
        return true;
    }

    public static Calendar toCalendar(String str) {
        Calendar c = Calendar.getInstance();
        DateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        Date d = null;
        try {
            d = f.parse(str);
        } catch (ParseException ex) {
            Logger.getLogger(CompanyRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        c.setTime(d);
        return c;
    }

    public static ArrayList<Customer> readFile(String path) {
        ArrayList<Customer> result = null;
        BufferedReader br = null;
        try {
            String line;
            br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
                List<String> strLine = parseCsvLine(line);
                String id = strLine.get(0);
                if(!id.matches(ValidatorUtils.REGEX_CUSTOMER_ID)){
                    continue;
                }
                String name = strLine.get(1);
                String phone = strLine.get(2);
                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Date birthDay = formatter.parse(strLine.get(3));
                Calendar date = Calendar.getInstance();
                date.setTime(birthDay);

                Customer s = new Customer(id, name, phone, date);
                Company.customerList.add(s);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return result;
    }

    public static List<String> parseCsvLine(String csvLine) {
        List<String> result = new ArrayList<>();
        if (csvLine != null) {
            String[] splitData = csvLine.split(COMMA_DELIMITER);
            for (int i = 0; i < splitData.length; i++) {
                result.add(splitData[i]);
            }
        }
        return result;
    }
}
