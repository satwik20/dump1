package com.cts.bms_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cts.bms_DbConnection.DatabaseConnection;

public class DeleteLoanDetailsDao {
	
	
	
	public String retrieveCustId(String accno)
	{
		String custId="";
		
		Connection con=null;
		PreparedStatement ps=null;
		con=DatabaseConnection.getConnection();
		try {
			ps=con.prepareStatement("select customer_id from account_details where account_number=?");
			ps.setString(1, accno);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				custId=rs.getString("customer_id");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return custId;
	}
	
	public boolean deleteRecords(String custId,String loanStatus,String loanId)
	{
		boolean flag=false;
		
		Connection con=null;
		PreparedStatement ps=null;
		try
		{
		con=DatabaseConnection.getConnection();
		String direction=loanStatus.substring(0, 1);
		if(direction.equals("EL"))
		{
			ps=con.prepareStatement("delete from educational_loan where customer_id=?");
			ps.setString(1, custId);
			int res=ps.executeUpdate();
			if(res!=0)
				flag=true;
		}
		else
		{
			ps=con.prepareStatement("delete from housing_loan where customer_id=?");
			ps.setString(1, custId);
			int res=ps.executeUpdate();
			if(res!=0)
				flag=true;
		}
		}
		catch(SQLException e)
		{
			
		}
		
		
		
		
		
		return flag;
	}
	
	
	
	
	

}
