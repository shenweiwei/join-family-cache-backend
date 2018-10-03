package org.sww.joinfamily.cache.manager;

import org.sww.framework.transfer.http.dto.AsyncHttpDataTransferObject;
import org.sww.joinfamily.cache.dto.request.FileRequestDTO;
import org.sww.joinfamily.cache.dto.response.FileResponseDTO;

public interface FileManager {
	public void upload(AsyncHttpDataTransferObject<FileRequestDTO, FileResponseDTO> asyncHttpDataTransferObject) throws Exception;
}
