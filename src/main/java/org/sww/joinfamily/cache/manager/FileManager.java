package org.sww.joinfamily.cache.manager;

import org.sww.framework.transfer.http.dto.AsyncHttpDataTransferObject;

public interface FileManager {
	public void upload(AsyncHttpDataTransferObject asyncHttpDataTransferObject) throws Exception;
}
