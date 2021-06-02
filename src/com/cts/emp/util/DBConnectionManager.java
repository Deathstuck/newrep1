package com.cts.emp.util;

import com.cts.emp.exception.EmployeeAdminSystemException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnectionManager {

    private static Connection con = null;
    private static DBConnectionManager instance;
    private static Properties props = new Properties();

    public DBConnectionManager() throws EmployeeAdminSystemException, IOException, ClassNotFoundException, SQLException {
        try {
            FileInputStream fis = null;

            fis = new FileInputStream("database.properties");
            props.load(fis);

            // load the Driver Class
            Class.forName(props.getProperty("db.classname"));

            // create the connection now
            con = DriverManager.getConnection(props.getProperty("db.url"), props.getProperty("db.username"), props.getProperty("db.password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // TYPE YOUR CODE HERE

        //return con;
    }

    public static DBConnectionManager getInstance() throws EmployeeAdminSystemException, ClassNotFoundException, IOException, SQLException {
        try {
            if (instance == null)
                instance = new DBConnectionManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

    public Connection openConnection() {
        try {
            DBConnectionManager dbc  = newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }


    private static DBConnectionManager newInstance() throws EmployeeAdminSystemException {

        return instance;
    }

    public Connection getConnection() {
        return con;
    }

    public void loadProperties() throws EmployeeAdminSystemException {


    }
}
