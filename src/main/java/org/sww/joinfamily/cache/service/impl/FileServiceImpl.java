package org.sww.joinfamily.cache.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.sww.joinfamily.cache.service.FileService;

@Service
@Transactional(rollbackFor = Exception.class)
public class FileServiceImpl implements FileService {
	// private static final Logger log = LoggerFactory.getLogger(FileServiceImpl.class);
	@Autowired
	private RedisTemplate<String, Serializable> redisCacheTemplate;

	@Override
	public void saveToLocal(MultipartFile file, String fileType) {
	}
	@Override
	public void saveToRedis() {
		redisCacheTemplate.opsForValue().set("k1", "v1");
	}
	@Override
	public void saveToDB() {
		// TODO Auto-generated method stub
	}
}
