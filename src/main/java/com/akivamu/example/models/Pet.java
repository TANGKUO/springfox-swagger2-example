package com.akivamu.example.models;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
@Data
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class Pet {
    @Id
    @GeneratedValue
    private Long id;

    private final String name;

    @ElementCollection(targetClass = String.class)
    private final List<String> photoUrls;

    @ApiModelProperty(value = "Pet status in the store", allowableValues = "available,pending,sold")
    private final String status;
}
