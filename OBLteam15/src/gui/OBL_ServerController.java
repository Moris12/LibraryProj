package gui;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
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
import serverr.ServerController;
import client.Client;
import client.ClientConsole;

public class OBL_ServerController implements Initializable
{
    @FXML
    private TextField OblServer_Host_TXF;

    @FXML
    private TextField OblServer_Password_PSF;

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

    @FXML
    private Button OblServer_DisConnect_BTN;
    
	Stage Stage;
	FXMLLoader loader;
	Pane root;
	Scene scene;
	ServerController sc;
	
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
		sc = new ServerController();
		OblServer_Host_TXF.setText(sc.getHost());
		OblServer_Password_PSF.setText(sc.getPass());
		OblServer_UserName_TXF.setText(sc.getUsername());
		
		try {
			OblServer_MyIp_TXA.setText(sc.getIP());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		OblServer_MyPort_TXA.setText(sc.getPort());

		
		
	}
    @FXML
    void Obl_server_Cancel() {
    	this.OblServer_GeneralTextArea_TXA.setText("Asaf The King");

    }
    @FXML
    void Obl_server_DisConnect() {

    }

    @FXML
    void Obl_server_SetConnection() {
    	sc.setHost(OblServer_Host_TXF.getText());
    	sc.setPass(OblServer_Password_PSF.getText());
    	sc.setUser(OblServer_UserName_TXF.getText());
    	
		if(OblServer_DBexist_CBX.isSelected() == true)
		{
			sc.setDBexists(true);
		}
		else
		{
			sc.setDBexists(false);
		}
		if(sc.connectToDB()) OblServer_GeneralTextArea_TXA.setText("Connection Succesfuly");
		else OblServer_GeneralTextArea_TXA.setText("Connection Unsuccesfuly");	

    }

}
