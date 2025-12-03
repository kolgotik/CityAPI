package com.example.cityapi.controller;

import com.example.cityapi.data.Country;
import com.example.cityapi.data.CountryRepository;
import com.example.cityapi.dto.CitiesRequestDTO;
import com.example.cityapi.dto.CitiesResponseDTO;
import com.example.cityapi.dto.CountriesResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v0.1/countries")
@AllArgsConstructor
public class CountryController {

    private final CountryRepository countryRepository;

    @GetMapping
    public ResponseEntity<CountriesResponse> getEUCountries() {
        List<Country> countries = countryRepository.findAll();
        List<String> names = countries.stream().map(Country::getName).toList();
        CountriesResponse response = new CountriesResponse(names);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/cities")
    public ResponseEntity<CitiesResponseDTO> getCities(@RequestBody CitiesRequestDTO request) {
        Country country = countryRepository.findByNameIgnoreCase(request.country());
        List<String> cities = country.getCities();
        CitiesResponseDTO response = new CitiesResponseDTO(country.getName(), cities);
        return ResponseEntity.ok(response);
    }

}
