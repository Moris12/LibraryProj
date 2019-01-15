package actors;

import java.sql.Date;
import java.util.LinkedHashMap;

import actors.Member.Status;

public class CopyOfABook {
private String C_id;
private String C_edition;
private Date C_printDate;
private Date C_purchaseDate;
private String C_shelf; //when in library, than it's the location.
//when lended to a member - than it will be the member's id.

public void setDetailsByHashMap(LinkedHashMap<String, Object> m)
{
	if(m.containsKey("C_id"))  
	this.C_id = (String) m.get("C_id");
	else System.out.println("ILLEGAL: NO C_id SET");  //essential field
	
	if(m.containsKey("C_edition"))
	this.C_edition = (String) m.get("C_edition");
	else System.out.println("ILLEGAL: NO C_edition SET"); //essential field
	
	if(m.containsKey("C_printDate"))
	this.C_printDate = (Date) m.get("C_printDate");
	else System.out.println("ILLEGAL: NO LNAME SET"); //essential field
	
	if(m.containsKey("C_purchaseDate"))
	this.C_purchaseDate = (Date) m.get("C_purchaseDate"); 
	
	if(m.containsKey("C_shelf"))
	this.C_shelf = (String) m.get("C_shelf");
}
public LinkedHashMap<String, Object> getDetailsByHashMap()
{
	LinkedHashMap<String,Object> m = new LinkedHashMap<String,Object>();
	m.put("C_id", C_id);
	m.put("C_edition", C_edition);
	m.put("C_printDate", C_printDate);
	m.put("C_purchaseDate", C_purchaseDate);
	m.put("C_shelf", C_shelf);
	
	return m;
}
public static LinkedHashMap<String, String> isValidInput(LinkedHashMap<String,Object> details) 
//in order to make sure the input is correct and there are no fields missing you need to send
//the details from the GUI to here, if it returns null - the input is all good.
//if it return SOMETHING than u just print it. 
{
	LinkedHashMap<String,String> m = new LinkedHashMap<String, String>(); //this should return empty. hopefully.
	CopyOfABook temp = new CopyOfABook(); //temp CopyOfABook just to work easily
	//HOW TO CHECK IF THE FIELD EXISTS
	temp.setDetailsByHashMap(details); //member is now full of the data that we want to create.
	
	//we don't need to check author's name. because some artists have stage-name which may include
	//dot,digits, and any other character(J.K.ROWLING, louie the 4th etc..). so we only need to check existance.
	//same for book name.
	
	//C_ID CHECK
	if(details.containsKey("C_id")) 
	{
		if(temp.C_id.length()>=1)
		{
			System.out.println("C_id is all good.");
		}
		else
		{
			System.out.println("C_id too short");
			m.put("C_id","C_id is too short.");
		}
	}
	else 
	{
		m.put("C_id", "no C_id input.");
		System.out.println("so C_id input");
	}

	
	
	//C_EDITION CHECK
	if(details.containsKey("C_edition")) 
	{
		if(temp.C_edition.length()>=1)
		{
			System.out.println("C_edition is all good.");
		}
		else
		{
			System.out.println("C_edition too short");
			m.put("C_id","C_edition is too short.");
		}
	}
	else 
	{
		m.put("C_edition", "no C_edition input.");
		System.out.println("so C_edition input");
	}
	
	
	
	//*****CHECK THE DATE IS NOT IN THE FUTURE. FOR BOTH PURCHASE, AND PRINT DATE.*****//
	
	
	
	//C_SHELF CHECK
	if(details.containsKey("C_shelf")) 
	{
		if(temp.C_shelf.length()>=1)
		{
			System.out.println("C_shelf is all good.");
		}
		else
		{
			System.out.println("C_shelf too short");
			m.put("C_shelf","C_shelf is too short.");
		}
	}
	else 
	{
		m.put("C_shelf", "no C_shelf input.");
		System.out.println("so C_shelf input");
	}
	
	
	return m;
}

}//end class
