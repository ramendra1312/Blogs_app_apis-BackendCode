package com.turbo.blog.services.imp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.turbo.blog.services.FileService;

@Service
public class FileServiceImp implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		// get file name
		String name = file.getOriginalFilename();
		// like--img.png
		String randomId = UUID.randomUUID().toString();
		String fileName1 = randomId.concat(name.substring(name.lastIndexOf(".")));

		// full path
		String filePath = path + File.separator + fileName1;

		// creating folder (if not)
		File f = new File(path);

		if (!f.exists()) {
			f.mkdir();
			// The mkdir() method stands for "make directory." It is a commonly used method

		}
		Files.copy(file.getInputStream(), Paths.get(filePath));

		return fileName1;

	}

	@Override
	public InputStream getResources(String path, String fileName) throws FileNotFoundException {
		String fullPath=path+File.separator+fileName;
		InputStream is=new FileInputStream(fullPath);
		//db logic  to return input strem

		return is;
	}

}
