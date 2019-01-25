package LibrarianGUI;
import java.io.IOException;
import MemberGUI.*;

import java.util.LinkedHashMap;
import MemberGUI.*;

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
public class RemoveBookPUPController {
	Client client;
	Stage stage;
	FXMLLoader loader;
	Pane root;
	Scene scene;
    
	public Client getClient() {
		return client;
	}

	public void setClient(Client clnt) {
		this.client = clnt;
	}

	@FXML
    private TextField RemoveBookPUP_BookName_TXF;

    @FXML
    private TextField RemoveBookPUP_AuthorName_TXF;

    @FXML
    private Label RemoveBookPUP_Done_LB;

    @FXML
    private Button RemoveBookPUP_Cancel_BTN;

    @FXML
    void RemoveBookPUP_DoneRemoveBook() throws IOException {
    	LinkedHashMap<String,Object> m = new LinkedHashMap<String,Object>();
    	Message msg;
    	m.put("Type", "remove book");
    	m.put("B_name", RemoveBookPUP_BookName_TXF.getText());
    	m.put("B_author",RemoveBookPUP_AuthorName_TXF.getText() );
    	msg = new Message(m);
    	client.sendToServer(msg);
    }

    @FXML
    void RemoveBookPUP_cancel() 
    {
    	Stage stage = (Stage) RemoveBookPUP_Done_LB.getScene().getWindow();
	    stage.close();
    }
    
public void start(Stage arg0) throws Exception {
		
		this.stage = new Stage();
		this.loader = new FXMLLoader();
		this.root = loader.load(getClass().getResource("/LibrarianGUI/RemoveBookPUP.fxml").openStream());
		this.scene = new Scene(root);	
		
		this.scene.getStylesheets().add(getClass().getResource("/gui/prototypeFXML.css").toExternalForm());
		this.stage.setScene(scene);
		this.stage.showAndWait();
	}

}
