package gui;

import java.awt.event.ActionEvent;
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
		
	}
    @FXML
    public void StartPage_ChangeConnection(ActionEvent event) throws Exception {
		CSC = new ConnectionSettingsController();
		CSC.start(null);
    }

    @FXML
    public void StartPage_Login(ActionEvent event) throws IOException {
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
    	if(this.client.getToDisplay().equals("Librarian")) {
    		this.TEXTAREA.setText("Librarian User");
    	}
    	if(this.client.getToDisplay().equals("Manager")) {
    		this.TEXTAREA.setText("Manager User");
    		
    	}
    	if(this.client.getToDisplay().equals("Member")) {
    		this.TEXTAREA.setText("Member User");
    		
    	}
    }

    @FXML
    public void StartPage_SearchByGroup(ActionEvent event) {
    	LinkedHashMap<String, Object> map = new LinkedHashMap<String,Object>();
    	map.put("Action", "Search Book");
    	Message msg;
    	String searchVal;
    	if(Search_group.getSelectedToggle() != null)
    	{
    		if(Search_group.getSelectedToggle() == StartPage_SearchByBookName_RDBTN) //search by name of the book
    		{
    			searchVal = StartPage_SearchByBookName_TXF.getText();
    			map.put("by", "book name");
    			map.put("key",searchVal);
    		}
    		if(Search_group.getSelectedToggle() == StartPage_SearchByAutorName_RDBTN) //search by author name
    		{
    			searchVal = StartPage_SearchByAutorName_TXF.getText();
    			map.put("by", "author name");
    			map.put("key",searchVal);
    		}
    		if(Search_group.getSelectedToggle() == StartPage_SearchByCategory_RDBTN) //search by categories 
    		{
    			searchVal = StartPage_SearchByCategory_TXF.getText();
    			map.put("by", "category");
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
