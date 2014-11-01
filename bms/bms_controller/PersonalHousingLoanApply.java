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
import com.cts.bms_bo.PersonalHousingLoanApplyBo;
import com.cts.bms_to.LoanApplyTo;
import com.cts.bms_to.PersonalloanTO;

public class PersonalHousingLoanApply extends HttpServlet {
	private static final long serialVersionUID = 1L;
public ServletConfig config=null;
public void init(ServletConfig config)
{
	this.config=config;
	
}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		LoanApplyTo to = (LoanApplyTo) session.getAttribute("to");
		//ServletContext context=config.getServletContext();
		PersonalHousingLoanApplyBo bo1 = new PersonalHousingLoanApplyBo();
		PersonalloanTO to1 = new PersonalloanTO();

		/* Setting the variables for Loan Apply */

		to1.setLoanType(to.getLoanType());
		to1.setLoanAmount(to.getLoanAmount());
		//to1.setCustomerId(context.getAttribute("customerId").toString());
		String day1 = to.getAday();
		String month1 = to.getAmonth();
		String year1 = to.getAyear();
		StringBuffer sb = new StringBuffer();
		sb.append(day1).append("/").append(month1).append("/").append(year1);
		String date1 = sb.toString();
		to1.setApplyDate(date1);

		String day2 = to.getIday();
		String month2 = to.getImonth();
		String year2 = to.getIyear();
		StringBuffer sb1 = new StringBuffer();
		sb1.append(day2).append("/").append(month2).append("/").append(year2);
		String date2 = sb1.toString();
		to1.setIssueDate(date2);

		to1.setRateOfInterest(to.getRateOfInterest());
		to1.setDurationOfLoan(to.getDurationOfLoan());

		/* Setting the variables for Personal Housing Loan Apply */
		ServletContext context=config.getServletContext();
		to1.setCustomerId(context.getAttribute("customerId").toString());
		to1.setIncome(request.getParameter("AnnualIncome"));
		to1.setName(request.getParameter("CompanyName"));
		to1.setDesign(request.getParameter("Designation"));
		to1.setTexperience(request.getParameter("TotalExperience"));
		to1.setExpwithcompany(request
				.getParameter("ExperienceWithCurrentCompany"));
		request.setAttribute("bean", to1);
		request.setAttribute("ubean", to);

		try {
			if (bo1.ValidatePersonalHousingLoanApplyDetails(to1) == true) {
				RequestDispatcher rd = request
						.getRequestDispatcher("/Success.jsp");
				rd.forward(request, response);
			}

		} catch (BussinessException e) {
			request.setAttribute("err", e.getMessage());
			request.setAttribute("key", "yes");
			RequestDispatcher rd = request
					.getRequestDispatcher("/Apply_Loan.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {
			System.out.println("krish" + e.getMessage());
		}

	}

}
