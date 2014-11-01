package com.cts.bms_bo;

import java.sql.SQLException;

import com.cts.bms_exception.BussinessException;
import com.cts.bms_to.LoanApplyTo;
import com.cts.bms_validation.LoanValidation;

public class LoanApplyBo {

	public boolean ValidateLoanApplyDetails(LoanApplyTo to)
			throws BussinessException, SQLException {
		int c = 0;
		boolean flag = true;

		/* Setting the variables for Loan Apply */

		String LoanType = to.getLoanType();
		String LoanAmount = to.getLoanAmount();

		String day1 = to.getAday();
		String month1 = to.getAmonth();
		String year1 = to.getAyear();
		StringBuffer sb = new StringBuffer();
		sb.append(day1).append("/").append(month1).append("/").append(year1);
		String ApplyDate = sb.toString();

		String day2 = to.getIday();
		String month2 = to.getImonth();
		String year2 = to.getIyear();
		StringBuffer sb1 = new StringBuffer();
		sb1.append(day2).append("/").append(month2).append("/").append(year2);
		String IssueDate = sb1.toString();

		String RateOfInterest = to.getRateOfInterest();
		String DurationOfLoan = to.getDurationOfLoan();

		/* Validation for Common Loan Apply */

		LoanValidation v = new LoanValidation();

		/* Validation for Loan Type */

		if (LoanType.contentEquals("empty")) {
			throw new BussinessException("Choose a Loan Type");
		} else {
			c++;
		}

		/* Validation for Loan Amount */

		if (v.loanAmountLength(LoanAmount)) {
			c++;
		} else {
			throw new BussinessException(
					"Enter a valid Amount for Loan Amount");
		}

		if (v.isNull(LoanAmount)) {
			c++;
		} else {
			throw new BussinessException("Loan Amount is blank");
		}

		if (v.isDigit(LoanAmount)) {
			c++;
		} else {
			throw new BussinessException("Loan Amount should B in format");
		}

		/* Validation for Apply Date */

		if (v.applyDateCheck(ApplyDate)) {
			c++;
		} else {
			throw new BussinessException("Choose Apply Date");
		}

		/* Validation for Issue Date */

		if (v.issueDateCheck(IssueDate)) {
			c++;
		} else {
			throw new BussinessException("Choose Issue Date");
		}

		/*
		 * if (v.applyDateCheck(ApplyDate, IssueDate)) { c++; } else { throw new
		 * BussinessException("30 Days of apply date"); }
		 */

		/* Validation for Rate Of Interest */

		if (RateOfInterest.contentEquals("empty")) {
			throw new BussinessException("Choose a Value for RateOfInterest");
		} else {
			c++;
		}

		/* Validation for Duration Of Loan */

		if (DurationOfLoan.contentEquals("empty")) {
			throw new BussinessException("Choose a Value for DurationOfLoan");
		} else {
			c++;
		}

		return flag;

	}

}
