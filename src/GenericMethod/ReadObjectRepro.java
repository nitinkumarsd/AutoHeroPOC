package GenericMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadObjectRepro {
	
public String ReturnObject(String parm) {
	Properties obj = new Properties();
	try{
		
	FileInputStream objfile = new FileInputStream("C:/Users/nitin/workspaceLuna/AutoHero/src/ObjectRepro" + "/Objects.properties");
	obj.load(objfile);
	}
	catch(IOException e){
		System.out.println(e.getMessage());
	}
	return obj.getProperty(parm);
	}
}
