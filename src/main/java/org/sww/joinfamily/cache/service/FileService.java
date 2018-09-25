package org.sww.joinfamily.cache.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author weiweiShen
 */
public interface FileService {
	
	/**
	 * 保存文件到本地磁盘
	 * @author: Administrator
	 * @date: 2018年9月25日 下午9:10:22
	 * @Title: saveToLocal
	 * @Description: TODO
	 * @param file
	 * @param fileType
	 * @return
	 * @return String
	 * @throws
	 */
	public String saveToLocal(MultipartFile file, String fileType);
	
	/**
	 * 保存文件到redis
	 * @author: Administrator
	 * @date: 2018年9月25日 下午9:10:14
	 * @Title: saveToRedis
	 * @Description: TODO
	 * @param file
	 * @param fileType
	 * @param filePath
	 * @return void
	 * @throws
	 */
	public void saveToRedis(MultipartFile file, String fileType, String filePath);
	public void saveToDB();
}
