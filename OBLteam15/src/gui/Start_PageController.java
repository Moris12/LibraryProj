package gui;


import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import client.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import models.Message;
import javafx.scene.control.TextArea;
import actors.Member;
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
	static public String Hostt = "192.168.1.78";
	static public int Portt = 5555;
	

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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
	@FXML
    public void StartPage_ChangeConnection() throws Exception {
		CSC = new ConnectionSettingsController();
		CSC.start(null);
    }

    @FXML
    public void StartPage_Login() throws Exception {
    	LinkedHashMap<String, Object> map = new LinkedHashMap<String,Object>();
    	
    	if(this.StartPage_UserName_TXF.getText()!=null||this.StartPage_Password_PSF.getText()!=null)
    	{
    		System.out.print("Error Invalid user name and password");//PUP Error 
    	}
    	map.put("Action", "Login");
    	map.put("User name", this.StartPage_UserName_TXF.getText());
    	map.put("Password", this.StartPage_Password_PSF.getText());
    	this.msg= new Message(map);
    	this.client.sendToServer(map); 
    	// send the information to server for approval of existance.
    	
    	//HOW CAN WE MAKE SURE THAT THE CLIENT COMPUTER WILL NOT CONTINUE THE CODE WHILE 
    	//THE SERVER COMPUTER IS STILL PROCESSING THE CLIENT'S REQUEST?
    	//after the server gets the message this client-PC should wait for response. 
    	//if the client PC gets to line 141 before the server replied than he will get null.
    	
    	
    		if(this.client.getToDisplay().equals("Librarian")) { 
    		//if the login matches Librarian account
    		this.TEXTAREA.setText("Librarian User");
    		//create such screen.
    		Librarian_MainPageController LMP = new Librarian_MainPageController(); 
    		//set the connection to the server. in order to give him access too.
    		LMP.setClient(this.client);
    		//start the GUI.
    		LMP.start(null);
    		
    	}
    	if(this.client.getToDisplay().equals("Manager")) {
    		//if the login matches Manager account
    		this.TEXTAREA.setText("Manager User");
    		//create such screen.
    		Manager_MainPageController MMP = new Manager_MainPageController();
    		//set the connection to the server. in order to give him access too.
    		MMP.setClient(this.client);
    		//start the GUI.
    		MMP.start(null);
    		
    	}
    	if(this.client.getToDisplay().equals("Member")) {
    		//if the login matches Member account
    		StudentMainPage1Controller SMP = new StudentMainPage1Controller();
    		this.TEXTAREA.setText("Member User");
    		SMP.setClient(this.client);
    		//SMP.me.setDetailsByHashMap(m);
    		//SMP.me(map);  //	FIX THE SERVER ACCORDINGLY.
    		SMP.start(null);
    	}
    }

    @FXML
    public void StartPage_SearchByGroup() {
    	LinkedHashMap<String, Object> map = new LinkedHashMap<String,Object>();
    	map.put("Action", "Search Book");
    	Message msg;
    	String searchVal;
    	if(Search_group.getSelectedToggle() != null)
    	{
    		if(Search_group.getSelectedToggle() == StartPage_SearchByBookName_RDBTN) //search by name of the book
    		{
    			searchVal = StartPage_SearchByBookName_TXF.getText();
    			map.put("by", "B_name");
    			map.put("key",searchVal);
    		}
    		if(Search_group.getSelectedToggle() == StartPage_SearchByAutorName_RDBTN) //search by author name
    		{
    			searchVal = StartPage_SearchByAutorName_TXF.getText();
    			map.put("by", "B_author");
    			map.put("key",searchVal);
    		}
    		if(Search_group.getSelectedToggle() == StartPage_SearchByCategory_RDBTN) //search by categories 
    		{
    			searchVal = StartPage_SearchByCategory_TXF.getText();
    			map.put("by", "B_themes");
    			map.put("key",searchVal);
    		}
    		if(Search_group.getSelectedToggle() == StartPage_SearchByFreeText_RDBTN) //search by free text
    		{
    			searchVal = StartPage_SearchByFreeText_TXF.getText();
    			map.put("by", "free text");
    			map.put("key",searchVal);
    		}
    		//end if's.
    		msg = new Message(map);
    		//send to server?.
    	}
    	else //no radio button selected 
    	{
    		System.out.println("no radio button selected.");
    	}

    }
}
