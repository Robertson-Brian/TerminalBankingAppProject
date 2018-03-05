package com.revature.project01.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import com.revature.project01.tools.BasicTools;

public class Admin extends Employee {

	public Admin() {}

	public Admin(String in1, String in2)
	{
		super(in1, in2);
	}
	
	private void approveDeny()
	{
		// enter account num, approve or deny, read in account object, modify status and serialize it
		
		String input;
		
   	   	System.out.println("enter account num");
   	   	
   	   	Scanner sc = new Scanner(System.in);
   	   	input = sc.nextLine();

   	   	
	   	ArrayList<File> arrayList = new ArrayList<File>();

	   	final String directoryName = System.getProperty("user.dir");
	   	BasicTools.listf( directoryName, arrayList );
	   	
	   	Account newAccount = new Account();   		

	   	for(File elem : arrayList)
	   	{
	   	    String tmp = elem.getName();
	   	    	   	    
	   	    logger.debug("elem.getname " + elem.getName());
	   	    	
		   	if(tmp.matches(input + "\\..+"))
	   	    {
			   	logger.debug("input " + input);
		   	   		
	   	   		newAccount = BasicTools.Deserialize(newAccount, tmp);
	   	    
	   	
			   	System.out.println("   " + newAccount.accountNumber + "                   " + newAccount.balance +
		                           "             " + newAccount.status);
			   		 
			   	if(newAccount.status.equals('p') || newAccount.status.equals('c'))
			   	{
			   		System.out.println("approve account creation : 1");
			   	   	System.out.println("deny account creation : 2");
		
			   	   	input = sc.nextLine();
			   	   	   	
			   	   	if(input.equals("1"))
			   	   		newAccount.status = 'a';
			   	   	   	
			   	   	else if(input.equals("1"))
			   	   		newAccount.status = 'c';
			   	   	
			   	   	
				   	System.out.println("   " + newAccount.accountNumber + "                   " + newAccount.balance +
	                           "             " + newAccount.status);
				   	
			        BasicTools.serialize(newAccount, tmp);
			   	} 	   	
		   	}
	   	}
      	
	}
}
