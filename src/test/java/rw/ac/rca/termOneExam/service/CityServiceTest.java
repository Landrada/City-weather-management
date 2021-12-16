package rw.ac.rca.termOneExam.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.repository.ICityRepository;

@RunWith(MockitoJUnitRunner.class)
public class CityServiceTest {
	
	@Mock
	private ICityRepository cityRepositoryMock;
	
	@InjectMocks
	private CityService cityService;

	@Test
	public void getAll_success() {
		when(cityRepositoryMock.findAll()).thenReturn(Arrays.asList(new City("Kigali",24),
				new City("Musanze",18)));
		assertEquals(24,cityService.getAll().get(0).getWeather());
	}
	
	@Test 
	 public void findById_test() {
        when(cityRepositoryMock.findById(101L)).thenReturn(Optional.of(new City("Kigali",24)));

        assertEquals("Kigali", cityService.getById(101L).get().getName());
    }
	
	 @Test
	    public void save_test() {
	        when(cityRepositoryMock.save(any(City.class))).thenReturn(new City(101, "Kigali", 24, 38.1));

	        assertEquals("Kigali", cityService.save(new City()).getName());
	    }
}
