package com.example.cityapi.service;

import com.example.cityapi.data.Country;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;

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
    private static final String INIT_FOLDER_NAME = "countries";

    @PostConstruct
    public void loadCountries() throws IOException {

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        Resource[] resources = resolver.getResources("classpath*:" + INIT_FOLDER_NAME + "/*.json");

        for (Resource resource : resources) {
            Country country = mapper.readValue(resource.getInputStream(), Country.class);
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
