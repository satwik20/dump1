package com.cts.bms_controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cts.bms_bo.Updatebo;
import com.cts.bms_dao.UpdateDao;
import com.cts.bms_exception.BussinessException;
import com.cts.bms_to.RegistrationTo;


public class UpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ServletConfig config=null;
	public void  init(ServletConfig config)
	{
		this.config=config;
	}
    public UpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UpdateDao dao=new UpdateDao();
		ServletContext context=config.getServletContext();
		System.out.print(context.getAttribute("customerId").toString());
		RegistrationTo to=dao.retrieve_Details(context.getAttribute("customerId").toString());
		System.out.println("yhcfgh"+to.getAccountNo());
		request.setAttribute("padetails",to);
		RequestDispatcher dispatcher=request.getRequestDispatcher("/Update_Details.jsp");
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("enter into updatecontroller");
		
		ServletContext context=config.getServletContext();
		
		if("Update".equals(request.getParameter("Update")))
		{
			UpdateDao d=new  UpdateDao();
			RegistrationTo to =d.details(context.getAttribute("customerId").toString());
			//to.setCustomer_id(request.getParameter("customer_Id"));
			//to.setUser_Name(request.getParameter("name"));
			to.setAdderss(request.getParameter("Address"));
			to.setContact_No(request.getParameter("Contact_No"));
			to.setEmail(request.getParameter("Email"));
			to.setMarital_Status(request.getParameter("Marrital_Status"));
			to.setAccount_Type(request.getParameter("Account_Type"));
			to.setCitizenship(request.getParameter("Citizenship"));
			to.setGardian_Type(request.getParameter("Guardian_Type"));
			to.setGuardian_Name(request.getParameter("Guardia_Name"));
			to.setGardian_Type(request.getParameter("gardian_Type"));
			
			System.out.println(to.getUser_Name());
			System.out.println(to.getName());
			
			
			
			if(to.getAccount_Type()=="Salary")
			{
				
			}else
			{
				to.setAccount_Type("Savings");
			}
			Updatebo bo=new Updatebo();
			try {
				if(bo.validupdateDetails(to))
				{
					//System.out.println(to.getInitial_Deposit());
					UpdateDao dao=new UpdateDao();
					if(dao.update_Cust_Details(to))
					{
						System.out.println("updated");
					}
					else
						System.out.println(" not updated");
					
				}
			} catch (BussinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
		}else
		{
		
			UpdateDao dao=new UpdateDao();
			ServletContext context1=config.getServletContext();
			System.out.print(context1.getAttribute("customerId").toString());
			RegistrationTo to=dao.retrieve_Details(context1.getAttribute("customerId").toString());
			System.out.println("yhcfgh"+to.getAccountNo());
			request.setAttribute("padetails",to);
			RequestDispatcher dispatcher=request.getRequestDispatcher("/Update_Details.jsp");
			dispatcher.forward(request, response);
		}
		
		
	}

}



