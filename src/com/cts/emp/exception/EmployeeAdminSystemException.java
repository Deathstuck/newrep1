package com.cts.emp.exception;


public class EmployeeAdminSystemException extends Exception {

	public EmployeeAdminSystemException() {
        super();
    }

    public EmployeeAdminSystemException(String strMsg1, Throwable strMsg2)
    {
        super(strMsg1,strMsg2);
    }

}