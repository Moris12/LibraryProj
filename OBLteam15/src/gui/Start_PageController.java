package gui;


import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;

import actors.Employee;
import actors.Member;
import client.Client;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import models.Message;
/** 
 * 
 * @author shahar shani
 * @author Asaf ben shabat
 *@author adi gamliel
 */


public class Start_PageController implements Initializable 
{
	Stage Stage;
	FXMLLoader loader;
	Pane root;
	Scene scene;
	String input=" ";
	Message msg;
	ConnectionSettingsController CSC;
	Client client;
	Manager_MainPageController ManagerMainPage;
	static public String Hostt = "192.168.1.66";
	static public int Portt = 5555;
	StudentMainPage1Controller StudentMainPage;
	Librarian_MainPageController LibrarianMainPage;
	SearchPUPStudent_NotsuccessController searchPUPStudNotSuccessful;
	SearchPUPStudent_successController searchPUPStudSuccessful;
	@FXML
    private Rectangle LibririanMainPage_SearchByCategory_TXF;

    @FXML
    private TextField StartPage_SearchByBookName_TXF;
    
    @FXML
    private TextArea TEXTAREA;

    @FXML
    private TextField StartPage_SearchByAutorName_TXF;

    @FXML
    private TextField StartPage_SearchByCategory_TXF;

    @FXML
    private Button StartPage_Search_BTN;

    @FXML
    private TextField StartPage_SearchByFreeText_TXF;

    @FXML
    private RadioButton StartPage_SearchByBookName_RDBTN;

    @FXML
    private ToggleGroup Search_group;

    @FXML
    private RadioButton StartPage_SearchByAutorName_RDBTN;

    @FXML
    private RadioButton StartPage_SearchByCategory_RDBTN;

    @FXML
    private RadioButton StartPage_SearchByFreeText_RDBTN;

    @FXML
    private TextField StartPage_UserName_TXF;

    @FXML
    private Button StartPage_Login_BTN;

    @FXML
    private PasswordField StartPage_Password_PSF;

    @FXML
    private Label StartPage_LibraryHours_LB;

    @FXML
    private Button StartPage_ChangeConnection_BTN;
    
	public void start(Stage arg0) throws Exception {
		
		this.Stage = new Stage();
		this.loader = new FXMLLoader();
		this.root = loader.load(getClass().getResource("/gui/Start_Page.fxml").openStream());
		this.scene = new Scene(root);	
		this.scene.getStylesheets().add(getClass().getResource("/gui/prototypeFXML.css").toExternalForm());
		this.Stage.setScene(scene);
		this.Stage.showAndWait();
		
	}
 
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		try {
			client = new Client(Hostt, Portt);
			System.out.println("made new client");
			client.openConnection();
			System.out.println("open conn done");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    

    @FXML
    public void StartPage_Login() throws Exception {
    	LinkedHashMap<String, Object> map = new LinkedHashMap<String,Object>();
    	
    	if(this.StartPage_UserName_TXF.getText()==null||this.StartPage_Password_PSF.getText()==null)
    	{
    		System.out.print("Error Invalid user name and password");//PUP Error 
    	}
    	map.put("Type", "log in");
    	//System.out.println((String) map.get("Type"));
    	map.put("Username", this.StartPage_UserName_TXF.getText());
    	map.put("Password", this.StartPage_Password_PSF.getText());
    	this.client.setSPC(this);
    	this.msg = new Message(map);
    	client.setSPC(this);
    	this.client.sendToServer(msg); 
    	
    	// send the information to server for approval of existance.
    	

    }

    @FXML
    public void StartPage_SearchByGroup() throws IOException {
    	LinkedHashMap<String, Object> map = new LinkedHashMap<String,Object>();
    	map.put("Type", "search book");
    	Message msg;
    	String searchVal;
    	if(Search_group.getSelectedToggle() == null)
    	{
    		
    		if(StartPage_SearchByBookName_RDBTN.isSelected()) //search by name of the book
    		{
    			searchVal = StartPage_SearchByBookName_TXF.getText();
    			map.put("By", "book name");
    			map.put("key",searchVal);
    		}
    		if(StartPage_SearchByAutorName_RDBTN.isSelected()) //search by author name
    		{
    			searchVal = StartPage_SearchByAutorName_TXF.getText();
    			map.put("By", "author name");
    			map.put("key",searchVal);
    		}
    		if(StartPage_SearchByCategory_RDBTN.isSelected()) //search by categories 
    		{
    			searchVal = StartPage_SearchByCategory_TXF.getText();
    			map.put("By", "category");
    			map.put("key",searchVal);
    		}
    		if(StartPage_SearchByFreeText_RDBTN.isSelected()) //search by free text
    		{
    			searchVal = StartPage_SearchByFreeText_TXF.getText();
    			map.put("By", "free text");
    			map.put("key",searchVal);
    		}
    		//end if's.
    		map.put("Sender", "Start_PageController");
    		msg = new Message(map);
    		client.setSPC(this);
    		this.client.sendToServer(msg);
    	}
    	else //no radio button selected 
    	{
    		System.out.println("no radio button selected.");
    	}

    }
    
    public Stage getStage() {return this.Stage;}
    
    public Start_PageController getSPC()
    {
    	return this;
    }
    public void callStudentMainPage(LinkedHashMap<String, Object> m) throws Exception
    {
    	StudentMainPage1Controller StudentMainPage = new StudentMainPage1Controller();
		StudentMainPage.setClient(this.client);
		StudentMainPage.me = new Member();
		StudentMainPage.me.setDetailsByHashMap(m);
		StudentMainPage.start(null);
    }
    
    public void callLibrarianMainPage(LinkedHashMap<String, Object> m) throws Exception
    {
    	LibrarianMainPage = new Librarian_MainPageController();
		LibrarianMainPage.setClient(this.client);
		LibrarianMainPage.me = new Employee();		
		LibrarianMainPage.me.setDetailsByHashMap(m);
		Stage stage = (Stage) StartPage_Login_BTN.getScene().getWindow();
	    stage.close();
	    Platform.runLater(()->{try {
			LibrarianMainPage.start(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}});
		

	
    }
    
    public void callManagerMainPage(LinkedHashMap<String, Object> m) throws Exception
    {
    	ManagerMainPage = new Manager_MainPageController();
		ManagerMainPage.setClient(this.client);
		ManagerMainPage.me = new Employee();
		ManagerMainPage.me.setDetailsByHashMap(m);
		ManagerMainPage.start(null);
	    Platform.runLater(()->{try {
			LibrarianMainPage.start(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}});
		
    }
   
    
    @FXML
    public void StartPage_ChangeConnection() throws Exception 
    {
		CSC = new ConnectionSettingsController();
		CSC.start(null);

    }

    public void callSearchBookPUP(LinkedHashMap<String, Object> m) throws Exception
    {
    	if((boolean) m.get("B_isWaiting") == true ) //there is waiting list
    	{//means there is no available book copy. i should present the earliest return date
    		searchPUPStudNotSuccessful = new SearchPUPStudent_NotsuccessController();
    		//searchPUPStudNotSuccessful.setSearchPUPstudentNotSCS_ClosestReturn__LB((String) m.get("B_shelf"));
    		searchPUPStudNotSuccessful.getSearchPUPstudentNotSCS_ClosestReturn__LB().setVisible(false);;
    		searchPUPStudNotSuccessful.start(null);
    	}
    	else if((boolean) m.get("B_isWaiting") == false)
    	{//i should get the shelf location
    		searchPUPStudSuccessful = new SearchPUPStudent_successController();
    		searchPUPStudSuccessful.setSearchPUPstudent_Shelfnum_LB((String) m.get("B_shelf"));
    		searchPUPStudSuccessful.start(null);
    	}
    }
}
