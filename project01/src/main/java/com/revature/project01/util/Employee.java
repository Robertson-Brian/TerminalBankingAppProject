package com.revature.project01.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.project01.tools.DAO;

public class Employee extends Person
{
	public Employee() {} // default constructor

	public Employee(String in1, String in2, Integer stat)
	{
		super(in1, in2, stat);
	}
	
	public Boolean approveAccount()//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	{
	   	System.out.println("pending accounts: ");
	   	
		return true;
	}

	public void viewUserInfo()
	{
	   	System.out.println("\n\nUser Info --------------------------------^ ");
	   	System.out.print("uname = " + uname);
	   	System.out.println(" - passwd = " + passwd);
        
		String sql = "SELECT USERTBL.UNAME, USERTBL.FIRSTNAME, USERTBL.LASTNAME, ACCOUNTTBL.ACCID, ACCOUNTTBL.BALANCE, ACCOUNTTBL.STATUS, ACCOUNTTBL.ACCTYPE "
				   + "FROM USERTBL JOIN USERACCJUNC ON USERTBL.UNAME = USERACCJUNC.UNAME JOIN ACCOUNTTBL "
				   + "ON USERACCJUNC.ACCNUM = ACCOUNTTBL.ACCID";
		
		ResultSet rs = DAO.SQLIn(sql);
						
		System.out.println("\n -- username - First name - Last name - Account ID - Balance - Status - Type\n");
		
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
				for (int x = Double.toString(bal).length(); x < 10; ++x) 
				{
					System.out.print(' ');	
				}
				
				Integer stat = rs.getInt("STATUS");
				
				if(stat == 1)
					System.out.print("Active   ");	
				
				else if(stat == 2)
					System.out.print("Pending  ");	
				
				else if(stat == 3)
					System.out.print("Canceled ");
				

				Integer type = rs.getInt("ACCTYPE");
				
				if(stat == 1)
					System.out.print("Checking ");	
				
				else if(stat == 2)
					System.out.print("Savings  ");	
				
				else if(stat == 3)
					System.out.print("Other    ");
				
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
		this.viewUserInfo();

    	String input = "z";
    	do
    	{
    		System.out.println("\n -- Employee account menu \n");
    		
    		System.out.println(" -- To approve / deny accounts press a");
    		System.out.println(" -- To deposit into a customers account press j");
//    		System.out.println(" -- To  press w");
//    		System.out.println(" -- To  press d");
//    		System.out.println(" -- To  press t");
    		
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
