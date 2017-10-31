package com.grupo401.proyecto.Helpers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.imageio.ImageIO;

public final class ImageHelper {
	

	private static ImageHelper imageHelper;
	public static ImageHelper getInstance(){
		if(imageHelper == null){
			imageHelper = new ImageHelper();
		}
		return imageHelper;
	}
	
	public InputStream doGet(String imagePath) throws IOException {

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		File file = new File(imagePath);
		String fullPath = file.getAbsolutePath();
		Path path = FileSystems.getDefault().getPath(fullPath);
		byte[] content = Files.readAllBytes(path);
		InputStream stream = new ByteArrayInputStream(content);
		//BufferedImage img = ImageIO.read(in);
		
		//ImageIO.write(img,"png",bos);
		//byte[] content = bos.toByteArray();
		
		return stream;
		
	}
}