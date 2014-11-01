package com.cts.bms_controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cts.bms_bo.LoginBo;
import com.cts.bms_dao.LoginDao;
import com.cts.bms_exception.BussinessException;
import com.cts.bms_to.LoginTo;
import com.cts.bms_to.RegistrationTo;
import com.sun.xml.internal.bind.CycleRecoverable.Context;

public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
public ServletConfig config=null;
public void  init(ServletConfig config)
{
	this.config=config;
}
	public LoginController() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		if ("login".equals(request.getParameter("submit"))) {
		String cus_id="";
		
		
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		
		LoginTo to= new LoginTo();
		to.setUserName(userName);
		to.setPassword(password);
		LoginBo bo =new LoginBo();
		Boolean flag1=false;
		try {
			flag1 = bo.userPassValid(to);
		
		
		if(flag1==true)
		{
			LoginDao dao=new LoginDao();
			cus_id=dao.retrieve_CustomerId(to);
			if("".equals(cus_id))
			{
				System.out.print("invalid");
				request.setAttribute("err","Invalid credential");
				RequestDispatcher dispatcher=request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
		
			}else
			{
				


			ServletContext context=config.getServletContext();
			System.out.println("customer_id"+cus_id);
			context.setAttribute("customerId", cus_id);
			System.out.println("hihihihihihi");
			
			RequestDispatcher dispatcher=request.getRequestDispatcher("home.jsp");
			dispatcher.forward(request, response);
			}
		}
		else
		{
			RequestDispatcher dispatcher=request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
		
	
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
		

		if ("Register".equals(request.getParameter("Register"))) {
			LoginDao ld = new LoginDao();
			ArrayList<String> state = ld.retrieve_States();
			ArrayList<String> country = ld.retrieve_CountryName();
			ServletContext context1=config.getServletContext();
			context1.setAttribute("state", state);
			context1.setAttribute("country", country);
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/PersonalDetails.jsp");
			dispatcher.forward(request, response);
		}
			}

}
