package com.grupo401.proyecto.Helpers;

import java.io.BufferedReader ;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.web.multipart.MultipartFile;

public class FileHelper {
	
	public String getContentFromGithub(String dirUrl) throws IOException
		{	
			//set connection and url
			URL url = new URL(dirUrl);
	    	HttpURLConnection connection = (HttpURLConnection)url.openConnection();
	    	connection.setRequestMethod("GET");
	    	connection.setDoInput(true);
			
			return this.contentToString(connection.getInputStream());
	}


	public String getContentFromMultipart(MultipartFile file) throws IOException {
		return this.contentToString(file.getInputStream());
	}
	
	private String contentToString(InputStream stream) throws IOException{
		
		String completo = new String();
		BufferedReader reader = new BufferedReader (new InputStreamReader(stream));
		for (String line; (line = reader.readLine()) != null;) {
				completo = completo.concat(line);
		}
		reader.close();
	    
	    return completo;
	}
}
