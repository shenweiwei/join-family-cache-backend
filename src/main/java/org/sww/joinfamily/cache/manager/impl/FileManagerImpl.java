package org.sww.joinfamily.cache.manager.impl ;

import javax.validation.ValidationException ;
import org.slf4j.Logger ;
import org.slf4j.LoggerFactory ;
import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.scheduling.annotation.Async ;
import org.springframework.stereotype.Component ;
import org.springframework.web.multipart.MultipartFile ;
import org.sww.framework.transfer.http.dto.AsyncHttpDataTransferObject ;
import org.sww.joinfamily.cache.constants.FileTypeConstant ;
import org.sww.joinfamily.cache.constants.SystemConstant ;
import org.sww.joinfamily.cache.dto.request.FileRequestDTO ;
import org.sww.joinfamily.cache.manager.FileManager ;
import org.sww.joinfamily.cache.service.FileService ;
import org.sww.joinfamily.cache.service.FolderService ;

@Component
public class FileManagerImpl implements FileManager {
	
	protected static Logger	logger	= LoggerFactory.getLogger(FileManagerImpl.class) ;
	@Autowired
	private FileService			fileService ;
	
	@Autowired
	private FolderService		folderService ;
	
	@Async
	@Override
	public void upload(AsyncHttpDataTransferObject asyncHttpDataTransferObject) {
		MultipartFile file = ((FileRequestDTO) asyncHttpDataTransferObject.getInputDTO()).getFile() ;
		String fileName = file.getOriginalFilename() ;
		String fileType = fileName.substring(fileName.indexOf("."), fileName.length()) ;
		uploadByFileType(file, fileType) ;
		asyncHttpDataTransferObject.transferFinish() ;
	}
	
	/**
	 * 根据文件类型上传文件
	 * @author: Administrator
	 * @date: 2018年10月5日 下午9:04:53
	 * @Title: uploadByFileType
	 * @Description: TODO
	 * @param file
	 * @param fileType
	 * @throws IOException
	 * @return void
	 * @throws
	 */
	private void uploadByFileType(MultipartFile file, String fileType) {
		switch (fileType.replace(".", "").toUpperCase()) {
			case FileTypeConstant.PNG:
				this.uploadPicture(file, fileType) ;
				break ;
			default:
				throw new ValidationException("file type not support upload") ;
		}
	}
	
	/**
	 * 上传图片
	 * @author: Administrator
	 * @date: 2018年10月5日 下午9:05:36
	 * @Title: uploadPicture
	 * @Description: TODO
	 * @param file
	 * @param fileType
	 * @throws IOException
	 * @return void
	 * @throws
	 */
	private void uploadPicture(MultipartFile file, String fileType) {
		String floder = folderService.createFolder(SystemConstant.PICTURE) ;
		String filePath = fileService.savePictureToLocal(file, floder, fileType) ;
		fileService.savePictureToRedis(file, fileType, filePath) ;
		fileService.saveToDB();
	}
	
}
