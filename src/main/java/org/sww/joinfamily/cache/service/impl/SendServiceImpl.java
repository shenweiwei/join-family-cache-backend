package org.sww.joinfamily.cache.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.sww.joinfamily.cache.sender.PicutreSender;
import org.sww.joinfamily.cache.service.SenderService;

@Service
@Transactional(rollbackFor = Exception.class)
public class SendServiceImpl implements SenderService {

	@Autowired
	private PicutreSender picutreSender;

	@Override
	public void pictureSend() {
		picutreSender.send("hello world");
	}
}
