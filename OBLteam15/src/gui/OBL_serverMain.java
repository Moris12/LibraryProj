package gui;

import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.application.Application;

public class OBL_serverMain extends Application {

	OBL_ServerController OBL_ServerController;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 launch(args);	
		

	}

	@Override
	public void start(Stage arg0) throws Exception {
		OBL_ServerController = new OBL_ServerController();		
		OBL_ServerController.start(arg0);
	}
}
