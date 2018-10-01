package org.sww.joinfamily.cache.controller;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.sww.framework.transfer.http.builder.AsyncHttpDataTranObjectBuilder;
import org.sww.framework.transfer.http.dto.AsyncHttpResponseDTO;
import org.sww.framework.transfer.http.dto.HttpDataTransferObject;
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

	@Async
	@PostMapping("/upload")
	public Future<AsyncHttpResponseDTO> upload(@RequestParam("file") MultipartFile file) throws Exception {
		HttpDataTransferObject httpDataTransferObject = AsyncHttpDataTranObjectBuilder
				.builder(FileRequestDTO.class, FileResponseDTO.class).build();
		
		fileManager.upload(initFileRequestDto(httpDataTransferObject, file));
		
		return ((AsyncHttpResponseDTO) httpDataTransferObject.getOutputDTO()).getFuture();
	}
	
	private HttpDataTransferObject initFileRequestDto(HttpDataTransferObject httpDataTransferObject,
			MultipartFile file) {
		FileRequestDTO fileRequestDto = new FileRequestDTO();
		fileRequestDto.setFile(file);
		httpDataTransferObject.setInputDTO(fileRequestDto);
		return httpDataTransferObject;
	}
}
