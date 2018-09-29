package org.sww.joinfamily.cache.dto.request;

import org.springframework.web.multipart.MultipartFile;
import org.sww.framework.transfer.http.dto.HttpRequestDTO;

public class FileRequestDTO extends HttpRequestDTO {
	private MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
}
