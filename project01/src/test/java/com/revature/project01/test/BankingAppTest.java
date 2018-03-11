package com.revature.project01.test;

import org.junit.Rule;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.revature.project01.tools.terminalBankAppTools;
import com.revature.project01.util.Account;
import com.revature.project01.util.Admin;
import com.revature.project01.util.Customer;
import com.revature.project01.util.terminalBankApp;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Unit test for simple App.
 */
public class BankingAppTest extends TestCase // extends TestCase
    {
    	@Rule
    	public ExpectedException expectedException = ExpectedException.none();
    	
    	private static terminalBankApp consoleBankApp = new terminalBankApp();
    	private static terminalBankAppTools consoleBankAppTools = new terminalBankAppTools();
    	private static Admin admin = new Admin("aTest", "1234a", 'a');
    	private static Customer customer = new Customer("cTest", "1234b", 'c');
    	private static Account account = new Account();
    	
    	@Test
    	public void createUserTest() 
    	{
    		assertEquals(8 , Admin.createNewUser());
    	}
    	
    	@Test
    	public void loginToAccountTest() 
    	{
    		assertEquals(8 , Admin.loginToAccount());
    	}
    	
//    	@Test
//    	public void viewUserInfoTest() 
//    	{
//    		assertEquals(8 , admin.viewUserInfo());
//    	}
    	
//    	@Test
//    	public void createAccountTest() 
//    	{
//    		assertEquals(8 , admin.createAccount());
//    	}
    	
    	@Test
    	public void deleteAccountTest() 
    	{
    		assertEquals(8 , admin.deleteAccount());
    	}
    	
//    	@Test
//    	public void applyForAccountTest() 
//    	{
//    		assertEquals(8 , customer.applyForAccount());
//    	}
    	
//    	@Test
//    	public void applyTodeleteAccountTest() 
//    	{
//    		assertEquals(8 , customer.applyTodeleteAccount());
//    	}
    	
    	@Test
    	public void applyTojoinAccountsTest() 
    	{
    		assertEquals(8 , customer.applyTojoinAccounts());
    	}
    	
    	@Test
    	public void withdrawTest() 
    	{
    		assertEquals(8 , customer.withdraw());
    	}
    	
    	@Test
    	public void depositTest() 
    	{
    		assertEquals(8 , customer.deposit());
    	}
    	
    	@Test
    	public void transferTest() 
    	{
    		assertEquals(8 , customer.transfer());
    	}
    	
//    	@Test
//    	public void serializeTest() 
//    	{
//    		assertEquals(8 , ConsoleBankAppTools.serialize());
//    	}
    //	
//    	@Test
//    	public void deserializeTest() 
//    	{
//    		assertEquals(8 , ConsoleBankAppTools.deserialize());
//    	}
    //	
//    	@Test
//    	public void getNextAccountNumTest() 
//    	{
//    		assertEquals(8 , ConsoleBankAppTools.getNextAccountNum());
//    	}
    //	
//    	@Test
//    	public void setAccountNumTest() 
//    	{
//    		assertEquals(8 , ConsoleBankAppTools.setAccountNum());
//    	}
    	
    	
    	
    	
    	
    	
    }