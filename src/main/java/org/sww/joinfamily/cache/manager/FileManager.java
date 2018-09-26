package org.sww.joinfamily.cache.manager;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface FileManager {
	public void upload(MultipartFile file) throws IOException;
}
