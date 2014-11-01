package com.cts.bms_bo;

import com.cts.bms_exception.BussinessException;
import com.cts.bms_to.RegistrationTo;
import com.cts.bms_validation.Validation;

public class Updatebo {

	public boolean validupdateDetails(RegistrationTo to)throws BussinessException
	{
		if(Validation.alpha_Checking(to.getAdderss()))
		{
			
		}else
		{
			throw new BussinessException("address should not empty"); 
		}
		
		if(Validation.contactNo_Checking(to.getContact_No()))
		{
			
		}else
		{
			throw new BussinessException("contact number should be in numbers and length should be in 10 "); 
		}
		if(Validation.email_Checking(to.getEmail()))
		{
			
		}else
		{
			throw new BussinessException("Email is not in a formate"); 
		}
		if( Validation.alpha_Checking(to.getCitizenship()))
		{
			
		}else
		{
			throw new BussinessException("Citizenship should be contain only alphabets"); 
		}
		if(Validation.alpha_Checking(to.getGuardian_Name()))
		{
			
		}else
		{
			throw new BussinessException("Guardian Name should be contain only alphabets"); 
		}
		
		
		
		
		return true;
	}
	
}
