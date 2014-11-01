package com.cts.bms_controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cts.bms_exception.BussinessException;
import com.cts.bms_bo.LoanApplyBo;
import com.cts.bms_to.LoanApplyTo;

public class LoanApply extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		LoanApplyBo bo = new LoanApplyBo();
		LoanApplyTo to = new LoanApplyTo();

		/* Setting the variables for Loan Apply */

		to.setLoanType((String) request.getParameter("loantype"));
		to.setLoanAmount((String) request.getParameter("LoanAmount"));

		to.setAday(request.getParameter("ApplyDate_Day"));
		to.setAmonth(request.getParameter("ApplyDate_Month"));
		to.setAyear(request.getParameter("ApplyDate_Year"));

		to.setIday(request.getParameter("IssueDate_Day"));
		to.setImonth(request.getParameter("IssueDate_Month"));
		to.setIyear(request.getParameter("IssueDate_Year"));

		to.setRateOfInterest((String) request.getParameter("RateofInterest"));
		to.setDurationOfLoan((String) request.getParameter("Duration"));

		request.setAttribute("ubean", to);
		session.setAttribute("to", to);

		try {

			if (bo.ValidateLoanApplyDetails(to)) {

				/* Choosing the Loan Type based on selection */

				if (request.getParameter("loantype").contentEquals(
						"personalhousing")) {
					request.setAttribute("key", "yes");
					RequestDispatcher rd = request
							.getRequestDispatcher("/Apply_Loan.jsp");
					rd.forward(request, response);
				} else if (request.getParameter("loantype").contentEquals(
						"education")) {
					request.setAttribute("key1", "yes");
					RequestDispatcher rd = request
							.getRequestDispatcher("/Apply_Loan.jsp");
					rd.forward(request, response);
				} else {

				}
			} else {

			}

		} catch (BussinessException e) {
			request.setAttribute("err", e.getMessage());
			RequestDispatcher rd = request
					.getRequestDispatcher("/Apply_Loan.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
