package gui;

import java.util.LinkedHashMap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Message;

public class CreateNewMemberPOPLibrarianController {

	Stage Stage;
	FXMLLoader loader;
	Pane root;
	Scene scene;
	
    @FXML
    private TextField CreateNewMemberPUPLibrarian_FirstName_TXF;

    @FXML
    private TextField CreateNewMemberPUPLibrarian_LastName_TXF;

    @FXML
    private TextField CreateNewMemberPUPLibrarian_ID_TXF;

    @FXML
    private TextField CreateNewMemberPUPLibrarian_Email_TXF;

    @FXML
    private TextField CreateNewMemberPUPLibrarian_PhoneNumber_TXF;

    @FXML
    private TextField CreateNewMemberPUPLibrarian_DateOfGraduate_TXF;

    @FXML
    private Button CreateNewMemberPUPLibrarian_Cancel_BTN;

    @FXML
    private Label CreateNewMemberPUPLibrarian_Done_LB;

    @FXML
    void CreateNewMemberPUP_Cancel(ActionEvent event) {//CANCEL button handler
    	Stage stage = (Stage) CreateNewMemberPUPLibrarian_Cancel_BTN.getScene().getWindow();
	    stage.close();
    }

    @FXML
    void CreateNewMemberPUP_DONE(MouseEvent event) { //DONE clicked.
    	LinkedHashMap<String, Object> map = new LinkedHashMap<String,Object>();
    	Message msg;
    	String FirstName, LastName, ID, GraduateDate, Email, PhoneNumber;
    	if(!CreateNewMemberPUPLibrarian_FirstName_TXF.getText().isEmpty() && !CreateNewMemberPUPLibrarian_LastName_TXF.getText().isEmpty() && !CreateNewMemberPUPLibrarian_ID_TXF.getText().isEmpty() && !CreateNewMemberPUPLibrarian_DateOfGraduate_TXF.getText().isEmpty())
    	{//check the essential fields.
    		
    		
    		//set variables with the fields input.
    		FirstName = CreateNewMemberPUPLibrarian_FirstName_TXF.getText();
    		LastName = CreateNewMemberPUPLibrarian_LastName_TXF.getText();
    		ID = CreateNewMemberPUPLibrarian_ID_TXF.getText();
    		GraduateDate = CreateNewMemberPUPLibrarian_DateOfGraduate_TXF.getText();
    		
    		
    		//check extra fields and set null if empty.
    		if(!CreateNewMemberPUPLibrarian_Email_TXF.getText().isEmpty())
    			Email = CreateNewMemberPUPLibrarian_Email_TXF.getText();
    		else Email = null;
    		if(CreateNewMemberPUPLibrarian_PhoneNumber_TXF.getText().isEmpty())
    			PhoneNumber = CreateNewMemberPUPLibrarian_PhoneNumber_TXF.getText();
    		else PhoneNumber = null;
    		map.put("phone number",PhoneNumber);
    		map.put("email", Email);
    		map.put("ID", ID);
    		map.put("First Name", FirstName);
    		map.put("last name", LastName);
    		map.put("graduate date", GraduateDate);
    		msg = new Message(map);
    		
    		
    		//send to server here
    	}
    	else //one of the field is missing information.
    	{
    		System.out.println("essential fields are not full.");
    	}
    	
    }

	public void start(Stage arg0) throws Exception {
		final Modality WINDOW_MODAL = null; //makes it appear as pop up that has to be closed.
		this.Stage = new Stage();
		Stage.initModality(WINDOW_MODAL);
		this.loader = new FXMLLoader();
		this.root = loader.load(getClass().getResource("/gui/Librarian_MainPage.fxml").openStream());
		this.scene = new Scene(root);			
		this.Stage.setScene(scene);
		this.Stage.showAndWait();
		
	}
}
