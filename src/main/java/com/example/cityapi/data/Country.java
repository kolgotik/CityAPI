package com.example.cityapi.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Country {
    private String name;
    private List<String> cities;
}
