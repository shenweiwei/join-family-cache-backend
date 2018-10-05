package org.sww.joinfamily.cache.service.impl ;

import java.io.File ;
import java.io.IOException ;
import java.io.Serializable ;
import java.util.Set ;
import java.util.UUID ;
import org.slf4j.Logger ;
import org.slf4j.LoggerFactory ;
import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.stereotype.Service ;
import org.springframework.transaction.annotation.Transactional ;
import org.springframework.web.multipart.MultipartFile ;
import org.sww.joinfamily.cache.constants.RedisConstant ;
import org.sww.joinfamily.cache.exception.RedisException ;
import org.sww.joinfamily.cache.exception.RequestException ;
import org.sww.joinfamily.cache.service.FileService ;
import org.sww.joinfamily.cache.utils.RedisUtil ;

@Service
@Transactional(rollbackFor = Exception.class)
public class FileServiceImpl implements FileService {
	
	protected final static Logger	logger	= LoggerFactory.getLogger(FileServiceImpl.class) ;
	@Autowired
	private RedisUtil							redisUtil ;
	
	@Override
	public String savePictureToLocal(MultipartFile multipartfile, String folder, String fileType) {
		String fileId = UUID.randomUUID().toString().replaceAll("-", "").concat(fileType) ;
		try {
			String filePath = folder.concat("/".concat(fileId)) ;
			File file = new File(filePath) ;
			multipartfile.transferTo(file) ;
			return filePath ;
		} catch (IllegalStateException | IOException e) {
			throw new RequestException(e.getMessage(), e) ;
		}
	}
	
	@Override
	public void savePictureToRedis(MultipartFile file, String fileType, String filePath) {
		long result = 0 ;
		
		try {
			result = redisUtil.sSet(RedisConstant.PICTURE_UNSAVED_SET, filePath) ;
		} catch (io.lettuce.core.RedisException e) {
			throw new RedisException("INSERT TO REDIS ERROR") ;
		}
		
		if (result <= 0) throw new RedisException("INSERT TO REDIS ERROR") ;
	}
	
	@Override
	public void saveToDB() {
		Set<Serializable> unSavedSet = redisUtil.sGet(RedisConstant.PICTURE_UNSAVED_SET) ;
		
		for (Serializable serializable : unSavedSet) {
			// TODO SAVE TO DB
			
			long result = redisUtil.setRemove(serializable.toString()) ;
		}
		
	}
	
}
