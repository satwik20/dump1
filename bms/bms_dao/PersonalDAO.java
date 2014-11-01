package com.cts.bms_dao;

import java.sql.Connection;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cts.bms_to.PersonalloanTO;

public class PersonalDAO {
	public static Connection getConnection() throws SQLException {
		/* DataBase Connectivity */

		Connection con = null;
		Driver driver = new oracle.jdbc.driver.OracleDriver();
		DriverManager.registerDriver(driver);
		String uname = "bms"; // username for the database
		String pass = "bms"; // password for the database
		String url = "jdbc:oracle:thin:@//localhost:1521/XE";
		con = DriverManager.getConnection(url, uname, pass);

		return con;

	}

	/* Function for Loan ID Generator */

	public String loan_IdGenerator(String cust_id) {
		String cus_no = "";

		Integer max = Integer.parseInt(cust_id.substring(2)) + 1;

		if (max <= 9) {
			cus_no = cus_no + "HL" + "00" + max;

		}
		if (max <= 99 && max > 9) {
			cus_no = cus_no + "HL" + "0" + max;

		}
		if (max <= 999 && max > 99) {
			cus_no = cus_no + "HL" + max;

		}

		return cus_no;

	}

	public boolean insertdao(PersonalloanTO to) throws SQLException {
		boolean flag = false;
		Connection con = getConnection();
		String qu = "select max(loan_id) from housing_loan";
		PreparedStatement stat = con.prepareStatement(qu);
		ResultSet rs = stat.executeQuery();
		String res = "";

		/* EMI Calculation*/
		
		double loanAmount = Double.parseDouble(to.getLoanAmount());
		double rate = Double.parseDouble(to.getRateOfInterest());
		double rateOfInterest = rate / 100;
		int loanDuration = Integer.parseInt(to.getDurationOfLoan());
		double EMI = 0;
		EMI = (loanAmount * rateOfInterest)
				* (Math.pow((1 + rateOfInterest), loanDuration));
		EMI = EMI / ((Math.pow((1 + rateOfInterest), loanDuration)) - 1);

		String emi = Double.toString(EMI);

		if (rs.next()) {
			res = rs.getString(1);
		}

		String id = loan_IdGenerator(res);

		/* Inserting values into personal housing loan */
		
		Connection con1 = getConnection();
		String query = "insert into Housing_Loan(loan_id,Loan_Type,Loan_Amount,Apply_Date,Issue_Date,Rate_Of_Interest,Duration_Of_Loan,Annual_Income,Comapany_name,Designation,Total_Exp,Total_exp_with_company,Loan_Emi,loan_status,customer_id)"
				+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement statement = con1.prepareStatement(query);

		statement.setString(1, id);
		statement.setString(2, to.getLoanType());
		statement.setString(3, to.getLoanAmount());
		statement.setString(4, to.getApplyDate());
		statement.setString(5, to.getIssueDate());
		statement.setString(6, to.getRateOfInterest());
		statement.setString(7, to.getDurationOfLoan());
		statement.setString(8, to.getIncome());
		statement.setString(9, to.getName());
		statement.setString(10, to.getDesign());
		statement.setString(11, to.getTexperience());
		statement.setString(12, to.getExpwithcompany());
		statement.setString(13, emi);
		statement.setString(14, "opening");
		statement.setString(15, to.getCustomerId());

		if (statement.executeUpdate() > 0) {
			flag = true;
		}

		return flag;

	}

}
