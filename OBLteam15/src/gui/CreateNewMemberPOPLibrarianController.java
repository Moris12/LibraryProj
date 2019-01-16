package gui;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import gui.*;
import actors.Member;
import client.Client;
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
	Client client;
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
    private TextField CreateNewMemberPUPLibrarian_Password_TXF;

    @FXML
    void CreateNewMemberPUP_Cancel(ActionEvent event) {//CANCEL button handler
    	Stage stage = (Stage) CreateNewMemberPUPLibrarian_Cancel_BTN.getScene().getWindow();
	    stage.close();
    }

    @FXML
    void CreateNewMemberPUP_DONE(MouseEvent event) throws ParseException, IOException { //DONE clicked.
    	LinkedHashMap<String, Object> m = new LinkedHashMap<String,Object>();
    	Message msg;
    	String M_pname, M_lname, M_id , M_graduateDate, M_email, M_phoneNumber, M_password;
    	if(!CreateNewMemberPUPLibrarian_FirstName_TXF.getText().isEmpty() && !CreateNewMemberPUPLibrarian_LastName_TXF.getText().isEmpty() && !CreateNewMemberPUPLibrarian_ID_TXF.getText().isEmpty() && !CreateNewMemberPUPLibrarian_DateOfGraduate_TXF.getText().isEmpty())
    	{
    		/*********CHECK IF GETTEXT FROM EMPTY FIELD IS NULL***********/
    		
    		//check the essential fields.
    		//set variables with the fields input.
    		M_pname = CreateNewMemberPUPLibrarian_FirstName_TXF.getText();
    		M_lname = CreateNewMemberPUPLibrarian_LastName_TXF.getText();
    		M_id = CreateNewMemberPUPLibrarian_ID_TXF.getText();
    		M_password = CreateNewMemberPUPLibrarian_Password_TXF.getText();
    		M_graduateDate = CreateNewMemberPUPLibrarian_DateOfGraduate_TXF.getText();
    		Date GD;
    		SimpleDateFormat SDF = new SimpleDateFormat("yyyy/mm/dd");
    		GD = (Date) SDF.parse(M_graduateDate);
    		
    		//extra fields. mail & phone.
    		if(!CreateNewMemberPUPLibrarian_Email_TXF.getText().isEmpty()) {
    			M_email = CreateNewMemberPUPLibrarian_Email_TXF.getText();
    			m.put("M_email",M_email);
    		}
    		else M_email = null;
    		
    		if(!CreateNewMemberPUPLibrarian_PhoneNumber_TXF.getText().isEmpty()) {
    			M_phoneNumber = CreateNewMemberPUPLibrarian_PhoneNumber_TXF.getText();
    			m.put("M_phoneNumber", M_phoneNumber);
    		}
    		else M_phoneNumber = null;
    		
    		m.put("M_id", M_id);
    		m.put("M_pname",M_pname);
    		m.put("M_lname",M_lname );
    		m.put("M_graduateDate",GD );
    		m.put("M_password", M_password);
    		
    		if(Member.isValidInput(m).isEmpty())
    		{
    			client.sendToServer(m);
    		}
    		else //problem with input
    		{
    			System.out.println("here should be error msg with hashmap. ");
    			System.out.println("problem with input");
    		}
    	}
    }

	public void start(Stage arg0) throws Exception {
		final Modality WINDOW_MODAL; //makes it appear as pop up that has to be closed.
		this.Stage = new Stage();
		//Stage.initModality(WINDOW_MODAL);
		this.loader = new FXMLLoader();
		this.root = loader.load(getClass().getResource("/gui/Librarian_MainPage.fxml").openStream());
		this.scene = new Scene(root);			
		this.Stage.setScene(scene);
		this.Stage.showAndWait();
		
	}
	void setClient(Client clnt)
	{
		this.client = clnt;
	}
}
