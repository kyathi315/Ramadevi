package com.stocks.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stocks.user.City;
import com.stocks.user.Countries;
import com.stocks.user.DataResponse;

@RestController
@CrossOrigin("*")
public class TempletController {

	List<Countries> countries = new ArrayList<>();

	@GetMapping("/cities")
	public List<Countries> getCountryDetails() {
		String uri = "https://countriesnow.space/api/v0.1/countries";
		RestTemplate template = new RestTemplate();
		Object[] objects = new Object[] { template.getForObject(uri, Object.class) };

		ObjectMapper objectMapper = new ObjectMapper();
		countries = Arrays.stream(objects).map(o -> objectMapper.convertValue(o, DataResponse.class))
				.map(DataResponse::getData).collect(Collectors.toList()).get(0);
		return countries;
	}



	@GetMapping("/citiesByCountry/{country}")
	public List<City> getCitiesByCountry(@PathVariable String country) {
		if (countries.isEmpty()) {
			getCountryDetails();
		}
		return countries.stream().filter(e -> e.getCountry().equalsIgnoreCase(country))
				.flatMap(e -> e.getCities().stream()).collect(Collectors.toList());
	}

}
