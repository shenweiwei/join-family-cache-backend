package org.sww.joinfamily.cache.manager;

import java.io.IOException;

import org.sww.joinfamily.cache.dto.DataTranDto;
import org.sww.joinfamily.cache.dto.request.FileRequestDto;

public interface FileManager {
	public void upload(DataTranDto<FileRequestDto> fileRequestDto) throws IOException;
}
