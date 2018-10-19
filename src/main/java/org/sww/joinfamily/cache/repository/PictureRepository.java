package org.sww.joinfamily.cache.repository ;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository ;
import org.sww.joinfamily.cache.po.Picture ;

@Repository
public interface PictureRepository extends CrudRepository<Picture, Long> {
	
}
