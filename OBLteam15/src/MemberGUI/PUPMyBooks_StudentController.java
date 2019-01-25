package MemberGUI;
import client.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class PUPMyBooks_StudentController {
	
	Stage Stage;
	FXMLLoader loader;
	Pane root;
	Scene scene;
	Client client;
	    @FXML
	    private TextArea PUPstudentMyBook_ViewMyBooks_TXA;

	    @FXML
	    private TextField PUPstudentMyBook_NumBookExtension_TXF;

	    @FXML
	    private Button PUPstudentMyBook_FinishExtension_BTN;

	    @FXML
	    private Button PUPstudentMyBook_Cancel_BTN;

	    @FXML
	    void PUPMyBooks_Cancel() {
	    	closeThisWindow();
	    }
	    public void closeThisWindow()
	    {
	    	Stage stage = (Stage) PUPstudentMyBook_Cancel_BTN.getScene().getWindow();
		    stage.close();
	    }
	    
	    @FXML
	    void PUPMyBooks_FinishExtensionRequest() 
	    {
	    	
	    }

	    @FXML
	    void PUPMyBooks_ListOfMyBooks() {

	    }
	    public void start(Stage arg0) throws Exception {
			this.Stage = new Stage();
		    Stage.initModality(Modality.APPLICATION_MODAL);
			this.loader = new FXMLLoader();
			this.root = loader.load(getClass().getResource("/gui/PUP_MyBooks_Student.fxml").openStream());
			this.scene = new Scene(root);			
			this.Stage.setScene(scene);
			this.Stage.showAndWait();
		}
	    public void setClient(Client clnt)
		{
			this.client = clnt;
		}
}
