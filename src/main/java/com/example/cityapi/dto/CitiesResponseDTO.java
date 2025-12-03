package com.example.cityapi.dto;

import java.util.List;

public record CitiesResponseDTO(String country, List<String> cities) {
}
