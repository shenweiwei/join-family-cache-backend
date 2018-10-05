package org.sww.joinfamily.cache.service.impl ;

import java.io.File ;
import java.io.IOException ;
import javax.transaction.Transactional ;
import org.apache.commons.io.FileUtils ;
import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.stereotype.Service ;
import org.sww.joinfamily.cache.constants.SystemConstant ;
import org.sww.joinfamily.cache.exception.RequestException ;
import org.sww.joinfamily.cache.service.FolderService ;
import org.sww.joinfamily.cache.utils.DateUtil ;
import org.sww.joinfamily.cache.utils.SystemUtil ;

@Service
@Transactional(rollbackOn = Exception.class)
public class FolderServiceImpl implements FolderService {
	
	@Autowired
	private SystemUtil systemUtil ;
	
	@Override
	public String createFolder(String filePath) {
		String envFilePath ;
		switch (filePath) {
			case SystemConstant.PICTURE:
				envFilePath = systemUtil.getPicturePathBySysEnv() ;
				break ;
			default:
				envFilePath = systemUtil.getFilePathBySysEnv() ;
				break ;
		}
		String sysdateFolder = DateUtil.getCurrentDateString() ;
		String folder = envFilePath.concat(sysdateFolder) ;
		try {
			FileUtils.forceMkdir(new File(folder)) ;
		} catch (IOException e) {
			throw new RequestException(e.getMessage(), e) ;
		}
		
		return folder ;
	}
	
}
