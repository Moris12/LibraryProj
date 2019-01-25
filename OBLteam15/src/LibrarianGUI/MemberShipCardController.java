package LibrarianGUI;

import java.util.LinkedHashMap;
import MemberGUI.*;
import client.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import gui.*;
public class MemberShipCardController {
	Client client;
	Librarian_MainPageController whoCalledMe;
	
	public Librarian_MainPageController getWhoCalledMe() {
		return whoCalledMe;
	}

	public void setWhoCalledMe(Librarian_MainPageController LMP) {
		this.whoCalledMe = LMP;
	}
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client clnt) {
		this.client = clnt;
	}

	Stage Stage;
	FXMLLoader loader;
	Pane root;
	Scene scene;
	
    @FXML
    private Label MemberShipCard_HelloLibririanName_LB;

    @FXML
    private Label MemberShipCard_StatusLibririan_LB;

    @FXML
    private Label MemberShipCard_Date_LB;

    @FXML
    private Label MemberShipCard_Hour_LB;

    @FXML
    private Label MemberShipCard_LogOut_LB;

    @FXML
    private Label MemberShipCard_MemberStatus_LB;

    @FXML
    private Label MemberShipCard_StudentName_LB;

    @FXML
    private Label MemberShipCard_ID_LB;

    @FXML
    private Label MemberShipCard_Email_LB;

    @FXML
    private Label MemberShipCard_PhoneNumber_LB;

    @FXML
    private Label MemberShipCard_MemberNum_LB;

    @FXML
    private TextArea MemberShipCard_HistoryOfLending_TXA;

    @FXML
    private Label LibririanMainPage_LibrarianName_LB13;

    @FXML
    private TextArea MemberShipCard_StatusOfBorrowesBooks_TXA;

    @FXML
    private Label LibririanMainPage_LibrarianName_LB131;

    @FXML
    private Label LibririanMainPage_LibrarianName_LB123;

    @FXML
    private Label LibririanMainPage_LibrarianName_LB1231;

    @FXML
    private Label LibririanMainPage_LibrarianName_LB12311;

    @FXML
    private Label LibririanMainPage_LibrarianName_LB123111;

    @FXML
    private TextField MemberShipCard_NumberBookForExtension_TXF;

    @FXML
    private Button MemberShipCard_FinishExtension_BTN;

    @FXML
    private Button MemberShipCard_Back_BTN;

    @FXML
    void MemberShipCard_Back() { /////////////////////////
    	
    }

    @FXML
    void MemberShipCard_Date() {

    }

    @FXML
    void MemberShipCard_Email() {

    }

    @FXML
    void MemberShipCard_FinishManualExtension() {

    }

    @FXML
    void MemberShipCard_HistoryOfLending() {

    }

    @FXML
    void MemberShipCard_Hour() {

    }

    @FXML
    void MemberShipCard_ID() {

    }

    @FXML
    void MemberShipCard_LibrarianName() {

    }

    @FXML
    void MemberShipCard_LogOut() {

    }

    @FXML
    void MemberShipCard_MemberNumber() {

    }

    @FXML
    void MemberShipCard_MemberStatus() {

    }

    @FXML
    void MemberShipCard_PhoneNumber() {

    }

    @FXML
    void MemberShipCard_Status() {

    }

    @FXML
    void MemberShipCard_StatusOfBorrowedBooks() {

    }

    @FXML
    void MemberShipCard_StudentName() {

    }

public void start(Stage arg0) throws Exception {
		
		this.Stage = new Stage();
		this.loader = new FXMLLoader();
		this.root = loader.load(getClass().getResource("/gui/RemoveBookPUP.fxml").openStream());
		this.scene = new Scene(root);	
		this.scene.getStylesheets().add(getClass().getResource("/gui/prototypeFXML.css").toExternalForm());
		this.Stage.setScene(scene);
		this.Stage.showAndWait();
	}

public void setLablesStudDetails(LinkedHashMap<String, Object> m)
{
	String fullName = (String) m.get("M_pname");
	fullName += " ";
	fullName += (String) m.get("M_lname");
	MemberShipCard_StudentName_LB.setText(fullName);
	MemberShipCard_ID_LB.setText((String) m.get("M_id"));
	MemberShipCard_Email_LB.setText((String) m.get("M_email"));
	MemberShipCard_PhoneNumber_LB.setText((String) m.get("M_phone"));
	String status = "Status: ";
	status += (String) m.get("M_status");
	MemberShipCard_MemberStatus_LB.setText(status);
}
}
