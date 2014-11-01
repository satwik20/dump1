package com.cts.bms_bo;

import java.util.ArrayList;

import com.cts.bms_dao.View_TransactionDAO;
import com.cts.bms_exception.*;
import com.cts.bms_to.View_TransactionTO;
import com.cts.bms_validation.Validation;
import com.cts.bms_to.View_TransactionDetailTO;

public class View_TransactionBO {
	boolean flag = false;

	public ArrayList<View_TransactionDetailTO> validateDetail(
			View_TransactionTO to) throws BussinessException {
		ArrayList<View_TransactionDetailTO> result = null;
		int count = 0;

		if ((Validation.date_Before(to.getEntryDate()) == true)
				&& (Validation.check_Month(to.getEntryDate(), to.getEndDate()) == true)) {
			/*
			 * View_TransactionDAO vt=new View_TransactionDAO();
			 * result=vt.getDetails(to);
			 */
			count++;

		} else {
			throw new BussinessException(
					"statement can view less than 12 month only!!!!");
		}
		if ("empty".equals(to.getTransactionType())) {
			count--;
			throw new BussinessException("select the transaction type");
		}
		if (count == 1) {
			System.out.println("hi BO");
			View_TransactionDAO vt = new View_TransactionDAO();
			result = vt.getDetails(to);
			
		}

		return result;

	}

}
