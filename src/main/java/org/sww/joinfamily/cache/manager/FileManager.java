package org.sww.joinfamily.cache.manager;

import javax.validation.ValidationException;

import org.springframework.web.multipart.MultipartFile;
import org.sww.joinfamily.cache.exception.BusinessException;

public interface FileManager {
	public void upload(MultipartFile file, String fileType) throws BusinessException, ValidationException;
}
