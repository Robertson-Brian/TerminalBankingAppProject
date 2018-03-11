package com.revature.project01.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import com.revature.project01.tools.terminalBankAppTools;

public class Customer extends Person 
{
	public Customer() {} // default constructor


	public Customer(String in1, String in2, Character stat)
	{
		super(in1, in2, stat);
	}
	

	
	public void viewUserInfo()
	{
	   	System.out.println("\n\nUser Info --------------------------------* ");
	   	System.out.print("uname = " + uname);
	   	System.out.println(" - passwd = " + passwd);
        
	   	ArrayList<File> arrayList = new ArrayList<File>();
	   	
	   	terminalBankAppTools.getFiles("Account/", arrayList);
	   	
	   	for(File elem : arrayList)
	   	{
	   		Account displayAccount = new Account();
	   		
		   	displayAccount = terminalBankAppTools.deserialize(displayAccount, elem.toString());
		   	
		   	for(String i : displayAccount.owners)
		   	{
		   		if(i.equals(this.uname))
				{	
					System.out.print("accountNumber " + displayAccount.accountNumber);
					System.out.println(" - balance: " + displayAccount.balance);
				}
			}
	   	}
	   	
	}
	
	public void personMenu()
	{
    	String input = "z";
    	do
    	{
   			this.viewUserInfo();

    		System.out.println(" -- Customer account menu \n");
    		
    		System.out.println(" -- To apply for new account press a");
    		System.out.println(" -- To apply to join accounts press j");
    		System.out.println(" -- To withdraw funds press w");
    		System.out.println(" -- To deposit funds press d");
    		System.out.println(" -- To transfer funds press t");
    		
    		System.out.println(" -- To go back press b");
    		
	   		Scanner sc = new Scanner(System.in);
	   		input = sc.nextLine();
	   		
	   		if(input.equals("a"))
	   			Person.applyForAccount(this.uname);
	   		
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




