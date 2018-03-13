package com.revature.project01.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.project01.tools.DAO;

public class Person // implements Serializable
{
    static final Logger logger = Logger.getLogger(terminalBankApp.class);

	protected String uname;
	protected String passwd;
	Integer status;
	ArrayList<String> accounts = new ArrayList<String>();

	public Person() {} // default constructor
	
	public Person(String in1, String in2, Integer stat)
	{
		uname  = in1;
		passwd = in2;
		status = stat;
	}
	
	public static Boolean createNewUser()
	{	
		String usname;
    	String pass;
    	String fname;
    	String lname;
    	
	   	Scanner sc = new Scanner(System.in);
	   	System.out.print("Enter your username: ");
	   	usname = sc.nextLine();
			
	   	System.out.print("Enter your password: ");
	   	pass = sc.nextLine();

	   	System.out.print("Enter your first name: ");
	   	fname = sc.nextLine();
			
	   	System.out.print("Enter your last name: ");
	   	lname = sc.nextLine();
	   	
	   	Person newUser = new Customer(usname, pass, 1);

		String sql = "INSERT INTO USERTBL (UNAME, FIRSTNAME, LASTNAME, PASSWD, UTYPE) VALUES (?, ?, ?, ?, 1)";

		DAO.SQLIn(sql, usname, pass, fname, lname);
		
	   	newUser.personMenu();   		
	   	

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
	   	
	   	Person returnUser = null;
	
		String sql = "SELECT * FROM USERTBL WHERE UNAME = ?"; 		// select * from usrtbl where uname = usname;

		ResultSet rs = DAO.SQLIn(sql, usname);
						
		try 
		{
			while (rs.next())
			{
				String name = rs.getString("UNAME");	// check if usrtbl.passwd = pass - then initialize person returnUser.personMenu();
				String pass2 = rs.getString("PASSWD");
				Integer utype = rs.getInt("UTYPE");
				
				if(pass.equals(pass2))
				{
				   	//logger.debug(usname + " " + pass + " logged in");
				   	
				   	if(utype == 1)
				   	{
				   		returnUser = new Customer(name, pass2, utype);
				   	}
				   	
				   	else if(utype == 2)
				   	{
				   		returnUser = new Employee(name, pass2, utype);
				   	}

				   	else if(utype == 3)
				   	{
				   		returnUser = new Admin(name, pass2, utype);
				   	}
				   	
				   	returnUser.personMenu();
				}
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
   	        
   		return true;
	}
	
	public static Boolean applyForAccount(String owner)/////////////////////////////////////////////////////////////////////////////////////////////////////
	{
		String a = "1";
		String b = "1";
		
		String sql = "INSERT INTO ACCOUNTTBL (ACCID, BALANCE, ACCTYPE, STATUS) VALUES (1, 0.00, ?, ?)";

		ResultSet rs = DAO.SQLIn(sql, a, b);

		sql = "SELECT MAX(ACCID) FROM ACCOUNTTBL";

		rs = DAO.SQLIn(sql);
		
		Integer accnum = 0;
		try 
		{
			while (rs.next())
			{			
				accnum = rs.getInt("MAX(ACCID)");
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		sql = "INSERT INTO USERACCJUNC (USERACCJUNCID, UNAME, ACCNUM) VALUES (1, ?, ?)";

		rs = DAO.SQLIn(sql, owner, Integer.toString(accnum));

	   	System.out.println("------------- Account application success -------------\n");
		return true;
	}
	
	public Boolean applyTojoinAccounts()///////////////////////////////////////////////////////////////////////////////////////////////////////////////
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

		System.out.println("Enter the number of the account you would like to make a withdraw from");
   		Scanner sc = new Scanner(System.in);
   		input = sc.nextLine();
   		
		System.out.println("Enter the ammount of meows to withdraw");
		ammount = sc.nextDouble();
		
//   		Account withdrawAccount = new Account();

   		
		if(ammount > 0)
		{
			String sql = "update ACCOUNTTBL set BALANCE = BALANCE - ? where ACCID = ?";

			DAO.SQLIn(sql, Double.toString(ammount), input);
		}
		else
			System.out.println("You may only withdraw a positive ammount");
  		
//		System.out.println("You do not have permission to withdraw from that account"); ///////////////////////////
		
		return 0;
	}
	
	public int deposit()
	{
    	String input = "z";
    	double ammount;

		System.out.println("Enter the number of the account you would like to make a deposit to");
   		Scanner sc = new Scanner(System.in);
   		input = sc.nextLine();
   		
		System.out.println("Enter the ammount of meows to deposit");
		ammount = sc.nextDouble();
		
		
		if(ammount > 0)
		{
			String sql = "update ACCOUNTTBL set BALANCE = BALANCE + ? where ACCID = ?";

			DAO.SQLIn(sql, Double.toString(ammount), input);
		}
		else
			System.out.println("You may only deposit a positive ammount");


		return 1;
	}
	
	public int transfer()/////////////////////////////////////// stored procedure? //////////////////////////////////////////////////////////////
	{
//    	String input = "z";
//    	String input2 = "z";
//    	double ammount;
//    	String filepath;
//    	String filepath2;
//
//		System.out.println("Enter the number of the account you would like to make a withdraw from");
//   		Scanner sc = new Scanner(System.in);
//   		input = sc.nextLine();
//   
//		System.out.println("Enter the number of the account you would like to make a deposit to");
//   		input2 = sc.nextLine();
   		
//		System.out.println("You do not have permission to withdraw from that account");
		
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
	   	System.out.println("person menu -------------------------------- ");

		viewUserInfo();
	}

}

















