package org.sww.joinfamily.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class JfcacheApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(JfcacheApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(JfcacheApplication.class);
	}

}
