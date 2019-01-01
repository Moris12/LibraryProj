package gui;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Message;
import ConsoleAndServer.*;
import client.Client;
import client.ClientConsole;
public class prototypeController implements Initializable{
	ConnectionSettingsController CSC; 
	Stage Stage;
	FXMLLoader loader;
	Pane root;
	Scene scene;
	Client client;
	static public String Hostt = "192.168.1.78";
	static public int Portt = 5555;
	
	@FXML
	private Button ConnectionSettingsBTN;
	@FXML
	private Button update_student_status_BTN;
	@FXML
	private Button Student_tn_id;
	@FXML
	private Button close_obl_system_window_btn;
	@FXML
	private Button Connect_btn;
	@FXML
	private Label Fill_Student_ID;
	@FXML
	private Label OBL_label;
	@FXML
	private Label Fill_Status;
	@FXML
	private TextField fillStudent_ID_TXTFLD;
	@FXML
	private TextField update_status_TXTFLD;
	@FXML
	private TextArea output_area_TXTAREA;
	
	
	
	public void closeWin()
	{
		System.exit(1);
	}
	
	public void setStudentDetailsHandler()
	{
		
		if(!(getIDFromTxtFld().isEmpty()))
		{
		//search DB func for member details
			String ID = "Search ";
		ID += getIDFromTxtFld();
		try {
			client.sendToServer(ID);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Thread.currentThread().sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		print_output_area(client.getToDisplay());
		}
		else print_output_area("Empty ID field");
	}
	
		@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}
	
	public void updateStudentStatus()
	{
		if(!(getIDFromTxtFld().isEmpty()))
		{
			if(!(getNewStatusTxtFld().isEmpty()))
			{
				String newStatus = update_status_TXTFLD.getText();
				if(newStatus.equals("NotRegistered") || newStatus.equals("Locked") || newStatus.equals("Frozen") || newStatus.equals("Active"))
				{
			    //func to DB
					String todo = new String("UpdateStatus ");
					todo+=getIDFromTxtFld() + " ";
					todo+=getNewStatusTxtFld();
					System.out.println(todo);
					
					try {
						System.out.println("line before clinet.sendtoServer()");
						client.sendToServer(todo);
						System.out.println("line after clinet.sendtoServer()");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					print_output_area("Successful update");
				}
			//end if
				else print_output_area("Illegal Status input");
			}
			else print_output_area("No Status Input");
		}
		else print_output_area("No ID input");
	    
	}
		
	public void start(Stage arg0) throws Exception {
		
		this.Stage = new Stage();
		this.loader = new FXMLLoader();
		this.root = loader.load(getClass().getResource("/gui/prototypeFXML.fxml").openStream());
		this.scene = new Scene(root);			
		this.Stage.setScene(scene);
		this.Stage.showAndWait();
		
	}
	
	public void setConnection() throws IOException
	{
		this.client = new Client(this.Hostt, this.Portt);
		System.out.println("Client has been created");
		this.client.openConnection();
		
		
		print_output_area("Connection successful");
	}
		
	public void print_output_area(String msg)
	{
		output_area_TXTAREA.setText(msg);
	}
	
	public String getIDFromTxtFld()
	{
		return fillStudent_ID_TXTFLD.getText();
	}
	
	public String getNewStatusTxtFld()
	{
		return update_status_TXTFLD.getText();
	}
	
	public void ConnectionSettingsHandler(ActionEvent event) throws Exception
	{
		CSC = new ConnectionSettingsController();
		CSC.start(null);
	}
	
	static void setHost(String newHost)
	{
		Hostt = newHost;
	}
	
	static void setPort(int newPort)
	{
		Portt = newPort;
	}
}
