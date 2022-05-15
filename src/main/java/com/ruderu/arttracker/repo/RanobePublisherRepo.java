package com.ruderu.arttracker.repo;

import com.ruderu.arttracker.entity.ranobe.RanobePublisher;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RanobePublisherRepo extends CrudRepository<RanobePublisher, Long> {

    List<RanobePublisher> findByRanobeId(long ranobeId);
}
