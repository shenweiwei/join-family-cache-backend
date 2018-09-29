package org.sww.joinfamily.cache.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.sww.framework.transfer.http.builder.HttpDataTranObjectBuilder;
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

	@PostMapping("/upload")
	public void upload(@RequestParam("file") MultipartFile file) throws IOException {
		fileManager.upload(this.initFileRequestDto(file));
	}
	private HttpDataTransferObject initFileRequestDto(MultipartFile file) {
		HttpDataTransferObject httpDataTransferObject = HttpDataTranObjectBuilder
				.builder(FileRequestDTO.class, FileResponseDTO.class).build();
		FileRequestDTO fileRequestDto = new FileRequestDTO();
		fileRequestDto.setFile(file);
		httpDataTransferObject.setHttpRequestDTO(fileRequestDto);
		return httpDataTransferObject;
	}
}
