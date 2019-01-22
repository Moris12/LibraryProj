package models;

import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashMap;
public class Message extends AbstractModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID =3L;
	//private LinkedMap<String,Object> Map;
	LinkedHashMap<String, Object> map = new LinkedHashMap<String,Object>(); 
	private String Key;
	private Object Value;

	public Message(LinkedHashMap<String,Object> map) {
		this.map = map;
	}
	
	public Object getMap(){
		return this.map;
	}

}
