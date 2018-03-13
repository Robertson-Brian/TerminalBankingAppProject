package com.revature.project01.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.project01.tools.DAO;

public class Customer extends Person 
{
	public Customer() {} // default constructor

	public Customer(String in1, String in2, Integer stat)
	{
		super(in1, in2, stat);
	}
	
	public void viewUserInfo()
	{
	   	System.out.println("\n\nUser Info -------------------------------- ");
	   	System.out.print("uname = " + uname);
	   	System.out.println(" - passwd = " + passwd);
  
		String sql = "SELECT USERTBL.UNAME, USERTBL.FIRSTNAME, USERTBL.LASTNAME, ACCOUNTTBL.ACCID, ACCOUNTTBL.BALANCE "
				   + "FROM USERTBL JOIN USERACCJUNC ON USERTBL.UNAME = USERACCJUNC.UNAME JOIN ACCOUNTTBL "
				   + "ON USERACCJUNC.ACCNUM = ACCOUNTTBL.ACCID where USERTBL.UNAME = ?";

		ResultSet rs = DAO.SQLIn(sql, uname);

		System.out.println("\n -- username - First name - Last name - Account ID - Balance\n");
		
		try 
		{
			while (rs.next())
			{			
				System.out.print("    ");

				String uname = rs.getString("UNAME");

				System.out.print(uname);
				for (int x = uname.length(); x < 11; ++x) 
				{
					System.out.print(' ');	
				}
				
				String fname = rs.getString("FIRSTNAME");

				System.out.print(fname);
				for (int x = fname.length(); x < 13; ++x) 
				{
					System.out.print(' ');	
				}
				
				String lname = rs.getString("LASTNAME");
				
				System.out.print(lname);
				for (int x = lname.length(); x < 12; ++x) 
				{
					System.out.print(' ');	
				}
				
				Integer accnum = rs.getInt("ACCID");
				
				System.out.print(accnum);
				for (int x = Integer.toString(accnum).length(); x < 13; ++x) 
				{
					System.out.print(' ');	
				}
				
				Double bal = rs.getDouble("BALANCE");
				
				System.out.print(bal);
				for (int x = Double.toString(bal).length(); x < 11; ++x) 
				{
					System.out.print(' ');	
				}
				
				System.out.println("  ");
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void personMenu()
	{
    	String input = "z";
    	do
    	{
   			this.viewUserInfo();

    		System.out.println("\n -- Customer account menu \n");
    		
    		System.out.println(" -- To apply for new account press a");
    		System.out.println(" -- To apply to join accounts press j");
    		System.out.println(" -- To withdraw meows press w");
    		System.out.println(" -- To deposit meows press d");
    		System.out.println(" -- To transfer meows press t");
    		
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



