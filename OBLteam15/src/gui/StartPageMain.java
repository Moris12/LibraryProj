package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StartPageMain extends Application {
	
	Start_PageController StartPageController;

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		launch(args);
	}
	@Override
	public void start(Stage arg0) throws Exception {
		StartPageController = new Start_PageController();		
		StartPageController.start(arg0);
	}
}
