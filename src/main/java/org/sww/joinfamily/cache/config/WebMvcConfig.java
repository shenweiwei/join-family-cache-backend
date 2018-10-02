package org.sww.joinfamily.cache.config;

import java.io.File;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebMvcConfig {
	// @Value("${}")
	// private String loaction;
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		// 文件最大
		factory.setMaxFileSize("30MB");
		// 设置总上传数据总大小
		factory.setMaxRequestSize("30MB");
		String location = System.getProperty("user.dir") + "/data/tmp";
		File tmpFile = new File(location);
		if (!tmpFile.exists()) {
			tmpFile.mkdirs();
		}
		factory.setLocation(location);
		return factory.createMultipartConfig();
	}
}
