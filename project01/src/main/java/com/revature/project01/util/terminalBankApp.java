package com.revature.project01.util;

import java.util.Scanner;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.revature.project01.tools.terminalBankAppTools;

public class terminalBankApp 
{
    static final Logger logger = Logger.getLogger(terminalBankApp.class);

    public static Integer nextAccountNumber;
    
	public void bankingApp() {}
	
    public static void main( String[] args )
    {
    	BasicConfigurator.configure();
        
	   	logger.debug("Banking app started");
		System.out.println(" -- Welcome to the terminal banking app \n");
        
        Person DefaultAdmin = new Admin("adm", "123", 'a');
        Person DefaultEmployee = new Employee("emp", "123", 'e');
        Person testCustomer = new Customer("cst", "123", 'c');
        
        terminalBankAppTools.serialize(DefaultAdmin, ("Admin/adm.BankingApp"));
        terminalBankAppTools.serialize(DefaultEmployee, ("Employee/emp.BankingApp"));
        terminalBankAppTools.serialize(testCustomer, ("Customer/cst.BankingApp"));
        
    	nextAccountNumber = terminalBankAppTools.getNextAccountNum();

    	String input = "z";
    	do
    	{
    		System.out.println(" -- Banking app main menu \n");
    		
    		System.out.println(" -- To login press l");
    		System.out.println(" -- To create a new user press n");
    		
    		System.out.println(" -- To exit press e");
    		
	   		Scanner sc = new Scanner(System.in);
	   		input = sc.nextLine();
	   		
	   		if(input.equals("l"))
	   			Person.loginToAccount();
	   		
	   		if(input.equals("n"))
	   			Person.createNewUser();
	   		
    	}
    	while(!input.equals("e"));
    	
		System.out.println(" -- Thank you for using the terminal bank app, goodbye. \n");

		return;
    }


}


























