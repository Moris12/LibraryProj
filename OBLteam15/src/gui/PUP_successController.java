package gui;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PUP_successController {
	Stage Stage;
	FXMLLoader loader;
	Pane root;
	Scene scene;
	
    @FXML
    private Label PUPSucces_ActionNameSucces_LB;

    @FXML
    private Button PUPSucces_Cancel_BTN;

    @FXML
    void PUPSucces_ActionNameSucces() {

    }

    @FXML
    void PUPSucces_Cancel() 
    {
    	Stage stage = (Stage) PUPSucces_Cancel_BTN.getScene().getWindow();
	    stage.close();
    }
    public void SetSuccessMessage(String str)
    {
    	PUPSucces_ActionNameSucces_LB.setText(str);
    }
public void start(Stage arg0) throws Exception {
		
		this.Stage = new Stage();
		this.loader = new FXMLLoader();
		this.root = loader.load(getClass().getResource("/gui/PUP_succes.fxml").openStream());
		this.scene = new Scene(root);
		//css here
		this.Stage.setScene(scene);
		this.Stage.showAndWait();
	}

}
