package com.revature.project01.tools;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.project01.util.BankingApp;
import com.revature.project01.util.Person;

public class BasicTools 
{
    static final Logger logger = Logger.getLogger(BankingApp.class);

	public BasicTools() {}
	
    public static void listf(String directoryName, ArrayList<File> files)
    {
        File directory = new File(directoryName);

        File[] fList = directory.listFiles();
        
   		//logger.debug("file = " + directoryName);

        for (File file : fList)
        {
            if (file.isFile())
                files.add(file);
        }
    }
    
    public static int initnextAccountNumber()
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
    
    public static void setnextAccountNumber(int in)
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
		
    }
    
    // Serialization 
    public static <T> void serialize(T newSerial, String filename)
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
	    }
    }
    
    // Deserialization
    public static <T> T Deserialize(T newDeserial, String filename)
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
	    }
	    catch(ClassCastException ex)
	    {
	        logger.debug("account creation - ClassCastException is caught");
	    }
	    catch(ClassNotFoundException ex)
	    {
	        logger.debug("ClassNotFoundException is caught");
	    }
        
	    return newDeserial;
    }

}



















