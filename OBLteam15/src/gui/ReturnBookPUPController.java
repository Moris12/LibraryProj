package gui;

import java.io.IOException;
import java.util.LinkedHashMap;

import client.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Message;

public class ReturnBookPUPController {
	
	Client client;
	Stage Stage;
	FXMLLoader loader;
	Pane root;
	Scene scene;
	
    @FXML
    private TextField ReturnBookPUP_BookName_TXF;

    @FXML
    private TextField ReturnBookPUP_MemberID_TXF;

    @FXML
    private Label ReturnBookPUP_Done_LB;

    @FXML
    private Button ReturnBookPUP_Cancel_BTN;

    @FXML 
    private TextField ReturnBookPUP_AuthorName_TXF;
    
    @FXML
    void ReturnBookPUP_Cancel() {
		Stage stage = (Stage) ReturnBookPUP_Cancel_BTN.getScene().getWindow();
		stage.close();
    }

    @FXML
    void ReturnBookPUP_DoneReturnBook() throws IOException {
    	String B_name = ReturnBookPUP_BookName_TXF.getText();
    	String M_id = ReturnBookPUP_MemberID_TXF.getText();
    	String B_author = ReturnBookPUP_AuthorName_TXF.getText();
    	LinkedHashMap<String, Object> map = new LinkedHashMap<String,Object>();
    	Message msg;
    	map.put("Type" ,"return book");
    	map.put("M_ID", M_id);
    	map.put("B_name", B_name);
    	map.put("B_author", B_author);
    	msg = new Message(map);
    	
    	client.sendToServer(map);
    	
    	
    	//send to server here.
    	
    	
    	
    }

	public void start(Stage arg0) throws Exception {
		final Modality WINDOW_MODAL = null; //makes it appear as pop up that has to be closed.
		this.Stage = new Stage();
		Stage.initModality(WINDOW_MODAL);
		this.loader = new FXMLLoader();
		this.root = loader.load(getClass().getResource("/gui/ReturnBookPUP.fxml").openStream());
		this.scene = new Scene(root);			
		this.Stage.setScene(scene);
		this.Stage.showAndWait();
		
	}
	void setClient(Client clnt)
	{
		this.client = clnt;
	}
}
