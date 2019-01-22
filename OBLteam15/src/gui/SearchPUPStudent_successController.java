package gui;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Message;

	public class SearchPUPStudent_successController {
		Stage Stage;
		FXMLLoader loader;
		Pane root;
		Scene scene;
		//String input=" ";
		Message msg;

	    @FXML
	    private Label SearchPUPstudent_Shelfnum_LB;

		@FXML
	    private Label SearchPUPstudent_BookLocation_LB;

	    @FXML
	    private Label SearchPUPstudent_ForViewTBC_LB;

	    @FXML
	    private Button SearchPUPstudent_Cancel_BTN;
	    
	    public Label getSearchPUPstudent_Shelfnum_LB() {
			return SearchPUPstudent_Shelfnum_LB;
		}

		public void setSearchPUPstudent_Shelfnum_LB(String str) {
			SearchPUPstudent_Shelfnum_LB.setText(str);
		}

	    @FXML
	    void SearchBookPUPStudentSucces_BookLocation() {

	    }

	    @FXML
	    void SearchBookPUPStudentSucces_ViewTBC() {

	    }

	    @FXML
	    void SearchBookPUPStudentSucces_cancel() {
	    	Stage stage = (Stage) SearchPUPstudent_Cancel_BTN.getScene().getWindow();
		    stage.close();
	    }
		public void start(Stage arg0) throws Exception {
			
			this.Stage = new Stage();
			this.loader = new FXMLLoader();
			this.root = loader.load(getClass().getResource("/gui/SearchPUPstudent_success.fxml").openStream());
			this.scene = new Scene(root);	
			//this.scene.getStylesheets().add(getClass().getResource("/gui/prototypeFXML.css").toExternalForm());
			this.Stage.setScene(scene);
			this.Stage.showAndWait();
			
		}

}

