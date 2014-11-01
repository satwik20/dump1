package com.cts.bms_bo;

import java.sql.SQLException;

import com.cts.bms_dao.LoginDao;
import com.cts.bms_to.LoginTo;
import com.cts.bms_validation.Validation;

public class LoginBo {
	


		boolean flag= false;
	public boolean userPassValid(LoginTo to) throws SQLException
	{
	   if(Validation.alpha_Checking(to.getUserName())==true && Validation.alpha_Checking(to.getPassword())==true)
	   {
		   flag=true;
	   }
		return flag;
	
	

}
}
