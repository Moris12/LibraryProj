package ConsoleAndServer;
// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 


import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import ocsf.server.*;

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
  
  /*
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
  }

  
  //Instance methods ************************************************
  
  /**
   * This method handles any messages received from the client.
   *
   * @param msg The message received from the client.
   * @param client The connection from which the message originated.
   */
  public void handleMessageFromClient
    (Object msg, ConnectionToClient client)
  {
	    System.out.println("Message received: " + msg + " from " + client);
	    this.sendToAllClients(msg);
	  }

    
  /**
   * This method overrides the one in the superclass.  Called
   * when the server starts listening for connections.
   */
  protected void serverStarted()
  {
    System.out.println("Server listening for connections on port " + getPort());
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
  public static void main(String[] args) 
  {
	  int port = 0; //Port to listen on
	    Connection conn=null;
	    Statement stmt=null;
    try
    {
      port = Integer.parseInt(args[0]); //Get port from command line
    }//if we didnt put any argument is RUN CONFIGUTARIONS than it will be set to 5555 by default
    catch(Throwable t)
    {
      port = DEFAULT_PORT; //Set port to 5555
    }
	
    EchoServer sv = new EchoServer(port); //call AbstractServer constructor
    
    try 
    {
      sv.listen(); //Start listening for connections
    } 
    catch (Exception ex) 
    {
      System.out.println("ERROR - Could not listen for clients!");//if
    } 
    
    //our paste code
    try 
   	{
           Class.forName("com.mysql.jdbc.Driver").newInstance();
       } catch (Exception ex) {/* handle the error*/}
       
      try 
       {
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/?user=root","root","123456");
           //Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.3.68/test","root","Root");
           System.out.println("SQL connection succeed");
           //createTableCourses(conn);
         
    	} catch (SQLException ex) 
    	    {/* handle any errors*/
           System.out.println("SQLException: " + ex.getMessage());
           System.out.println("SQLState: " + ex.getSQLState());
           System.out.println("VendorError: " + ex.getErrorCode());
           }
       
     
    /*   
   	try {
   		  conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/?user=root","root","123456");
   		stmt = conn.createStatement();
   		//stmt.executeUpdate("create table client(UserName VARCHAR(40) , id Varchar(10), Department VARCHAR(10), tel Varchar(10));");
   		//stmt = conn.createStatement();
   		String query="insert into assignment_2.obl.member values(?,?,?,?,?,?,?)"; 
   		PreparedStatement ps = conn.prepareStatement(query);
   		
   		ps.setString(1,"moris");
   		ps.setString(2,"mor@gmail.com");
   		ps.setString(3,"123");
   	  //ps.setString(4,""); automaticlly filled with time and date
   		ps.setString(5,"305143"); 
   		ps.setString(6,"1");
   		ps.setString(7,"05252");
   		ps.executeUpdate();
   		
    		
   	} catch (SQLException e) {	e.printStackTrace();}*/
       
       
     
    
  }//End of EchoServer class
}
