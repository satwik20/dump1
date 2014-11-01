package com.cts.bms_bo;

import com.cts.bms_exception.BussinessException;
import com.cts.bms_validation.Validation;

public class DeleteLoanDetailsBo {

	public boolean validate_Details(String str)throws BussinessException
	{
		
		if(Validation.AccountNo_Checking(str))
		{
			
		}
		else
		{
			throw new BussinessException("Account No not valid");
		}
		
		
		return true;
	}
	
	
	
	
	
}
