package com.cts.bms_bo;

import java.sql.SQLException;

import com.cts.bms_exception.BussinessException;
import com.cts.bms_dao.EducationDAO;
import com.cts.bms_to.EducationloanTO;
import com.cts.bms_validation.LoanValidation;

public class EducationLoanApplyBo {

	public boolean ValidateEducationLoanApplyDetails(EducationloanTO to2)
			throws BussinessException, SQLException {
		int c = 0;
		boolean flag = true;

		/* Setting the variables for Education Loan Apply*/
		
		String CourseFees = to2.getCfees();
		String CourseName = to2.getCname();
		String FatherName = to2.getFname();
		String FatherOccupation = to2.getFoccupation();
		String FathersTotalExperience = to2.getFtexperience();
		String FathersExperienceWithCurrentCompany = to2.getFexpwithcompany();
		String RationCardNo = to2.getCardno();
		String AnnualIncome = to2.getIncome();
		
		/*Validation for Education Loan Apply */
		
		LoanValidation v = new LoanValidation();
		
		
		
		/*Validation for CourseFees */

		if (v.isNull(CourseFees)) {
			c++;
		} else {
			throw new BussinessException("Coures Fees s Blank");
		}

		if (v.isDigit(CourseFees)) {
			c++;
		} else {
			throw new BussinessException("Enter a valid amount for Course Fees");
		}

		if (v.courseFees(CourseFees)) {
			c++;
		} else {
			throw new BussinessException(
					"Amount should in proper format for Course Fees");
		}

		
		/*Validation for CourseName */
		
		if (v.isNull(CourseName)) {
			c++;
		} else {
			throw new BussinessException("Course Name s blank");
		}

		if (v.alphaChecking(CourseName)) {
			c++;
		} else {
			throw new BussinessException("Valid Course Name");
		}

		if (v.courseName(CourseName)) {
			c++;
		} else {
			throw new BussinessException("Check format");
		}
		
		
		/*Validation for Father Name */
		

		if (v.isNull(FatherName)) {
			c++;
		} else {
			throw new BussinessException("Father name s blank");
		}

		if (v.alphaChecking(FatherName)) {
			c++;
		} else {
			throw new BussinessException("Enter a valid name");
		}

		if (v.fatherName(FatherName)) {
			c++;
		} else {
			throw new BussinessException("Minimum 20 alpha");
		}
		
		
		/*Validation for Father Occupation */
		

		if (v.isNull(FatherOccupation)) {
			c++;
		} else {
			throw new BussinessException("Occupation is blank");
		}

		if (v.alphaChecking(FatherOccupation)) {
			c++;
		} else {
			throw new BussinessException("Enter a valid Occupation");
		}

		if (v.fatherOccupation(FatherOccupation)) {
			c++;
		} else {
			throw new BussinessException("Occuaption length");
		}

		
		
		/*Validation for Fathers Total Experience */
		
		
		if (v.isNull(FathersTotalExperience)) {
			c++;
		} else {
			throw new BussinessException("Father total exp s blank");
		}

		if (v.splChecking(FathersTotalExperience)) {
			c++;
		} else {
			throw new BussinessException("Enter  a valid total exp ");
		}

		if (v.fatherTotalExperience(FathersTotalExperience)) {
			c++;
		} else {
			throw new BussinessException("Enter with in range");
		}
		
		
		
		/*Validation for Father Experience with current Company */
		
		

		if (v.isNull(FathersExperienceWithCurrentCompany)) {
			c++;
		} else {
			throw new BussinessException("Father curr exp is blank");
		}

		if (v.splChecking(FathersExperienceWithCurrentCompany)) {
			c++;
		} else {
			throw new BussinessException("Enter a valid curr exp ");
		}

		if (v.fatherExperienceWithCurrentCompany(FathersExperienceWithCurrentCompany)) {
			c++;
		} else {
			throw new BussinessException("Length is not valid");
		}
		
		
		/*Validation for Ration Card No */
		

		if (v.isNull(RationCardNo)) {
			c++;
		} else {
			throw new BussinessException("Ration Card is Blank");
		}

		if (v.isDigit(RationCardNo)) {
			c++;
		} else {
			throw new BussinessException("Enter a valif ration card format");
		}

		if (v.rationCardNo(RationCardNo)) {
			c++;
		} else {
			throw new BussinessException("Enter a validlength of ration card");
		}
		
		
		/*Validation for Annual Income */
		

		if (v.annualIncomeLength(AnnualIncome)) {
			c++;
		} else {
			throw new BussinessException("Enter a valid Amount of 4 digits");
		}

		if (v.isNull(AnnualIncome)) {
			c++;
		} else {
			throw new BussinessException("Annual Income is blank");
		}

		if (v.isDigit(AnnualIncome)) {
			c++;
		} else {
			throw new BussinessException("Enter a valid amount");
		}

		
		
		EducationDAO dao = new EducationDAO();
		flag = dao.insertdao(to2);

		return flag;
	}

}
