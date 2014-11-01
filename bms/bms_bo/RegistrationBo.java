package com.cts.bms_bo;

import java.util.HashMap;

import com.cts.bms_exception.BussinessException;
import com.cts.bms_to.RegistrationTo;
import com.cts.bms_to.TransactionTo;
import com.cts.bms_validation.Validation;

public class RegistrationBo {

	HashMap<String,String> hm=null;
	public HashMap<String,String> validPersonalDetails(RegistrationTo to)throws BussinessException
	{
		hm=new HashMap<String, String>();
		System.out.println("enter");
		
		if(Validation.alpha_Checking(to.getName()))
		{
			
		}else
		{
			hm.put("name","Name should be contain only alphabets");
			//throw new BussinessException("Name should be contain only alphabets"); 
		}
		
		if(Validation.notempty(to.getPassword()))
		{
			hm.put("Password","Password should not empty");
		}else
		{
			if(to.getPassword().equals(to.getRe_EnterPassword()))
			{
				
			}else
			{
				hm.put("Password","Password are miss matching");
				//throw new BussinessException("Password are miss matching"); 
			}
		}
		if(Validation.notempty(to.re_EnterPassword))
		{
			hm.put("rePassword","Re type password should not empty");
		}
		
		if(Validation.alpha_Checking(to.getUser_Name()))
		{
			
		}else
		{
			hm.put("username","UserName should be contain only alphabets");
			//throw new BussinessException("UserName should be contain only alphabets"); 
		}
		
		
		
		if(Validation.alpha_Checking(to.getGuardian_Name()))
		{
			
		}else
		{
			hm.put("Guardian","Guardian Name should be contain only alphabets");
			//throw new BussinessException("Guardian Name should be contain only alphabets"); 
		}
		
		if(Validation.alpha_Checking(to.getAdderss()))
		{
			
		}else
		{
			hm.put("Address","Address should not empty");
			//throw new BussinessException("Address should not empty"); 
		}
		if( Validation.alpha_Checking(to.getCitizenship()))
		{
			
		}else
		{

			hm.put("Citizenship","Citizenship should be contain only alphabets");
			//throw new BussinessException("Citizenship should be contain only alphabets"); 
		}
		if(to.getCountry().equals("empty"))
		{
			hm.put("Country","please select the country");
			//throw new BussinessException("please select the Country"); 
		}else
		{
			
		}
		if(to.getState().equals("empty"))
		{
			hm.put("State","please select the state");
			//throw new BussinessException("please select the State"); 
		}else
		{
			
		}
		
		if(Validation.email_Checking(to.getEmail()))
		{
			
		}else
		{
			hm.put("Email","Email is not in a formate");
			//throw new BussinessException("Email is not in a formate"); 
		}
		if(Validation.contactNo_Checking(to.getContact_No()))
		{
			
		}else
		{
			hm.put("Contact","contact number should be in numbers and length should be in 10 ");
			//throw new BussinessException("contact number should be in numbers and length should be in 10 "); 
		}
		if(Validation.date_formate(to.getDob()))
		{
			
		}else
		{
			hm.put("Date","date of birth is in wrong format ");
			//throw new BussinessException("date of birth is in wrong format"); 
		}
		return hm;
	}
	
	
	
	
	public HashMap<String,String> validate_AccountDetails(RegistrationTo to)throws BussinessException
	{
		HashMap<String,String> hm= new HashMap<String,String>();
		if( Validation.alpha_Checking(to.getRefrence_Account_Holder_Name()))
		{
			
		}else
		{
			hm.put("refname","References Name should be contain only alphabets");
			//throw new BussinessException("Name should be contain only alphabets"); 
		}
		
		if(to.getBranch().equals("empty"))
		{
			hm.put("branch","Select the branch");
			//throw new BussinessException("select the branch");
		}else
		{
			
		}
		
		if(to.getCitizen_Status().equals("empty"))
		{
			hm.put("citizenship","Select the citizenship");
			//throw new BussinessException("select the citizenship");
		}else
		{
			
		}
		
		if( Validation.isnum(to.getInitial_Deposit()))
		{
			
		}else
		{
			hm.put("Initial","Initial deposit is should be in number");
			//throw new BussinessException("Initial deposit is should be in num"); 
		}
		
		if( Validation.notempty(to.getId_Document_No()))
		{
			hm.put("Id","Id document no is should not blank");
		}
		if(to.getId_Proof_Type().equals("empty"))
		{hm.put("Idproof","please select the idproof");
			//throw new BussinessException("please select the idproof"); 
		}else
		{
			
		}
		if(!Validation.notempty(to.getReference_Account_Holder_Address()))
		{
			if(!Validation.alpha_Checking(to.getReference_Account_Holder_Address()))
			{
				hm.put("refaddress","References address should be a alphabets");
			}
			
		}else
			
		{hm.put("refaddress","References address should not empty");
			//throw new BussinessException("References address should not empty"); 
		}
		if(!Validation.notempty(to.getReference_Account_Holder_No()))
		{
			
		}else
		{hm.put("refaccount","References account no should not empty");
			//throw new BussinessException("References account no should not empty"); 
		}
		/*if(to.getReference_Account_Holder_No().length()-1==16)
		{
			
		}else
		{
			throw new BussinessException("References account no shold be a 16 digits"); 
		}*/
		if(Integer.parseInt(to.getInitial_Deposit())<limit_Checking(to))
		{
			hm.put("Initial","enter the valid intial amount based on the type of account  and country");
		}
		
	return hm;
	}
	
	
	public int limit_Checking(RegistrationTo to)
	{
		boolean flag=false;
		int set=0;
		if("India".equals(to.getCountry()))
		{
			if("Salary".equals(to.getAccount_Type()))
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
			if("Salary".equals(to.getAccount_Type()))
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
			if("Salary".equals(to.getAccount_Type()))
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
			if("Salary".equals(to.getAccount_Type()))
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
