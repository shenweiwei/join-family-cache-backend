package org.sww.joinfamily.cache.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author weiweiShen
 */
public interface FileService {
	
	/**
	 * 保存文件到本地磁盘
	 * 
	 * @author: Administrator
	 * @date: 2018年9月23日 下午1:17:58
	 * @Title: saveToLocal
	 * @Description: TODO
	 * @param file
	 * @param fileType
	 * @return void
	 * @throws
	 */
	public void saveToLocal(MultipartFile file, String fileType);
	public void saveToRedis();
	public void saveToDB();
}
