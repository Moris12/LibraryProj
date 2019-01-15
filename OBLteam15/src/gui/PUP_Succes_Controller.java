package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PUP_Succes_Controller {
	Stage Stage;
	FXMLLoader loader;
	Pane root;
	Scene scene;
    @FXML
    private Label PUPSucces_ActionNameSucces_LB;

    @FXML
    private Button PUPSucces_Cancel_BTN;
    
    private String ActionStr;

	public void start(Stage arg0) throws Exception {
		Stage = new Stage();
		loader = new FXMLLoader();
		root = loader.load(getClass().getResource("/gui/PUP_Error.fxml").openStream());
		scene = new Scene(root);
		Stage.setScene(scene);
		Stage.showAndWait();
		
	}
	
    @FXML
    void PUPSucces_ActionNameSucces() {
    	this.PUPSucces_ActionNameSucces_LB.setText(ActionStr);
    }

    @FXML
    void PUPSucces_Cancel() {
    	this.Stage.close();

    }

	public String getActionStr() {
		return ActionStr;
	}

	public void setActionStr(String actionStr) {
		ActionStr = actionStr;
	}

}
