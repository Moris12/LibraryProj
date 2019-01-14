package gui;

import java.util.LinkedHashMap;
import client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import models.Message;
/**	Description of Librarian_MainPageController class
 * @author asaf bens habat
 * @author adi gamliel
 * @author yana mamedov
 */
public class Librarian_MainPageController {

	Stage Stage;
	FXMLLoader loader;
	Pane root;
	Scene scene;
	Client client;

    @FXML
    private Label LibririanMainPage_LibrarianName_LB;

    @FXML
    private Label LibririanMainPage_LibrarianStatus_LB;

    @FXML
    private MenuButton LibririanMainPage_InventoryManag_MNBTN;

    @FXML
    private MenuItem LibririanMainPage_AddNewBook_MNI;

    @FXML
    private MenuItem LibririanMainPage_RemoveBook_MNI;

    @FXML
    private MenuItem LibririanMainPage_ViewBookDetails_MNI;

    @FXML
    private Label LibririanMainPage_Date_LB;

    @FXML
    private Label LibririanMainPage_Hour_LB;

    @FXML
    private Button LibririanMainPage_CreatNewMember_BTN;

    @FXML
    private Rectangle LibririanMainPage_SearchByCategory_TXF;

    @FXML
    private TextField LibririanMainPage_SearchByBookName_TXF;

    @FXML
    private TextField LibririanMainPage_SearchByAutorName_TXF;

    @FXML
    private TextField LibririanMainPage_SearchCategory_TXF;

    @FXML
    private Button LibririanMainPage_Search_BTN;

    @FXML
    private TextField LibririanMainPage_SearchByFreeText_TXF;

    @FXML
    private Label LibririanMainPage_LogOut_LB;

    @FXML
    private Button LibririanMainPage_ReturnBook_BTN;

    @FXML
    private Button LibririanMainPage_ViewMemberCard_BTN;

    @FXML
    private Button LibririanMainPage_Messages_BTN;

    @FXML
    private RadioButton LibririanMainPage_SearchByBookName_RDBTN;

    @FXML
    private ToggleGroup Search_group;

    @FXML
    private RadioButton LibririanMainPage_SearchByAutorName_RDBTN;

    @FXML
    private RadioButton LibririanMainPage_SearchByCategory_RDBTN;

    @FXML
    private RadioButton LibririanMainPage_SearchByFreeText_RDBTN;

    @FXML
    void LaibrarianMainPage_SearchByGroup() //search button click function. 
    //take information from txtfld, and search by the argument given.
    {
    	LinkedHashMap<String, Object> map = new LinkedHashMap<String,Object>();
    	map.put("Action", "Search Book");
    	Message msg;
    	String searchVal;
    	if(Search_group.getSelectedToggle() != null)
    	{
    		if(Search_group.getSelectedToggle() == LibririanMainPage_SearchByBookName_RDBTN) //search by name of the book
    		{
    			searchVal = LibririanMainPage_SearchByBookName_TXF.getText();
    			map.put("by", "book name");
    			map.put("key",searchVal);
    		}
    		if(Search_group.getSelectedToggle() == LibririanMainPage_SearchByAutorName_RDBTN) //search by author name
    		{
    			searchVal = LibririanMainPage_SearchByAutorName_TXF.getText();
    			map.put("by", "author name");
    			map.put("key",searchVal);
    		}
    		if(Search_group.getSelectedToggle() == LibririanMainPage_SearchByCategory_RDBTN) //search by categories 
    		{
    			searchVal = LibririanMainPage_SearchCategory_TXF.getText();
    			map.put("by", "category");
    			map.put("key",searchVal);
    		}
    		if(Search_group.getSelectedToggle() == LibririanMainPage_SearchByFreeText_RDBTN) //search by free text
    		{
    			searchVal = LibririanMainPage_SearchByFreeText_TXF.getText();
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

    @FXML
    void LibrarianMainPage_CreateNewMemberCard(ActionEvent event) throws Exception //create new member func 
    {
    	CreateNewMemberPOPLibrarianController newMemberForm = new CreateNewMemberPOPLibrarianController();
    	newMemberForm.start(null); //one day we will send client here
    }

    @FXML
    void LibrarianMainPage_Date() {

    }

    @FXML
    void LibrarianMainPage_HelloLibrarianName() {

    }

    @FXML
    void LibrarianMainPage_Hour() {

    }

    @FXML
    void LibrarianMainPage_InventoryManagment() {

    }

    @FXML
    void LibrarianMainPage_LogOut() {

    }

    @FXML
    void LibrarianMainPage_Messages() {

    }

    @FXML
    void LibrarianMainPage_ReturnBook() throws Exception //return book method 
    {
    	ReturnBookPUPController RetBookCntrlr = new ReturnBookPUPController();
    	RetBookCntrlr.start(null);
    }

    @FXML
    void LibrarianMainPage_Status() {

    }

    @FXML
    void LibrarianMainPage_ViewMemberShipCard() {

    }
    
	public void start(Stage arg0) throws Exception 
	{	
		this.Stage = new Stage();
		this.loader = new FXMLLoader();
		this.root = loader.load(getClass().getResource("/gui/Librarian_MainPage.fxml").openStream());
		this.scene = new Scene(root);			
		this.Stage.setScene(scene);
		this.Stage.showAndWait();
		
	}

	void setClient(Client clnt) {
		this.client = clnt;
	}
	Client getClient()
	{
		return this.client;
	}
}
