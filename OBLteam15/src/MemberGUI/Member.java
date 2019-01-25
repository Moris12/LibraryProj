package MemberGUI;

import java.sql.Date;
import java.util.LinkedHashMap;

public class Member {
	
	/*enum Status
	{
		Active, Frozen, Locked, NotRegistered;
	}*/
static String table = "member";
private String M_id;
private String M_pname;
private String M_lname;
private String M_phone;
private String M_email;
private String M_status;
private Date M_registerDate;
private Date M_graduateDate;
private int M_runLate;
private String M_password;

public Member() {}
public Member(LinkedHashMap<String, Object> m) { this.setDetailsByHashMap(m);}
//if there is exception thrown here. probably because the enum data type
public void setDetailsByHashMap(LinkedHashMap<String, Object> m)
{
	if(m.containsKey("M_id"))  
		this.M_id = (String) m.get("M_id");
	else System.out.println("ILLEGAL: NO ID SET");  //essential field
	
	if(m.containsKey("M_pname"))
		this.M_pname = (String) m.get("M_pname");
	else System.out.println("ILLEGAL: NO PNAME SET"); //essential field
	
	if(m.containsKey("M_lname"))
		this.M_lname = (String) m.get("M_lname");
	else System.out.println("ILLEGAL: NO LNAME SET"); //essential field
	
	if(m.containsKey("M_phone"))
		this.M_phone = (String) m.get("M_phone"); 
	
	if(m.containsKey("M_email"))
		this.M_email = (String) m.get("M_email");
	
	if(m.containsKey("M_status"))
		this.M_status = (String) m.get("M_status"); //NOTICE: this might be a problem.
	
	if(m.containsKey("M_graduateDate"))
		this.M_graduateDate = (Date) m.get("M_graduateDate");
	else System.out.println("ILLEGAL: NO GRADUATE DATE SET");  //essential field
	
	if(m.containsKey("M_registerDate"))
		this.M_registerDate = (Date) m.get("M_registerDate"); //will fill automatically when the user is created 
	
	if(m.containsKey("M_runLate"))
		this.M_runLate = (int) m.get("M_runLate"); 
	
	if(m.containsKey("M_password"))
		this.M_password = (String) m.get(M_password);
}
public LinkedHashMap<String, Object> getDetailsByHashMap()
{
	LinkedHashMap<String,Object> m = new LinkedHashMap<String,Object>();
	m.put("M_id", M_id);
	m.put("M_pname", M_pname);
	m.put("M_lname", M_lname);
	m.put("M_email", M_email);
	m.put("M_status", M_status);
	m.put("M_phone", M_phone);
	m.put("M_registerDate", M_registerDate);
	m.put("M_runLate",M_runLate);
	m.put("M_graduateDate", M_graduateDate);
	m.put("FROM", table);
	m.put("M_password", M_password);
	return m;
}
public static LinkedHashMap<String, String> isValidInput(LinkedHashMap<String,Object> details) 
//in order to make sure the input is correct and there are no fields missing you need to send
//the details from the GUI to here, if it returns null - the input is all good.
//if it return SOMETHING than u just print it. 
{
	LinkedHashMap<String,String> m = new LinkedHashMap<String, String>(); //this should return empty. hopefully.
	Member temp = new Member(); //temp member just to work easily
	//HOW TO CHECK IF THE FIELD EXISTS
	temp.setDetailsByHashMap(details); //member is now full of the data that we want to create.
	 
	
	
	
	
	//PRIVATE NAME CHECK
	if(details.containsKey("M_pname"))
	{
		if(!(temp.M_pname.length()<=1))
		{
			if(Member.isAlphabets(temp.M_pname) == true) //the Pname is made of chars which are not only letters.
			{
				System.out.println("all good with the Pname");
			}//end if made of signs
			else 
			{
				System.out.println("illegal: private name made of illegal characters");
				m.put("M_pname", "illegal: private name made of illegal characters");
			}
		}//end if length
		else 
		{
		System.out.println("illegal: Private name is too short");
		m.put("M_pname", "illegal: Private name is too short");
		}
	}//end if contains key
	else
	{
		System.out.println("illegal: no Private name");
		m.put("M_pname","illegal: no private name");
	}

	
	
	
	
	
	//LAST NAME CHECK
	if(details.containsKey("M_lname"))
	{
		if(!(temp.M_lname.length()<=1))
		{
			if(Member.isAlphabets(temp.M_lname) == true) //the Pname is made of chars which are not only letters.
			{
				System.out.println("all good with the Lname");
			}//end if made of signs
			else 
			{
				System.out.println("illegal: last name made of illegal characters");
				m.put("M_lname", "illegal: last name made of illegal characters");
			}
		}//end if length
		else 
		{
		System.out.println("illegal: last name is too short");
		m.put("M_lname", "illegal: last name is too short");
		}
	}//end if contains key
	else
	{
		System.out.println("illegal: no last name");
		m.put("M_lname","illegal: no last name");
	}
	
	
	
	
	
	//ID CHECK 
	if(details.containsKey("M_id"))
	{
		if(temp.M_id.length()>=1)
		{
			if(Member.isNumbers(temp.M_lname) == true) //the id is made of chars which are not only numbers.
			{
				System.out.println("all good with the id");
			}//end if made of signs
			else 
			{
				System.out.println("illegal: id made of illegal characters");
				m.put("M_id", "illegal: id made of illegal characters");
			}
		}//end if length
		else 
		{
		System.out.println("illegal: id is too short");
		m.put("M_id", "illegal: id is too short");
		}
	}//end if contains key
	else
	{
		System.out.println("illegal: no id");
		m.put("M_id","illegal: no id");
	}
	
	//*****************HOW TO CHECK DATE?!?!?!****************//
	if(details.containsKey("M_graduateDate"))
	{
		//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		java.sql.Date now = new java.sql.Date(new java.util.Date().getTime());
		if(temp.M_graduateDate.after(now))
		{
			System.out.println("than it's ok");
		}
		else
		{
			System.out.println("the graduate date belongs to the past.");
		}
	}
	else
	{
		System.out.println("no graduate date");
	}
	
	
	
	//WE HAVE THE ESSENTIALS, NOW CHECK THE EXTRA FIELDS.
	
	
	
	if(details.containsKey("M_email"))
	{
		if(!(temp.M_email.length()<=4))  //a@a.b shortest mail possible.
		{
			if(temp.M_email.contains("@"))
			{
				if(temp.M_email.contains("."))
				{
					int shtrudelPos = temp.M_email.indexOf("@");
					int dotPos = temp.M_email.lastIndexOf(".");
					if(shtrudelPos < dotPos)
					{
						System.out.println("all is good with the email");
					}
					else 
					{
						System.out.println("the format of the email adress is illegal.");
						m.put("M_email", "the format of the email is illegal");
					}
				}
				else 
				{
					System.out.println("illegal: no dot in the email adress");
					m.put("M_email", "illegal: no dot in the email adress");
				}
			}
			else 
			{
				System.out.println("illegal: email must contain a @ sign");
				m.put("M_email", "illegal: no @ sign");
			}
		}//end if length
		else 
		{
		System.out.println("illegal: email name is too short");
		m.put("M_email", "illegal: email is too short");
		}
	}//end if contains key
	else
	{
		System.out.println("this else belongs to the existance of the key 'email'");
		System.out.println("since it's not essential we are not goind to report it is the hashMap");
		//we are going just to make sure that IF it exists, it exists right.
	}
	
	
	
	
	
	//PHONE NUMBER CHECK
	if(details.containsKey("M_phone"))
	{
		if(temp.M_phone.length()>=3 && temp.M_phone.length()<=10)
		{
			if(Member.isNumbers(temp.M_phone) == true) //the id is made of chars which are not only numbers.
			{
				System.out.println("all good with the phone number");
			}//end if made of signs
			else 
			{
				System.out.println("illegal: phone number made of illegal characters");
				m.put("M_phone", "illegal: phone number made of illegal characters");
			}
		}//end if length
		else 
		{
		System.out.println("illegal: phone number must be 3 to 10 digits");
		m.put("M_phone", "illegal: phone number must be 3 to 10 digits");
		}
	}//end if contains key
	else
	{
		System.out.println("notice: no phone number.");
		//but, same as email, it's OK to leave it empty.
	}
	
	
	if(m.containsKey("M_password"))
	{
		if(temp.M_password.length() <=2) //3 or more.
		{
			System.out.println("password all good");
		}
		else //too short
		{
			System.out.println("password is too short");
			m.put("M_password", "illegal: password has to be 3 chars or more.");
		}
	}
	else
	{
		System.out.println("illegal: M_password not set");
		m.put("M_password", "no password input");
	}
	
return m;
}
	
public String getM_id()
{
	return (String)this.M_id;
}
public String getM_pname()
{
	return (String)this.M_pname;
}
public String getM_lname()
{
	return (String) this.M_pname;
}
	
	



static public boolean isAlphabets(String data)
{
	char[] chars = data.toCharArray();
	for(char c: chars)
	{
		if(!Character.isLetter(c))
		{
			return false;
		}
	}
	return true;
}

static public boolean isNumbers(String data)
{
	char[] chars = data.toCharArray();
	for(char c: chars)
	{
		if(!Character.isDigit(c))
		{
			return false;
		}
	}
	return true;
}

}