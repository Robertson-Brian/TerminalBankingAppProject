package com.revature.project01.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import com.revature.project01.tools.BasicTools;

public class Employee extends Person {

	public Employee() {}

	public Employee(String in1, String in2)
	{
		super(in1, in2);
	}
	
	public void viewUserInfo()
	{  		
   		logger.debug("\nclass - " + this.getClass().getName()); //will return the name (as String) (== "SomeClass")
   		logger.debug("class - " + this.getClass()); //will return the SomeClass' Class object
   		
   		System.out.print("\nusername = " + uname);
   		logger.debug(" : password = " + passwd);
   		
   		logger.debug("\n -- " + this.getClass().getName() + " -----------------\n");   
   		
	   	ArrayList<File> arrayList = new ArrayList<File>();

	   	final String directoryName = System.getProperty("user.dir");
	   	BasicTools.listf( directoryName, arrayList );
	   	
	   	for(File elem : arrayList)
	   	{
	   	    String tmp = elem.getName();
	   	    	   	    
	   	 logger.debug("elem.getname " + elem.getName());
	   	    	
		   	if(tmp.matches("[A-Z,a-z,0-9]+\\.[A-Z,a-z,0-9]+.BankingApp"))
	   	    {
	   	   		Person newUser = new Customer();   		

   	   			newUser = BasicTools.Deserialize(newUser, elem.toString());

		   	    if(newUser.uname != null)
		   	    {
		   	    	System.out.println("\n-- username : " + newUser.uname + " - password : " + newUser.passwd);
		   	    	
		   	   		System.out.println("-- Account number ------- Balance ------- Status -------");
		   	    
	   	        
		   		   	ArrayList<File> arrayList2 = new ArrayList<File>();
	
		   		   	final String directoryName2 = System.getProperty("user.dir");
		   		   	BasicTools.listf( directoryName2, arrayList2 );
		   		   	
		   		   	for(File elem2 : arrayList2)
		   		   	{
		   		   	    String tmp2 = elem2.getName();
		   		   	    
			   		   	 logger.debug("elem2.getname " + elem2.getName());
			   		   	 logger.debug("uname " + newUser.uname + " passwd " + newUser.passwd);

	   			   	    if(tmp2.matches(".+" + newUser.uname + "\\." + newUser.passwd + ".BankingApp"))
		   		   	    {
		   		   	   		Account newAccount = new Account();   		
		   		   	   		
	   		   	   			newAccount = BasicTools.Deserialize(newAccount, elem2.toString());

		   		   	        if(newAccount.accountNumber != null)
		   		   	        {
		   		   	        		System.out.println("   " + newAccount.accountNumber + "                   " + newAccount.balance +
		   		   	        				           "             " + newAccount.status);
		   		   	        }
		   		   	    }
		   		   	}
		   	    }
	   	    }
	   	} 
	   	   
        
   		int input = 0;

   		// apply for new account, transfer funds, apply to make a join accounts, close account, exit?
   			
   	   	System.out.println("\n - Account options ------------------------------");
   	   	System.out.println("|                                                    |");
   	   	System.out.println("| To approve or deny an account, press 1 & return            |");
   	   	System.out.println("| To approve or deny a joint account, press 2 & return       |");
   	   	System.out.println("| To exit, press 3 & return                          |");
   	   	System.out.println("|                                                    |");
   	   	System.out.println(" ----------------------------------------------------");
   	   		
   	   	Scanner sc = new Scanner(System.in);
   	   	input = sc.nextInt();

   	   	if(input == 1)
   	   		approveDeny();
   		
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
			   	   	   	
			   	   	else
			   	   		newAccount.status = 'c';
			   	   	
				   	System.out.println("   " + newAccount.accountNumber + "                   " + newAccount.balance +
	                           "             " + newAccount.status);
				   	
			        BasicTools.serialize(newAccount, tmp);
			   	} 	   	
		   	}
	   	}
      	
	}
	


}
