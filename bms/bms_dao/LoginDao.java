package com.cts.bms_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.cts.bms_DbConnection.DatabaseConnection;
import com.cts.bms_to.LoginTo;
import com.cts.bms_to.RegistrationTo;

public class LoginDao {
	
	public String retrieve_CustomerId(LoginTo to) 
	{
		String cus_id="";
		try
		{
		Connection con= DatabaseConnection.getConnection();
		String userName=to.getUserName();
		String password=to.getPassword();
		String qrystring = "select customer_id from personal_details where user_name=? and password=?";
		PreparedStatement ps1=con.prepareStatement(qrystring);
		ps1.setString(1,userName);
		ps1.setString(2,password);
		/*ps1.setString(1, to.getUserName());
		ps1.setString( 2,to.getPassword());*/
		ResultSet rs1= ps1.executeQuery();
		
		if(rs1.next())
		{
			
		cus_id=rs1.getString("customer_id");
		}
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
				
		return cus_id;
	}

	
	public ArrayList<String> retrieve_States()
	{
	ArrayList<String> state=new ArrayList<String>();
	Connection con=null;
	Statement st=null;
	ResultSet rs=null;
		try {
			con=DatabaseConnection.getConnection();
			st=con.createStatement();
			rs=st.executeQuery("select distinct state from country ");
			while(rs.next())
			{
				state.add(rs.getString("state"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	return state;
	}
	
	public ArrayList<String> retrieve_CountryName()
	{
	ArrayList<String> country=new ArrayList<String>();
	Connection con=null;
	Statement st=null;
	ResultSet rs=null;
		try {
			con=DatabaseConnection.getConnection();
			st=con.createStatement();
			rs=st.executeQuery("select distinct country_name from country ");
			while(rs.next())
			{
				country.add(rs.getString("country_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	return country;
	}
	
	public ArrayList<String> retrieve_Branch()
	{
	ArrayList<String> branch=new ArrayList<String>();
	Connection con=null;
	Statement st=null;
	ResultSet rs=null;
		try {
			con=DatabaseConnection.getConnection();
			st=con.createStatement();
			rs=st.executeQuery("select distinct branch_name from ifsc ");
			while(rs.next())
			{
				branch.add(rs.getString("branch_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	return branch;
	}
	
	
	public RegistrationTo retrieveBank_Details(String custid)
	{
		Connection con=null;

		PreparedStatement ps=null;
		PreparedStatement ps1=null;
		PreparedStatement ps2=null;
		RegistrationTo to=new RegistrationTo();
		try
		{
			con=DatabaseConnection.getConnection();
			ps=con.prepareStatement("select p.NAME,p.country,a.ACCOUNT_NUMBER,a.ACCOUNT_TYPE,a.INITIAL_DEPOSIT,c.amount from account_details a,personal_details p,customer_transaction c where a.customer_id=p.customer_id and c.account_no=a.account_number and a.customer_id=?");
			ps.setString(1,custid);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next())
			{
				
				to.setAccountNo(rs.getString("ACCOUNT_NUMBER"));
				to.setName(rs.getString("NAME"));
				to.setAccount_Type(rs.getString("ACCOUNT_TYPE"));
				to.setCountry(rs.getString("country"));
				to.setInitial_Amount(rs.getString("INITIAL_DEPOSIT"));
				to.setAmount(rs.getString("amount"));
				to.setCustomer_id(custid);
				
			}
			
			ps=con.prepareStatement("select max(total_amount)from customer_transaction where account_no=?");
			ps.setString(1,to.getAccountNo());
			ResultSet rs2=ps.executeQuery();
			if(rs2.next())
				to.setTotal_amount(rs2.getString("max(total_amount)"));
			
			ps1=con.prepareStatement("select LOAN_ID,LOAN_EMI,LOAN_STATUS from  housing_loan where customer_id=?");
			ps1.setString(1,custid);
			ResultSet rs1=ps1.executeQuery();
			if(rs1.next())
			{
				to.setEmi(rs1.getString("LOAN_EMI"));
				to.setLoan_Status(rs1.getString("LOAN_STATUS"));
				to.setLoan_Id(rs1.getString("LOAN_ID"));
				
			}
			/*if(Integer.parseInt(to.getEmi())<=0)
			{
				ps2=con.prepareStatement("select LOAN_EMI,LOAN_STATUS from  educational_loan where customer_id=?");
				ps2.setString(1,custid);
				ResultSet rs2=ps2.executeQuery();
				if(rs2.next())
				{
					to.setEmi(rs2.getInt("LOAN_EMI")+"");
					to.setLoan_Status(rs2.getString("LOAN_STATUS"));
					to.setLoan_Id(rs2.getString("LOAN_ID"));
				}
				
			}*/
			
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return to;
		
	}
	
	/*public static void main(String arg[])
	{
		LoginDao ld=new LoginDao();
		RegistrationTo to=new RegistrationTo();
		try
		{
		to=ld.retrieveBank_Details("R000009");
		System.out.println(to.getAccountNo());
		System.out.println(to.getName());
		System.out.println(to.getAccount_Type());
		System.out.println(to.getAmount());
		System.out.println(to.getInitial_Amount());
		System.out.println(to.getTotal_amount());
		System.out.println(to.getEmi());
		System.out.println("emi"+Math.round(Math.floor(Double.valueOf(to.getEmi()))));
		}
		catch(Exception e){
		System.out.println(e.getMessage());
		e.printStackTrace();
		}
	}*/
	
	
	
}
