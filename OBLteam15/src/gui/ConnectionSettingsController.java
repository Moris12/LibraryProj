package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConnectionSettingsController {
	private Stage stg;
	private FXMLLoader FxmlL;
	private Scene scn;
	private Pane rt;
	private String host;
	private int port;
	
	@FXML
	private TextField Host_TextField;
	@FXML
	private TextField Port_Number;
	@FXML 
	private Button BTN_done;
	
	public void start(Stage arg0) throws Exception {
		stg = new Stage();
	    stg.initModality(Modality.APPLICATION_MODAL);
		FxmlL = new FXMLLoader();
		rt = FxmlL.load(getClass().getResource("/gui/ConnectionSettingsFXML.fxml").openStream());
		scn = new Scene(rt);
		stg.setScene(scn);
		stg.showAndWait();
		
	}
	public void DoneBTNHandler()
	{
		port =  Integer.parseInt(Port_Number.getText());
		host = Host_TextField.getText();
		prototypeController.setHost(host);
		prototypeController.setPort(port);
		Stage stage = (Stage) BTN_done.getScene().getWindow();
	    // do what you have to do
	    stage.close();
	}
	
}
