package com.cts.bms_controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.StyledEditorKit.BoldAction;

import com.cts.bms_bo.DeleteLoanDetailsBo;
import com.cts.bms_dao.DeleteLoanDetailsDao;
import com.cts.bms_dao.LoginDao;
import com.cts.bms_exception.BussinessException;
import com.cts.bms_to.RegistrationTo;

/**
 * Servlet implementation class DeleteLoanDetails
 */
public class DeleteLoanDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteLoanDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("controller");
		
		
		
		if("submit".equals(request.getParameter("submit")))
		{
		String accno=request.getParameter("accountNo");
		DeleteLoanDetailsBo bo=new DeleteLoanDetailsBo();
		try
		{
		if(bo.validate_Details(accno))
		{
			DeleteLoanDetailsDao dao=new DeleteLoanDetailsDao();
			String custId=dao.retrieveCustId(accno);
			
			if("".equals(custId))
			{
			throw new BussinessException("account no not found");	
			}
			else
			{
			LoginDao ldao=new LoginDao();
			RegistrationTo to=ldao.retrieveBank_Details(custId);
			request.setAttribute("to",to);
			request.setAttribute("display","ok");
			RequestDispatcher dispatcher=request.getRequestDispatcher("deleteaccount.jsp");
			dispatcher.forward(request, response);
			}
		}
		}
		catch(BussinessException e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		}
		else
		{
			String custId=request.getParameter("customerid");
			String accno=request.getParameter("accountNo");
			String loanstatus=request.getParameter("LoanStatus");
			DeleteLoanDetailsDao dao=new DeleteLoanDetailsDao();
			
			boolean flag=dao.deleteRecords(custId, accno, loanstatus);
			if(flag==true)
			{
				RequestDispatcher dispatcher=request.getRequestDispatcher("deletesuccess.jsp");
				dispatcher.forward(request, response);
			}
			else
				System.out.println("not Deleted");
			
			
		}
		
		
	}

}
