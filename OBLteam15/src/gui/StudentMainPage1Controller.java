package gui;

import java.net.URL;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import actors.Member;
import client.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import models.Message;

public class StudentMainPage1Controller implements Initializable {
	
	//gui-loading atts
	Stage Stage;
	FXMLLoader loader;
	Pane root;
	Scene scene;
	Client client;
	Member me; //i hold reference to member (class only for attributes).
	//it is set by the one who created this screen. and helps to get my info faster.
	
	//gui components
	
    @FXML
    private Label StudentMainPage_OBL_LB;

    @FXML
    private Label StudentMainPage_HeloStudentName_LB;

    @FXML
    private Label StudentMainPage_Status_LB;

    @FXML
    private Label StudentMainPage_DateRT_LB;

    @FXML
    private Label StudentMainPage_HourRT_LB;

    @FXML
    private TextArea StudentMainPage_HistoryLending_TXA;

    @FXML
    private TextArea StudentMainPage_StatusOfOrders_TXA_TXA;

    @FXML
    private Rectangle LibririanMainPage_SearchByCategory_TXF;

    @FXML
    private TextField StudentMainPage_SearchByBookName_TXF;

    @FXML
    private TextField StudentMainPage_SearchByAutorName_TXF;

    @FXML
    private TextField StudentMainPage_SearchCategory_TXF;

    @FXML
    private Button StudentMainPage_Search_BTN;

    @FXML
    private TextField StudentMainPage_SearchByFreeText_TXF;

    @FXML
    private RadioButton StudentMainPage_SearchByBookName_RDBTN;

    @FXML
    private ToggleGroup Search_group;

    @FXML
    private RadioButton StudentMainPage_SearchByAutorName_RDBTN;

    @FXML
    private RadioButton StudentMainPage_SearchByCategory_RDBTN;

    @FXML
    private RadioButton StudentMainPage_SearchByFreeText_RDBTN;

    @FXML
    void StudentMainPage_Date(DragEvent event) {

    }

    @FXML
    void StudentMainPage_EditDetails() {

    }

    @FXML
    void StudentMainPage_HistoryLendingArea(MouseEvent event) {

    }

    @FXML
    void StudentMainPage_Hour(DragEvent event) {

    }

    @FXML
    void StudentMainPage_LogOut(MouseEvent event) {

    }

    @FXML
    void StudentMainPage_Messages() {

    }

    @FXML
    void StudentMainPage_SearchByGroup() {
        //take information from txtfld, and search by the argument given.
        {
        	LinkedHashMap<String, Object> map = new LinkedHashMap<String,Object>();
        	map.put("Action", "Search Book");
        	Message msg;
        	String searchVal;
        	if(Search_group.getSelectedToggle() != null) //if you actually selected some field to look by..
        	{
        		if(Search_group.getSelectedToggle() == StudentMainPage_SearchByBookName_RDBTN) //search by name of the book
        		{
        			searchVal = StudentMainPage_SearchByBookName_TXF.getText();
        			map.put("by", "book name");
        			map.put("key",searchVal);
        		}
        		if(Search_group.getSelectedToggle() == StudentMainPage_SearchByAutorName_RDBTN) //search by author name
        		{
        			searchVal = StudentMainPage_SearchByAutorName_TXF.getText();
        			map.put("by", "author name");
        			map.put("key",searchVal);
        		}
        		if(Search_group.getSelectedToggle() == StudentMainPage_SearchByCategory_RDBTN) //search by categories 
        		{
        			searchVal = StudentMainPage_SearchCategory_TXF.getText();
        			map.put("by", "category");
        			map.put("key",searchVal);
        		}
        		if(Search_group.getSelectedToggle() == StudentMainPage_SearchByFreeText_RDBTN) //search by free text
        		{
        			searchVal = StudentMainPage_SearchByFreeText_TXF.getText();
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

    @FXML
    void StudentMainPage_StatusOfOrdersArea(MouseEvent event) {

    }

    @FXML
    void StudentMainPage_StudentName() {

    }

    @FXML
    void StudentMainPage_StudentStatus() {

    }

    @FXML
    void StudentMainPage_ViewMyBooks() {

    }
    //void PrintInfoToTXA(TextArea area , DATASTRUCTRE DS);
    //this method should get a data structre too. 
    //and decide about the print format. + lock this window for writing
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) { 
		//this method will load history of lending details for the text area
		// and the status of order text area details
    	LinkedHashMap<String, Object> map = new LinkedHashMap<String,Object>();
    	Message msg;
    	map.put("Action", "Get history");
    	map.put("From", "LendRetHistory");
    	//map.put("Member", );
			
	}
	
	public void start(Stage arg0) throws Exception {
		this.Stage = new Stage();
		this.loader = new FXMLLoader();
		this.root = loader.load(getClass().getResource("/gui/StudentMainPage1.fxml").openStream());
		this.scene = new Scene(root);			
		this.Stage.setScene(scene);
		this.Stage.showAndWait();
		
	}

	Client getClient()
	{
		return this.client;
	}
	void setClient(Client clnt)
	{
		this.client = clnt;
	}
	
}
