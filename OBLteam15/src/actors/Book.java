package actors;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import actors.Member.Status;

public class Book {
	
	enum Type
	{
		normal, exclusive;
	}
	
private String B_name;		//KEY FIELD
private String B_author;	//KEY FIELD
private ArrayList<String> B_themes = new ArrayList<String>();
private String B_TcPath;
private Type B_type;
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
	this.B_type = (Type) m.get("B_type"); 
	
	if(m.containsKey("M_email"))
	this.email = (String) m.get("M_email");
	
	if(m.containsKey("M_status"))
	this.status = (Status) m.get("M_status"); //NOTICE: this might be a problem.
	
	if(m.containsKey("M_graduateDate"))
	this.graduateDate = (Date) m.get("M_graduateDate");
	else System.out.println("ILLEGAL: NO GRADUATE DATE SET");  //essential field
	
	if(m.containsKey("M_registerDate"))
	this.registerDate = (Date) m.get("M_registerDate"); //will fill automatically when the user is created 
	
	if(m.containsKey("M_runLate"))
	this.runLate = (int) m.get("M_runLate"); 
}
}
