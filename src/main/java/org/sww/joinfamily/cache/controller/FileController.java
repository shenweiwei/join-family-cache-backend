package org.sww.joinfamily.cache.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;
import org.sww.framework.transfer.http.builder.AsyncHttpDataTranObjectBuilder;
import org.sww.framework.transfer.http.dto.AsyncHttpDataTransferObject;
import org.sww.framework.transfer.http.dto.AsyncHttpResponseDTO;
import org.sww.joinfamily.cache.dto.request.FileRequestDTO;
import org.sww.joinfamily.cache.dto.response.FileResponseDTO;
import org.sww.joinfamily.cache.manager.FileManager;

/**
 * 文件服务接口
 * @author weiweiShen
 */
@RestController
@RequestMapping("/file")
public class FileController {
	protected static Logger logger = LoggerFactory.getLogger(FileController.class);
	@Autowired
	private FileManager fileManager;

	@PostMapping("/upload")
	public DeferredResult<AsyncHttpResponseDTO> upload(@RequestParam("file") MultipartFile file) {
		AsyncHttpDataTransferObject asyncHttpDataTransferObject = AsyncHttpDataTranObjectBuilder.builder(FileRequestDTO.class, FileResponseDTO.class).build();	
		fileManager.upload(initFileRequestDto(asyncHttpDataTransferObject, file));
		return asyncHttpDataTransferObject.getDeferredResult();
	}
	private AsyncHttpDataTransferObject initFileRequestDto(
			AsyncHttpDataTransferObject asyncHttpDataTransferObject, MultipartFile file) {
		FileRequestDTO fileRequestDto = new FileRequestDTO();
		fileRequestDto.setFile(file);
		asyncHttpDataTransferObject.setInputDTO(fileRequestDto);
		asyncHttpDataTransferObject.setInputWatch(true);
		return asyncHttpDataTransferObject;
	}
}
