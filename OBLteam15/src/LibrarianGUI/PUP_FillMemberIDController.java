package LibrarianGUI;
import java.io.IOException;
import java.util.LinkedHashMap;
import MemberGUI.*;

import MemberGUI.Member;
import client.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Message;
import gui.*;
public class PUP_FillMemberIDController {
	Stage Stage;
	FXMLLoader loader;
	Pane root;
	Scene scene;
	Client client;

	public Client getClient() {
		return client;
	}

	public void setClient(Client clnt) {
		this.client = clnt;
	}
	
    @FXML
    private TextField PUPFillMemberID_MemberID_TXF;

    @FXML
    private Label PUPFillMemberID_Done_LB;

    @FXML
    private Button PUPFillMemberID_Cancel_BTN;

    @FXML
    void PUPFillMemberID_Cancel() 
    {
    	Stage stage = (Stage) PUPFillMemberID_Cancel_BTN.getScene().getWindow();
	    stage.close();
    }

    @FXML
    void PUPFillMemberID_Done() throws IOException 
    {
    	LinkedHashMap<String,Object> m = new LinkedHashMap<String, Object>();
    	Message msg;
    	m.put("Type", "view membership card");
    	m.put("M_id", PUPFillMemberID_MemberID_TXF.getText());
    	//INPUT CHECK. SPLIT CHECK OF MEMBER CLASS
    	m.put("Sender", "librarian");
    	
    	msg = new Message(m);
    	client.sendToServer(msg);
    }  	
    		

public void start(Stage arg0) throws Exception 
{
		this.Stage = new Stage();
		this.loader = new FXMLLoader();
		this.root = loader.load(getClass().getResource("/LibrarianGUI/PUP_FillMemberID.fxml").openStream());
		this.scene = new Scene(root);	
		//this.scene.getStylesheets().add(getClass().getResource("/gui/prototypeFXML.css").toExternalForm());
		this.Stage.setScene(scene);
		this.Stage.showAndWait();
}

}
