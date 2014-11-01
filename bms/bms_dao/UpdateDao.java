package com.cts.bms_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cts.bms_DbConnection.DatabaseConnection;
import com.cts.bms_to.RegistrationTo;

public class UpdateDao {
	
	public RegistrationTo retrieve_Details(String str)
	{
		RegistrationTo to=new RegistrationTo();
		Connection con=null;
		PreparedStatement ps=null;
		PreparedStatement ps1=null;
		PreparedStatement ps2=null;
		
		con=DatabaseConnection.getConnection();
		
		try {
			System.out.println("customer_id"+str);
			ps=con.prepareStatement("select *from personal_Details where CUSTOMER_ID=? ");
			ps.setString(1, str);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				to.setCustomer_id(rs.getString("CUSTOMER_ID"));
				to.setName(rs.getString("NAME"));
				to.setCountry(rs.getString("COUNTRY"));
				to.setState(rs.getString("STATE"));
				to.setGender(rs.getString("GENDER"));
				to.setDob(rs.getString("DOB"));
				to.setContact_No(rs.getString("CONTACT_NO"));
				to.setMarital_Status(rs.getString("MARTIAL_STATUS"));
				to.setEmail(rs.getString("EMAIL"));
				to.setCitizenship(rs.getString("CITIZENSHIP"));
				to.setAdderss(rs.getString("ADDRESS"));
				to.setGardian_Type(rs.getString("GUARDIAN_TYPE"));
				to.setGuardian_Name(rs.getString("GUARDIAN_NAME"));
				
			}
			
System.out.println("1quersy executed");
			ps1=con.prepareStatement("select * from account_details where CUSTOMER_ID=? ");
			ps1.setString(1, str);
			ResultSet rs1=ps1.executeQuery();
			if(rs1.next())
			{
				to.setRegistrateion_date(rs1.getString("REGISTRATION_DATE"));
				to.setBranch(rs1.getString("BRANCH_NAME"));
				to.setId_Proof_Type(rs1.getString("ID_PROOF"));
				to.setId_Document_No(rs1.getString("ID_DOCUMENT"));
				to.setRefrence_Account_Holder_Name(rs1.getString("REF_HOLDER_ACC_NAME"));
				to.setReference_Account_Holder_No(rs1.getString("REF_HOLDER_ACC_NO"));
				to.setReference_Account_Holder_Address(rs1.getString("REF_HOLDER_ADDRESS"));
				to.setAccountNo(rs1.getString("ACCOUNT_NUMBER"));
				to.setAccount_Type(rs1.getString("ACCOUNT_TYPE"));
			}
			System.out.println("2quersy executed");
			
			ps2=con.prepareStatement("select * from ifsc where  branch_name=?");
			ps2.setString(1, to.getBranch());
			ResultSet rs2=ps2.executeQuery();
			if(rs2.next())
			{
				to.setIfsc_code(rs2.getString("IFSC_CODE"));
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("----------"+to.accountNo);
		return to;
	}
	
	
	public boolean update_Cust_Details(RegistrationTo to)
	{
		boolean flag=true;
		
		Connection con=null;
		PreparedStatement ps=null;
		PreparedStatement ps1=null;
		Statement st=null;
		try
		{
			con=DatabaseConnection.getConnection();
			
			/*PreparedStatement pst=con.prepareStatement("delete from personal_details where customer_id=?");
			pst.setString(1,to.getCustomer_id());
			pst.executeUpdate();
			System.out.println("deleted from personal details");
			
			PreparedStatement pst1=con.prepareStatement("delete from account_details where customer_id=?");
			pst1.setString(1,to.getCustomer_id());
			pst1.executeUpdate();
			System.out.println("deleted from account details");
			
			 ps1=con.prepareStatement("insert into personal_details values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
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
			ps1.setString(15,to.getCustomer_id());
			ps1.executeUpdate();
			System.out.println(to.getName()+" "+to.getUser_Name()+" "+to.getPassword());
			System.out.println("insert into personal details");
			
			PreparedStatement ps2=null;
			
			 ps2=con.prepareStatement("insert into account_details values(?,?,?,?,?,?,?,?,?,?,?,?)");
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
				ps2.setString(11, to.getAccountNo());
				ps2.setString(12,to.getCustomer_id());
				ps2.executeUpdate();
				System.out.println("insert into personal details");
*/			
			ps=con.prepareStatement("update personal_details set address=?,CONTACT_NO=?,EMAIL=?,MARTIAL_STATUS=?,CITIZENSHIP=?,GUARDIAN_TYPE=?,GUARDIAN_NAME=? where customer_id=?");
			ps.setString(1,to.getAdderss());
			ps.setString(2,to.getContact_No());
			ps.setString(3,to.getEmail());
			ps.setString(4,to.getMarital_Status());
			ps.setString(5,to.getCitizenship());
			ps.setString(6,to.getGardian_Type());
			ps.setString(7,to.getGuardian_Name());
			ps.setString(8,to.getCustomer_id());
			System.out.println(to.getContact_No());

			//int val=ps.executeUpdate();
			ResultSet rs=ps.executeQuery();
			//ps.close();
			System.out.println("update:"+rs.getRow());
			if(rs!=null)
			{
				System.out.println("update1");
				ps1=con.prepareStatement("update account_details set ACCOUNT_TYPE=?,CITIZENSHIP_STATUS=? where customer_id=?");
				ps1.setString(1,to.getAccount_Type());
				ps1.setString(2,to.getCitizen_Status());
				ps1.setString(3,to.getCustomer_id());
				rs=ps1.executeQuery();
				System.out.println("update 2");
				//ps1.close();
				if(rs!=null)
				{
				flag=true;
				}
				
			}
			
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		
		
		return flag;
	}
	
	public RegistrationTo details(String custid)
	{
		Connection con=null;
		PreparedStatement ps=null;
		PreparedStatement ps1=null;
		con=DatabaseConnection.getConnection();
		RegistrationTo to=new RegistrationTo();
		try
		{
		ps=con.prepareStatement("select * from personal_details where customer_id=?");
		ps.setString(1, custid);
		ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
			to.setName(rs.getString("name"));
			to.setUser_Name(rs.getString("user_name"));
			to.setPassword(rs.getString("password"));
			to.setGuardian_Name(rs.getString("GUARDIAN_NAME"));
			to.setGardian_Type(rs.getString("GUARDIAN_TYPE"));
			to.setAdderss(rs.getString("ADDRESS"));
			to.setCitizenship(rs.getString("CITIZENSHIP"));
			to.setState(rs.getString("STATE"));
			to.setCountry(rs.getString("COUNTRY"));
			to.setEmail(rs.getString("EMAIL"));
			to.setGender(rs.getString("GENDER"));
			to.setMarital_Status(rs.getString("MARTIAL_STATUS"));
			to.setContact_No(rs.getString("CONTACT_NO"));
			to.setDob(rs.getString("DOB"));
			to.setCustomer_id(rs.getString("CUSTOMER_ID"));
		}
		
		ps1=con.prepareStatement("select * from account_details where customer_id=?");
		ps1.setString(1, custid);
		ResultSet rs1=ps1.executeQuery();
		if(rs1.next())
		{
			to.setRegistrateion_date(rs1.getString("REGISTRATION_DATE"));
			to.setAccount_Type(rs1.getString("ACCOUNT_TYPE"));
			to.setBranch(rs1.getString("BRANCH_NAME"));
			to.setCitizen_Status(rs1.getString("CITIZENSHIP_STATUS"));
			to.setInitial_Deposit(String.valueOf(rs1.getInt("INITIAL_DEPOSIT")));
			to.setId_Proof_Type(rs1.getString("ID_PROOF"));
			to.setId_Document_No(rs1.getString("ID_DOCUMENT"));
			to.setRefrence_Account_Holder_Name(rs1.getString("REF_HOLDER_ACC_NAME"));
			to.setReference_Account_Holder_No(rs1.getString("REF_HOLDER_ACC_NO"));
			to.setReference_Account_Holder_Address(rs1.getString("REF_HOLDER_ADDRESS"));
			to.setAccountNo(rs1.getString("ACCOUNT_NUMBER"));
			to.setCustomer_id(rs1.getString("CUSTOMER_ID"));
			//System.out.println(String.valueOf(rs1.getInt("INITIAL_DEPOSIT")));
		}
		
		}catch(SQLException e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return to;
	}
	
	/*public static void main(String arg[])
	{
		 UpdateDao d=new  UpdateDao();
		 RegistrationTo to=d.details("R000003");
		 System.out.println(to.getInitial_Deposit());
	}*/
	
	
	
	
	

}
