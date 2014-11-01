package com.cts.bms_to;

public class View_TransactionTO {
	public String entryDate;
	public String endDate;
	public String accountNo;
	
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String transactionType;
	public String transactionCount;
	
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getTransactionCount() {
		return transactionCount;
	}
	public void setTransactionCount(String transactionCount) {
		this.transactionCount = transactionCount;
	}
}
