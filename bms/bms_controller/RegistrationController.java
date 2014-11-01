package com.cts.bms_controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cts.bms_bo.RegistrationBo;
import com.cts.bms_bo.TransactionBo;
import com.cts.bms_dao.LoginDao;
import com.cts.bms_dao.RegistrationDao;
import com.cts.bms_exception.BussinessException;
import com.cts.bms_to.RegistrationTo;
import com.sun.corba.se.spi.protocol.RequestDispatcherDefault;


public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	public ServletConfig config=null;
	public void  init(ServletConfig config)
	{
		this.config=config;
	}
		
    
    public RegistrationController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if("Continue".equals(request.getParameter("Continue")))
		{
			RegistrationTo rt=new RegistrationTo();
			
			String dob=request.getParameter("DOB_Day")+"/"+request.getParameter("start_Date_Month")+"/"+request.getParameter("start_Date_Year");
			rt.setName(request.getParameter("name"));
			rt.setUser_Name(request.getParameter("user_Name"));
			rt.setPassword(request.getParameter("password"));
			rt.setRe_EnterPassword(request.getParameter("re_EnterPassword"));
			rt.setGardian_Type(request.getParameter("gardian_Type"));
			rt.setGuardian_Name(request.getParameter("guardian_Name"));
			rt.setAdderss(request.getParameter("adderss"));
			rt.setCitizenship(request.getParameter("citizenship"));
			rt.setCountry(request.getParameter("country"));
			rt.setState(request.getParameter("state"));
			rt.setEmail(request.getParameter("email"));
			rt.setGender(request.getParameter("gender"));
			rt.setMarital_Status(request.getParameter("marital_Status"));
			rt.setContact_No(request.getParameter("contact_No"));
			System.out.print(dob);
			rt.setDob(dob);
			
			
			HashMap<String,String> hm=null;
		HttpSession session=request.getSession(true);
			session.setAttribute("personaldetails", rt);
			RegistrationBo bo=new RegistrationBo();
			try {
				hm=bo.validPersonalDetails(rt);
				if(hm.size()==0)
				{
					LoginDao ld=new LoginDao();
					ArrayList<String> branch = ld.retrieve_Branch();
					ServletContext context1=config.getServletContext();
					context1.setAttribute("branch1", branch);
					request.setAttribute("branch", branch);	
				RequestDispatcher dispatcher=request.getRequestDispatcher("/AccountDetails.jsp");
				dispatcher.forward(request, response);
				}
				else
				{
					throw new BussinessException("error");
				}
			} catch (BussinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("to", rt);
				request.setAttribute("hm", hm);
				RequestDispatcher dispatcher=request.getRequestDispatcher("PersonalDetails.jsp");
				dispatcher.forward(request, response);
				
			}
			
				}
		
		if("Register".equals(request.getParameter("Register"))) 
		{HashMap<String,String> hm=null;
			System.out.println("inside the else");
			HttpSession session=request.getSession(true);
			RegistrationTo rt=(RegistrationTo)session.getAttribute("personaldetails");
			
			rt.setRegistrateion_date(request.getParameter("registration_Date"));
			rt.setAccount_Type(request.getParameter("account_Type"));
			rt.setBranch(request.getParameter("branch"));
			rt.setCitizen_Status(request.getParameter("citizen_Status"));
			rt.setInitial_Deposit(request.getParameter("initial_Deposit"));
			rt.setId_Proof_Type(request.getParameter("id_Proof_Type"));
			rt.setId_Document_No(request.getParameter("id_Document_No"));
			rt.setRefrence_Account_Holder_Name(request.getParameter("refrence_Account_Holder_Name"));
			rt.setReference_Account_Holder_No(request.getParameter("reference_Account_Holder_No"));
			rt.setReference_Account_Holder_Address(request.getParameter("reference_Account_Holder_Address"));
			RegistrationBo bo=new RegistrationBo();
		
			try {
				hm=bo.validate_AccountDetails(rt);
				System.out.println(hm.size());
				
				if(hm.size()==0)
				{
					RegistrationDao rd=new RegistrationDao();
					String refNo=rt.getReference_Account_Holder_No().toString();
					if(rd.check_Reference(refNo))
					{
						rd.insert_Details(rt);
						request.setAttribute("success","successfully account is created");
						RequestDispatcher dispatcher=request.getRequestDispatcher("login.jsp");
						dispatcher.forward(request, response);
						System.out.println("Success");
					}
					else
					{
						hm.put("refaccount", "references account not found");
						request.setAttribute("to", rt);
						request.setAttribute("hm", hm);
						RequestDispatcher dispatcher=request.getRequestDispatcher("AccountDetails.jsp");
						dispatcher.forward(request, response);
						System.out.println("ref not found");
					}
				}
				else
				{	hm.put("refaccount", "references account not found");
					request.setAttribute("to", rt);
					request.setAttribute("hm", hm);
					System.out.println(rt.getBranch());
					RequestDispatcher dispatcher=request.getRequestDispatcher("AccountDetails.jsp");
					dispatcher.forward(request, response);
				}
			} catch (BussinessException e) {
				// TODO Auto-generated catch block
				
				request.setAttribute("to", rt);
				System.out.println(rt.getBranch());
				request.setAttribute("hm", hm);
				RequestDispatcher dispatcher=request.getRequestDispatcher("AccountDetails.jsp");
				dispatcher.forward(request, response);
				
				
				e.printStackTrace();
			}
			
			
			
			
			
		}

	}

}
