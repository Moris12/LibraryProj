package client;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedHashMap;

import actors.*;
import gui.*;
import javafx.stage.Stage;
import models.Message;
import ocsf.client.AbstractClient;
import ocsf.server.AbstractServer;

public class Client extends AbstractClient {
	//controllers.
	PUP_ErrorController PUP_Error;
	PUP_Succes_Controller PUP_Success;
	StudentMainPage1Controller StudentMainPage;
	Librarian_MainPageController LibrarianMainPage;
	ReturnBookPUPController returnBookController;
	Manager_MainPageController ManagerMainPage;
	Start_PageController SPC;
	Stage stg;
	
	public Start_PageController getSPC() {
		return SPC;
	}

	public void setSPC(Start_PageController sPC) {
		SPC = sPC;
	}
	Employee currLibrarian, currManager;
	Member currMember;
	
	private int port;
	
	private String host;
	
	private ObjectOutputStream output;
	
	private ObjectInputStream input;
	
	private String ToDisplay;
	
	final public static int DEFAULT_PORT = 5555;
	
	public Client(String hostt, int port) throws IOException {
		super(hostt,port);
		this.host = hostt;
		if(port == 0) {
			this.port = DEFAULT_PORT;
		}
		this.input = super.input;
		this.setOutput(super.output);
		this.port = port;
	}
	
	@Override
	protected void handleMessageFromServer(Object msg) throws Exception {
		//this function is a "navigation" func. we get the details from the server and than call the function we need.
		Message mesag = (Message) msg;
		LinkedHashMap<String, Object> m = (LinkedHashMap<String, Object>) mesag.getMap();
		switch((String) m.get("Type"))
		{
		case "log in":
		{
			handleLogIn(m);
		}break; //end log in.
		
		case "add":  //reply for 'add' from server. 
		{//if add contains Error key than it failed. if not contains than successful.
			handleAdd(m);
		}
		
		}//end switch
		
		
		
		
		
		
		
		
		//this.ToDisplay = (String)msg;
	}
	
	public String getToDisplay() {
		return this.ToDisplay;
	}
	
	public void setInput(ObjectInputStream inp) {
		this.input = inp;
	}
	public void setOutput(ObjectOutputStream out) {
		this.output = out;
	}
	


	public ObjectOutputStream getOutput() {
		return output;
	}

void handleLogIn(LinkedHashMap<String,Object> m) throws Exception
{

	if( !(m.containsKey("Error"))) //found the user
	{
		if(m.containsKey("M_id")) //it's a member
		{
			StudentMainPage = new StudentMainPage1Controller();
			StudentMainPage.setClient(this);
			currMember = new Member();
			currMember.setDetailsByHashMap(m);
			StudentMainPage.start(null);
		}
		else if(m.containsKey("Emp_num")) //it's an employee
		{
			if("Management" == (String) m.get("Emp_organization")) //it's the manager.
			{
				ManagerMainPage = new Manager_MainPageController();
				ManagerMainPage.setClient(this);
				currManager = new Employee();
				currManager.setDetailsByHashMap(m);
				ManagerMainPage.start(null);					}
			else //regular librarian 
			{
				LibrarianMainPage = new Librarian_MainPageController();
				LibrarianMainPage.setClient(this);
				currLibrarian = new Employee();		
				currLibrarian.setDetailsByHashMap(m);
				System.out.println(SPC.getStage());
				LibrarianMainPage.start(SPC.getStage());
				
			}
		}
	}
	else //user not found OR locked member.
	{
		PUP_Error = new PUP_ErrorController();
		PUP_Error.setErrorStr((String) m.get("Error"));
		PUP_Error.start(null);
	}
	
}
void handleAdd(LinkedHashMap<String,Object> m) throws Exception
{
	if(m.containsKey("Error"))
	{
		PUP_Error = new PUP_ErrorController();
		PUP_Error.setErrorStr((String) m.get("Error"));
		PUP_Error.start(null);
	}
	else //successful add. 
	{
		PUP_Success = new PUP_Succes_Controller();
		PUP_Success.setActionStr("Added successfully.");
		PUP_Success.start(null);
	}
	

}

	  
	  
	  
	  
	  
	
	
}
