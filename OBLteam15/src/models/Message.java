package models;

import java.util.HashMap;
import java.util.Map;

public class Message extends AbstractModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID =3L;
	private Map<String,Object> Map;
	
	public Message(Map<String,Object> Map) {
		this.Map = Map;
	}
	
	public Map<String,Object> getMap(){
		return Map;
	}

	public void setMap(Map<String,Object> Map) {
		this.Map = Map;
	}
}
