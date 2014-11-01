package com.cts.bms_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.cts.bms_DbConnection.DatabaseConnection;
import com.cts.bms_to.TransactionTo;

public class TransactionDao {

	
	public boolean updateTransaction_details(TransactionTo to)
	{
		boolean flag=true;
		
		Connection con=null;
		PreparedStatement ps=null;
		PreparedStatement ps1=null;
		Statement st=null;
		try
		{
			con=DatabaseConnection.getConnection();
			ps=con.prepareStatement("insert into customer_transaction values(?,?,?,trans_id.nextval,?,to_date(?,'dd/MM/YYYY'),?,?)");
			ps.setString(1,to.getAccount_no());
			ps.setString(2, to.getAccount_name());
			ps.setString(3,to.getAccount_type());
			ps.setString(4,to.getTransaction_type());
			ps.setString(5,to.getTransaction_date());
			ps.setString(6,to.getAmount());
			ps.setString(7,to.getTotal_amount());

			int val=ps.executeUpdate();
			ps.close();
		
	
	}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		}
		return flag;
	}
}
