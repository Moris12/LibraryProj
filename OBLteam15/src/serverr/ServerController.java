package serverr;


	import java.io.IOException;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.text.SimpleDateFormat;
	import java.util.Date;




	public class ServerController {
		
		
		public static void main(String[] args) {
			
			// TODO Auto-generated method stub

			ServerController sv = new ServerController();
			sv.connectToDB();
		}

	private ServerController serverController;
		
		/** The conn. */
		private Connection conn; 
		
		/** The username.  */
		private String username="root" ;
		
		/** The password. */
		private String password="Aa123456" ;
		
		/** The port. */
		private int port = 5555;
		
		/** The database. */
		private String database ;
		
		/** The dbname. */
		private String dbname="DataBase";
		
		/** The host. */
		private String host="127.0.0.1:3306/?user=root";
		
		/** The echo server. */
		private EchoServer echoServer;
		
		/** The files dir. */
		private String filesDir;
	    
	    /** The openedtime. */
	    private String openedtime;
	    
		public ServerController()
		{
			
			Date date = new Date();
	    	SimpleDateFormat sdf = new SimpleDateFormat("MM_dd_yyyy_h_mm_ss");
	    	openedtime = sdf.format(date);
		}
		
		public void disconnect()
		{
			if(echoServer!=null &&echoServer.isListening())
			{
			try {
				echoServer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
		
		public  boolean connectToDB()
		  {
			String StmtQuery;
			Statement stmt;
			
			database = "jdbc:mysql://"+host+"/";
				try 
				{
		            Class.forName("com.mysql.jdbc.Driver").newInstance();
		        }
				catch (Exception ex) {/* handle the error*/}
		        
		        try 
		        {
		            conn = DriverManager.getConnection(database,username,password);
		            stmt = conn.createStatement();
		            StmtQuery = "CREATE DATABASE assignment2";
		            stmt.execute(StmtQuery);
		            StmtQuery = "CREATE TABLE assignment2.student("
		            		+ "StudentID  VARCHAR(40),"
		            		+ "StudentNAme  VARCHAR(40),"
		            		+ "StatusMembership ENUM('Locked', 'Active', 'Frozen', 'NotRegistered'))"; 
		            stmt.execute(StmtQuery);
		          
		            //Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.3.68/test","root","Root");
		            System.out.println("SQL connection succeed");
		            clearLoggedInUsers();
		            
		     	} 
		        catch (SQLException ex) 
		     	    {/* handle any errors*/
		            System.out.println("SQLException: " + ex.getMessage());
		            System.out.println("SQLState: " + ex.getSQLState());
		            System.out.println("VendorError: " + ex.getErrorCode());
		            return false;
		            }
		    	echoServer = new EchoServer(port);
				echoServer.setConn(conn);
				echoServer.saveUserToDB("123451", "Shifra", "Active");
				echoServer.saveUserToDB("123452", "Naomi-onckolos", "Frozen");
				echoServer.saveUserToDB("123453", "Bochris", "NotRegistered");
		        try 
		        {
		        	echoServer.listen(); //Start listening for connections
					echoServer.setController(this);
					return true;
				} 
				catch (Exception ex) 
				{
					//TODO Change this to log4j
					System.out.println("ERROR - Could not listen for clients!");
					return false;
					}
		        
		   	}
		  	
	    public void clearLoggedInUsers()
	    {
	    	try
	    	{
	    		Statement stmt = conn.createStatement(); 
	    		stmt.executeUpdate("UPDATE users SET status='ACTIVE' where status='LOGGED_IN'");
	    	}
	    	catch (Exception ex)
	    	{
	    		
	    	}
	    }
	    
}
