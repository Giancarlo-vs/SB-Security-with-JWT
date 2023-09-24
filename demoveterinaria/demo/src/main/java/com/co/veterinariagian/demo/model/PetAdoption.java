package com.co.veterinariagian.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class PetAdoption {
    @Id
    private Integer idRequester;
    private String name;
    private String petType;
    private String description;
    private String telephone;

}
