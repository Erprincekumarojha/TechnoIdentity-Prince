package com.technoidentity.agastya;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.technoidentity.agastya.model.TelemetryEvent;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-local.yml")
@TestMethodOrder(OrderAnnotation.class)
class AgastyaServiceApplicationTests {

	@Autowired
	private MockMvc mvc;
	

	@Test
	void contextLoads() {
	}

	@Test
	@Order(1)
	public void getAllTelemetryEventsTest() throws Exception {

		MvcResult result = mvc.perform(get("/agastya/telemetry/select")).andReturn();
		MockHttpServletRequest req=result.getRequest();
		 
		MockHttpServletResponse resp = result.getResponse();
		assertNotNull(resp.getContentAsString());
		assertEquals(HttpStatus.OK.value(), resp.getStatus());
		
}
	@Test
	@Order(14)
	public void getAllTelemetryEventsTest2() throws Exception {

		MvcResult result = mvc.perform(get("/agastya/telemetry/select?i=_1")).andReturn();
		MockHttpServletRequest req=result.getRequest();
		 
		MockHttpServletResponse resp = result.getResponse();
		assertNotNull(resp.getContentAsString());
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), resp.getStatus());
		
		
	}
	
	@Test
	@Order(15)
	public void getAllTelemetryEventsTest3() throws Exception {

		MvcResult result = mvc.perform(get("/agastya/telemetry/select?i=_2")).andReturn();
		MockHttpServletRequest req=result.getRequest();
		 
		MockHttpServletResponse resp = result.getResponse();
		assertNotNull(resp.getContentAsString());
		assertEquals(HttpStatus.NO_CONTENT.value(), resp.getStatus());
		
		
	}
	
	


	@Test
	@Order(2)
	public void getTelemetryEventByIdTest() throws Exception {
		MvcResult result = mvc.perform(get("/agastya/telemetry/2")).andReturn();
		MockHttpServletResponse resp = result.getResponse();
		assertEquals(HttpStatus.OK.value(), resp.getStatus());
		assertNotNull(resp.getContentAsString());
		
	}
	@Test
	@Order(10)
	public void getTelemetryEventByIdTest2() throws Exception {
		MvcResult result = mvc.perform(get("/agastya/telemetry/_100")).andReturn();
		MockHttpServletResponse resp = result.getResponse();
		assertEquals(HttpStatus.NOT_FOUND.value(), resp.getStatus());
		assertNotNull(resp.getContentAsString());
		
	}

	
	@Test
	@Order(3)
	public void createTelemetryEventTest() throws Exception {
 
	
		MvcResult result = mvc
				.perform(post("/agastya/telemetry")
						.header("Content-Type", "application/json")
						//.contentType(MediaType.APPLICATION_JSON)
						.content("{\"deviceId\":\"14\",\"timestamp\":null,\"telemetryKey\":\"Krishna\",\"telemetryValue\":\"Joti\"}"))
				.andReturn();
		MockHttpServletResponse resp = result.getResponse();
		assertNotNull(resp.getContentAsString());
		assertEquals(HttpStatus.OK.value(), resp.getStatus());
      
	}
	

     @Test
     @Order(4)
     public void updateTelemetryEvent() throws Exception {
    	 MvcResult result=mvc.perform(put("/agastya/telemetry/14")
    			 .contentType(MediaType.APPLICATION_JSON)
    			 .content("{\"deviceId\":\"14\",\"timestamp\":null,\"telemetryKey\":\"Krishna\",\"telemetryValue\":\"Joti\"}"))
    			 .andReturn();
    	 MockHttpServletResponse resp = result.getResponse();
 		assertNotNull(resp.getContentAsString());
 		assertEquals(HttpStatus.OK.value(), resp.getStatus());
    	 
     }
     @Test
     @Order(11)
     public void updateTelemetryEvent2() throws Exception {
    	 MvcResult result=mvc.perform(put("/agastya/telemetry/11")
    			 .contentType(MediaType.APPLICATION_JSON)
    			 .content("{\"deviceId\":\"14\",\"timestamp\":null,\"telemetryKey\":\"Krishna\",\"telemetryValue\":\"Joti\"}"))
    			 .andReturn();
    	 MockHttpServletResponse resp = result.getResponse();
 		assertNotNull(resp.getContentAsString());
 		assertEquals(HttpStatus.NOT_FOUND.value(), resp.getStatus());
    	 
     }
    @Test
    @Order(5)
 	public void deleteTelemetryEventTest() throws Exception {
 		MvcResult result = mvc.perform(delete("/agastya/telemetry/14")).andReturn();
 		MockHttpServletResponse resp = result.getResponse();
 		assertEquals(HttpStatus.OK.value(), resp.getStatus());
 		assertNotNull(resp.getContentAsString());
 		
 	}
    @Test
    @Order(12)
 	public void deleteTelemetryEventTest2() throws Exception {
 		MvcResult result = mvc.perform(delete("/agastya/telemetry/98")).andReturn();
 		MockHttpServletResponse resp = result.getResponse();
 		
 		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), resp.getStatus());
 		assertNotNull(resp.getContentAsString());
 		
 	}
    
    @Test
	@Order(6)
	public void createTelemetryTest() throws Exception {

	
		MvcResult result = mvc
				.perform(post("/agastya/telemetry")
						.header("Content-Type", "application/json")
						//.contentType(MediaType.APPLICATION_JSON)
						.content("{\"deviceId\":\"14\",\"timestamp\":null,\"telemetryKey\":\"Krishna\",\"telemetryValue\":\"Joti\"}"))
				.andReturn();
		MockHttpServletResponse resp = result.getResponse();
		assertNotNull(resp.getContentAsString());
		assertEquals(HttpStatus.OK.value(), resp.getStatus());
       
	}
    
    
    
    @Test
   	@Order(13)
   	public void createTelemetryTest2() throws Exception {

   	
   		MvcResult result = mvc
   				.perform(post("/agastya/telemetry")
   						.header("Content-Type", "application/json")
   						//.contentType(MediaType.APPLICATION_JSON)
   						.content("{\"deviceId\":\"200\",\"timestamp\":null,\"telemetryKey\":\"Krishna\",\"telemetryValue\":\"Joti\"}"))
   				.andReturn();
   		MockHttpServletResponse resp = result.getResponse();
   		assertNotNull(resp.getContentAsString());
   		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), resp.getStatus());
   
   	}
       
       
  
    @Test
    public void mainMethodTest() {
    	AgastyaServiceApplication a=new AgastyaServiceApplication();
    	String args[]= {"prince","Deepak"};
    	a.main(args);
    }
       
    

 	
    
}
