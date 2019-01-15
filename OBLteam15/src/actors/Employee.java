package actors;

import java.sql.Date;
import java.util.LinkedHashMap;

import actors.Member.Status;

public class Employee {
static String table = "Employee";
private String actor; //*** i couldn't make it static like in member table. because here it might be manager
//and might be Librarian. StartMainPageController will have to update this field according to moris's message.
private String Emp_num;
private String Emp_Pname;
private String Emp_Lname;
private String Emp_email;
private String Emp_organzation;

void setDetailsByHashMap(LinkedHashMap<String, Object> m)
{
	this.actor = (String) m.get("actor_type"); 
	this.Emp_num = (String) m.get("Emp_num");
	this.Emp_Pname = (String) m.get("Emp_Pname");
	this.Emp_Lname = (String) m.get("Emp_Lname");
	this.Emp_email = (String) m.get("Emp_email");
	this.Emp_organzation = (String) m.get("Emp_organzation");
}
LinkedHashMap<String, Object> getDetailsByHashMap()
{
	LinkedHashMap<String,Object> m = new LinkedHashMap<String,Object>();
	m.put("Emp_num", this.Emp_num);
	m.put("Emp_Pname", this.Emp_Pname);
	m.put("Emp_Lname", this.Emp_Lname);
	m.put("Emp_email", this.Emp_email);
	m.put("Emp_organization", this.Emp_organzation);
	m.put("actor type", this.actor);
	m.put("table", table);
	return m;
}
String getEmpNum()
{
	return this.Emp_num;
}
String getActor()
{
	return this.actor;
}


}
