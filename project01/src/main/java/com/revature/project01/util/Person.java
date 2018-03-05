package com.revature.project01.util;

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.log4j.Logger;

public abstract class Person implements Serializable 
{
    static final Logger logger = Logger.getLogger(BankingApp.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = -7439763992072205876L; // why is this static?
	
	protected String uname;
	protected String passwd;
	
	public Person() {} // default constructor
	
	public Person(String in1, String in2)
	{
		uname  = in1;
		passwd = in2;
	}

	public String 	getUname() 					{return uname;}
	public void 	setUname(String uname)  	{this.uname = uname;}

	public String 	getPasswd() 				{return passwd;}
	public void 	setPasswd(String passwd)	{this.passwd = passwd;}

	public void viewUserInfo()
	{  		
   		logger.debug("\nclass - " + this.getClass().getName()); //will return the name (as String) (== "SomeClass")
   		logger.debug("class - " + this.getClass()); //will return the SomeClass' Class object
   		
   		logger.debug("\nusername = " + uname);
   		logger.debug("password = " + passwd);
	}
}
