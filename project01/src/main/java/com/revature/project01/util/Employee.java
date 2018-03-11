package com.revature.project01.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import com.revature.project01.tools.ConsoleBankAppTools;

public class Employee extends Person
{
	public Employee() {} // default constructor

	private static final long serialVersionUID = 6164319139768525393L;

	public Employee(String in1, String in2, Character stat)
	{
		super(in1, in2, stat);
	}
	
	public Boolean approveAccount()
	{
	   	System.out.println("pending accounts: ");
	   	
	   	ArrayList<File> arrayList = new ArrayList<File>();
	   	
	   	ConsoleBankAppTools.getFiles("Account/Pending", arrayList);
	   	
	   	for(File elem : arrayList)
	   	{
	   		Account displayAccount = new Account();
	   		
		   	displayAccount = ConsoleBankAppTools.deserialize(displayAccount, elem.toString());
		   	
			System.out.print("accountNumber:  " + displayAccount.accountNumber);
			System.out.print(" - balance:  " + displayAccount.balance);
			System.out.print(" - owner(s)");

			for(String i : displayAccount.owners)
		   	{				
				System.out.print(", " + displayAccount.owners);

		   	}
			System.out.print("\n");

	   	}
	   	
    	String selection = "z";
    	String accNum;

		System.out.println("To approve an account press x");
		System.out.println("To deny an account press z");

   		Scanner sc = new Scanner(System.in);
   		selection = sc.nextLine();
   		
		System.out.println("please enter an account number");
   		accNum = sc.nextLine();

   		if(selection.equals("x"))
   		{
   	    	String filepath;

   	   		Account approveAccount = new Account();

   	   		filepath = "Account/Pending/" + accNum + ".BankingApp";
   	   		
   			approveAccount = ConsoleBankAppTools.deserialize(approveAccount, filepath);
   			
   	   		filepath = "Account/" + accNum + ".BankingApp";

   	   		ConsoleBankAppTools.serialize(approveAccount, filepath);
   	   			
   	    	File file = new File("Account/Pending/" + accNum + ".BankingApp");
   	    	
   		   	logger.debug("account " + approveAccount.accountNumber + " approved");

   	    	file.delete();  
   		}
   			
   		if(selection.equals("z"))
   		{
   	    	String filepath;

   	   		Account denyAccount = new Account();

   	   		filepath = "Account/Pending/" + accNum + ".BankingApp";
   	   		
   			denyAccount = ConsoleBankAppTools.deserialize(denyAccount, filepath);
   			
   	   		filepath = "Account/Canceled/" + accNum + ".BankingApp";

   	   		ConsoleBankAppTools.serialize(denyAccount, filepath);
   	   		
   	   		File delFile = new File("Account/Canceled/" + accNum + ".BankingApp");
   	   		
   		   	logger.debug("account " + denyAccount.accountNumber + " denied");
   	        
   	   		delFile.delete();
   		}
		
		return true;
	}

	public void viewUserInfo()
	{
	   	System.out.println("\n\nUser Info --------------------------------^ ");
	   	System.out.print("uname = " + uname);
	   	System.out.println(" - passwd = " + passwd);
	   	System.out.print("this.uname = " + this.uname);
        System.out.println(" - this.passwd = " + this.passwd + "\n");
        
	   	ArrayList<File> arrayList = new ArrayList<File>();
	   	
	   	ConsoleBankAppTools.getFiles("Account/", arrayList);
	   	
	   	for(File elem : arrayList)
	   	{
	   		Account displayAccount = new Account();
	   		
		   	displayAccount = ConsoleBankAppTools.deserialize(displayAccount, elem.toString());

			System.out.print("accountNumber:  " + displayAccount.accountNumber);
			System.out.print(" - balance:  " + displayAccount.balance);
			System.out.print(" - owner(s)");

			for(String i : displayAccount.owners)
		   	{				
				System.out.print(", " + displayAccount.owners);

		   	}
			System.out.print("\n");

	   	}
	}
	
	public void personMenu()
	{
		this.viewUserInfo();

    	String input = "z";
    	do
    	{
    		System.out.println(" -- Customer account menu \n");
    		
    		System.out.println(" -- To approve / deny accounts press a");
    		System.out.println(" -- To deposit into a customers account press j");
    		System.out.println(" -- To  press w");
    		System.out.println(" -- To  press d");
    		System.out.println(" -- To  press t");
    		
    		System.out.println(" -- To go back press b");
    		
	   		Scanner sc = new Scanner(System.in);
	   		input = sc.nextLine();
	   		
	   		if(input.equals("a"))
	   			this.approveAccount();
	   		
	   		else if(input.equals("j"))
	   			this.applyTojoinAccounts();  // send account num?

	   		else if(input.equals("w"))
	   			this.withdraw();
	   		
	   		else if(input.equals("d"))
	   			this.deposit();
	   		
	   		else if(input.equals("t"))
	   			this.transfer();
    	}
    	while(!(input.equals("b")));
	}	
}
