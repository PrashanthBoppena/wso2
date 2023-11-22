package com.knot;

import org.apache.synapse.MessageContext; 
import org.apache.synapse.mediators.AbstractMediator;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
public class RaptrCredentials extends AbstractMediator { 
	
	public static void main (String[] name){
		String str = "hey\u6366";
		try {
			byte[] charset = str.getBytes("UTF-8");
			String result = new String(charset, "UTF-8");
			System.out.println(result);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}

	public boolean mediate(MessageContext mc) { 
		// TODO Implement your mediation logic here 
		try {
			FileReader file = new FileReader("F:\\Integration studio\\work space\\raptrDXP\\PropertiesToJsonObj\\PropertiesToJsonObjRegistryResources\\raptrCredentials.properties");
			Properties prop = new Properties();
			prop.load(file);
			/*
			 if you want particular property from the file do the following..
			 
			String userID = new String(prop.getProperty("loginID"));
			
			set this value to the message context property
			
			mc.setProperty("loginID", userID); */
			
			// following code is for get entire file to json obj as key value pair..
			
			JSONObject jo = new JSONObject();
			
			for(String key :prop.stringPropertyNames()) {
				String val = prop.getProperty(key);
				jo.put(key,val);
				
				
			}
			//log.info(jo);
			
			String credentials = new String(jo.toString());
			mc.setProperty("cred", credentials);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}
