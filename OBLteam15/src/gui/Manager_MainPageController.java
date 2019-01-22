package gui;

import actors.Employee;
import client.Client;
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

public class Manager_MainPageController {
	//gui atts
	Stage Stage;
	FXMLLoader loader;
	Pane root;
	Scene scene;
	Client client;
	Employee me;

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
    void ManagerMainPage_CreateNewMemberCard() {

    }

    @FXML
    void ManagerMainPage_Date() {

    }

    @FXML
    void ManagerMainPage_GetStatisticRport() {

    }

    @FXML
    void ManagerMainPage_HelloManagerName() {

    }

    @FXML
    void ManagerMainPage_Hour() {

    }

    @FXML
    void ManagerMainPage_InventoryManagment() {

    }

    @FXML
    void ManagerMainPage_LogOut() {

    }

    @FXML
    void ManagerMainPage_Messages() {

    }

    @FXML
    void ManagerMainPage_ReturnBook() {

    }

    @FXML
    void ManagerMainPage_SearchByGroup() {

    }

    @FXML
    void ManagerMainPage_Status() {

    }

    @FXML
    void ManagerMainPage_ViewMemberShipCard() {

    }

    @FXML
    void ManagerMainPage_ViewingInformation() {

    }
    
    
public void start(Stage arg0) throws Exception {
		
		this.Stage = new Stage();
		this.loader = new FXMLLoader();
		this.root = loader.load(getClass().getResource("/gui/Manager_MainPage.fxml").openStream());
		this.scene = new Scene(root);	
		this.scene.getStylesheets().add(getClass().getResource("/gui/prototypeFXML.css").toExternalForm());
		this.Stage.setScene(scene);
		this.Stage.showAndWait();
		
	}

public void setClient(Client clnt) {
	// TODO Auto-generated method stub
	this.client = clnt;
}

}
