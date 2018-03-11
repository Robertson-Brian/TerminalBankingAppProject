package com.revature.project01.util;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.project01.tools.terminalBankAppTools;

public class Person implements Serializable
{
    static final Logger logger = Logger.getLogger(terminalBankApp.class);

	private static final long serialVersionUID = -3522257060495844756L;
	protected String uname;
	protected String passwd;
	Character status;
	ArrayList<String> accounts = new ArrayList<String>();

	public Person() {} // default constructor
	
	public Person(String in1, String in2, Character stat)
	{
		uname  = in1;
		passwd = in2;
		status = stat;
	}
	
	public static Boolean createNewUser()
	{	
		String usname;
    	String pass;
    	
	   	Scanner sc = new Scanner(System.in);
	   	System.out.print("Enter your username: ");
	   	usname = sc.nextLine();
			
	   	System.out.print("Enter your password: ");
	   	pass = sc.nextLine();
	   		   	
	   	Person newUser;
	   	
	   	if(!(terminalBankAppTools.validate("Customer/", usname, pass)))
	   	{ 	
   	 	    System.out.println("uname free");

	   		newUser = new Customer(usname, pass, 'c'); 
	   		String filename = ("Customer/" + usname + ".BankingApp");            
	   		terminalBankAppTools.serialize(newUser, filename);	

	   	}
	   	else
	   	{
		   	System.out.println("Account already exists, please choose other username and password\n");
	   		return false;
	   	}
	   	newUser.personMenu();   		
	   	
	   	logger.debug("new user " + usname + " " + pass + " created");

   		return true;
	}

	public static Boolean loginToAccount()
	{
		String usname;
    	String pass;
    	
	   	Scanner sc = new Scanner(System.in);
	   	System.out.print("Enter your username: ");
	   	usname = sc.nextLine();
			
	   	System.out.print("Enter your password: ");
	   	pass = sc.nextLine();
	   	
	   	Person returnUser;
	   	
	   	if(terminalBankAppTools.validate("Customer/", usname, pass))
	   	{ 	
	   		returnUser = new Customer(); 
	   		String filename = ("Customer/" + usname + ".BankingApp");            
	   		returnUser = terminalBankAppTools.deserialize(returnUser, filename);
	   		
	   		if(!(returnUser.passwd.equals(pass)))
	   			return false;
	   	}
	   	else if(terminalBankAppTools.validate("Employee/", usname, pass))
	   	{ 	
		   	returnUser = new Employee(); 
	   		String filename = ("Employee/" + usname + ".BankingApp");            
	   		returnUser = terminalBankAppTools.deserialize(returnUser, filename);
	   		
	   		if(!(returnUser.passwd.equals(pass)))
	   			return false;
	   	}
	   	else if(terminalBankAppTools.validate("Admin/", usname, pass))
	   	{ 	
		   	returnUser = new Admin(); 
	   		String filename = ("Admin/" + usname + ".BankingApp");            
	   		returnUser = terminalBankAppTools.deserialize(returnUser, filename);
	   		
	   		if(!(returnUser.passwd.equals(pass)))
	   			return false;
	   	}
	   	else
	   	{
		   	System.out.println("User name or password not found\n");

	   		return false;
	   	}
    	
        returnUser.personMenu();   		
	   	
	   	logger.debug(usname + " " + pass + " logged in");
        
   		return true;
	}
	
	public static Boolean applyForAccount(String owner)
	{
		// add new account to the pending folder	
		
		Account newAccount = new Account(terminalBankApp.nextAccountNumber, 0.0, 'a', owner);
		
		System.out.println("owner + \".BankingApp\" = " + terminalBankApp.nextAccountNumber + ".BankingApp");

		
   		terminalBankAppTools.serialize(newAccount, "Account/Pending/" + terminalBankApp.nextAccountNumber + ".BankingApp");	

		System.out.println("terminalBankApp.nextAccountNumber " + terminalBankApp.nextAccountNumber);

   		terminalBankApp.nextAccountNumber++;
   		terminalBankAppTools.setAccountNum(terminalBankApp.nextAccountNumber);
   		
		System.out.println("terminalBankApp.nextAccountNumber " + terminalBankApp.nextAccountNumber);
		
	   	logger.debug(owner + " applied for account");


		return true;
	}
	
	public Boolean applyTojoinAccounts()
	{
		// enter account num
		// check if account num exists
		//
		// use account user list to get users uname and pass
		// require uname and pass entry
		//
		// add new account num to users account array
		// add user to accounts user array
		//
		//serialize acount amd users
		
		return true;
	}
	
	public int withdraw()
	{
    	String input = "z";
    	double ammount;
    	String filepath;

		System.out.println("Enter the number of the account you would like to make a withdraw from");
   		Scanner sc = new Scanner(System.in);
   		input = sc.nextLine();
   		
   		Account withdrawAccount = new Account();

   		filepath = "Account/" + input + ".BankingApp";
   		
		withdrawAccount = terminalBankAppTools.deserialize(withdrawAccount, filepath);
   		
	   	for(String i : withdrawAccount.owners)
	   	{
			//System.out.println(i);
			
			if(i.equals(this.uname))
			{
				System.out.println("Enter the ammount");
				ammount = sc.nextInt();
				
				withdrawAccount.balance -= ammount;
				
		   		terminalBankAppTools.serialize(withdrawAccount, filepath);
		   		
			   	logger.debug(ammount + " withdrawn from " + withdrawAccount.accountNumber + " by " + this.uname);

		   		return 1;
			}

	   	}
   		
		System.out.println("You do not have permission to withdraw from that account");
		
		return 0;
	}
	
	public int deposit()
	{
    	String input = "z";
    	double ammount;
    	String filepath;

		System.out.println("Enter the number of the account you would like to make a deposit to");
   		Scanner sc = new Scanner(System.in);
   		input = sc.nextLine();
   		
		System.out.println("Enter the ammount");
		ammount = sc.nextInt();

   		Account depositAccount = new Account();

   		filepath = "Account/" + input + ".BankingApp";
   		
		System.out.println("file ---  " + filepath);

   		depositAccount = terminalBankAppTools.deserialize(depositAccount, filepath);
		
		depositAccount.balance += ammount;
		
   		terminalBankAppTools.serialize(depositAccount, filepath);	
   		
	   	logger.debug(ammount + " deposited to " + depositAccount.accountNumber + " by " + this.uname);

		return 1;
	}
	
	public int transfer()
	{
    	String input = "z";
    	String input2 = "z";
    	double ammount;
    	String filepath;
    	String filepath2;

		System.out.println("Enter the number of the account you would like to make a withdraw from");
   		Scanner sc = new Scanner(System.in);
   		input = sc.nextLine();
   
		System.out.println("Enter the number of the account you would like to make a deposit to");
   		input2 = sc.nextLine();
   		
   		Account withdrawAccount = new Account();

   		filepath = "Account/" + input + ".BankingApp";
   		
		System.out.println("file ---  " + filepath);

		withdrawAccount = terminalBankAppTools.deserialize(withdrawAccount, filepath);
   		
   		
	   	for(String i : withdrawAccount.owners)
	   	{			
			if(i.equals(this.uname))
			{
				System.out.println("Enter the ammount");
				ammount = sc.nextInt();
				
				withdrawAccount.balance -= ammount;
				
		   		terminalBankAppTools.serialize(withdrawAccount, filepath);
		   		
		   		
		   		filepath2 = "Account/" + input2 + ".BankingApp";

		   		Account depositAccount = new Account();
		   		
				System.out.println("file ---  " + filepath2);

		   		depositAccount = terminalBankAppTools.deserialize(depositAccount, filepath2);
				
				depositAccount.balance += ammount;
				
		   		terminalBankAppTools.serialize(depositAccount, filepath2);
		   		
			   	logger.debug(ammount + " transfered from " + withdrawAccount.accountNumber + " to " + 
			   			     depositAccount.accountNumber + " by " + this.uname);
		   		
		   		return 1;
			}

	   	}
   		
		System.out.println("You do not have permission to withdraw from that account");
		
		return 0;
	}
	
	public void viewUserInfo()
	{
	   	System.out.println("viewUserInfo -------------------------------- ");
	   	System.out.print("uname = " + uname);
	   	System.out.println(" - passwd = " + passwd);
	}

	public void personMenu()
	{
		viewUserInfo();
	}

}

















