package com.cts.bms_dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;


import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

import com.cts.bms_DbConnection.DatabaseConnection;
import com.cts.bms_to.RegistrationTo;

public class RegistrationDao {

	
	
	public boolean check_Reference(String refNo)
	{
		boolean flag=false;
		Connection con=null;
		PreparedStatement ps=null;
		
		con=DatabaseConnection.getConnection();
		try {
			ps=con.prepareStatement("select * from account_details where ACCOUNT_NUMBER=? ");
			ps.setString(1,refNo);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				flag=true;
			}
			else
				flag=false;
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return flag;
	}
	
	
	public boolean insert_Details(RegistrationTo to)//RegistrationTo to
	{
		boolean flag=false;
		System.out.println("Inside insert Method");
		Connection con=DatabaseConnection.getConnection();
		try {
			PreparedStatement ps=con.prepareStatement("select max(CUSTOMER_ID) from personal_details");
			String cust_Id="";
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				 cust_Id=rs.getString("max(CUSTOMER_ID)");
				 flag=true;
			}
			System.out.println(cust_Id);
			cust_Id=customer_IdGenerator(cust_Id);
			
		PreparedStatement ps1=con.prepareStatement("insert into personal_details values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		ps1.setString(1,to.getName());
		ps1.setString(2,to.getUser_Name());
		ps1.setString(3,to.getPassword());
		ps1.setString(4,to.getGuardian_Name());
		ps1.setString(5,to.getGardian_Type());
		ps1.setString(6,to.getAdderss());
		ps1.setString(7,to.getCitizenship());
		ps1.setString(8,to.getState());
		ps1.setString(9,to.getCountry());
		ps1.setString(10,to.getEmail());
		ps1.setString(11,to.getGender());
		ps1.setString(12,to.getMarital_Status());
		ps1.setString(13,to.getContact_No());
		ps1.setString(14,to.getDob());
		ps1.setString(15,cust_Id);
		
		System.out.println(ps1.executeUpdate());
		
		
		PreparedStatement ps2=null;
		
		 ps2=con.prepareStatement("insert into account_details values(?,?,?,?,?,?,?,?,?,?,acc_no.nextval,?)");
		 ps2.setString(1,to.getRegistrateion_date());
			ps2.setString(2,to.getAccount_Type());
			ps2.setString(3,to.getBranch());
			ps2.setString(4,to.getCitizen_Status());
			ps2.setInt(5,Integer.parseInt(to.getInitial_Deposit()));
			ps2.setString(6,to.getId_Proof_Type());
			ps2.setString(7,to.getId_Document_No());
			ps2.setString(8,to.getRefrence_Account_Holder_Name());
			ps2.setString(9,to.getReference_Account_Holder_No());
			ps2.setString(10,to.getReference_Account_Holder_Address());
		
			ps2.setString(11,cust_Id);
			System.out.println(ps2.executeUpdate());
			
			
		PreparedStatement ps3=null;
		ps3=con.prepareStatement("insert into customer_transaction values(acc_no.currval,?,?,trans_id.nextval,?,to_date(?,'dd/mm/yyyy'),?,?)");
		ps3.setString(1,to.getName());
		ps3.setString(2,to.getAccount_Type());
		ps3.setString(3, "Deposit");
		ps3.setString(4,to.getRegistrateion_date());
		ps3.setInt(5,Integer.parseInt(to.getInitial_Deposit()));
		ps3.setInt(6,Integer.parseInt(to.getInitial_Deposit()));
		System.out.println(ps3.executeUpdate());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			//e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		return flag;
	}
	
	
	public  String customer_IdGenerator(String cust_id)
	{String cus_no="";
	
		int max=Integer.parseInt(cust_id.substring(1))+1;
		
		if(max<=9)
		{
			cus_no=cus_no+"R"+"00000"+max;
		
		}
		if(max<=99 && max>9)
		{
			cus_no=cus_no+"R"+"0000"+max;
		
		}
		if(max<=999 && max>99)
		{
			cus_no=cus_no+"R"+"000"+max;
		
		}
		if(max<=9999 && max>999)
		{
			cus_no=cus_no+"R"+"00"+max;
		
		}
		
		
		
		return cus_no;
		
	}
	
	
	
	
}
