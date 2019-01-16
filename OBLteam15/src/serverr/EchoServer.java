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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

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

	  System.out.println("Message received: " + msg + " from " + client);

	  String PstmtQuery;
	  LinkedHashMap<String,Object> details;
	  Message mesag;
	  System.out.println("declarations");
	  mesag = (Message)msg;
	  System.out.println("after message casting");
	  details = (LinkedHashMap<String,Object>) mesag.getMap();
	  System.out.println("after map");
	  String type =(String) details.get("Type");
	  System.out.println("hhh" + (String)details.get("Type"));

	  
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
								Message repl = new Message(map);
								repl.setToMap("Type", "add");
								client.sendToClient(repl);
							}
							else
							{
								LinkedHashMap<String, Object> map = new LinkedHashMap<String,Object>();
								Message repl = new Message(map);
								repl.setToMap("Type", "log in");
								repl.setToMap("Error", "Cant add member!");
								client.sendToClient(repl);
							}
							
						}
			} break;
						
				
			case "log in":
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
						Message repl = new Message(map);
						repl.setToMap("Type", "log in");
						repl.setToMap("Error", replay);
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
					Message repl = new Message(map);
					repl.setToMap("Type", "log in");
					repl.setToMap("M_id", res.getString("M_id"));
					repl.setToMap("M_email", res.getString("M_email"));
					repl.setToMap("M_registerDate", res.getDate("M_registerDate"));
					repl.setToMap("M_phone", res.getString("M_phone"));
					repl.setToMap("M_status", res.getString("M_status"));
					repl.setToMap("M_runLate", res.getInt("M_runLate"));
					repl.setToMap("M_graduateDate", res.getDate("M_graduateDate"));
					repl.setToMap("M_pname", res.getString("M_pname"));
					repl.setToMap("M_lname", res.getString("M_lname"));
					repl.setToMap("M_password", res.getString("M_password"));
					
					System.out.println("im before send!");
					client.sendToClient(repl);
				}
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
