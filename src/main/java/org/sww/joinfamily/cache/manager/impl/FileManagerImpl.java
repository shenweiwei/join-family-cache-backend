package org.sww.joinfamily.cache.manager.impl;

import java.io.File;
import java.io.IOException;

import javax.validation.ValidationException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.sww.framework.transfer.http.dto.HttpDataTransferObject;
import org.sww.joinfamily.cache.constants.FileTypeConstant;
import org.sww.joinfamily.cache.constants.SystemConstant;
import org.sww.joinfamily.cache.dto.request.FileRequestDTO;
import org.sww.joinfamily.cache.manager.FileManager;
import org.sww.joinfamily.cache.service.FileService;
import org.sww.joinfamily.cache.utils.DateUtil;
import org.sww.joinfamily.cache.utils.SystemUtil;

@Component
public class FileManagerImpl implements FileManager {
	protected static Logger logger = LoggerFactory.getLogger(FileManagerImpl.class);
	
	@Autowired
	private FileService fileService;
	@Autowired
	private SystemUtil systemUtil;

	@Override
	public void upload(HttpDataTransferObject httpDataTransferObject) throws Exception {
		MultipartFile file = ((FileRequestDTO) httpDataTransferObject.getInputDTO()).getFile();
		String fileName = file.getOriginalFilename();
		String fileType = fileName.substring(fileName.indexOf("."), fileName.length());
		switch (fileType.replace(".", "").toUpperCase()) {
			case FileTypeConstant.PNG:
				this.uploadPicture(file, fileType);
				break;
			default:
				throw new ValidationException("file type not support upload");
		}
	}
	private void uploadPicture(MultipartFile file, String fileType) throws IOException {
		String floder = createFolder(SystemConstant.PICTURE);
		String filePath = fileService.savePictureToLocal(file, floder, fileType);
		fileService.savePictureToRedis(file, fileType, filePath);
	}
	private String createFolder(String filePath) throws IOException {
		String envFilePath;
		switch (filePath) {
			case SystemConstant.PICTURE:
				envFilePath = systemUtil.getPicturePathBySysEnv();
				break;
			default:
				envFilePath = systemUtil.getFilePathBySysEnv();
				break;
		}
		String sysdateFolder = DateUtil.getCurrentDateString();
		String folder = envFilePath.concat(sysdateFolder);
		FileUtils.forceMkdir(new File(folder));
		return folder;
	}
}
