package rw.ac.rca.termOneExam.utils;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.repository.ICityRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CityUtilTest {

	@Autowired
    private ICityRepository cityRepository;
	
	@Test
	public void cityWithMoreTemperature() {
		List<City> cities = cityRepository.findAll();
		List<City> citiesWithMoreTemp = new ArrayList<City>();
		for(City city:cities) {
			if(city.getWeather()>40) {
				citiesWithMoreTemp.add(city);
			}
		}
        assertEquals(citiesWithMoreTemp.size(), 0);
	}
	
	@Test
	public void cityWithLessTemperature() {
		List<City> cities = cityRepository.findAll();
		List<City> citiesWithLessTemp = new ArrayList<City>();
		for(City city:cities) {
			if(city.getWeather()<10) {
				citiesWithLessTemp.add(city);
			}
		}
        assertEquals(citiesWithLessTemp.size(), 0);
	}
	
	@Test
	public void citiesContainMusanzeAndKigali() {
		List<City> cities= cityRepository.findAll();
		City kigali = new City("Rubavu",24);
		City musanze = new City("Musanze",18);
		List<City> kmCities = new ArrayList<City>();
		kmCities.add(musanze);
		kmCities.add(kigali);
		equals(cities.containsAll(kmCities));
	}
	@Test
	public void testSpying() {
		List<City> cities = cityRepository.findAll();
		List<City> spyCities = Mockito.spy(cities);
		spyCities.add(new City("Rusizi",20));
		spyCities.add(new City("Muhanga",23));
		assertEquals(2,spyCities.size());
	}
}
