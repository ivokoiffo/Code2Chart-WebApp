package com.grupo401.proyecto.githubHelper;

import java.io.BufferedReader ;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public final class FileHelper {
	
	private static FileHelper fileHelper;
	public static FileHelper getInstance(){
		if(fileHelper == null){
			fileHelper = new FileHelper();
		}
		return fileHelper;
	}
	
	
	public static String escribirEnFS(String dirUrl) throws IOException
		{	
			//set connection and url
			URL url = new URL(dirUrl);
	    	HttpURLConnection connection = (HttpURLConnection)url.openConnection();
	    	connection.setRequestMethod("GET");
	    	connection.setDoInput(true);
			
			String completo = new String();
			
			//read and store in my string variable the whole content
			BufferedReader reader = new BufferedReader ( new InputStreamReader(connection.getInputStream()));
			for (String line; (line = reader.readLine()) != null;) {
					completo = completo.concat(line);
			}
			reader.close();
		    
		    return completo;
	}
}
