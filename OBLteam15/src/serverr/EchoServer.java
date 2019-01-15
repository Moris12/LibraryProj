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
	private Message Message;
	
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


	  String PstmtQuery;
	 // String str = new String();
	//  str = (String)msg;
	  
	  LinkedHashMap details = new LinkedHashMap();
	  Message mesag;
	  mesag = (Message)msg;
	  details = (LinkedHashMap) mesag.getMap();
	 // System.out.println(details.get("hey"));
	//  String str2[] = str.split(" ");
	  String type =(String) details.get("Action");
	 // String ID = str2[1];
	  String Id =(String) details.get("ID");

	  
	  try 
		{
			Statement stmt = con.createStatement();  
			PreparedStatement pstmt = null;
			switch (type)
			{
			case "UpdateStatus":
				String status = (String) details.get("NEW_STATUS");
				PstmtQuery="UPDATE assignment2.student SET StatusMembership=? WHERE StudentID=?";
				pstmt = con.prepareStatement(PstmtQuery);
				pstmt.setString(1,status);
				pstmt.setString(2, Id);
				pstmt.executeUpdate();
				break;
				
			case "Search":
				PstmtQuery="SELECT * FROM assignment2.student WHERE StudentID = ? LIMIT 1";
				pstmt = con.prepareStatement(PstmtQuery);
				pstmt.setString(1, Id);
				ResultSet res =pstmt.executeQuery();
				int rcount = getRowCount(res);
				if (rcount == 0) {
					String replay = "The user does not exist!";
					client.sendToClient(replay);
				}
				else {
					res.next();
					
					String repl = res.getString("StudentID");
					repl += " ";
					repl += res.getString("StudentNAme");
					repl += " ";
					repl += res.getString("StatusMembership");
					repl += " ";
					System.out.println(repl);
					client.sendToClient(repl);
				}
				}
			}	
	  catch(Exception e) {}
	  
	  /*System.out.println("Ani po");
	  LinkedHashMap<String,Object> params = new LinkedHashMap<String,Object>();
	  Message reply = new Message(params);
	 
	  Message mesg = (Message) msg;
	  String type = (String) mesg.getMap().get("type");
	  String query = null;
	  //PreparedStatement pstmt = null;
	  String PstmtQuery = null;
	  String searchExp = null;
	  Date date = new Date();
	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H:mm:ss");
	  String formattedDate = sdf.format(date);
	  
	 try 
		{
			Statement stmt = con.createStatement();  
			PreparedStatement pstmt = null;
			switch (type)
			{
			case "UpdateStatus":
				System.out.println("syso ze hashov");
				String StudentID = (String) mesg.getMap().get("ID");
				String Status = (String) mesg.getMap().get("Status");
				
				PstmtQuery="UPDATE assignment2.student SET status=? WHERE StudentID=?";
				pstmt = con.prepareStatement(PstmtQuery);
				pstmt.setString(1,Status);
				pstmt.setString(2, StudentID);
				pstmt.executeUpdate();
				break;
				
				
			
			default:*/
	  			
				System.out.println("Message received: " + msg + " from " + client);
				//break;
	  			
			
			}
	  
		//}
	   //System.out.println("Message received: " + msg + " from " + client);
			
		//catch(Exception e) {}	
	 // }

  
  
  public void saveUserToDB(String ID, String name, String enumstatus)/*, String operation, boolean a)*/
  {

		try {
			//con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/?user=Mor","Mor","123456");
			//java.sql.Statement stmt = conn.createStatement();
			//stmt.executeUpdate("create table client(UserName VARCHAR(40) , id Varchar(10), Department VARCHAR(10), tel Varchar(10));");
			//stmt = conn.createStatement();
			String query="insert into assignment2.student values(?,?,?)"; 
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setString(2,name);
			//ps.setString(2,operation);
			ps.setString(3,enumstatus);
			ps.setString(1,ID);
			//ps.setBoolean(5, a);
			ps.executeUpdate();
			
	 		
		} catch (SQLException e) {	e.printStackTrace();}
	    
	    
	  
	 }
  	
  public void setConn(Connection conn) {
	  this.con = conn;
  }
  
  public void setController(ServerController sc) {
	  this.controller = sc;
  }
  public void parsingTheData()
  {
  	
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
  
  /**
   * This method is responsible for the creation of 
   * the server instance (there is no UI in this phase).
   *
   * @param args[0] The port number to listen on.  Defaults to 5555 
   *          if no argument is entered.
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
