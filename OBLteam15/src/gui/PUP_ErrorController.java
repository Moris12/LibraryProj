package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class PUP_ErrorController implements Initializable {
	
	Stage Stage;
	FXMLLoader loader;
	Pane root;
	Scene scene;
	@FXML
	private Label PUPError_ErrorReason_LB;
	@FXML
	private Button PUPError_Cancel_BTN;
	
	private String ErrorStr;
	
	public void start(Stage arg0) throws Exception {
		//For setting new Error message you need to set the String in setErrorStr function when you call for PUP_Error//
		Stage = new Stage();
		loader = new FXMLLoader();
		root = loader.load(getClass().getResource("/gui/PUP_Error.fxml").openStream());
		scene = new Scene(root);
		Stage.setScene(scene);
		Stage.showAndWait();
		
	}
    @FXML
    void PUPError_Cancel() {
    	this.Stage.close();

    }

    @FXML
    void PUPError_ErrorReason() {
    	this.PUPError_ErrorReason_LB.setText(ErrorStr);
    	

    }

	public String getErrorStr() {
		return ErrorStr;
	}

	public void setErrorStr(String errorStr) {
		this.ErrorStr = errorStr;
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.PUPError_ErrorReason();
	}

}
