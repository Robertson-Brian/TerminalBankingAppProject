package com.revature.project01.tools;

import java.io.*;


import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.project01.util.terminalBankApp;


public class terminalBankAppTools 
{
    static final Logger logger = Logger.getLogger(terminalBankApp.class);
    
   	public static Boolean validate(String fname, String uname, String pass)
   	{
	   	ArrayList<File> arrayList = new ArrayList<File>();
	   	final String directoryName = fname;

	   	arrayList = getFiles(directoryName, arrayList);
	   			   	    
	   	for(File elem : arrayList)
	   	{
	   	    String tmp = elem.getName();
	   	    	
	   	    if(tmp.matches("(.*)" + uname + ".BankingApp"))
	   	    {
	   	  	    return true;
	   	    }
	   	}    
	   	return false;
   	}
	
	public static <T> Boolean serialize(T newSerial, String filename)
	{
	    try
	    {   
	        FileOutputStream file = new FileOutputStream(filename);
	        ObjectOutputStream out = new ObjectOutputStream(file);
	         
	        out.writeObject(newSerial);
	         
	        out.close();
	        file.close();
	    }
	    catch(IOException ex)
	    {
	        logger.debug("IOException is caught");
	        return false;
	    }
		
		return true;
	}	
	
	public static <T> T deserialize(T newDeserial, String filename)
	{
	    try
	    {   
	        FileInputStream file = new FileInputStream(filename);
	        ObjectInputStream in = new ObjectInputStream(file);
	         
	        newDeserial = (T)in.readObject();
	         
	        in.close();
	        file.close();
	    }       
	    catch(IOException ex)
	    {
	        logger.debug("An account with that name does not exist - IOException is caught");
	        logger.debug(filename);
	        return null;
	    }
	    catch(ClassCastException ex)
	    {
	        logger.debug("account creation - ClassCastException is caught");
	        return null;
	    }
	    catch(ClassNotFoundException ex)
	    {
	        logger.debug("ClassNotFoundException is caught");
	        return null;
	    }
      
	    return newDeserial;
	}
	
	public static int getNextAccountNum()
	{
	    Scanner scanner = null;
		try 
		{
			scanner = new Scanner(new File("nextAccountNum"));
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
			
	    int temp;
	
	    temp = scanner.nextInt();
	      
      	return temp;
	}
	
	public static Boolean setAccountNum(int in)
	{
		Writer wr;
		try 
		{
			wr = new FileWriter("nextAccountNum");
	    	wr.write(String.valueOf(in));
	    	wr.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return true;
	}
	
    public static void displayFiles(String directoryName) // display files?
    {
    	File directory = new File(directoryName);

    	File[] fList = directory.listFiles();
      
 		logger.debug("directory = " + directoryName);

    	for (File file : fList)
    	{
    		logger.debug("file = " + file);
    	}
    }

    public static ArrayList<File> getFiles(String directoryName, ArrayList<File> files) // match regex file and return list of results?
    {
    	File directory = new File(directoryName);

    	File[] fList = directory.listFiles();
      
    	if(fList != null)
    	{
	    	for (File file : fList)
	    	{
	    		if (file.isFile())
	    			files.add(file);
	    	}
    	}
    	return files;
    }

}


