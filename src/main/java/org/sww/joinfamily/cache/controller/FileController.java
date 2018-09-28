package org.sww.joinfamily.cache.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.sww.joinfamily.cache.dto.DataTranObjectBuilder;
import org.sww.joinfamily.cache.dto.request.FileRequestDTO;
import org.sww.joinfamily.cache.manager.FileManager;

/**
 * 文件服务接口
 * @author weiweiShen
 */
@RestController
@RequestMapping("/file")
public class FileController {
	@Autowired
	private FileManager fileManager;

	@PostMapping("/upload")
	public void upload(@RequestParam("file") MultipartFile file) throws IOException {
		fileManager.upload(this.initFileRequestDto(file));
	}
	
	private FileRequestDTO initFileRequestDto(MultipartFile file) {
		FileRequestDTO fileRequestDto = (FileRequestDTO) DataTranObjectBuilder.builder(FileRequestDTO.class).build();
		fileRequestDto.setFile(file);
		return fileRequestDto;
	}
}
