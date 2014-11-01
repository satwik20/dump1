package com.cts.bms_validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*validation
 * validate all the details for bfs
 */
public class Validation {
	// validate the names
	public static boolean alpha_Checking(String str) {
		String alpha = "[A-Za-z ]+";

		boolean flag = false;
		if (str.matches(alpha))

		{
			flag = true;
		}
		return flag;

	}

	// validate the phone number
	public static boolean contactNo_Checking(String str) {
		String alpha = "[0-9]{10}";

		boolean flag = false;
		if (str.matches(alpha))

		{
			flag = true;
		}
		return flag;

	}
	
	public static boolean isnum(String str) {
		String alpha = "[0-9]{1,5}";

		boolean flag = false;
		if (str.matches(alpha))

		{
			flag = true;
		}
		return flag;

	}


	// validate the account number

	public static boolean AccountNo_Checking(String str) {
		String alpha = "[0-9]{16}";

		boolean flag = false;
		if (str.matches(alpha))

		{
			flag = true;
		}
		return flag;

	}

	// validate the email details
	public static boolean email_Checking(String str) {
		String email = "[A-Za-z ]+[0-9]+@[a-z]+[.][a-z]{3}";

		boolean flag = false;
		if (str.matches(email))

		{
			flag = true;
		}
		return flag;

	}

	// validate the date before the current date
	public static boolean date_Before(String str) {
		boolean flag = false;
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date d1 = sdf.parse(str);
			if (d1.before(d)) {
				return true;
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flag;
	}

	// validate the date after the current date
	public static boolean date_After(String str) {
		boolean flag = false;
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date d1 = sdf.parse(str);
			String fromDate1 = sdf.format(d);
			String currentDate = sdf.format(d1);
			if (d1.after(d) || fromDate1.equals(currentDate)) {
				return true;
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flag;
	}

	// validate the date format

	public static boolean date_formate(String str) {
		boolean flag = false;

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date d1 = sdf.parse(str);

			if (str.equals(sdf.format(d1))) {
				return true;
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flag;
	}

	// calculating the activation date
	public static String activation_Date(String str) {
		String str1 = null;

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date d1 = sdf.parse(str);
			Calendar c = Calendar.getInstance();
			c.setTime(d1);
			c.add(Calendar.DATE, 5);

			d1 = c.getTime();
			str1 = sdf.format(d1);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return str1;
	}
	
	public static boolean notempty(String str)
	{
		boolean flag=false;
		if(str=="")
		{
			flag=true;
		}
		return flag;
	
	}
	
	
	public static boolean check_Month(String str1,String str2) {
		boolean flag = false;
		Calendar c= Calendar.getInstance();
		Date d1=new Date();
		Date d2=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			d1=sdf.parse(str1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			d2=sdf.parse(str2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.setTime(d1);
		long time1=c.getTimeInMillis();
		//System.out.println(time1);
		c.setTime(d2);
		long time2=c.getTimeInMillis();
		//System.out.println(time2);
		long res=time2-time1;
		res=res/1000;
		//System.out.println(res);
	
		long t1=34128000;
		//System.out.println(t1);
		if(res<=t1)
		{
			flag=true;
		}
		else
		{
			flag=false;
		}
		
		
		//c.setTime(s);
		return flag;
	}
	

}
