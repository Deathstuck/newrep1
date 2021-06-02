package com.cts.emp.service;

import com.cts.emp.dao.EmployeeDAO;
import com.cts.emp.dto.Employee;
import com.cts.emp.exception.EmployeeAdminSystemException;
import com.cts.emp.util.ApplicationUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EmployeeAdminService {
    public List<Employee> buildEmployeeList(List<String> records) {

        List<Employee> employeeList = records.parallelStream()
                .map(line -> new StringToEmployeeObjFunction().apply(line))
                .collect(Collectors.toList());
        return employeeList;
    }

    static class StringToEmployeeObjFunction implements Function<String, Employee> {
        @Override
        public Employee apply(String line) {
            String a[] = line.split(",");

            Employee e = new Employee();
            e.setEmpId(a[0]);
            e.setEmpName(a[1]);
            e.setDesignation(a[2]);
            e.setEmpType(a[3]);

            try {
                double sal = Double.parseDouble(a[4]);
                double currentSalary = ApplicationUtil.getSalary(a[3], a[2], sal);
                e.setSalary(currentSalary);

                e.setDob(ApplicationUtil.stringToDateConverter(a[5]));
            } catch (ParseException | NumberFormatException ex) {
                return null;
            }
            return e;
        }
    }

    public boolean hikeSalary(String empType, String designation) throws EmployeeAdminSystemException, ClassNotFoundException, SQLException, IOException {
        List<String> record = ApplicationUtil.readFile("inputFeed.txt");
        List<Employee> li = buildEmployeeList(record);

        if (li == null)
            return false;

        EmployeeDAO empDao = new EmployeeDAO();
        boolean add = empDao.addEmployeeDetails(li);

        return true;

    }

}
