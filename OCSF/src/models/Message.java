package models;

import java.util.HashMap;
import java.util.Map;

public class Message extends AbstractModel{
	final private static long  serialVersionUID = 1L;
	private HashMap<String,Object> Map;
	
	public Message(HashMap<String, Object> Map) {
		this.Map = Map;
	}
	//empty constructor
	public Message() {
		// TODO Auto-generated constructor stub
	}

	public Map<String,Object> getMap(){
		return Map;
	}

	public void setMap(HashMap<String, Object> Map) {
		this.Map = Map;
	}
}