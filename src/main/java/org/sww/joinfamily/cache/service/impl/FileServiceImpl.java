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
import org.sww.joinfamily.cache.po.Picture ;
import org.sww.joinfamily.cache.repository.PictureRepository ;
import org.sww.joinfamily.cache.service.FileService ;
import org.sww.joinfamily.cache.utils.RedisUtil ;

@Service
@Transactional(rollbackFor = Exception.class)
public class FileServiceImpl implements FileService {
	
	protected final static Logger	logger	= LoggerFactory.getLogger(FileServiceImpl.class) ;
	@Autowired
	private RedisUtil							redisUtil ;
	
	@Autowired
	private PictureRepository			pictureRepository ;
	
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
		try {
			long result = redisUtil.sSet(RedisConstant.PICTURE_UNSAVED_SET, filePath, file.getOriginalFilename(), fileType.substring(1, fileType.length())) ;
			
			if (result <= 0) throw new RedisException("INSERT TO REDIS ERROR") ;
		} catch (io.lettuce.core.RedisException e) {
			throw new RedisException(e.getMessage(), e) ;
		}
		
	}
	
	@Override
	public void saveToDB() {
		Set<Serializable> unSavedSet = redisUtil.sGet(RedisConstant.PICTURE_UNSAVED_SET) ;
		
		for (Serializable serializable : unSavedSet) {
			Object [] values = (Object []) serializable ;
			this.initPictureInfo(values) ;
			
			long deleteResult = redisUtil.setRemove(RedisConstant.PICTURE_UNSAVED_SET, serializable) ;
			if (deleteResult <= 0) throw new RedisException("DELETE TO REDIS ERROR") ;
			long addResult = redisUtil.sSet(RedisConstant.PICTURE_SAVED_SET, values) ;
			if (addResult <= 0) throw new RedisException("INSERT TO REDIS ERROR") ;
		}
	}
	
	private void initPictureInfo(Object [] values) {
		Picture picture = new Picture() ;
		picture.setFilePath(values[0].toString()) ;
		picture.setName(values[1].toString()) ;
		picture.setSuffixName(values[2].toString()) ;
		pictureRepository.save(picture) ;
	}
	
}
