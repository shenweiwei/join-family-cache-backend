package org.sww.joinfamily.cache.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.sww.joinfamily.cache.service.FileService;

@Service
@Transactional(rollbackFor = Exception.class)
public class FileServiceImpl implements FileService {
	@Autowired
	private RedisTemplate<String, Serializable> redisTemplate;
	
	@Autowired
	private LettuceConnectionFactory lettuceConnectionFactory;

	@Override
	public void saveToLocal(MultipartFile file, String fileType) {
	}
	@Override
	public void saveToRedis() {
		lettuceConnectionFactory.getClusterConfiguration();
		redisTemplate.opsForValue().set("k1", "v1");
	}
	@Override
	public void saveToDB() {
		// TODO Auto-generated method stub
	}
}
