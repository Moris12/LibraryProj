package actors;

import java.sql.Date;
import java.util.LinkedHashMap;

public class CopyOfABook {
private String C_id; //key
private String C_edition;
private Date C_printDate;
private Date C_purchaseDate;
private String C_shelf;

public void setDetailsByHashMap(LinkedHashMap<String, Object> m)
{
	if(m.containsKey("C_id"))  
		this.C_id = (String) m.get("C_id");
	else System.out.println("ILLEGAL: NO C_id SET");  //essential KEY field
	
	if(m.containsKey("C_edition"))
		this.C_edition = (String) m.get("C_edition");
	else System.out.println("ILLEGAL: NO C_edition SET"); //essential field
	
	if(m.containsKey("C_printDate"))
		this.C_printDate = (Date) m.get("C_printDate");
	else System.out.println("ILLEGAL: NO C_printDate SET"); //essential field
	
	if(m.containsKey("C_purchaseDate"))
		this.C_purchaseDate = (Date) m.get("C_purchaseDate");
	else System.out.println("ILLEGAL: NO C_purchaseDate SET"); //essential field
	
	if(m.containsKey("C_shelf"))  
		this.C_shelf = (String) m.get("C_shelf");
	else System.out.println("ILLEGAL: NO C_shelf SET"); //essential field
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


//COMPLETE IS VALID INPUT CHECK.
}
