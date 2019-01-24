package serverr;


import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;




	public class ServerController {
		
		private boolean DBexists;
		/** The conn. */
		private Connection conn; 
		
		/** The username.  */
		private String username="root" ;
		
		/** The password. */
		private String password="123456" ;
		
		/** The port. */
		private int port = 5555;
		
		/** The database. */
		private String database ;
		
		/** The dbname. */
		private String dbname="DataBase";
		
		/** The host. */
		private String host="127.0.0.1";
		
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
		//function that disconnect the srver from listening to clients
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
		//Function that creates a connection to the db and in case needed set him up from scratch
		public  boolean connectToDB()
		  {
			String StmtQuery;
			Statement stmt;
		 String copyofbook = "CREATE TABLE `assignment2`.`?` (" //Save the String if the db needs to be set up
			 + " `C_id` INT NOT NULL,"
			 +" `C_edition` VARCHAR(45) NULL,"
			  +"`C_printDate` DATE NULL,"
			  +"`C_purchaseDate` DATE NULL,"
			  +"`C_shelf` VARCHAR(45) NULL,"
			  +"PRIMARY KEY (`C_id`))"; 
		  
		  String book = "CREATE TABLE `assignment2`.`book` ("
				 +" `B_name` VARCHAR(45) NOT NULL,"
				  +"`B_author` VARCHAR(45) NOT NULL,"
				  +"`B_themes` VARCHAR(45) NULL DEFAULT NULL,"
				 + "`B_TcPath` VARCHAR(45) NULL DEFAULT NULL,"
				 +" `B_type` ENUM('exclusive', 'normal') NULL DEFAULT NULL,"
				 +" `B_instancesAmount` INT(11) NULL DEFAULT NULL,"
				 +" `B_isWaiting` TINYINT(4) NULL DEFAULT 0,"
				+"PRIMARY KEY (`B_name`, `B_author`))"; 

		  String member = "CREATE TABLE `assignment2`.`member` ("
				  +"`M_id` VARCHAR(45) NOT NULL,"
				  +"`M_email` VARCHAR(45) NULL,"
				  +"`M_registerDate` DATE NULL DEFAULT NULL,"
				  +"`M_phone` VARCHAR(45) NULL DEFAULT NULL,"
				  +"`M_status` ENUM('Locked', 'Active', 'Frozen', 'NotRegistered') NULL DEFAULT NULL,"
				  +"`M_runLate` INT(11) NULL DEFAULT NULL,"
				  +"`M_graduateDate` DATE NULL,"
				  +"`M_pname` VARCHAR(45) NULL,"
				  +"`M_lname` VARCHAR(45)NULL,"
				  +"`M_password` VARChAR(45) NULL,"
				  +"PRIMARY KEY (`M_id`))";  
		  
		 String libra = "CREATE TABLE `assignment2`.`librarian` ("
				 +" `Emp_num` VARCHAR(45) NOT NULL,"
				  +"`Emp_pname` VARCHAR(45) NULL,"
				  +"`Emp_lname` VARCHAR(45) NULL DEFAULT NULL,"
				  +"`Emp_password` VARCHAR(45) NULL,"
				  +"`Emp_email` VARCHAR(45) NULL DEFAULT NULL,"
				  +"`Emp_organization` VARCHAR(45) NULL DEFAULT NULL,"
				  +"PRIMARY KEY (`Emp_num`))"; 
			
		 String lend =  "CREATE TABLE `assignment2`.`lend` ("
				  +"`L_M_id` VARCHAR(45) NOT NULL,"
				  +"`L_C_id` INT NOT NULL,"
				  +"`L_B_author` VARCHAR(45) NULL,"
				  +"`L_B_name` VARCHAR(45) NULL,"
				  +"`L_lendDate` DATE NULL,"
				  +"`L_initReturnDate` DATE NULL,"
				  +"`L_newReturnDate` DATE NULL,"
				  +"`L_Emp_num_initReturnDate` DATE NULL,"
				  +"`L_emp_num_extReturnDate` DATE NULL,"
				  +"`L_extMakeDate` DATE NULL,"
				  +"PRIMARY KEY (`L_M_id`, `L_C_id`))";  

		 String orderbook = "CREATE TABLE `assignment2`.`orderbook` ("
				  +"`O_supply` TINYINT NULL DEFAULT 0,"
				  +"`B_name` VARCHAR(45) NOT NULL,"
				  +"`B_author` VARCHAR(45) NOT NULL,"
				  +"`M_id` VARCHAR(45) NOT NULL,"
				  +"`O_date` DATE NULL,"
				  +"`O_arriveDate` DATE NULL DEFAULT NULL,"
				  +"PRIMARY KEY (`B_name`, `B_author`, `M_id`))"; 
		  
		 String orderhistort = "CREATE TABLE `assignment2`.`orderhistory` ("
				  +"`OH_B_name` VARCHAR(45) NOT NULL,"
				  +"`OH_B_author` VARCHAR(45) NOT NULL,"
				  +"`OH_M_id` VARCHAR(45) NULL,"
				  +"`OH_O_date` DATE NULL,"
				  +"`OH_O_supply` TINYINT NULL,"
				  +"PRIMARY KEY (`OH_B_name`, `OH_B_author`))"; 
		  
		 String lendhistory = "CREATE TABLE `assignment2`.`lendrethistory` ("
				  +"`LRH_M_id` VARCHAR(45) NOT NULL,"
				  +"`LRH_B_name` VARCHAR(45) NULL,"
				  +"`LRH_B_author` VARCHAR(45) NULL,"
				  +"`LRH_C_id` VARCHAR(45) NULL,"
				  +"`LRH_L_date` DATE NULL,"
				  +"`LRH_actualReturnDate` DATE NULL,"
				  +"`LRH_L_newReturnDate` DATE NULL,"
				  +"PRIMARY KEY (`LRH_M_id`))";  
			
			database = "jdbc:mysql://"+host+"/";
				try 
				{
					
		            Class.forName("com.mysql.jdbc.Driver").newInstance();
		        }
				catch (Exception ex) {/* handle the error*/}
		        
		        try 
		        {
		        	//getting connection to the db
		            conn = DriverManager.getConnection(database,username,password);
		            System.out.println("SQL connection succeed");
		            
		            
		     	} 
		        catch (SQLException ex) 
		     	    {/* handle any errors*/
		            System.out.println("SQLException: " + ex.getMessage());
		            System.out.println("SQLState: " + ex.getSQLState());
		            System.out.println("VendorError: " + ex.getErrorCode());
		            return false;
		            }
		        if(DBexists == false)
		        {
		        	/*if the user indicate in the screen that the db does not exist
		        	 *and than set him up
		        	 */
		        	try {
		        		StmtQuery = "CREATE DATABASE assignment2";
		        		stmt = conn.createStatement();
		        		stmt.execute(StmtQuery);
						stmt.execute(book);
			        	stmt.execute(lendhistory);
			        	stmt.execute(orderhistort);
			        	stmt.execute(orderbook);
			        	stmt.execute(lend);
			        	stmt.execute(libra);
			        	stmt.execute(member);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        	
		        }
		        
		    	echoServer = new EchoServer(port);
				echoServer.setConn(conn);
				//echoServer.saveUserToDB("123451", "Shifra", "Active");
				//echoServer.saveUserToDB("123452", "Naomi-onckolos", "Frozen");
				//echoServer.saveUserToDB("123453", "Bochris", "NotRegistered");
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
	    
	    public void setHost(String host)
	    {
	    	this.host = host;
	    }
	    public void setPass(String pass)
	    {
	    	this.password = pass;
	    }
	    public void setUser(String user)
	    {
	    	this.username = user;
	    }
	    public String getHost()
	    {
	    	return this.host;
	    }
	    public String getPass()
	    {
	    	return this.password;
	    }
	    public String getUsername()
	    {
	    	return this.username;
	    }
	    public String getPort()
	    {
	    	return String.valueOf(this.port);
	    }
	    public String getIP() throws UnknownHostException
	    {
	    	try {
	            InetAddress candidateAddress = null;
	            // Iterate all NICs (network interface cards)...
	            for (Enumeration ifaces = NetworkInterface.getNetworkInterfaces(); ifaces.hasMoreElements();) {
	                NetworkInterface iface = (NetworkInterface) ifaces.nextElement();
	                // Iterate all IP addresses assigned to each card...
	                for (Enumeration inetAddrs = iface.getInetAddresses(); inetAddrs.hasMoreElements();) {
	                    InetAddress inetAddr = (InetAddress) inetAddrs.nextElement();
	                    if (!inetAddr.isLoopbackAddress()) {

	                        if (inetAddr.isSiteLocalAddress()) {
	                            // Found non-loopback site-local address. Return it immediately...
	                            return inetAddr.getHostAddress();
	                        }
	                        else if (candidateAddress == null) {
	                            // Found non-loopback address, but not necessarily site-local.
	                            // Store it as a candidate to be returned if site-local address is not subsequently found...
	                            candidateAddress = inetAddr;
	                            // Note that we don't repeatedly assign non-loopback non-site-local addresses as candidates,
	                            // only the first. For subsequent iterations, candidate will be non-null.
	                        }
	                    }
	                }
	            }
	            if (candidateAddress != null) {
	                // We did not find a site-local address, but we found some other non-loopback address.
	                // Server might have a non-site-local address assigned to its NIC (or it might be running
	                // IPv6 which deprecates the "site-local" concept).
	                // Return this non-loopback candidate address...
	                return candidateAddress.getHostAddress();
	            }
	            // At this point, we did not find a non-loopback address.
	            // Fall back to returning whatever InetAddress.getLocalHost() returns...
	            InetAddress jdkSuppliedAddress = InetAddress.getLocalHost();
	            if (jdkSuppliedAddress == null) {
	                throw new UnknownHostException("The JDK InetAddress.getLocalHost() method unexpectedly returned null.");
	            }
	            return jdkSuppliedAddress.getHostAddress();
	        }
	        catch (Exception e) {
	            UnknownHostException unknownHostException = new UnknownHostException("Failed to determine LAN address: " + e);
	            unknownHostException.initCause(e);
	            throw unknownHostException;
	        }
	    }
	    public void setDBexists(boolean bol)
	    {
	    	this.DBexists = bol;
	    }

}
