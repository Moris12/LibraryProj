package gui;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SearchPUPStudent_NotsuccessController {
	Stage Stage;
	FXMLLoader loader;
	Pane root;
	Scene scene;
	
	
	    @FXML
	    private Label PUPError_ErrorReason_LB;
	    
	    @FXML
	    private Label SearchPUPstudentNotSCS_ClosestReturn__LB;


		@FXML
	    private Label SearchPUPstudentNotSCS_RegisterWaitingList__LB;

	    @FXML
	    private Label SearchPUPstudentNotSCS_ViewTBC__LB;

	    @FXML
	    private Button SearchPUPstudentNotSCS_Cancel_BTN;

	    public Label getSearchPUPstudentNotSCS_ClosestReturn__LB() {
			return SearchPUPstudentNotSCS_ClosestReturn__LB;
		}

		public void setSearchPUPstudentNotSCS_ClosestReturn__LB(String str) {
			SearchPUPstudentNotSCS_ClosestReturn__LB.setText(str); 
		}
		
	    @FXML
	    void SearchBookPUPStudentNotSucces_Cancel() {
	    	Stage stage = (Stage) SearchPUPstudentNotSCS_Cancel_BTN.getScene().getWindow();
		    stage.close();
	    }

	    @FXML
	    void SearchBookPUPStudentNotSucces_CloseReturnDate() {

	    }

	    @FXML
	    void SearchBookPUPStudentNotSucces_RegisterToWaitingList() {

	    }

	    @FXML
	    void SearchBookPUPStudentNotSucces_ViewTBC() {

	    }
	    public void start(Stage arg0) throws Exception {
			
			this.Stage = new Stage();
			this.loader = new FXMLLoader();
			this.root = loader.load(getClass().getResource("/gui/SearchPUPstudent_Notsuccess.fxml").openStream());
			this.scene = new Scene(root);	
			//this.scene.getStylesheets().add(getClass().getResource("/gui/prototypeFXML.css").toExternalForm());
			this.Stage.setScene(scene);
			this.Stage.showAndWait();
			
		}
}

