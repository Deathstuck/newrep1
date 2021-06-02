package com.cts.emp.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ApplicationUtil {
   public static List<String> readFile() throws IOException {
       return readFile("C:\\Users\\Kallal\\eclipse-workspace\\Employee Appraisal test\\src\\inputFeed.txt");
    }

    public static List<String> readFile(String inputFeed) throws IOException {
        List<String> empList = new ArrayList<>();

        FileReader fr = new FileReader(new File(inputFeed));
        try (BufferedReader br = new BufferedReader(fr)) {
            String l;
            while ((l = br.readLine()) != null) {
                String[] a = l.split(",");
                if( a.length>3) {
                    String empType = a[3];
                    if (checkIfFullTimeEmployee(empType)) {
                        empList.add(l);
                    }
                }

            }
        }
        return empList;


    }

    public static Date stringToDateConverter(String stringDate) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(stringDate);
    }

    private static boolean checkIfFullTimeEmployee(String empType) {
        return "F".equals(empType);
    }

    public static java.sql.Date utilToSqlDateConverter(java.util.Date utDate) {
        return new java.sql.Date(utDate.getTime());
    }

    public static double getSalary(String emptype, String desig, double currentSalary) {

        if ("F".equals(emptype) && desig.equalsIgnoreCase("clerk")) {
            currentSalary = 1.07 * currentSalary;
        } else if ("F".equals(emptype) && desig.equalsIgnoreCase("manager")) {
            currentSalary = 1.1 * currentSalary;
        } else if ("C".equals(emptype) && desig.equalsIgnoreCase("clerk")) {
            currentSalary = 1.03 * currentSalary;
        } else if ("C".equals(emptype) && desig.equalsIgnoreCase("manager")) {
            currentSalary = 1.05 * currentSalary;
        }


        return currentSalary;
    }


}
