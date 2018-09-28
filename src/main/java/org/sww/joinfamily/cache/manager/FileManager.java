package org.sww.joinfamily.cache.manager;

import java.io.IOException;

import org.sww.joinfamily.cache.dto.request.FileRequestDTO;

public interface FileManager {
	public void upload(FileRequestDTO fileRequestDto) throws IOException;
}
