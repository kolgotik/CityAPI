package com.example.cityapi.service;

import com.example.cityapi.data.Country;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Log4j2
public class CountryStorage {

    private final Map<String, Country> countries = new HashMap<>();
    private final ObjectMapper mapper;
    private static final String INIT_FOLDER_NAME = "init";

    @PostConstruct
    public void loadCountries() throws IOException {

        File folder = new File(INIT_FOLDER_NAME);

        File[] files = folder.listFiles((dir, name) -> name.endsWith(".json"));

        if (files == null) {
            throw new IllegalStateException("Folder " + INIT_FOLDER_NAME + " not found or empty");
        }

        for (File file : files) {
            Country country = mapper.readValue(file, Country.class);
            countries.put(country.getName().toLowerCase(), country);
        }

        log.info("Loaded: {}", countries.keySet());
    }

    public List<String> getAllCountries() {
        return countries.values().stream()
                .map(Country::getName)
                .toList();
    }

    public Country getCountry(String name) {
        return countries.get(name.toLowerCase());
    }
}
