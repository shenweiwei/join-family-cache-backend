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
	 * @Title: savePictureToLocal
	 * @Description: TODO
	 * @param file
	 * @param folder
	 * @param fileType
	 * @return
	 * @return String
	 * @throws
	 */
	public String savePictureToLocal(MultipartFile file, String folder, String fileType);
	
	/**
	 * 保存文件到redis和DB
	 * @author: Administrator
	 * @date: 2018年9月25日 下午9:10:14
	 * @Title: savePictureToRedis
	 * @Description: TODO
	 * @param file
	 * @param fileType
	 * @param filePath
	 * @return void
	 * @throws
	 */
	public void savePicture(MultipartFile file, String fileType, String filePath);
}
