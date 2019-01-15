package gui;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Message;
import client.Client;
import client.ClientConsole;

public class OBL_ServerController implements Initializable
{
    @FXML
    private TextField OblServer_Host_TXF;

    @FXML
    private TextField OblServer_Password_TXF;

    @FXML
    private Button OblServer_ChangeConnection_BTN;

    @FXML
    private TextArea OblServer_MyIp_TXA;

    @FXML
    private TextArea OblServer_MyPort_TXA;

    @FXML
    private CheckBox OblServer_DBexist_CBX;

    @FXML
    private Button OblServer_Close_BTN;

    @FXML
    private TextField OblServer_UserName_TXF;

    @FXML
    private TextArea OblServer_GeneralTextArea_TXA;
    
	Stage Stage;
	FXMLLoader loader;
	Pane root;
	Scene scene;
	
	public void start(Stage arg0) throws Exception {
		
		this.Stage = new Stage();
		this.loader = new FXMLLoader();
		this.root = loader.load(getClass().getResource("/gui/Obl_Server.fxml").openStream());
		this.scene = new Scene(root);	
		this.scene.getStylesheets().add(getClass().getResource("/gui/prototypeFXML.css").toExternalForm());
		this.Stage.setScene(scene);
		this.Stage.showAndWait();
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
    @FXML
    void Obl_server_Cancel() {
    	this.OblServer_GeneralTextArea_TXA.setText("Asaf The King");

    }

    @FXML
    void Obl_server_SetConnection() {

    }

}
