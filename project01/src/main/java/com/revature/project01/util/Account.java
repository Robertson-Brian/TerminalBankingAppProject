package com.revature.project01.util;

import java.io.Serializable;

public class Account implements Serializable
{

	private static final long serialVersionUID = -2580129293792048770L;
	
	Integer accountNumber;
	Double balance = 0.0;
	Character status;
	
	public Account() {}

	public Account(Integer accNum, Double bal, Character stat)
	{
		accountNumber = accNum;
		balance = bal;
		status = stat;
	}

	
}
