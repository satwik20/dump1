package com.cts.bms_controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cts.bms_bo.TransactionBo;
import com.cts.bms_dao.LoginDao;
import com.cts.bms_dao.TransactionDao;
import com.cts.bms_dao.UpdateDao;
import com.cts.bms_exception.BussinessException;
import com.cts.bms_to.RegistrationTo;
import com.cts.bms_to.TransactionTo;

public class TransactionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ServletConfig config = null;

	public void init(ServletConfig config) {
		this.config = config;
	}

	public TransactionController() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		ServletContext context = config.getServletContext();
		String custid = context.getAttribute("customerId").toString();
		System.out.print(context.getAttribute("customerId").toString());
		LoginDao dao = new LoginDao();
		RegistrationTo to = dao.retrieveBank_Details(custid);
		request.setAttribute("trans", to);
		context.setAttribute("to", to);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("transaction.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		ServletContext context = config.getServletContext();
		String custid = context.getAttribute("customerId").toString();
		System.out.println("custoemrid"+custid);
		LoginDao ldao = new LoginDao();
		RegistrationTo cto = ldao.retrieveBank_Details(custid);
		// RegistrationTo cto=(RegistrationTo)context.getAttribute("to");
		// int amount=Integer.parseInt(request.getParameter("amount"));
		String type = request.getParameter("transcation_Type");
		String tdate = request.getParameter("transaction_Date");
		TransactionBo bo = new TransactionBo();
		TransactionTo to = new TransactionTo();
		to.setAmount(request.getParameter("amount"));
		to.setAccount_no(request.getParameter("account_no"));
		to.setAccount_name(request.getParameter("account_Holder_Name"));
		to.setAccount_type(request.getParameter("AccountType"));
		to.setTransaction_date(tdate);
		to.setTransaction_type(type);
		to.setTotal_amount(cto.getTotal_amount());
		to.setCountry(cto.getCountry());
		//System.out.println(cto.getLoan_Status());
		
		try {
			if (bo.validTransaction(to)) {
				if ("Withdraw".equals(to.getTransaction_type())) {
					
					
					int val=Integer.parseInt(to
							.getTotal_amount()) - Integer.parseInt(to
									.getAmount());
					int initAmt=bo.limit_Checking(to);
					if(val>initAmt)
					to.setTotal_amount(String.valueOf(val));
					else
					throw new BussinessException("insufficient balance");	
				}
				if ("Deposit".equals(to.getTransaction_type())) {
					to.setTotal_amount(String.valueOf((Integer.parseInt(to
							.getTotal_amount()) + Integer.parseInt(to
							.getAmount()))));
					
					
				}
				if ("Loan".equals(to.getTransaction_type())) {
					if("opening".equals(cto.getLoan_Status()))
					{
					int val=Integer.parseInt(to
							.getTotal_amount()) - (int)Math.round(Math.floor(Double.valueOf(cto.getEmi())));
					int initAmt=bo.limit_Checking(to);
					System.out.println("initamt"+initAmt+" value= "+val+" "+(int)Math.round(Math.floor(Double.valueOf(cto.getEmi()))));
					System.out.println("totalamount"+to.getTotal_amount());
					if(val>initAmt)
					{
					to.setTotal_amount(String.valueOf(val));
					}
					else
					{
						
					throw new BussinessException("insufficient balance");
					}
				to.setAmount(String.valueOf(val));
					
				}
					else
					{
						throw new BussinessException("sorry you dont hava any loan account");
					}
					
				}
				TransactionDao dao = new TransactionDao();
				if (dao.updateTransaction_details(to)) {
					
					LoginDao ldao1 = new LoginDao();
					cto = ldao.retrieveBank_Details(custid);
					System.out.print(cto.getTotal_amount());
					System.out.println("insert success");
				} else
					System.out.println("not insert ");

			}
		} catch (BussinessException e) {
			// TODO Auto-generated catch block
System.out.println(e.getMessage());
			request.setAttribute("err", e.getMessage());
			request.setAttribute("trans", to);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("transaction.jsp");
			dispatcher.forward(request, response);

			e.printStackTrace();
		}

	}

}
