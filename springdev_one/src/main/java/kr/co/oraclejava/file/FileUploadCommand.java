package kr.co.oraclejava.file;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FileUploadCommand {
	private CommonsMultipartFile file;

	public CommonsMultipartFile getUpFile() {
		return file;
	}

	public void setUpFile(CommonsMultipartFile file) {
		this.file = file;
	}
	
}
