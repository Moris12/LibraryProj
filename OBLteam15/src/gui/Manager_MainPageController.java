package gui;

import java.awt.event.ActionEvent;
import java.util.LinkedHashMap;

import client.Client;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
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
	import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import models.Message;

	public class Manager_MainPageController extends Librarian_MainPageController {
		
		Stage Stage;
		FXMLLoader loader;
		Pane root;
		Scene scene;
		Client client;

	    @FXML
	    private Label ManagerMainPage_HelloManagerName_LB;

	    @FXML
	    private Label ManagerMainPage_StatusManager_LB;

	    @FXML
	    private MenuButton ManagerMainPage_InventoryManagment_MNBTN;

	    @FXML
	    private MenuItem ManagerMainPage_AddNewBook_MNI;

	    @FXML
	    private MenuItem ManagerMainPage_RemoveBook_MNI;

	    @FXML
	    private MenuItem ManagerMainPage_ViewBookDetails_MNI;

	    @FXML
	    private Label ManagerMainPage_Date_LB;

	    @FXML
	    private Label ManagerMainPage_Hour_LB;

	    @FXML
	    private Button ManagerMainPage_CreatNewMember_BTN;

	    @FXML
	    private Rectangle LibririanMainPage_SearchByCategory_TXF;

	    @FXML
	    private TextField ManagerMainPage_SerarchByBookName_TXF;

	    @FXML
	    private TextField ManagerMainPage_SerarchByAutorName_TXF;

	    @FXML
	    private TextField ManagerMainPage_SerarchByCategory_TXF;

	    @FXML
	    private Button ManagerMainPage_SerarchFinish_BTN;

	    @FXML
	    private TextField ManagerMainPage_SerarchByFreeText_TXF;

	    @FXML
	    private Label ManagerMainPage_LogOut_LB;

	    @FXML
	    private Button ManagerMainPage_ReturnBook_BTN;

	    @FXML
	    private Button ManagerMainPage_ViewMemberCard_BTN;

	    @FXML
	    private Button ManagerMainPage_Messages_BTN;

	    @FXML
	    private MenuButton ManagerMainPage_GetStatisticReprt_MNBTN;

	    @FXML
	    private MenuItem ManagerMainPage_ActivityReport_MNI;

	    @FXML
	    private MenuItem ManagerMainPage_LendingReport_MNI;

	    @FXML
	    private MenuItem ManagerMainPage_LateReturnReprt_MNI;

	    @FXML
	    private MenuItem ManagerMainPage_DinamicReport_MNI;

	    @FXML
	    private Button ManagerMainPage_ViewInformation_BTN;

	    @FXML
	    private RadioButton ManagerMainPage_SearchByBookName_RDBTN;

	    @FXML
	    private ToggleGroup Search_group;

	    @FXML
	    private RadioButton ManagerMainPage_SearchByAutorName_RDBTN;

	    @FXML
	    private RadioButton ManagerMainPage_SearchByCategory_RDBTN;

	    @FXML
	    private RadioButton ManagerMainPage_SearchByFreeText_RDBTN;

	    @FXML
	    void ManagerMainPage_CreateNewMemberCard(ActionEvent event) throws Exception {

	    	CreateNewMemberPOPLibrarianController newMemberForm = new CreateNewMemberPOPLibrarianController();
	    	newMemberForm.start(null); //one day we will send client her
	    	
	    }

	    @FXML
	    void ManagerMainPage_Date(MouseEvent event) {

	    }

	    @FXML
	    void ManagerMainPage_GetStatisticRport(ActionEvent event) {

	    }

	    @FXML
	    void ManagerMainPage_HelloManagerName(MouseEvent event) {

	    }

	    @FXML
	    void ManagerMainPage_Hour(MouseEvent event) {

	    }

	    @FXML
	    void ManagerMainPage_InventoryManagment(ActionEvent event) {

	    }

	    @FXML
	    void ManagerMainPage_LogOut(MouseEvent event) {

	    }

	    @FXML
	    void ManagerMainPage_Messages(ActionEvent event) {

	    }

	    @FXML
	    void ManagerMainPage_ReturnBook(ActionEvent event) {

	    }

	    @FXML
	    void ManagerMainPage_SearchByGroup(ActionEvent event) {
	    	
	    //	Librarian_MainPageController getMainLpage = new Librarian_MainPageController();
	    
	    	
	    	
	  /*	LinkedHashMap<String, Object> map = new LinkedHashMap<String,Object>();
	    	map.put("Action", "Search Book");
	    	Message msg;
	    	String searchVal;
	    	
	    	if(Search_group.getSelectedToggle() != null)
	    	{
	    		if(Search_group.getSelectedToggle() == ManagerMainPage_SerarchByBookName_TXF) //search by name of the book
	    		{
	    			searchVal = ManagerMainPage_SerarchByBookName_TXF.getText();
	    			map.put("by", "book name");
	    			map.put("key",searchVal);
	    		}
	    		if(Search_group.getSelectedToggle() == ManagerMainPage_SearchByAutorName_RDBTN) //search by author name
	    		{
	    			searchVal = ManagerMainPage_SerarchByAutorName_TXF.getText();
	    			map.put("by", "author name");
	    			map.put("key",searchVal);
	    		}
	    		if(Search_group.getSelectedToggle() == ManagerMainPage_SearchByCategory_RDBTN) //search by categories 
	    		{
	    			searchVal = ManagerMainPage_SerarchByCategory_TXF.getText();
	    			map.put("by", "category");
	    			map.put("key",searchVal);
	    		}
	    		if(Search_group.getSelectedToggle() == ManagerMainPage_SearchByFreeText_RDBTN) //search by free text
	    		{
	    			searchVal = ManagerMainPage_SerarchByFreeText_TXF.getText();
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
 */
	    }

	    @FXML
	    void ManagerMainPage_Status(MouseEvent event) {

	    }

	    @FXML
	    void ManagerMainPage_ViewMemberShipCard(ActionEvent event) {

	    }

	    @FXML
	    void ManagerMainPage_ViewingInformation(ActionEvent event) {

	    }
	    
		public void start(Stage arg0) throws Exception {
			
			this.Stage = new Stage();
			this.loader = new FXMLLoader();
			this.root = loader.load(getClass().getResource("/gui/Manager_MainPage.fxml").openStream());
			this.scene = new Scene(root);			
			this.Stage.setScene(scene);
			this.Stage.showAndWait();
			
		}

	}



