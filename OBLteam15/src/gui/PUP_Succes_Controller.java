package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PUP_Succes_Controller {
	
    @FXML
    private Label PUPSucces_ActionNameSucces_LB;

    @FXML
    private Button PUPSucces_Cancel_BTN;
    
    private String ActionStr;

    @FXML
    void PUPSucces_ActionNameSucces() {

    }

    @FXML
    void PUPSucces_Cancel() {

    }

	public String getActionStr() {
		return ActionStr;
	}

	public void setActionStr(String actionStr) {
		ActionStr = actionStr;
	}

}
