package org.sww.joinfamily.cache.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.sww.joinfamily.cache.exception.RedisException;
import org.sww.joinfamily.cache.exception.RequestException;
import org.sww.joinfamily.cache.po.Picture;
import org.sww.joinfamily.cache.repository.PictureRepository;
import org.sww.joinfamily.cache.service.FileService;

@Service
@Transactional(rollbackFor = Exception.class)
public class FileServiceImpl implements FileService {

	protected final static Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

	@Autowired
	private PictureRepository pictureRepository;

	@Override
	public String savePictureToLocal(MultipartFile multipartfile, String folder, String fileType) {
		String fileId = UUID.randomUUID().toString().replaceAll("-", "").concat(fileType);
		try {
			String filePath = folder.concat("/".concat(fileId));
			File file = new File(filePath);
			multipartfile.transferTo(file);
			return filePath;
		} catch (IllegalStateException | IOException e) {
			throw new RequestException(e.getMessage(), e);
		}
	}

	@Override
	public void savePicture(MultipartFile file, String fileType, String filePath) {
		try {
			Picture result = pictureRepository
					.save(picture(filePath, file.getOriginalFilename(), fileType.substring(1, fileType.length())));
			if (ObjectUtils.isEmpty(result))
				throw new RedisException("INSERT TO REDIS ERROR");
		} catch (io.lettuce.core.RedisException e) {
			throw new RedisException(e.getMessage(), e);
		}

	}

	private Picture picture(String filePath, String fileName, String suffixName) {
		Picture result = new Picture();
		result.setFilePath(filePath);
		result.setName(fileName);
		result.setSuffixName(suffixName);

		return result;
	}

}
