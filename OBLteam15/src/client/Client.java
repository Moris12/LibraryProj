package client;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedHashMap;

import LibrarianGUI.Employee;
import LibrarianGUI.Librarian_MainPageController;
import LibrarianGUI.ReturnBookPUPController;
import ManagerGUI.Manager_MainPageController;
import MemberGUI.Member;
import MemberGUI.StudentMainPage1Controller;
import actors.*;
import gui.*;
import javafx.application.Platform;
import javafx.stage.Stage;
import models.Message;
import ocsf.client.AbstractClient;
import ocsf.server.AbstractServer;

public class Client extends AbstractClient {
	//controllers.
	PUP_ErrorController PUP_Error;
	PUP_Succes_Controller PUP_Success;
	StudentMainPage1Controller StudentMainPage = null;
	Librarian_MainPageController LibrarianMainPage;
	ReturnBookPUPController returnBookController;
	Manager_MainPageController ManagerMainPage;
	Start_PageController SPC;
	PUP_successController PUP_success;
	//gui
	
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
		LinkedHashMap<String, Object> m = ((LinkedHashMap<String, Object>) mesag.getMap());
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
		case "search book":
		{
			switch((String) m.get("Sender"))
			{
			case "Start_PageController":
			{
				
			}break;
			
			}//end switch search book
		}//end search book
		case "remove book":
		{
			handleRemoveBook(m);
		}break;
		case "view membership card":
		{
			handleViewMembershipCard(m);
		}break;
		case "view my books":
		{
			handleViewMyBooks(m);
		}break;
		case "log out":
		{
			handleLogOut(m);
		}break;
		}//end switch "Type"
}//end handleMessageFromServer
		
		
		
		
	
	

	private void handleLogOut(LinkedHashMap<String, Object> m) throws Exception 
	{
		switch((String) m.get("Sender"))
		{
		case "student":
		{
			StudentMainPage = null;
		}break;
		case "librarian":
		{
			LibrarianMainPage = null;
		}break;
		case "manager":
		{
			ManagerMainPage = null;
		}break;
		}
	SPC = new Start_PageController();
	
	SPC.start(null);
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
	System.out.println("inside handle login");
	if( !(m.containsKey("Error"))) //found the user
	{
		if(m.containsKey("M_id")) //it's a member
		{
			Platform.runLater(()->{
				try {
					SPC.releaseKeys();
					SPC.callStudentMainPage(m);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				});
			
		}
		else if(m.containsKey("Emp_num")) //it's an employee
		{
			if("Management" == (String) m.get("Emp_organization")) //it's the manager.
			{
				Platform.runLater(()->{
					try {
						SPC.callManagerMainPage(m);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					});
									}
			else //regular librarian 
			{
				Platform.runLater(()->{
				try {
					System.out.println("before call libra MP");
					SPC.callLibrarianMainPage(m);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				});
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

/**handleAdd handles all kind of addings, books, copies, members etc.*/
void handleAdd(LinkedHashMap<String,Object> m) throws Exception
{
	if(m.containsKey("Error"))
	{
		PUP_Error = new PUP_ErrorController();
		PUP_Error.setErrorStr((String) m.get("Error"));
		Platform.runLater(()->{
			try {
				PUP_Error.start(null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			});
	}
	else //successful add. 
	{
		PUP_Success = new PUP_Succes_Controller();
		PUP_Success.setActionStr("Added successfully.");
		Platform.runLater(()->{
			try {
				PUP_success.start(null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			});
	}
}	

void handleRemoveBook(LinkedHashMap<String,Object> m) throws Exception
{
	if(m.containsKey("Error")) 
	{
		PUP_Error = new PUP_ErrorController();
		PUP_Error.setErrorStr((String) m.get("Error"));
		Platform.runLater(()->{
			try {
				PUP_Error.start(null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			});
	}
	else //no error
	{
		PUP_success = new PUP_successController();
		PUP_success.SetSuccessMessage("the book "+(String)m.get("B_name") +
				" by author: " + (String) m.get("B_author") + " successfully removed");
		Platform.runLater(()->{
			try {
				PUP_success.start(null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			});
	}

}

void handleViewMembershipCard(LinkedHashMap<String,Object> m) throws Exception
{
	if(m.containsKey("Error"))
	{
		PUP_Error = new PUP_ErrorController();
		PUP_Error.setErrorStr((String) m.get("Error"));
		Platform.runLater(()->{
			try {
				PUP_Error.start(null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			});
	}
	else 
	{
		if((String) m.get("Sender") == "librarian")
		{
			LibrarianMainPage.callViewMembershipCard(m);
		}
		else if((String) m.get("Sender") == "manager")
		{
			
		}
	}	
}

void handleViewMyBooks(LinkedHashMap<String, Object> m) throws Exception 
{
	if((String) m.get("Sender") == "student")
	{
		Platform.runLater(()->{
			try {
				StudentMainPage.callViewMyBooks(m);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			});
	}
	
}

public void setManagerMainPage(Manager_MainPageController MMP)
{
	this.ManagerMainPage = MMP;
}
public void setStudentMainPage(StudentMainPage1Controller SMP)
{
	this.StudentMainPage = SMP;
}
public void setLibrarianMainPage(Librarian_MainPageController Real) 
{
	LibrarianMainPage = Real;
}

}//end class
