package com.ruderu.mediarecord.repo;

import com.ruderu.mediarecord.entity.RanobePublisher;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RanobePublisherRepo extends CrudRepository<RanobePublisher, Long> {

    List<RanobePublisher> findByRanobeId(long ranobeId);
}
