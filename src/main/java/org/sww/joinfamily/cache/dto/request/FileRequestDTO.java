package org.sww.joinfamily.cache.dto.request;

import org.springframework.web.multipart.MultipartFile;
import org.sww.joinfamily.cache.dto.RequestDTO;

public class FileRequestDTO extends RequestDTO{
	private MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
}
