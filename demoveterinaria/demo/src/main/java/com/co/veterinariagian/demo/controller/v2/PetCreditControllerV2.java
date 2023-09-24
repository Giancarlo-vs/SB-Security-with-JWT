package com.co.veterinariagian.demo.controller.v2;


import com.co.veterinariagian.demo.model.PetCredit;
import com.co.veterinariagian.demo.service.PetCreditService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("v2/petcredit")
public class PetCreditControllerV2 {

    PetCreditService petCreditService;


    public PetCreditControllerV2(PetCreditService creditoService) {
        this.petCreditService = creditoService;
    }

    @GetMapping("/find-by-active")
    public Flux<PetCredit> getCreditosInactivos() {
        return petCreditService.findAllPetCreditsByActive(Boolean.TRUE);
    }
    @PostMapping("/save")
    public Mono<PetCredit> createPetCredit(@RequestBody PetCredit petCredit) {

        return petCreditService.saveNewPetCredit(petCredit);

    }
}