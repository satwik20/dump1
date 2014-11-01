package com.cts.bms_controller;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import com.csvreader.CsvWriter;
import com.cts.bms_bo.View_TransactionBO;
import com.cts.bms_exception.*;
//import com.cts.bms_file.File_Download;
import com.cts.bms_to.View_TransactionDetailTO;
import com.cts.bms_to.View_TransactionTO;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

/**
 * Servlet implementation class View_Transaction_Controller
 */
public class View_Transaction_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public View_Transaction_Controller() {
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
		
		
		
		ArrayList<View_TransactionDetailTO> res=null;
		System.out.println(request.getParameter("download"));
		
		String entryDate=request.getParameter("start_Date_Day")+"/"+request.getParameter("start_Date_Month")+"/"+request.getParameter("start_Date_Year");
		String endDate=request.getParameter("end_Date_Day")+"/"+request.getParameter("end_Date_Month")+"/"+request.getParameter("end_Date_Year");
		String transactionType=request.getParameter("transaction_Type");
		String transactionCount=request.getParameter("noOfTransaction");
		String view=request.getParameter("view");
		String download=request.getParameter("download");
		
		//System.out.println(view);
		//System.out.println(download);
		System.out.println("tran"+transactionCount);
		int count5=0;
		int count=0;
		if(transactionCount.equals("empty")==false)
		{
			count5=Integer.parseInt(transactionCount);
			count=count5;
		//System.out.println(count5);
	
		}
		View_TransactionTO to= new View_TransactionTO();
		to.setAccountNo("1000000000000026");
		to.setEntryDate(entryDate);
		to.setEndDate(endDate);
		to.setTransactionType(transactionType);
		to.setTransactionCount(transactionCount);
		System.out.println("hi"+to.getTransactionType());
		int size=0;
	
		View_TransactionBO bo= new View_TransactionBO();
		try {
			//System.out.println("before");
			res=bo.validateDetail(to);
			//System.out.println("after");
			if(res!=null)
			 size=res.size();
		} catch (BussinessException e1) {
			// TODO Auto-generated catch block
			//System.out.println("business exception");
			request.setAttribute("to",to);
			request.setAttribute("err",e1.getMessage());
			RequestDispatcher dispatcher1 =request.getRequestDispatcher("Enter_Transaction_Details.jsp");
			dispatcher1.forward(request, response);
		}
		
		
		HttpSession session=request.getSession(true);
		session.setAttribute("res", res);
		//request.setAttribute("res",res);
		request.setAttribute("count1",0);
		request.setAttribute("count2",count);
		
		//String temp=res.toString();
		request.setAttribute("count",count);
		request.setAttribute("size",size);
	//	View_TransactionDetailTO t=new View_TransactionDetailTO();
		//String id=t.getAccountNo();
		try
		{
		if(view!=null)
		{
		RequestDispatcher dispatcher=request.getRequestDispatcher("Transaction_Details_Success.jsp");
		dispatcher.forward(request, response);
		}
		}
		catch (Exception e) {
			
		}
		if(download!=null)
		{ 
			
			
			

          
             HttpSession s=request.getSession(true);
            
             String temp1=null;
             ArrayList<View_TransactionDetailTO> list = (ArrayList<View_TransactionDetailTO>) s.getAttribute("res");
             if("excel".equals(request.getParameter("format").toString()))
				{
             CsvWriter writer =new CsvWriter(new FileWriter("D:/mfrp/Pallav/id_entrydate_enddate.csv"), '\t');
             String det="AccountNo"+" "+"AccountName"+" "+"AccountType"+" "+"TransactionType"+" "+"TransactionDate"+" "+"TransactionID"+" "+"Amount";
             writer.write(det);
             writer.endRecord();
            
             for (View_TransactionDetailTO to1 : list)
             {
            	 
            	 temp1=to1.getAccountNo()+" "+to1.getAccountName()+" "+to1.getAccountType()
            	 +" "+to1.getTransactionType()+" "+to1.getTransactionDate().substring(0,10)+" "+
            	 to1.getTransactionID()+" "+to1.getAmount();
            	writer.write(temp1);
            	 writer.endRecord();
            	 temp1=null;
            	 
            	 
             }
           
             
		     
			
		writer.flush();
			writer.close();
					}
             else
             {
			 Document document=new Document();       
			 try {
				PdfWriter.getInstance(document,new FileOutputStream("D:/mfrp/Pallav/data.pdf"));
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			document.open();
		
			PdfPTable table=new PdfPTable(7);
			table.addCell("Account_No");
			table.addCell("Account_name");
			table.addCell("Account_Type");
			table.addCell("Transaction_Type");
			table.addCell("Transaction_Date");
			table.addCell("Transaction_ID");
			table.addCell("Amount");
			
			 for (View_TransactionDetailTO to1 : list)
             {
            	 
            	table.addCell(to1.getAccountNo());
            	table.addCell(to1.getAccountName());
            	table.addCell(to1.getAccountType());
            	table.addCell(to1.getTransactionType());
            	table.addCell(to1.getTransactionDate().substring(0,10));
            	table.addCell(to1.getTransactionID());
            	table.addCell(Integer.toString((int)to1.getAmount()));
            	
            	 
            	 
             }
			
			 try {
				document.add(table);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			
			 document.close();
             }
			
			RequestDispatcher dispatcher=request.getRequestDispatcher("Transaction_Details_Download.jsp");
			dispatcher.forward(request, response);
			


		}
		
		
		}

}
