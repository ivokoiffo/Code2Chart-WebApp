package com.grupo401.proyecto.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApkController {
	
	
	@RequestMapping(value="/api/getApk", method=RequestMethod.GET, 
			produces="application/vnd.android.package-archive")
	public @ResponseBody byte[] generateDiagram() throws IOException{
				
			
		try {
			
			InputStream inputStream = ClassLoader.getSystemResourceAsStream("test.apk");
			/*File apk = new File("test.apk");
			String path = apk.getAbsolutePath();
			Path p = FileSystems.getDefault().getPath(path);
			byte[] bytes2 = Files.readAllBytes(p);*/
			return IOUtils.toByteArray(inputStream);
		} catch (Exception e) {
			return null;
		}
	}
}
