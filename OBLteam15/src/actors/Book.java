package actors;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;



public class Book {
	
	/*enum Type
	{
		normal, exclusive;
	}*/
	
private String B_name;		//KEY FIELD
private String B_author;	//KEY FIELD
private ArrayList<String> B_themes = new ArrayList<String>();
private String B_TcPath;
private String B_type;
private int B_instanceAmount;
private boolean B_isWaiting;
private ArrayList<CopyOfABook> myCopies = new ArrayList<CopyOfABook>();


void setDetailsByHashMap(LinkedHashMap<String, Object> m)
{
	if(m.containsKey("B_name"))  
	this.B_name = (String) m.get("B_name");
	else System.out.println("ILLEGAL: NO BOOK NAME SET");  //essential field
	
	if(m.containsKey("B_author"))
	this.B_author = (String) m.get("B_author");
	else System.out.println("ILLEGAL: NO AUTHOR NAME SET"); //essential field
	
	if(m.containsKey("B_TcPath"))
	this.B_TcPath = (String) m.get("B_TcPath");
	else System.out.println("ILLEGAL: NO TABLE CONTENT SET"); //essential field
	
	if(m.containsKey("B_type"))
	this.B_type = (String) m.get("B_type"); 		//SHOULD BE ENUM
	
	if(m.containsKey("B_instanceAmount"))
	this.B_instanceAmount = (int) m.get("B_instanceAmount");
	
	if(m.containsKey("B_isWaiting"))
	this.B_isWaiting = (boolean) m.get("B_isWaiting"); //NOTICE: this might be a problem.
}
}
