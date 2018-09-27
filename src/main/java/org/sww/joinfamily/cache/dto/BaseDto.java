package org.sww.joinfamily.cache.dto;

public abstract class BaseDto<Model> implements DataTranDto<Model> {

	@Override
	@SuppressWarnings("unchecked")
	public Model builder (){		
		return (Model)this;
	}
}
