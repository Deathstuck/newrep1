package com.cts.emp.dao;

import com.cts.emp.dto.Employee;
import com.cts.emp.exception.EmployeeAdminSystemException;
import com.cts.emp.util.ApplicationUtil;
import com.cts.emp.util.DBConnectionManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class EmployeeDAO {

    public boolean addEmployeeDetails(List<Employee> li) throws EmployeeAdminSystemException, SQLException, ClassNotFoundException, IOException {
        Connection con = null;
        PreparedStatement myStmt = null;
        try {
            DBConnectionManager d = new DBConnectionManager();
            con = d.getConnection();
            if(con.getAutoCommit())
            	con.setAutoCommit(false);

            //String sql = "INSERT INTO EMPLOYEE VALUES(" +"'"+ li.get(i).getEmpId() +"'"+ "," + "'" + li.get(i).getEmpName() + "'" + "," +"'"+ li.get(i).getDesignation() +"'"+ "," +"'"+ li.get(i).getEmpType() +"'" +"," + li.get(i).getSalary() +"," + li.get(i).getDob() + ")" + "on duplicate key update salary="  + li.get(i).getSalary()  + ";" ;
            myStmt = con.prepareStatement("insert into employee" +
                    "(empno,empname,desig,emptype,salary,dob)" +
                    "values" +
                    "(?,?,?,?,?,?)");

            int count = 0, batchSize = 50;
            for (Employee e : li) {
                myStmt.setString(1, e.getEmpId());
                myStmt.setString(2, e.getEmpName());
                myStmt.setString(3, e.getDesignation());
                myStmt.setString(4, e.getEmpType());
                myStmt.setDouble(5, e.getSalary());
                java.sql.Date date = ApplicationUtil.utilToSqlDateConverter(e.getDob());
                myStmt.setDate(6, date);
                myStmt.addBatch();

                count++;

                if (count % batchSize == 0) {
                    myStmt.executeBatch();
                }
            }
            myStmt.executeBatch();
            return true;
        } catch (SQLException e) {
            if (con != null) {
                con.rollback();
            }
           // throw new EmployeeAdminSystemException();
        } catch (Exception e) {
            throw new EmployeeAdminSystemException();
        } finally {
            if (myStmt != null) {
                myStmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
		return true;
    }

}