package org.sww.joinfamily.cache.manager;

import java.io.IOException;

import org.sww.framework.transfer.http.dto.HttpDataTransferObject;

public interface FileManager {
	public void upload(HttpDataTransferObject httpDataTransferObject) throws IOException;
}
