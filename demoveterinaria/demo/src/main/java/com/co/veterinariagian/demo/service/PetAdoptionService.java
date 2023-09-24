package com.co.veterinariagian.demo.service;

import com.co.veterinariagian.demo.model.PetAdoption;
import com.co.veterinariagian.demo.repository.PetAdoptionRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PetAdoptionService {
    PetAdoptionRepository petAdoptionRepository;

    public PetAdoptionService(PetAdoptionRepository petAdoptionRepository) {
        this.petAdoptionRepository = petAdoptionRepository;
    }

    public Mono<PetAdoption> findAdoptionRequestsById(Integer idRequester) {
        return petAdoptionRepository.findById(idRequester);
    }

    public Flux<PetAdoption> findAllPetAdoptionsRequests() {
        return petAdoptionRepository.findAll();
    }

    public Mono<PetAdoption> saveNewPetAdoptionsRequest(PetAdoption petAdoption) {

        return petAdoptionRepository.save(petAdoption);


    }

    public Mono<Void> deleteAllPetRequests() {
        return petAdoptionRepository.deleteAll();
    }
}
