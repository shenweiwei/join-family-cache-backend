package org.sww.joinfamily.cache.dto.request;

import org.springframework.web.multipart.MultipartFile;
import org.sww.joinfamily.cache.dto.BaseDto;

public class FileRequestDto extends BaseDto<FileRequestDto>{
	private MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
}
