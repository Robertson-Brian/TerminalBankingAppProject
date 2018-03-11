package com.revature.project01.util;

import java.io.Serializable;
import java.util.ArrayList;

public class Account implements Serializable
{
	private static final long serialVersionUID = -6224749724667534594L;
	Integer accountNumber;
	Double balance = 0.0;
	Character status;
	ArrayList<String> owners = new ArrayList<String>();
	
	public Account() {}
	
	public Account(Integer accNum, Double bal, Character stat, String owner)
	{
		accountNumber = accNum;
		balance = bal;
		status = stat;
		owners.add(owner);
	}

}
