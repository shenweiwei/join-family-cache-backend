package org.sww.joinfamily.cache.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.sww.joinfamily.cache.exception.RequestException;

public class DataTranObjectBuilder {
	protected static Logger logger = LoggerFactory.getLogger(DataTranObjectBuilder.class);
	private Class<?> ofType;

	public static DataTranObjectBuilder builder(Class<?> dto) {
		DataTranObjectBuilder dataTranObjectBuilder = new DataTranObjectBuilder();
		dataTranObjectBuilder.setOfType(dto);
		return dataTranObjectBuilder;
	}

	public DataTranObject build() {
		try {
			Assert.notNull(ofType, "The class must not be null");
			return (DataTranObject) ofType.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RequestException(e.getMessage(), e);
		}
	}
	
	public Class<?> getOfType() {
		return ofType;
	}
	public void setOfType(Class<?> ofType) {
		this.ofType = ofType;
	}
}
