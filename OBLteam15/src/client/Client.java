package client;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import ocsf.client.AbstractClient;
import ocsf.server.AbstractServer;

public class Client extends AbstractClient {

	private int port;
	
	private String host;
	
	private ObjectOutputStream output;
	
	private ObjectInputStream input;
	
	private String ToDisplay;
	
	final public static int DEFAULT_PORT = 5555;
	
	public Client(String hostt, int port) throws IOException {
		super(hostt,port);
		this.host = hostt;
		if(port == 0) {
			this.port = DEFAULT_PORT;
		}
		this.input = super.input;
		this.setOutput(super.output);
		this.port = port;
	}
	
	@Override
	protected void handleMessageFromServer(Object msg) {
		//this method actually gets the info from the server. we can do 
		//casting to whatever data type we want. as long as it matches what the server sent
		this.ToDisplay = (String)msg;
	}
	
	public String getToDisplay() {
		return this.ToDisplay;
	}
	
	public void setInput(ObjectInputStream inp) {
		this.input = inp;
	}
	public void setOutput(ObjectOutputStream out) {
		this.output = out;
	}
	


	public ObjectOutputStream getOutput() {
		return output;
	}




	  
	  
	  
	  
	  
	
	
}
