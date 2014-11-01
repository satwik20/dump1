package com.cts.bms_controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cts.bms_exception.BussinessException;
import com.cts.bms_bo.EducationLoanApplyBo;
import com.cts.bms_to.EducationloanTO;
import com.cts.bms_to.LoanApplyTo;

public class EducationLoanApply extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ServletConfig config=null;
	public void init(ServletConfig config)
	{
		this.config=config;
		
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		EducationLoanApplyBo bo2 = new EducationLoanApplyBo();
		EducationloanTO to2 = new EducationloanTO();
		LoanApplyTo to = (LoanApplyTo) session.getAttribute("to");

		/* Setting the variables for Loan Apply */

		to2.setLoanType(to.getLoanType());
		to2.setLoanAmount(to.getLoanAmount());
		ServletContext context=config.getServletContext();
		to2.setCustomerId(context.getAttribute("customerId").toString());
		String day1 = to.getAday();
		String month1 = to.getAmonth();
		String year1 = to.getAyear();
		StringBuffer sb = new StringBuffer();
		sb.append(day1).append("/").append(month1).append("/").append(year1);
		String date1 = sb.toString();
		to2.setApplyDate(date1);
		String day2 = to.getIday();
		String month2 = to.getImonth();
		String year2 = to.getIyear();
		StringBuffer sb1 = new StringBuffer();
		sb1.append(day2).append("/").append(month2).append("/").append(year2);
		String date2 = sb1.toString();
		to2.setIssueDate(date2);

		to2.setRateOfInterest(to.getRateOfInterest());
		to2.setDurationOfLoan(to.getDurationOfLoan());
		
		/* Setting the variables for Educational Loan Apply */

		to2.setCfees(request.getParameter("CourseFees"));
		to2.setCname(request.getParameter("CourseName"));
		to2.setFname(request.getParameter("FatherName"));
		to2.setFoccupation(request.getParameter("FatherOccupation"));
		to2.setFtexperience(request.getParameter("FathersTotalExperience"));
		to2.setFexpwithcompany(request
				.getParameter("FathersExperienceWithCurrentCompany"));
		to2.setCardno(request.getParameter("RationCardNo"));
		to2.setIncome(request.getParameter("AnnualIncome"));

		request.setAttribute("eto", to2);
		request.setAttribute("ubean", to);

		try {
			if (bo2.ValidateEducationLoanApplyDetails(to2) == true) {
				RequestDispatcher rd = request
						.getRequestDispatcher("/Success.jsp");
				rd.forward(request, response);
			}

		} catch (BussinessException e) {
			request.setAttribute("key1", "yes");
			request.setAttribute("err", e.getMessage());
			RequestDispatcher rd = request
					.getRequestDispatcher("/Apply_Loan.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {

		}

	}

}
