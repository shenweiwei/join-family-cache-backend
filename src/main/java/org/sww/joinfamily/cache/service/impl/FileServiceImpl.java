package org.sww.joinfamily.cache.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.sww.joinfamily.cache.exception.RedisException;
import org.sww.joinfamily.cache.exception.RequestException;
import org.sww.joinfamily.cache.service.FileService;
import org.sww.joinfamily.cache.utils.DateUtil;
import org.sww.joinfamily.cache.utils.RedisUtil;
import org.sww.joinfamily.cache.utils.SystemUtil;

@Service
@Transactional(rollbackFor = Exception.class)
public class FileServiceImpl implements FileService {
	protected final static Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private SystemUtil systemUtil;

	@Override
	public String saveToLocal(MultipartFile multipartfile, String fileType) {
		String fileId = UUID.randomUUID().toString().replaceAll("-", "").concat(fileType);
		try {
			String folder = this.createFolder(fileType, fileId);
			String filePath = folder.concat("/".concat(fileId));
			File file = new File(filePath);
			multipartfile.transferTo(file);
			return filePath;
		} catch (IllegalStateException | IOException e) {
			throw new RequestException(e.getMessage(), e);
		}
	}
	@Override
	public void saveToRedis(MultipartFile file, String fileType, String filePath) {
		boolean result = redisUtil.set(filePath, "");
		if (result)
			throw new RedisException("INSERT TO REDIS ERROR");
	}
	@Override
	public void saveToDB() {
		// TODO Auto-generated method stub
	}
	private String createFolder(String fileType, String fileId) throws IOException {
		String envFilePath = systemUtil.getFilePathBySysEnv();
		String sysdateFolder = DateUtil.getCurrentDateString();
		String folder = envFilePath.concat("/").concat(sysdateFolder);
		FileUtils.forceMkdir(new File(folder));
		return folder;
	}
}
