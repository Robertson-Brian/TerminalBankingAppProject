package com.revature.project01.util;

import java.util.Scanner;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class terminalBankApp 
{
    static final Logger logger = Logger.getLogger(terminalBankApp.class);

	public void bankingApp() {}
	
    public static void main( String[] args )
    {
    	BasicConfigurator.configure();
        
	   	//logger.debug("Banking app started");
		System.out.println(" -- Welcome to the International Bank of Meows");
		System.out.println(" -- The future of meow banking \n");

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
    	
		System.out.println(" -- Thank you for using the International Bank of Meows, goodbye. \n");

		return;
    }


}


























