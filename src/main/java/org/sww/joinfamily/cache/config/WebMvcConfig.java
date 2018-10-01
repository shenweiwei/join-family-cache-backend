package org.sww.joinfamily.cache.config;

import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.sww.joinfamily.cache.utils.SystemUtil;

@Configuration
public class WebMvcConfig {
	@Autowired
	private SystemUtil systemUtil;
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setLocation(systemUtil.getPicturePathBySysEnv());
		return factory.createMultipartConfig();
	}
}
