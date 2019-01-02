package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class prototypeMain extends Application{
	prototypeController prototypeController;
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		 launch(args);	
	}
	@Override
	public void start(Stage arg0) throws Exception {
		prototypeController = new prototypeController();		
		prototypeController.start(arg0);
	}
	
}
