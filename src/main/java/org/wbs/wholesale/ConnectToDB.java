package org.wbs.wholesale;

import java.sql.Connection;
import java.sql.DriverManager;
import org.wbs.wholesale.UI_xpath_locators_Reader.*;

public class ConnectToDB {

	public UI_xpath_locators_Reader uimap;
	
public void ConnectToDB()
	{
	
			String DB_Username=uimap.getData("DB_Username");
			String DB_Password=uimap.getData("DB_Password");
			String Host=uimap.getData("Host");
			String Port_number=uimap.getData("Port_number");
			String Service_Name=uimap.getData("Service_Name");
	
try{
		uimap= new UI_xpath_locators_Reader("C:\\Users\\thukapr\\workspace_selenium\\WBS_5.0\\src\\test\\TestData\\System_Details.properties");
		Class.forName("oracle.jdbc.driver.OracleDriver"); 
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@Host:Port_number:Service_name","DB_Username","DB_Password");
		}
	catch(Exception e)
	{
		System.out.println("Exception caugth " +e);
	}
	}
	
}
