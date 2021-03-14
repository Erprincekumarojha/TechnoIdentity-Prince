package com.technoidentity.agastya.model;
import java.util.Date;

import javax.annotation.Generated;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("TelemetryEvent")
@Component
public class TelemetryEvent {
	@Id
	private String deviceId; // PK
	private Date timestamp;
	private String telemetryKey;
	private String telemetryValue;

}
