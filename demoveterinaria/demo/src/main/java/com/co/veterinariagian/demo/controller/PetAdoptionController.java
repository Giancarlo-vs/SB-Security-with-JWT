package com.co.veterinariagian.demo.controller;

import com.co.veterinariagian.demo.model.PetAdoption;
import com.co.veterinariagian.demo.model.PetCredit;
import com.co.veterinariagian.demo.service.PetAdoptionService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/petadoption")
public class PetAdoptionController {
    PetAdoptionService petAdoptionService;

    public PetAdoptionController(PetAdoptionService petAdoptionService) {
        this.petAdoptionService = petAdoptionService;
    }
    @GetMapping("/getPetAdoptionRequestByid/{id}")
    public Mono<PetAdoption> getPetCreditById(@PathVariable Integer id) {
        return petAdoptionService.findAdoptionRequestsById(id);
    }
    @GetMapping("/findallpetAdoptionrequests")
    public Flux<PetAdoption> findAll() {
        return petAdoptionService.findAllPetAdoptionsRequests();

    }
    @PostMapping("/save")
    public Mono<PetAdoption> createPetAdoptionRequest(@RequestBody PetAdoption petAdoption) {

        return petAdoptionService.saveNewPetAdoptionsRequest(petAdoption);

    }
    @DeleteMapping("/deleteAllpetrequests")
    public Mono<Void> deleteAllPetRequests() {
        return petAdoptionService.deleteAllPetRequests();

    }
}
