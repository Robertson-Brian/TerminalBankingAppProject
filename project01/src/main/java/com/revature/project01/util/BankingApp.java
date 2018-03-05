package com.revature.project01.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import com.revature.project01.tools.BasicTools;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * Hello world!
 *
 */
public class BankingApp 
{
    static final Logger logger = Logger.getLogger(BankingApp.class);
    
    public static Integer nextAccountNumber;
        
	public void bankingApp() {}
	
    public static void main( String[] args )
    {

        Person DefaultAdmin = new Admin("adm", "123");
        Person DefaultEmployee = new Employee("emp", "123");
        Person testCustomer = new Customer("cst", "123");
        
        BasicTools.serialize(DefaultAdmin, ("adm.123.BankingApp"));
        BasicTools.serialize(DefaultEmployee, ("emp.123.BankingApp"));
        BasicTools.serialize(testCustomer, ("cst.123.BankingApp"));
    	
    	int tmp = 0;
    	nextAccountNumber = BasicTools.initnextAccountNumber();
    	
        do
        {
	        tmp = mainMenu();
	        
	        if(tmp == 1)
	        	login();
	        
	        else if(tmp == 2)
	        	createNewUser();
	        
    	}
    	while(tmp != 3);
        
   		System.out.println("\nThank you for using the BankingApp, have a nice day.");

    }
    
    public static int mainMenu()
    {
    	int input = 0;
    	
   		System.out.println(" - Welcome to the BankingApp ------------------------");
   		System.out.println("|                                                    |");
   		System.out.println("| To login to an existing account, press 1 & return  |");
   		System.out.println("| To create a new user, press 2 & return             |");
   		System.out.println("| To exit, press 3 & return                          |");
   		System.out.println("|                                                    |");
   		System.out.println(" ----------------------------------------------------");
   		
   		Scanner sc = new Scanner(System.in);
   		input = sc.nextInt();
        
   		return input;
    }
    
    public static void login()
    {
    	String uname;
    	String passwd;
    	
   		Scanner sc = new Scanner(System.in);
   		System.out.print("Enter your username: ");
		uname = sc.nextLine();
		
   		System.out.print("Enter your password: ");
   		passwd = sc.nextLine();

        String filename = (uname + "." + passwd + "." + "BankingApp");      
   		Person returnUser = new Customer();
   		
	   	logger.debug("uname = " + uname);
        logger.debug("passwd = " + passwd);
        
        returnUser = BasicTools.Deserialize(returnUser, filename);
   		
   		returnUser.viewUserInfo();
    }
    
    public static void createNewUser()
    {
    	String uname;
    	String passwd;
    	
	   	Scanner sc = new Scanner(System.in);
	   	System.out.print("Enter your desired username: ");
		uname = sc.nextLine();
			
	   	System.out.print("Enter your desired password: ");
	   	passwd = sc.nextLine();
	   		
	   	// check if uname already exists
	   	ArrayList<File> arrayList = new ArrayList<File>();
	
	   	final String directoryName = System.getProperty("user.dir");
	   	BasicTools.listf( directoryName, arrayList );
		   	    
	   	for(File elem : arrayList)
	   	{
	   	    String tmp = elem.getName();
	   	    	
	   	    if(tmp.matches("(.*)" + uname + "[.]" + passwd + "(.*)" + "BankingApp"))
	   	    {
	   	 	    System.out.println("\nUsername and or password is already in use.\n");
	   	  	    return;
	   	    }
	   	}    

	   	Person newUser = new Customer(uname, passwd);   		
   		//Person newUser = new Admin(uname, passwd, 'a');   		

	   	logger.debug("uname = " + uname);
        logger.debug("passwd = " + passwd);
        logger.debug("newUser.uname = " + newUser.uname);
        logger.debug("newUser.passwd = " + newUser.passwd);
    	
        String filename = (uname + "." + passwd + "." + "BankingApp");      
        
        BasicTools.serialize(newUser, filename);
                
   		newUser.viewUserInfo();
    }
}






































