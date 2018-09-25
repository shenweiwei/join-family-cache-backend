package org.sww.joinfamily.cache.manager.impl;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.sww.joinfamily.cache.constants.FileTypeConstant;
import org.sww.joinfamily.cache.manager.FileManager;
import org.sww.joinfamily.cache.service.FileService;

@Component
public class FileManagerImpl implements FileManager {
	@Autowired
	private FileService fileService;

	@Override
	public void upload(MultipartFile file) {
		String fileName = file.getOriginalFilename();
		String fileType = fileName.substring(fileName.indexOf("."), fileName.length());
		switch (fileType) {
			case FileTypeConstant.PNG:
				this.uploadPicture(file, fileType);
				break;
			default:
				throw new ValidationException("file type not support upload");
		}
	}
	private void uploadPicture(MultipartFile file, String fileType) {
		String filePath = fileService.saveToLocal(file, fileType);
		fileService.saveToRedis(file, fileType, filePath);
	}
}
