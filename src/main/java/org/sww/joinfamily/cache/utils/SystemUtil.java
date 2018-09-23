package org.sww.joinfamily.cache.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.sww.joinfamily.cache.constants.SystemConstant;

@Component
public class SystemUtil {
	@Value("${file.path.windows}")
	private String windows;
	@Value("${file.path.linux}")
	private String linux;

	public String getFilePathBySysEnv() {
		String osName = System.getProperties().getProperty("os.name");
		switch (osName) {
			case SystemConstant.LINUX_SYS:
				return linux;
			default:
				return windows;
		}
	}
}
