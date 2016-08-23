package com.platform.iot.dao;

import org.springframework.data.repository.CrudRepository;
import com.platform.iot.model.Tag;

/**
 * Created by ioan.vranau on 4/27/2016.
 */
public interface TagRepository extends CrudRepository<Tag, Long> {
}
