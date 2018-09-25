package org.sww.joinfamily.cache.manager;

import org.springframework.web.multipart.MultipartFile;

public interface FileManager {
	public void upload(MultipartFile file);
}
