package com.revature.project01.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.project01.tools.BasicTools;

public class Customer extends Person 
{
    static final Logger logger = Logger.getLogger(BankingApp.class);

	public Customer() {}


	public Customer(String in1, String in2)
	{
		super(in1, in2);
	}
	

	public void viewUserInfo()
	{  		
		logger.debug("\nclass - " + this.getClass().getName()); //will return the name (as String) (== "SomeClass")
		logger.debug("class - " + this.getClass()); //will return the SomeClass' Class object
   		
   		System.out.print("\nusername = " + uname);
   		System.out.println(" : password = " + passwd);
   		System.out.println("\n -- Account number ------- Balance -----------------");
   		
   		
	   	ArrayList<File> arrayList = new ArrayList<File>();

	   	final String directoryName = System.getProperty("user.dir");
	   	BasicTools.listf( directoryName, arrayList );
	   	
	   	for(File elem : arrayList)
	   	{
	   	    String tmp = elem.getName();
	   	    	
	   	    if(tmp.matches("(.*)" + "[.]" + uname + "[.]" + passwd + ".BankingApp"))
	   	    {
	   	    	Account newAccount = new Account();
	      		
	   	   		newAccount = BasicTools.Deserialize(newAccount, elem.toString());
	   	        
	   	   		logger.debug("newAccount.accountNumber = " + newAccount.accountNumber);
	   	   		logger.debug("newAccount.balance = " + newAccount.balance);
	   	   		logger.debug("newAccount.status = " + newAccount.status);
	   	   		
	   	   		if(newAccount.accountNumber != null)
	   	   			if(newAccount.status != 'p' && newAccount.status != 'c')
	   	   				System.out.println("    " + newAccount.accountNumber + "                   " + newAccount.balance);
	   	    }
	   	} 
        
   		int input = 0;

   		// apply for new account, transfer funds, apply to make a join accounts, close account, exit?
   			
   	   	System.out.println("\n - Account options ------------------------------");
   	   	System.out.println("|                                                    |");
   	   	System.out.println("| To apply for new account, press 1 & return         |");
   	   	System.out.println("| To apply for joint account, press 2 & return       |");
   	   	System.out.println("| To withdraw funds, press 3 & return       |");
   	   	System.out.println("| To deposit funds, press 4 & return       |");
   	   	System.out.println("| To exit, press 5 & return                          |");
   	   	System.out.println("|                                                    |");
   	   	System.out.println(" ----------------------------------------------------");
   	   		
   	   	Scanner sc = new Scanner(System.in);
   	   	input = sc.nextInt();

   	   	if(input == 1)
   	   		applyForNewAccount();

   	   	if(input == 3)
   	   		withdraw();
   	   	
   	   	if(input == 4)
   	   		deposit();
   		
	}
	
    public void applyForNewAccount()
    {
    	logger.debug("accNum " + BankingApp.nextAccountNumber);
    	
    	Account newAccount = new Account(BankingApp.nextAccountNumber, 10.00, 'p');
    	
        String filename = (BankingApp.nextAccountNumber + "." + uname + "." + passwd + "." + "BankingApp");   
        
        BankingApp.nextAccountNumber++;
        BasicTools.setnextAccountNumber(BankingApp.nextAccountNumber);

    	//logger.debug("accNum " + BankingApp.nextAccountNumber);

        BasicTools.serialize(newAccount, filename);
                
   		this.viewUserInfo();
    }
    
    public void withdraw()
    {
   		int input = 0;
   		
		String stringIn;
		
   	   	System.out.println("enter account num to deposit to");
   	   	
   	   	Scanner sc = new Scanner(System.in);
   	   	stringIn = sc.nextLine();
   	   	
	   	ArrayList<File> arrayList = new ArrayList<File>();

	   	final String directoryName = System.getProperty("user.dir");
	   	BasicTools.listf( directoryName, arrayList );
	   	
	   	for(File elem : arrayList)
	   	{
	   	    String tmp = elem.getName();
	   	    	
	   	    if(tmp.matches(stringIn + "[.]" + uname + "[.]" + passwd + ".BankingApp"))
	   	    {
	   	    	Account newAccount = new Account();
	      		
	   	   		newAccount = BasicTools.Deserialize(newAccount, elem.toString());
	   	        
   	            logger.debug("newAccount.accountNumber = " + newAccount.accountNumber);
   	            logger.debug("newAccount.balance = " + newAccount.balance);
   	            logger.debug("newAccount.status = " + newAccount.status);
	   	   		
	   	   		if(newAccount.accountNumber != null)
	   	   			if(newAccount.status != 'p' && newAccount.status != 'c')
	   	   				System.out.println("    " + newAccount.accountNumber + "                   " + newAccount.balance);
	   	   		
	   	   	   	System.out.println("amount to withdraw");

	   	   	   	input = sc.nextInt();
	   	   	   	
	   	   	   	newAccount.balance -= input;
	   	   	   	
	   	        BasicTools.serialize(newAccount, elem.toString());
	   	   	   	
	   	    }
	   	} 
   		this.viewUserInfo();
    	
    }
    
    public void deposit()
    {
   		int input = 0;
   		
		String stringIn;
		
   	   	System.out.println("enter account num to deposit to");
   	   	
   	   	Scanner sc = new Scanner(System.in);
   	   	stringIn = sc.nextLine();
   	   	
	   	ArrayList<File> arrayList = new ArrayList<File>();

	   	final String directoryName = System.getProperty("user.dir");
	   	BasicTools.listf( directoryName, arrayList );
	   	
	   	for(File elem : arrayList)
	   	{
	   	    String tmp = elem.getName();
	   	    	
	   	    if(tmp.matches(stringIn + "[.]" + uname + "[.]" + passwd + ".BankingApp"))
	   	    {
	   	    	Account newAccount = new Account();
	      		
	   	   		newAccount = BasicTools.Deserialize(newAccount, elem.toString());
	   	        
   	            logger.debug("newAccount.accountNumber = " + newAccount.accountNumber);
   	            logger.debug("newAccount.balance = " + newAccount.balance);
   	            logger.debug("newAccount.status = " + newAccount.status);
	   	   		
	   	   		if(newAccount.accountNumber != null)
	   	   			if(newAccount.status != 'p' && newAccount.status != 'c')
	   	   				System.out.println("    " + newAccount.accountNumber + "                   " + newAccount.balance);
	   	   		
	   	   	   	System.out.println("amount to deposit");

	   	   	   	input = sc.nextInt();
	   	   	   	
	   	   	   	newAccount.balance += input;
	   	   	   	
	   	        BasicTools.serialize(newAccount, elem.toString());
	   	   	   	
	   	    }
	   	} 
   		this.viewUserInfo();
    }
    
    public void applyForJointAccount()
    {
    	
    	
    }
}
