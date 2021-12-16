package rw.ac.rca.termOneExam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.dto.CreateCityDTO;
import rw.ac.rca.termOneExam.repository.ICityRepository;

@Service
public class CityService {

	@Autowired
	private ICityRepository cityRepository;
	
	public Optional<City> getById(long id) {
//		Optional<City> city = cityRepository.findById(id);
//		
		
		return cityRepository.findById(id);
	}

	public List<City> getAll() {
		
		return cityRepository.findAll();
	}

	public boolean existsByName(String name) {
		
		return cityRepository.existsByName(name);
	}

	public City save(City city2) {
		City city =  new City(city2.getName(), city2.getWeather());
		return cityRepository.save(city);
	}
	public float calculateFahreneit(City city) {
//		(city.getWeather()*1.8)+32;
		return (float) ((city.getWeather()*1.8)+32);
		
	}

}
