package org.sww.joinfamily.cache.repository.redis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.sww.joinfamily.cache.po.redis.PictureSaved;

@Repository
public interface PicutreSavedRepository extends JpaRepository<PictureSaved, Long> {

}
