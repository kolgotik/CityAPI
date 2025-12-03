package com.example.cityapi.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "countries")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Country {

    @Id
    @JsonIgnore
    private ObjectId id;
    private String name;
    private List<String> cities;
}
