package HoangLong.MyShoes.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

public class FileStore {
	public static String UPLOAD_FOLDER = "D:/Code Reactjs/myshoes-ui/src/assets/image/";// lay thu muc hien tai cua project de luu
	
	// luu file va return ve ten hinh anh upload 
	public static String getFileName(MultipartFile multipartFile, String prefix) {
		if(multipartFile != null && !multipartFile.isEmpty()) {
			try {
				int index = multipartFile.getOriginalFilename().lastIndexOf(".");
				String ext = multipartFile.getOriginalFilename().substring(index);
				String image = prefix + UUID.randomUUID().toString() + ext;
				
				File file = new File(UPLOAD_FOLDER + image);
				
				multipartFile.transferTo(file);
				
				return image;
			} catch (Exception e) {
				
			}
		}
		return null;
	}
	
	// luu nhieu file va return ve list ten hinh anh upload 
	public static List<String> getFileNames(List<MultipartFile> multipartFiles, String prefix) {
		List<String> images = new ArrayList<String>();
		if(multipartFiles != null) {
			for (MultipartFile multipartFile : multipartFiles) {
				if(multipartFile != null && !multipartFile.isEmpty()) {
					try {
						int index = multipartFile.getOriginalFilename().lastIndexOf(".");
						String ext = multipartFile.getOriginalFilename().substring(index);
						String image = prefix + UUID.randomUUID().toString() + ext;
							
						File file = new File(UPLOAD_FOLDER + image);
							
						multipartFile.transferTo(file);
						images.add(image);
					} catch (Exception e) {
							
					}
				}
			}
		}
				
		return images;
	}
	
	// xoa 1 file 
	public static void deleteFile(String filename) {
		if(filename != null) {
			File file = new File(UPLOAD_FOLDER + filename);
			if(file.exists()) {
				file.delete();
			}
		}
	}
	// xoa nhieu file
	public static void deleteFiles(List<String> filenames) {
	    if(filenames != null) {
	    	for (String filename : filenames) {
	    		if(filename != null) {
	    			File file = new File(UPLOAD_FOLDER + filename);
	    			if(file.exists()) {
	    				file.delete();
	    			}
	    		}
			}
	    }	
	}
	
	// download file
	public static void downloadFile(String filename, HttpServletResponse response) throws IOException {
		File file = new File(UPLOAD_FOLDER+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"static"+File.separator+"image"+File.separator+filename);
		
		Files.copy(file.toPath(), response.getOutputStream());	
	}
}
