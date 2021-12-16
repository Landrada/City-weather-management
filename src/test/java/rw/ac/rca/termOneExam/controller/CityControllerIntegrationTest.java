package rw.ac.rca.termOneExam.controller;

import static org.junit.Assert.assertEquals;

import java.util.Objects;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.utils.APICustomResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CityControllerIntegrationTest {

	@Autowired
    private TestRestTemplate restTemplate;
	
	@Test
    public void getAll_testSuccess() throws JSONException {
		String response = this.restTemplate.getForObject("/all", String.class);
		System.out.println(response);
//		JSONAssert.assertEquals("[]", response, false);
		JSONAssert.assertEquals("[{\"id\":101,\"name\":\"Kigali\",\"weather\":24},{\"id\":102,\"name\":\"Musanze\",\"weather\":18},{\"id\":103,\"name\":\"Rubavu\",\"weather\":20},{\"id\":104,\"name\":\"Nyagatare\",\"weather\":28}]", response, true);
	
    }
	
	@Test
	public void getAl_succes() throws JSONException {
		String response = this.restTemplate.getForObject("/all", String.class);
		System.out.println(response);
		//JSONAssert.assertEquals("[]", response, false);
		JSONAssert.assertEquals("[{\"id\":101,\"name\":\"Kigali\",\"weather\":24},{\"id\":102,\"name\":\"Musanze\",\"weather\":18},{\"id\":103,\"name\":\"Rubavu\",\"weather\":20},{\"id\":104,\"name\":\"Nyagatare\",\"weather\":28}]", response, true);
	
	}
	
	@Test 
	public void getAll_testFailure() {
		ResponseEntity<String> response = restTemplate.getForEntity("/all", String.class);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	@Test
    public void getById_testSuccess() {
        ResponseEntity<String> response = restTemplate.getForEntity("/id/103", String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
	@Test
	public void getById_404() {
		ResponseEntity<APICustomResponse> response = this.restTemplate.getForEntity("/id/1000", APICustomResponse.class);
		
		assertEquals(404, response.getStatusCodeValue());
		assertEquals("City not found", response.getBody().getMessage());
	}
	
	 @Test
	    public void create_testSuccess() {
	        City newCity = new City("Rusizi", 24);
	        ResponseEntity<City> response = restTemplate.postForEntity("/api/cities/add", newCity, City.class);

	        assertEquals(HttpStatus.CREATED, response.getStatusCode());
	        assertEquals("Rusizi", Objects.requireNonNull(response.getBody()).getName());
	    }
	 
	 
	
}
