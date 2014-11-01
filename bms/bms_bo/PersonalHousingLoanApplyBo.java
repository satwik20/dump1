package com.cts.bms_bo;

import java.sql.SQLException;
import com.cts.bms_exception.BussinessException;
import com.cts.bms_dao.PersonalDAO;
import com.cts.bms_to.PersonalloanTO;
import com.cts.bms_validation.LoanValidation;

public class PersonalHousingLoanApplyBo {

	public boolean ValidatePersonalHousingLoanApplyDetails(PersonalloanTO to1)
			throws BussinessException, SQLException {
		int c = 0;
		boolean flag = true;
		
		/* Setting the variables for Personal Housing Loan Apply*/

		String AnnualIncome = to1.getIncome();
		String CompanyName = to1.getName();
		String Designation = to1.getDesign();
		String TotalExperience = to1.getTexperience();
		String ExperienceWithCurrentCompany = to1.getExpwithcompany();

		/*Validation for Education Loan Apply */
		
		LoanValidation v = new LoanValidation();
		
		
		
		/*Validation for Annual Income */
		

		if (v.annualIncomeLength(AnnualIncome)) {
			c++;
		} else {
			throw new BussinessException(
					"Enter a valid Amount for Annual Income");
		}

		if (v.isNull(AnnualIncome)) {
			c++;
		} else {
			throw new BussinessException("Annual Income is blank");
		}

		if (v.isDigit(AnnualIncome)) {
			c++;
		} else {
			throw new BussinessException(
					"Enter a valid amount for Annual Income");
		}
		
		
		/*Validation for Company Name */
		
		

		if (v.isNull(CompanyName)) {
			c++;
		} else {
			throw new BussinessException("Company Name should is Blank");
		}

		if (v.alphaChecking(CompanyName)) {
			c++;
		} else {
			throw new BussinessException("Company Name should B in alpha");
		}

		if (v.companyNameLength(CompanyName)) {
			c++;
		} else {
			throw new BussinessException(
					"Company Name should B within 30 chars");
		}

		
		
		/*Validation for Designation */
		
		
		
		if (v.isNull(Designation)) {
			c++;
		} else {
			throw new BussinessException("Desingation is blank");
		}

		if (v.alphaChecking(Designation)) {
			c++;
		} else {
			throw new BussinessException("Desingation should B in alpha");
		}

		if (v.designationLength(Designation)) {
			c++;
		} else {
			throw new BussinessException("Designation should B within 20 chars");
		}

		
		
		/*Validation for Total Experience */
		
		
		if (v.isNull(TotalExperience)) {
			c++;
		} else {
			throw new BussinessException("Total Exp is blank");
		}

		if (v.splChecking(TotalExperience)) {
			c++;
		} else {
			throw new BussinessException("Total Exp should B in num");
		}

		if (v.totalExperienceLength(TotalExperience)) {
			c++;
		} else {
			throw new BussinessException("Total Exp should 4-5 digits");
		}
		
		
		
		/*Validation for Experience With Current Company */
		

		if (v.isNull(ExperienceWithCurrentCompany)) {
			c++;
		} else {
			throw new BussinessException("Current Exp is blank");
		}

		if (v.splChecking(ExperienceWithCurrentCompany)) {
			c++;
		} else {
			throw new BussinessException("Current Exp should B in num");
		}

		if (v.experienceWithCurrentCompany(ExperienceWithCurrentCompany)) {
			c++;
		} else {
			throw new BussinessException(" Current Exp should 4-5 digits");
		}

		
		
		
		PersonalDAO dao = new PersonalDAO();
		flag = dao.insertdao(to1);

		return flag;
	}

}
