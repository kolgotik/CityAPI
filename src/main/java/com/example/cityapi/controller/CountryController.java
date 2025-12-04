package com.example.cityapi.controller;

import com.example.cityapi.data.Country;
import com.example.cityapi.dto.CitiesRequestDTO;
import com.example.cityapi.dto.CitiesResponseDTO;
import com.example.cityapi.dto.CountriesResponse;
import com.example.cityapi.service.CountryStorage;
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

    private final CountryStorage countryStorage;

    @GetMapping
    public ResponseEntity<CountriesResponse> getEUCountries() {
        List<String> countries = countryStorage.getAllCountries();
        return ResponseEntity.ok(new CountriesResponse(countries));
    }

    @PostMapping("/cities")
    public ResponseEntity<CitiesResponseDTO> getCities(@RequestBody CitiesRequestDTO request) {
        Country country = countryStorage.getCountry(request.country());
        return ResponseEntity.ok(new CitiesResponseDTO(country.getName(), country.getCities()));
    }

}
