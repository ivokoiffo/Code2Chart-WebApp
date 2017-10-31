package com.grupo401.proyecto.Helpers;

import java.io.File;
import java.io.IOException;
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
	
	public static byte[] doGet(String imagePath) throws IOException {

		//ByteArrayOutputStream bos = new ByteArrayOutputStream();
		File file = new File(imagePath);
		String fullPath = file.getAbsolutePath();
		Path path = FileSystems.getDefault().getPath(fullPath);
		byte[] content = Files.readAllBytes(path);
		/*FileInputStream in = new FileInputStream(fullPath);
		BufferedImage img = ImageIO.read(in);
		*//*
		ImageIO.write(img,"jpg",bos);*/
		//byte[] content = bos.toByteArray();
		
		return content;
		
	}
}