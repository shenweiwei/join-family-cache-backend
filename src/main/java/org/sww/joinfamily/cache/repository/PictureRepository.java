package org.sww.joinfamily.cache.repository ;

import org.springframework.data.jpa.repository.JpaRepository ;
import org.springframework.stereotype.Repository ;
import org.sww.joinfamily.cache.po.Picture ;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {
	
}
