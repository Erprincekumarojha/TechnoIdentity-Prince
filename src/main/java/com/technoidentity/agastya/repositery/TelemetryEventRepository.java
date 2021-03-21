package com.technoidentity.agastya.repositery;



import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.technoidentity.agastya.model.TelemetryEvent;

@Repository
public interface TelemetryEventRepository extends MongoRepository<TelemetryEvent, String> {
	
	List<TelemetryEvent> findByDeviceId(String deviceId);


}
