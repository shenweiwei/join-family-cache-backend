package org.sww.joinfamily.cache.dto.request;

import org.springframework.web.multipart.MultipartFile;
import org.sww.framework.transfer.http.dto.AsyncHttpRequestDTO;

public class FileRequestDTO extends AsyncHttpRequestDTO<FileRequestDTO> {
	private MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
}
