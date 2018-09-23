package org.sww.joinfamily.cache.manager.impl;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.sww.joinfamily.cache.constants.FileTypeConstant;
import org.sww.joinfamily.cache.exception.BusinessException;
import org.sww.joinfamily.cache.manager.FileManager;
import org.sww.joinfamily.cache.service.FileService;

@Component
public class FileManagerImpl implements FileManager {
	@Autowired
	private FileService fileService;

	@Override
	public void upload(MultipartFile file, String fileType) throws BusinessException, ValidationException {
		switch (fileType) {
			case FileTypeConstant.PNG:
				this.uploadPicture(file, fileType);
				break;
			default:
				break;
		}
	}
	private void uploadPicture(MultipartFile file, String fileType) throws BusinessException, ValidationException {
		fileService.saveToLocal(file, fileType);
		fileService.saveToRedis();
	}
}
