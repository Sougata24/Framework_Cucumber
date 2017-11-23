package com.framework.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;



public class ConnectORproperties {

	
	public String getProperty(String propertyName)  throws IOException
	{
		File srcPropertiesOR = new File("objectRepo.properties");
		FileInputStream fisOR = new FileInputStream(srcPropertiesOR);
		
		Properties propOR = new Properties();
		propOR.load(fisOR);
		
		String propertyXPath = propOR.getProperty(propertyName);
		
		return propertyXPath;
		
	}
	
}
