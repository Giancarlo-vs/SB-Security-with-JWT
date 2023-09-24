package com.co.veterinariagian.demo.repository;

import com.co.veterinariagian.demo.model.PetAdoption;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetAdoptionRepository extends R2dbcRepository<PetAdoption, Integer> {
}
