package com.revature.project01.util;

public class Admin extends Employee 
{
	public Admin() {} // default constructor

	private static final long serialVersionUID = 2960031709505174922L;

	public Admin(String in1, String in2, Character stat)
	{
		super(in1, in2, stat);
	}
	
	public Boolean deleteAccount()
	{
		// delete file
		
		return true;
	}
	
	// alter other accounts
	// view all accounts inherited from employee
	// special admin menu with delete, and withdraw / deposit on all accounts
	
}

