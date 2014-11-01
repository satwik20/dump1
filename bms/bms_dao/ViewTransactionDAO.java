package com.cts.bms_dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.cts.bms_to.View_TransactionDetailTO;
import com.cts.bms_to.View_TransactionTO;
import com.cts.bms_exception.*;
import com.cts.bms_DbConnection.*;
public class View_TransactionDAO {
	boolean flag=false;
	String query=null;
	public ArrayList<View_TransactionDetailTO> getDetails(View_TransactionTO to) throws BussinessException
	{
		int flag=0;
		
	
		Connection con= DatabaseConnection.getConnection();
		//View_TransactionTO vto= new View_TransactionTO();
		String accNo=to.getAccountNo();
		//System.out.println("hi account"+accNo);
		ArrayList<View_TransactionDetailTO> transactionDetail= new ArrayList<View_TransactionDetailTO>();
		//View_TransactionDetailTO toDetails=new View_TransactionDetailTO();
		String startDate= to.getEntryDate();
		String endDate=to.getEndDate();
		SimpleDateFormat sf= new SimpleDateFormat("dd-MMM-yy");
		SimpleDateFormat sf1= new SimpleDateFormat("dd/MM/yy");
		try {
			Date d1=sf1.parse(startDate);
			Date d2=sf1.parse(endDate);
			startDate=sf.format(d1);
			endDate=sf.format(d2);
			//System.out.println(startDate+"  "+endDate);
			
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		System.out.println(startDate);
		
		/*String accNo=toDetails.getAccountNo();
		String accName= toDetails.getAccountName();
		String accType=toDetails.getAccountType();
		String tranId=toDetails.getTransactionID();
		String tranType=toDetails.getTransactionType();
		String tranDate=toDetails.getTransactionDate();*/
		System.out.println("hidao"+to.getTransactionType());
if("Deposit".equals(to.getTransactionType())){
			
			query="select distinct account_no,account_Name,account_type,Transaction_Date,customer_transaction.Transaction_Type,Transaction_ID,amount from Customer_Transaction where customer_transaction.account_no='"+accNo+"' and customer_transaction.Transaction_Type='Deposit' and TO_DATE(customer_transaction.Transaction_Date,'DD/MM/RRRR') >= TO_DATE('"+startDate+"','DD/MM/RRRR') and TO_DATE(customer_transaction.Transaction_Date,'DD/MM/RRRR') <= TO_DATE('"+endDate+"','DD/MM/RRRR') ";
			
		}
else if("Withdrawl".equals(to.getTransactionType())){
	
	query="select distinct account_no,account_Name,account_type,Transaction_Date,customer_transaction.Transaction_Type,Transaction_ID,amount from Customer_Transaction where customer_transaction.account_no='"+accNo+"' and customer_transaction.Transaction_Type='Withdraw' and TO_DATE(customer_transaction.Transaction_Date,'DD/MM/RRRR') >= TO_DATE('"+startDate+"','DD/MM/RRRR') and TO_DATE(customer_transaction.Transaction_Date,'DD/MM/RRRR') <= TO_DATE('"+endDate+"','DD/MM/RRRR') ";
}
else if("All".equals(to.getTransactionType())){
	
	query="select distinct account_no,account_Name,account_type,Transaction_Date,customer_transaction.Transaction_Type,Transaction_ID,amount from Customer_Transaction where customer_transaction.account_no='"+accNo+"' and customer_transaction.Transaction_Type='Deposit' and TO_DATE(customer_transaction.Transaction_Date,'DD/MM/RRRR') >= TO_DATE('"+startDate+"','DD/MM/RRRR') and TO_DATE(customer_transaction.Transaction_Date,'DD/MM/RRRR') <= TO_DATE('"+endDate+"','DD/MM/RRRR') union select distinct account_no,account_Name,account_type,Transaction_Date,customer_transaction.Transaction_Type,Transaction_ID,amount from Customer_Transaction where customer_transaction.account_no='"+accNo+"' and customer_transaction.Transaction_Type='Withdraw' and TO_DATE(customer_transaction.Transaction_Date,'DD/MM/RRRR') >= TO_DATE('"+startDate+"','DD/MM/RRRR') and TO_DATE(customer_transaction.Transaction_Date,'DD/MM/RRRR') <= TO_DATE('"+endDate+"','DD/MM/RRRR') ";
}
		
	
try {
	PreparedStatement ps= con.prepareStatement(query);
	//ps.setString(1, accNo);
	//ps.setString(2, endDate);
	ResultSet res=ps.executeQuery();
	
	System.out.println(ps.getMaxRows());
	//System.out.println(res);
	while(res.next())
	{
		System.out.println("gjhfgm");
		View_TransactionDetailTO vtdo= new View_TransactionDetailTO();
		vtdo.setAccountNo(res.getString("account_no"));
		System.out.println(vtdo.getAccountNo());
		vtdo.setAccountName(res.getString("account_Name"));
		System.out.println(vtdo.getAccountName());
		vtdo.setAccountType(res.getString("account_type"));
		vtdo.setTransactionDate(res.getString("Transaction_Date"));
		vtdo.setTransactionType(res.getString("Transaction_Type"));
		vtdo.setTransactionID(res.getString("Transaction_ID"));
		vtdo.setAmount(res.getLong("amount"));
		transactionDetail.add(vtdo);
		flag=1;
		
	}
	if(flag==0)
	{
		throw new BussinessException("fatal error");
	}
	
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	//System.out.println(e.getMessage());
	
}

		return transactionDetail;
	}
}
