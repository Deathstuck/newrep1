package com.cts.emp.main;

import com.cts.emp.dao.EmployeeDAO;
import com.cts.emp.dto.Employee;
import com.cts.emp.exception.EmployeeAdminSystemException;
import com.cts.emp.service.EmployeeAdminService;
import com.cts.emp.util.ApplicationUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws IOException, EmployeeAdminSystemException, ParseException, SQLException, ClassNotFoundException, URISyntaxException {
        List<String> records = ApplicationUtil.readFile("C:\\Users\\Kallal\\eclipse-workspace\\Employee Appraisal test\\inputFeed");
        EmployeeAdminService service = new EmployeeAdminService();
        List<Employee> employeeList = service.buildEmployeeList(records);
        EmployeeDAO dao = new EmployeeDAO();
        boolean status = dao.addEmployeeDetails(employeeList);
        System.out.println("Status : " + status);
    }
}
