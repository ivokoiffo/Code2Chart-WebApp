package com.grupo401.proyecto.Helpers;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public final class ImageHelper {
	

	private static ImageHelper imageHelper;
	public static ImageHelper getInstance(){
		if(imageHelper == null){
			imageHelper = new ImageHelper();
		}
		return imageHelper;
	}
	
	public InputStream doGet(String imagePath) throws IOException {

		File file = new File(imagePath);
		String fullPath = file.getAbsolutePath();
		Path path = FileSystems.getDefault().getPath(fullPath);
		byte[] content = Files.readAllBytes(path);
		return new ByteArrayInputStream(content);		
	}
}