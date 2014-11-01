package com.cts.bms_validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class LoanValidation {

	String alpha = "[A-Za-z ]*";
	String num = "[0-9]{1,10}";
	String spl = "[0-9]*[.][0-9]*";

	/* Validation for Numeric Checking */

	public boolean isDigit(String str) {
		boolean flag = false;
		if (str.matches(num)) {
			flag = true;
		}
		return flag;
	}

	/* Validation for Alphabet Checking */

	public boolean alphaChecking(String str) {
		boolean flag = false;
		if (str.matches(alpha)) {
			flag = true;
		}
		return flag;
	}

	/* Validation for Special Checking Format */

	public boolean splChecking(String str) {
		boolean flag = false;
		if (str.matches(spl)) {
			flag = true;
		}
		return flag;
	}

	/*
	 * public boolean companyChecking(String str) { boolean flag=false;
	 * if(str.matches(spl1)) { flag=true; } return flag; }
	 */

	/* Validation for Loan Type Empty check */

	public boolean loanTypeCheck(String LoanType) {
		boolean flag = true;
		if (LoanType.equals("empty")) {
			flag = false;
		}
		return flag;

	}

	/* Validation for Loan Amount Length Checking */

	public boolean loanAmountLength(String str) {
		boolean flag = false;
		if (str.length() >= 5 && str.length() <= 7)

		{
			flag = true;
		}
		return flag;
	}

	/*
	 * public boolean loanAmountCheck(String str) { boolean flag=false;
	 * if(str.matches(spl) ) { flag=true; } return flag; }
	 */

	/* Validation for Apply Date Checking */

	public boolean applyDateCheck(String applydate) {
		boolean flag = false;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		String ckdate = "";
		try {
			date = sdf.parse(applydate);
			ckdate = sdf.format(date);

		} catch (ParseException e) {

			e.printStackTrace();
		}
		if (ckdate.equals(applydate)) {
			flag = true;
		}
		return flag;
	}

	/* Validation for Issue Date Checking */

	public boolean issueDateCheck(String issuedate) {
		boolean flag = false;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		String ckdate = "";
		try {
			date = sdf.parse(issuedate);
			ckdate = sdf.format(date);

		} catch (ParseException e) {

			e.printStackTrace();
		}
		if (ckdate.equals(issuedate)) {
			flag = true;
		}
		return flag;
	}

	public boolean applyDateCheck(String str1, String str2) {
		boolean flag = false;
		Date d1 = new Date();
		Date d2 = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		// SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");

		try {

			d1 = sdf1.parse(str1);
			d2 = sdf1.parse(str2);

			Calendar c = Calendar.getInstance();
			c.setTime(d1);
			c.add(Calendar.DATE, 30);
			Date aferAddingDate = c.getTime();

			Calendar cc = Calendar.getInstance();
			Date currentDate = cc.getTime();
			// String fromDate1 = sdf1.format(d2);
			String issueDate = sdf1.format(d2);
			String currentDatestr = sdf1.format(cc.getTime());
			// String currentDate = sdf1.format();
			if (issueDate.equals(currentDatestr) || d2.after(currentDate)
					|| d2.before(aferAddingDate)) {
				return true;
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flag;
	}

	/* Validation for Null Checking */

	public boolean isNull(String str) {
		boolean flag = true;
		if (str == "")

		{
			flag = false;
		}
		return flag;
	}

	/* Validation for Rate Of Interest Checking */

	public boolean rateofInterestCheck(String RateOfInterest) {
		boolean flag = true;
		if (RateOfInterest.equals("empty")) {
			flag = false;
		}
		return flag;

	}

	/* Validation for Duration Of Loan Checking */

	public boolean durationofLoanCheck(String DurationOfLoan) {
		boolean flag = true;
		if (DurationOfLoan.equals("empty")) {
			flag = false;
		}
		return flag;

	}

	/* Validation for Annual Income Length Checking */

	public boolean annualIncomeLength(String str) {
		boolean flag = false;
		if (str.length() >= 5 && str.length() <= 7)

		{
			flag = true;
		}
		return flag;
	}

	/* Validation for Company Name Length Checking */

	public boolean companyNameLength(String str) {
		boolean flag = false;
		if (str.length() <= 30)

		{
			flag = true;
		}
		return flag;
	}

	/* Validation for Designation Length Checking */

	public boolean designationLength(String str) {
		boolean flag = false;
		if (str.length() <= 20)

		{
			flag = true;
		}
		return flag;
	}

	/* Validation for Total Experience Length Checking */

	public boolean totalExperienceLength(String str) {
		boolean flag = false;
		if (str.length() <= 5)

		{
			flag = true;
		}
		return flag;
	}

	/* Validation for Experience With Current Company Checking */

	public boolean experienceWithCurrentCompany(String str) {
		boolean flag = false;
		if (str.length() <= 5)

		{
			flag = true;
		}
		return flag;
	}

	/* Validation for Course Fees Length Checking */

	public boolean courseFees(String str) {
		boolean flag = false;
		if (str.length() >= 5 && str.length() <= 7)

		{
			flag = true;
		}
		return flag;
	}

	/* Validation for Course Name Length Checking */

	public boolean courseName(String str) {
		boolean flag = false;
		if (str.length() <= 3)

		{
			flag = true;
		}
		return flag;
	}

	/* Validation for Father Name Length Checking */

	public boolean fatherName(String str) {
		boolean flag = false;
		if (str.length() <= 20)

		{
			flag = true;
		}
		return flag;
	}

	/* Validation for Father Occupation Length Checking */

	public boolean fatherOccupation(String str) {
		boolean flag = false;
		if (str.length() <= 20)

		{
			flag = true;
		}
		return flag;
	}

	/* Validation for Fathers Total Experience Length Checking */

	public boolean fatherTotalExperience(String str) {
		boolean flag = false;
		if (str.length() <= 5)

		{
			flag = true;
		}
		return flag;
	}

	/* Validation for Father Experience With Current Company Length Checking */

	public boolean fatherExperienceWithCurrentCompany(String str) {
		boolean flag = false;
		if (str.length() <= 5)

		{
			flag = true;
		}
		return flag;
	}

	/* Validation for Ration Card No Length Checking */

	public boolean rationCardNo(String str) {
		boolean flag = false;
		if (str.length() <= 5)

		{
			flag = true;
		}
		return flag;
	}

}
