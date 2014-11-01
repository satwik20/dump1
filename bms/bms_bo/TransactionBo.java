package com.cts.bms_bo;

import com.cts.bms_exception.BussinessException;
import com.cts.bms_to.TransactionTo;

public class TransactionBo {

	public boolean validTransaction(TransactionTo to)throws BussinessException
	{
		boolean flag=true;
		if(to.getTransaction_type().equals("Loan")&& to.getAmount()==null)
		{
			//to.setAmount(0);
			throw new BussinessException("for loan amount should be null"); 
		}else
		{
			flag=true;
		}
		
		if("Withdraw".equals(to.getTransaction_type()))
				{
					if(Integer.parseInt(to.getAmount())<Integer.parseInt(to.getTotal_amount()))
					{
						
						flag=true;
					}else
					{
						throw new BussinessException("Insufficient balance");
					}
				}
		
		
		

		
		return flag;
	}
	


		public int limit_Checking(TransactionTo to)
		{
			boolean flag=false;
			int set=0;
			if("India".equals(to.getCountry()))
			{
				if("Salary".equals(to.getAccount_type()))
				{
					set=0;
				}
				else
				{
				set=5000;
				}
			}
		/*	UK',14000);
			insert into Nri_citizen values('USA',15000);
			insert into Nri_citizen values('Italy'*/
			if("UK".equals(to.getCountry()))
			{
				if("Salary".equals(to.getAccount_type()))
				{
					set=0;
				}
				else
				{
				set=14000;
				}
			}
			if("USA".equals(to.getCountry()))
			{
				if("Salary".equals(to.getAccount_type()))
				{
					set=0;
				}
				else
				{
				set=15000;
				}
			}
			if("Italy".equals(to.getCountry()))
			{
				if("Salary".equals(to.getAccount_type()))
				{
					set=0;
				}
				else
				{
				set=13000;
				}
			}
			
			
			return set;
		}
}
