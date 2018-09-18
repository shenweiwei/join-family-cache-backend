package org.sww.joinfamily.cache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sww.joinfamily.cache.service.SenderService;

@RestController
@RequestMapping("/sender")
public class SenderController {
	
	@Autowired
	private SenderService senderService;
	
	@GetMapping("/picture")
	public void pictureSender() {
		senderService.pictureSend();
	}
}
