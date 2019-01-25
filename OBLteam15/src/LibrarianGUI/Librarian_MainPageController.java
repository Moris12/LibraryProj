package LibrarianGUI;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;

import MemberGUI.*;
import client.Client;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import models.Message;
import gui.*;
public class Librarian_MainPageController implements Initializable{

	private Stage stg;
	private FXMLLoader FxmlL;
	private Scene scn;
	private Pane rt;
	private Client client;
	public Employee me;
	RemoveBookPUPController RBPC;
	CreateNewMemberPOPLibrarianController newMemberForm;
	PUP_FillMemberIDController fillMemberID;
	MemberShipCardController MembershipCardController;
	
	@FXML
	private Button LibririanMainPage_logOut_BTN;
	
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
    		new Message(map);
    	}
    	else //no radio button selected 
    	{
    		System.out.println("no radio button selected.");
    	}
    }

    @FXML
    void LibrarianMainPage_CreateNewMemberCard() throws Exception //create new member func 
    {
    	newMemberForm = new CreateNewMemberPOPLibrarianController();
    	newMemberForm.setClient(this.client);
    	Platform.runLater(()->{try {
			newMemberForm.start(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} });
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
    void LibrarianMainPage_ReturnBook() throws Exception 
    {
    	ReturnBookPUPController returnBookPUPController = new ReturnBookPUPController();
    	
    	returnBookPUPController.setClient(client);
    	returnBookPUPController.start(null);
    }

    @FXML
    void LibrarianMainPage_Status() {

    }

    @FXML
    void LibrarianMainPage_ViewMemberShipCard() throws Exception 
    {
    	fillMemberID = new PUP_FillMemberIDController();
    	fillMemberID.setClient(this.client);
    	fillMemberID.start(null);
    }
    
	public void start(Stage arg0, LinkedHashMap<String, Object> m) throws Exception 
	{
		System.out.println("making new stage");
		this.stg = new Stage();
		System.out.println("making new loader");
		this.FxmlL = new FXMLLoader();
		System.out.println("load FXML");
		this.rt = FxmlL.load(getClass().getResource("/LibrarianGUI/Librarian_MainPage.fxml").openStream());
		System.out.println("found the fxml");
		this.scn = new Scene(rt);
		this.scn.getStylesheets().add(getClass().getResource("/gui/prototypeFXML.css").toExternalForm());
		this.stg.setScene(scn);
		client.setLibrarianMainPage(this);
		System.out.println("before show and wait");
		
		this.stg.showAndWait();
		System.out.println("after show and wait");

	}

	public void setClient(Client clnt) 
	{
		this.client = clnt;
	}
	
	/**INVENTORY MANAGEMENT FUNCS : */
	
	@FXML
	public void LibrarianMainPage_addNewBook()
	{
		
	}
	
	@FXML
	public void LibrarianMainPage_removeBook() throws Exception
	{
		RBPC = new RemoveBookPUPController();
		RBPC.setClient(this.client);
		System.out.println("before start remove");

		RBPC.start(null);
	}
	
	@FXML
	public void LibrarianMainPage_addNewCopy()
	{
		
	}
	
	@FXML
	public void LibrarianMainPage_vewBookDetails()
	{
		
	}
	
	public void callViewMembershipCard(LinkedHashMap<String, Object> m) throws Exception
	{
		MembershipCardController = new MemberShipCardController();
		MembershipCardController.setWhoCalledMe(this);
		Stage stage = (Stage) LibririanMainPage_Hour_LB.getScene().getWindow();
	    stage.hide();
	    //SET ALL DETAILS ACCORDING TO MORIS SEND TYPE
	    MembershipCardController.start(null);
	}
	
	@FXML	/**log out handler*/
    void StudentMainPage_LogOut() throws IOException { 
		System.out.println("inside log out func");
    	LinkedHashMap<String,Object> m = new LinkedHashMap<String,Object>();
    	Message msg;
    	m.put("Type", "log out");
    	m.put("M_id", me.getEmp_num());
    	m.put("Sender", "librarian"); /**to set the pointer in client to null*/
    	msg = new Message(m);
    	client.sendToServer(msg);
    }
	
	public void initLabels(LinkedHashMap<String, Object> m)
	{
		System.out.println((String)m.get("Emp_pname"));
		System.out.println((String)m.get("Emp_lname"));
		if(m.containsKey("Emp_pname") && m.containsKey("Emp_lname")) {
		LibririanMainPage_LibrarianName_LB.setText("Hello " + (String)m.get("Emp_pname")
		+ " "+(String)m.get("Emp_lname") );}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//initLabels(me.getDetailsByHashMap());
	}
}
