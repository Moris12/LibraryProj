package serverr;
// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.io.IOException;
import java.net.ServerSocket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import com.mysql.fabric.xmlrpc.base.Data;

import models.*;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;
import ocsf.server.ConnectionToClient.*;



/**
 * This class overrides some of the methods in the abstract 
 * superclass in order to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @version July 2000
 */
public class EchoServer extends AbstractServer 
{
  //Class variables *************************************************
 
	private Connection con;
	
	private ServerController controller;
	public int LoginCounter=0;
	
	private ServerSocket serverSocket;
	private ConnectionToClient clientConnection;
	private Message Msg;
	
	private Map<String,Object> params;
	private String userDir;
	//Class variables *************************************************
	/**
	 * The default port to listen on.
	 */
	private final  static int DOWNLOAD_PORT = 6666;
	private static final String Controller = null;

  /**
   * The default port to listen on.
   */
  final public static int DEFAULT_PORT = 5555;
  
  //Constructors ****************************************************
  
  /**
   * Constructs an instance of the echo server.
   *
   * @param port The port number to connect on.
   */
  public EchoServer(int port) 
  {
    super(port);
    try 
	{
		serverSocket = new ServerSocket(DOWNLOAD_PORT);
	} catch (IOException e) {e.printStackTrace();} 
  }

  
  //Instance methods ************************************************
  
  /**
   * This method handles any messages received from the client.
   *
   * @param msg The message received from the client.
   * @param client The connection from which the message originated.
   */
  public void handleMessageFromClient(Object msg, ConnectionToClient client)
  {

	  //System.out.println("Message received: " + msg + " from " + client);

	  String PstmtQuery;
	  LinkedHashMap<String,Object> details;
	  Message mesag;
	  mesag = (Message)msg;
	  details = (LinkedHashMap<String,Object>) mesag.getMap();
	  String type =(String) details.get("Type");
	  System.out.println("hhh" + " " + (String)details.get("Type"));

	  
	  try 
		{
			Statement stmt = con.createStatement();  
			PreparedStatement pstmt = null;
			switch (type)
			{
			case "add":
			{
						String table = (String) details.get("Table");
						if(table == "mem")
						{
							String ID = (String)details.get("M_id");
							String email = (String)details.get("M_email");
							java.sql.Date register = (java.sql.Date)details.get("M_registerDate");
							String phone = (String)details.get("M_phone");
							String status = (String)details.get("M_status");
							int runlate = (int)details.get("M_runLate");
							java.sql.Date graduate = (java.sql.Date)details.get("M_graduateDate");
							String pname = (String)details.get("M_pname");
							String lname = (String)details.get("M_lname");
							String pass = (String)details.get("M_password");
							if(saveUserToDB(ID, pname, pass, register, phone, status, runlate, graduate, lname, email))
							{
								
								LinkedHashMap<String, Object> map = new LinkedHashMap<String,Object>();
								map.put("Type", "add");
								Message repl = new Message(map);
								System.out.println("im before sending user saved");
								client.sendToClient(repl);
							}
							else
							{
								LinkedHashMap<String, Object> map = new LinkedHashMap<String,Object>();
								
								map.put("Type", "add");
								map.put("Error", "Cant add member!");
								Message repl = new Message(map);
								client.sendToClient(repl);
							}
							
						}
						else if(table == "emp")
						{
							String num = (String)details.get("Emp_num");
							String email = (String)details.get("Emp_email");
							String pname = (String)details.get("Emp_pname");
							String lname = (String)details.get("Emp_lname");
							String pass = (String)details.get("Emp_password");
							String organization = (String)details.get("Emp_organization");
							if(SaveLibToDB(num, pname,lname, email,organization,pass))
							{
								LinkedHashMap<String, Object> map = new LinkedHashMap<String,Object>();
								map.put("Type", "add");
								Message repl = new Message(map);
								client.sendToClient(repl);
							}
							else
							{
								LinkedHashMap<String, Object> map = new LinkedHashMap<String,Object>();
								
								map.put("Type", "add");
								map.put("Error", "Cant Employee!");
								Message repl = new Message(map);
								client.sendToClient(repl);
							}
							
						}
						else if(table == "book")
						{
							String name = (String)details.get("B_name");
							String author = (String)details.get("B_author");
							String themes = (String)details.get("B_themes");
							String tcPath = (String)details.get("B_TcPath");
							String btype = (String)details.get("B_type");
							int instancesAmount = (int)details.get("B_instancesAmount");
							boolean iswait = (boolean)details.get("B_isWaiting");
							if(SaveBookToDB(name, author,themes, tcPath,btype,instancesAmount,iswait))
							{
								LinkedHashMap<String, Object> map = new LinkedHashMap<String,Object>();
								map.put("Type", "add");
								Message repl = new Message(map);
								client.sendToClient(repl);
							}
							else
							{
								LinkedHashMap<String, Object> map = new LinkedHashMap<String,Object>();
								
								map.put("Type", "add");
								map.put("Error", "Cant add book!");
								Message repl = new Message(map);
								client.sendToClient(repl);
							}
							
						
						}
						else if(table == "copy of book" )
						{
							 String copyofbook = "CREATE TABLE `assignment2`.`?` (" //Save the String if the db needs to be set up
									 + " `C_id` INT NOT NULL,"
									 +" `C_edition` VARCHAR(45) NULL,"
									  +"`C_printDate` DATE NULL,"
									  +"`C_purchaseDate` DATE NULL,"
									  +"`C_shelf` VARCHAR(45) NULL,"
									  +"PRIMARY KEY (`C_id`))"; 
							 String copyname = (String)details.get("B_name")+(String)details.get("B_author");
							 String[] UID = UUID.randomUUID().toString().split("-", 2);
							 String uniqueID = UID[0];
							 int cid = Integer.parseInt(uniqueID);
							 String edition = (String)details.get("C_edition");
							 java.sql.Date print = (java.sql.Date)details.get("C_printDate");
							 Date date = new Date();  //java util date
							 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
							 String currentTime = sdf.format(date);
							 try 
							 {
								date=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(currentTime);
							} 
							 catch (ParseException e) 
							{
								e.printStackTrace();
							}
							java.sql.Date purchase = new java.sql.Date(date.getTime());
							String shelf = (String)details.get("C_shelf");
							if(SavecopyOfBook(copyname, cid, edition, print, purchase, shelf))
							{
								LinkedHashMap<String, Object> map = new LinkedHashMap<String,Object>();	
								map.put("Type", "add");
								Message repl = new Message(map);
								client.sendToClient(repl);
							}
							else
							{
								LinkedHashMap<String, Object> map = new LinkedHashMap<String,Object>();	
								map.put("Type", "add");
								map.put("Error", "Cant add copy of book!");
								Message repl = new Message(map);
								client.sendToClient(repl);
							}
							 
						}
							
						
			} break;
						
				
			case "log in":
			{
				PstmtQuery="SELECT * FROM assignment2.member WHERE M_id=? AND M_password=? LIMIT 1";
				pstmt = con.prepareStatement(PstmtQuery);
				pstmt.setString(1, (String)details.get("Username"));
				pstmt.setString(2, (String)details.get("Password"));
				ResultSet res =pstmt.executeQuery();
				int rcount = getRowCount(res);
				if (rcount == 0) {
					System.out.println("im in lib");
					PstmtQuery="SELECT * FROM assignment2.librarian WHERE Emp_num=? AND Emp_password=? LIMIT 1";
					pstmt = con.prepareStatement(PstmtQuery);
					pstmt.setString(1,(String)details.get("Username") );
					pstmt.setString(2,(String)details.get("Password") );
					ResultSet res1 =pstmt.executeQuery();
					int rscount = getRowCount(res1);
					System.out.println(rscount);
					if(rscount == 0)
					{
						String replay = "The user does not exist!";
						LinkedHashMap<String, Object> map = new LinkedHashMap<String,Object>();
						
						map.put("Type", "log in");
						map.put("Error", replay);
						Message repl = new Message(map);
						System.out.println("im sending no user");
						client.sendToClient(repl);
					}
					else
					{
						res1.next();
						LinkedHashMap<String, Object> map = new LinkedHashMap<String,Object>();
						System.out.println("befor set to map");
						map.put("Type", "log in");
						String toshow = res1.getString("Emp_num");
						System.out.println(toshow);
						map.put("Emp_num", res1.getString("Emp_num"));
						map.put("Emp_pname", res1.getString("Emp_pname"));
						map.put("Emp_lname", res1.getString("Emp_lname"));
						map.put("Emp_password", res1.getString("Emp_password"));
						map.put("Emp_email", res1.getString("Emp_email"));
						map.put("Emp_organization", res1.getString("Emp_organization"));
						
						Message repl = new Message(map);
						
						System.out.println("im before send lib!");
						client.sendToClient(repl);
					}
				}
				else {
					res.next();
					LinkedHashMap<String, Object> map = new LinkedHashMap<String,Object>();
					
					map.put("Type", "log in");
					map.put("M_id", res.getString("M_id"));
					map.put("M_email", res.getString("M_email"));
					map.put("M_registerDate", res.getDate("M_registerDate"));
					map.put("M_phone", res.getString("M_phone"));
					map.put("M_status", res.getString("M_status"));
					map.put("M_runLate", res.getInt("M_runLate"));
					map.put("M_graduateDate", res.getDate("M_graduateDate"));
					map.put("M_pname", res.getString("M_pname"));
					map.put("M_lname", res.getString("M_lname"));
					map.put("M_password", res.getString("M_password"));
					Message repl = new Message(map);
					System.out.println("im before send!");
					client.sendToClient(repl);
				}
				
				}break;
				
			case"search book":
			{
				System.out.println((String)details.get("By"));
				switch((String)details.get("By"))
				{
				case "book name":
				{
					String bname = (String)details.get("key");
					System.out.println(bname);
					PstmtQuery="SELECT * FROM assignment2.book WHERE B_name=? LIMIT 1";
					pstmt = con.prepareStatement(PstmtQuery);
					pstmt.setString(1, bname);
					ResultSet res =pstmt.executeQuery();
					int rcount = getRowCount(res);
					System.out.println(rcount);
					if(rcount == 0)
					{
						String replay = "The book does not exist!";
						LinkedHashMap<String, Object> map = new LinkedHashMap<String,Object>();
						
						map.put("Type", "search book");
						map.put("Error", replay);
						Message repl = new Message(map);
						System.out.println("im sending no user");
						client.sendToClient(repl);
					}
					else {
						res.next();
						LinkedHashMap<String, Object> map = new LinkedHashMap<String,Object>();
						
						map.put("Type", "search book");
						map.put("Sender", (String)details.get("Sender"));
						map.put("B_name", res.getString("B_name"));
						map.put("B_author", res.getString("B_author"));
						map.put("B_themes", res.getString("B_themes"));
						map.put("B_TcPath", res.getString("B_TcPath"));
						map.put("B_type", res.getString("B_type"));
						map.put("B_instancesAmount", res.getInt("B_instancesAmount"));
						map.put("B_isWaiting", res.getBoolean("B_isWaiting"));
						Message repl = new Message(map);
						client.sendToClient(repl);
					}
					
				}break;
				case "author name":
				{
					String aname = (String)details.get("key");
					PstmtQuery="SELECT * FROM assignment2.book WHERE B_author=? LIMIT 1";
					pstmt = con.prepareStatement(PstmtQuery);
					pstmt.setString(1, aname);
					ResultSet res =pstmt.executeQuery();
					int rcount = getRowCount(res);
					if(rcount == 0)
					{
						String replay = "The book does not exist!";
						LinkedHashMap<String, Object> map = new LinkedHashMap<String,Object>();
						
						map.put("Type", "search book");
						map.put("Error", replay);
						Message repl = new Message(map);
						System.out.println("im sending no user");
						client.sendToClient(repl);
					}
					else {
						res.next();
						LinkedHashMap<String, Object> map = new LinkedHashMap<String,Object>();
						
						map.put("Type", "search book");
						map.put("B_name", res.getString("B_name"));
						map.put("B_author", res.getString("B_author"));
						map.put("B_themes", res.getDate("B_themes"));
						map.put("B_TcPath", res.getString("B_TcPath"));
						map.put("B_type", res.getString("B_type"));
						map.put("B_instancesAmount", res.getInt("B_instancesAmount"));
						map.put("B_isWaiting", res.getDate("B_isWaiting"));
						Message repl = new Message(map);
						System.out.println("im before send!");
						client.sendToClient(repl);
					}
					
				}break;
				
				case "category":
				{
					String aname = (String)details.get("key");
					PstmtQuery="SELECT * FROM assignment2.book WHERE B_themes=? LIMIT 1";
					pstmt = con.prepareStatement(PstmtQuery);
					pstmt.setString(1, aname);
					ResultSet res =pstmt.executeQuery();
					int rcount = getRowCount(res);
					if(rcount == 0)
					{
						String replay = "The book does not exist!";
						LinkedHashMap<String, Object> map = new LinkedHashMap<String,Object>();
						
						map.put("Type", "search book");
						map.put("Error", replay);
						Message repl = new Message(map);
						System.out.println("im sending no user");
						client.sendToClient(repl);
					}
					else {
						res.next();
						LinkedHashMap<String, Object> map = new LinkedHashMap<String,Object>();
						
						map.put("Type", "search book");
						map.put("B_name", res.getString("B_name"));
						map.put("B_author", res.getString("B_author"));
						map.put("B_themes", res.getDate("B_themes"));
						map.put("B_TcPath", res.getString("B_TcPath"));
						map.put("B_type", res.getString("B_type"));
						map.put("B_instancesAmount", res.getInt("B_instancesAmount"));
						map.put("B_isWaiting", res.getDate("B_isWaiting"));
						Message repl = new Message(map);
						System.out.println("im before send!");
						client.sendToClient(repl);
					}
					
				}break;
				case "free text":
				{
					String aname = (String)details.get("key");
					PstmtQuery="SELECT * FROM assignment2.book WHERE B_themes=? LIMIT 1";
					pstmt = con.prepareStatement(PstmtQuery);
					pstmt.setString(1, aname);
					ResultSet res =pstmt.executeQuery();
					int rcount = getRowCount(res);
					if(rcount == 0)
					{
						String replay = "The book does not exist!";
						LinkedHashMap<String, Object> map = new LinkedHashMap<String,Object>();
						
						map.put("Type", "search book");
						map.put("Error", replay);
						Message repl = new Message(map);
						System.out.println("im sending no user");
						client.sendToClient(repl);
					}
					else {
						res.next();
						LinkedHashMap<String, Object> map = new LinkedHashMap<String,Object>();
						
						map.put("Type", "search book");
						map.put("B_name", res.getString("B_name"));
						map.put("B_author", res.getString("B_author"));
						map.put("B_themes", res.getDate("B_themes"));
						map.put("B_TcPath", res.getString("B_TcPath"));
						map.put("B_type", res.getString("B_type"));
						map.put("B_instancesAmount", res.getInt("B_instancesAmount"));
						map.put("B_isWaiting", res.getDate("B_isWaiting"));
						Message repl = new Message(map);
						System.out.println("im before send!");
						client.sendToClient(repl);
					}
					
				}break;
				}
				
				
			}break;
			
			
			}
		}
		
	  catch(Exception e) {}
			  

	  			
				System.out.println("Message received: " + msg + " from " + client);
				//break;
	  			
			
			}
	  
  
  /*function that saves user member to the db by getting all the attributes
   * 
   */
  public boolean saveUserToDB(String ID, String pname, String pass, java.sql.Date register, String phone, String status, int runlate, java.sql.Date graduate, String lname,String email)/*, String operation, boolean a)*/
  {

		try {
			//con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/?user=Mor","Mor","123456");
			//java.sql.Statement stmt = conn.createStatement();
			//stmt.executeUpdate("create table client(UserName VARCHAR(40) , id Varchar(10), Department VARCHAR(10), tel Varchar(10));");
			//stmt = conn.createStatement();
			String query="insert into assignment2.member values(?,?,?,?,?,?,?,?,?,?)"; 
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1,ID);
			ps.setString(2,email);
			ps.setDate(3, register);
			ps.setString(4,phone);
			ps.setString(5, status);
			ps.setInt(6, runlate);
			ps.setDate(7, graduate);
			ps.setString(8, pname);
			ps.setString(9, lname);
			ps.setString(10, pass);
			ps.executeUpdate();
			return true;
			
	 		
		} catch (SQLException e) {	
			e.printStackTrace();
			return false;
		}
	    
	    
	  
	 }
  
  public boolean SaveLibToDB(String num, String pname, String lname, String email, String organization, String pass)
  {
	  try {
		  String query="insert into assignment2.librarian values(?,?,?,?,?,?)"; 
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1,num);
			ps.setString(2,pname);
			ps.setString(3, lname);
			ps.setString(4,email);
			ps.setString(5, organization);
			ps.setString(6, pass);
			ps.executeUpdate();
			return true;
	  }
	  catch (SQLException e) {	
			e.printStackTrace();
			return false;
		}
  }
  
  public boolean SaveBookToDB(String name,String author,String themes,String tcPath,String btype, int instancesAmount, boolean iswaiting)
  {
	  try {
		  String query="insert into assignment2.book values(?,?,?,?,?,?,?)"; 
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1,name);
			ps.setString(2,author);
			ps.setString(3, themes);
			ps.setString(4,tcPath);
			ps.setString(5, btype);
			ps.setInt(6, instancesAmount );
			ps.setBoolean(7, iswaiting);
			ps.executeUpdate();
			return true;
	  }
	  catch (SQLException e) {	
			e.printStackTrace();
			return false;
		}
  }
  
  public boolean SavecopyOfBook(String copyname,int cid, String edition, java.sql.Date print, java.sql.Date purchase, String shelf)
  {
	  	PreparedStatement pstmt = null;
		 String copyofbook = "CREATE TABLE `assignment2`.`?` (" //Save the String if the db needs to be set up
				 + " `C_id` INT NOT NULL,"
				 +" `C_edition` VARCHAR(45) NULL,"
				  +"`C_printDate` DATE NULL,"
				  +"`C_purchaseDate` DATE NULL,"
				  +"`C_shelf` VARCHAR(45) NULL,"
				  +"PRIMARY KEY (`C_id`))";
		 try {
			 pstmt = con.prepareStatement(copyofbook);
			 pstmt.setString(1, copyname);
			 pstmt.execute();
			 String query="insert into assignment2.? values(?,?,?,?,?)"; 
			 pstmt = con.prepareStatement(query);
			 pstmt.setString(1,copyname);
			 pstmt.setInt(2,cid);
			 pstmt.setString(3, edition);
			 pstmt.setDate(4,print);
			 pstmt.setDate(5, purchase);
			 pstmt.setString(6, shelf );
			 pstmt.executeUpdate();
			 return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		 
		
  }
  	
  public void setConn(Connection conn) {
	  this.con = conn;
  }
  
  public void setController(ServerController sc) {
	  this.controller = sc;
  }


  
  
  
  /**
   * This method overrides the one in the superclass.  Called
   * when the server starts listening for connections.
   */
  protected void serverStarted()
  {
    System.out.println
      ("Server listening for connections on port " + getPort());
  }
  
  /**
   * This method overrides the one in the superclass.  Called
   * when the server stops listening for connections.
   */
  protected void serverStopped()
  {
    System.out.println
      ("Server has stopped listening for connections.");
  }
  
  //Class methods ***************************************************
  


  /*Function that recieve a result set type and returns the number of rows 
   * that exists in the result set
   */
	private int getRowCount(ResultSet resultSet) //helper method to see if a select query has any matching rows
	{
		if (resultSet == null) {
			return 0;
		}
		try {
			resultSet.last();
			return resultSet.getRow();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			try {
				resultSet.beforeFirst();
			} catch (SQLException exp) {
				exp.printStackTrace();
			}
		}
		return 0;
	}
  
}



//End of EchoServer class

