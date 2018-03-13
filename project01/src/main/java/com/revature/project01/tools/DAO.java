package com.revature.project01.tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;

import org.apache.log4j.Logger;

import com.revature.project01.util.ConnectionFactory;
import com.revature.project01.util.terminalBankApp;

public class DAO 
{
    static final Logger logger = Logger.getLogger(terminalBankApp.class);
    
    public static ResultSet SQLIn(String sql)
    {
	   	Connection conn = null;
	   	ResultSet rs = null;
	   	
		try 
		{			
			conn = ConnectionFactory.getInstance().getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
						
			rs = ps.executeQuery();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return rs;
    }
    
    public static ResultSet SQLIn(String sql, String param1)
    {
	   	Connection conn = null;
	   	Savepoint sp = null;
	   	ResultSet rs = null;
	   	
		try 
		{			
			conn = ConnectionFactory.getInstance().getConnection();
			conn.setAutoCommit(false);
			sp = conn.setSavepoint("myFirstSavepoint");
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, param1);
			
			rs = ps.executeQuery();
			
			conn.commit();
		} 
		catch (SQLException e) 
		{
			try {conn.rollback(sp);} 
			catch (SQLException e1) {e1.printStackTrace();}
			e.printStackTrace();
		}
	   	finally
	   	{
			try {conn.setAutoCommit(true);} 
			catch (SQLException e) {e.printStackTrace();}
	   	}
		return rs;
    }
    
    public static ResultSet SQLIn(String sql, String param1, String param2)
    {
	   	Connection conn = null;
	   	Savepoint sp = null;
	   	ResultSet rs = null;
	   	
		try 
		{
			conn = ConnectionFactory.getInstance().getConnection();
			conn.setAutoCommit(false);
			sp = conn.setSavepoint("myFirstSavepoint");
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, param1);
			ps.setString(2, param2);
			
			rs = ps.executeQuery();
			
			conn.commit();
		} 
		catch (SQLException e) 
		{
			try {conn.rollback(sp);} 
			catch (SQLException e1) {e1.printStackTrace();}
			e.printStackTrace();
		}
	   	finally
	   	{
			try {conn.setAutoCommit(true);} 
			catch (SQLException e) {e.printStackTrace();}
	   	}
		return rs;
    }
    
    public static ResultSet SQLIn(String sql, String param1, String param2, String param3, String param4)
    {
	   	Connection conn = null;
	   	Savepoint sp = null;
	   	ResultSet rs = null;
	   	
		try 
		{
			conn = ConnectionFactory.getInstance().getConnection();
			conn.setAutoCommit(false);
			sp = conn.setSavepoint("myFirstSavepoint");
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, param1);
			ps.setString(2, param2);			
			ps.setString(3, param3);
			ps.setString(4, param4);
			
			rs = ps.executeQuery();
			
			conn.commit();
		} 
		catch (SQLException e) 
		{
			try {conn.rollback(sp);} 
			catch (SQLException e1) {e1.printStackTrace();}
			e.printStackTrace();
		}
	   	finally
	   	{
			try {conn.setAutoCommit(true);} 
			catch (SQLException e) {e.printStackTrace();}
	   	}
		return rs;
    }
    
    
}


