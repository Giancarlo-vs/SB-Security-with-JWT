package com.co.veterinariagian.demo.repository;

import com.co.veterinariagian.demo.model.PetCredit;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface PetCreditRepository extends R2dbcRepository<PetCredit, Integer> {

    Flux<PetCredit> findByIsActive(boolean isActive);
    Flux<PetCredit> findByDescriptionContaining(String description);

}
