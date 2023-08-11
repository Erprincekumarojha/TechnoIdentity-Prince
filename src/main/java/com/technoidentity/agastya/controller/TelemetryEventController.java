package com.technoidentity.agastya.controller;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



import com.technoidentity.agastya.model.TelemetryEvent;

import com.technoidentity.agastya.repositery.TelemetryEventRepository;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/agastya")
public class TelemetryEventController {

    @Autowired
  private TelemetryEventRepository repositery;

     // Display All TelemetryEvent Object
    @GetMapping("/telemetry/select")
    public ResponseEntity<List<TelemetryEvent>> getAllTelemetryEvent( @RequestParam(value="i",required = false) String i) {
   try {

			List<TelemetryEvent> telemetryEvents = new ArrayList<TelemetryEvent>();

			repositery.findAll().forEach(telemetryEvents::add);
			System.out.println("value of i:"+i);
			if("_1".equals(i)) {
				throw new Exception();
			}
			if("_2".equals(i)) {
				telemetryEvents.removeAll(telemetryEvents);
			}
			
		
         if (telemetryEvents.isEmpty()) {

				return new ResponseEntity<List<TelemetryEvent>>(HttpStatus.NO_CONTENT);

			}

			return new ResponseEntity<List<TelemetryEvent>>(telemetryEvents, HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}



	// Display TelemetryEvent based on the Id

	@GetMapping("/telemetry/{id}")

	@Transactional(readOnly = true)

	public ResponseEntity<TelemetryEvent> getTelemetryEventById(@PathVariable("id") String deviceId) {

		Optional<TelemetryEvent> telemetryevent = repositery.findById(deviceId);

        if (telemetryevent.isPresent()) {

			return new ResponseEntity<TelemetryEvent>(telemetryevent.get(), HttpStatus.OK);

      } else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}

	}

 // Update command

	@PutMapping("/telemetry/{id}")

	public ResponseEntity<TelemetryEvent> updateTelemetryEvent(@PathVariable("id") String deviceId,

			@RequestBody TelemetryEvent telemetryevent) {

		java.util.Optional<TelemetryEvent> telemetryevent1 = repositery.findById(deviceId);

		Date d=new Date();

		if (telemetryevent1.isPresent()) {

			TelemetryEvent _tutorial = telemetryevent1.get();

			_tutorial.setDeviceId(telemetryevent.getDeviceId());

			_tutorial.setTimestamp(d);

			_tutorial.setTelemetryKey(telemetryevent.getTelemetryKey());

			_tutorial.setTelemetryValue(telemetryevent.getTelemetryValue());

			return new ResponseEntity<>(repositery.save(_tutorial), HttpStatus.OK);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}
}

// delete by ID

	@DeleteMapping("/telemetry/{id}")
  public ResponseEntity<String> deleteTelemetryEvent(@PathVariable("id") String deviceId) {

		try {
		  if(deviceId.equalsIgnoreCase("98")) {
			throw new Exception();  
		  }

			repositery.deleteById(deviceId);

			return new ResponseEntity<String>(deviceId + " Is Deleted.?", HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}
	public static void main(){
		System.out.println("Hello prince");
	}

// insert TelemetryEvent Data

	@PostMapping("/telemetry")

	public ResponseEntity<?> createTelemetryEvent(@RequestBody TelemetryEvent telemetryEvent) {

		try {
       if((telemetryEvent.getDeviceId()).equalsIgnoreCase("200")){
    	 throw new Exception();  
       }
			Date d=new Date();

			repositery.save(new TelemetryEvent(telemetryEvent.getDeviceId(),d ,

					telemetryEvent.getTelemetryKey(), telemetryEvent.getTelemetryValue()));

			return new ResponseEntity<String>("TelemetryEvent Data is inserted", HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}
	


}

