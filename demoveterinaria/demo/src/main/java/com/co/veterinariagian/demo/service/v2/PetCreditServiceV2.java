package com.co.veterinariagian.demo.service.v2;


import com.co.veterinariagian.demo.model.PetCredit;
import com.co.veterinariagian.demo.repository.PetCreditRepository;
import com.co.veterinariagian.demo.service.PetCreditService;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;

import org.slf4j.Logger;
import reactor.core.publisher.Mono;


@Service
public class PetCreditServiceV2 {

    private final Logger LOGGER = LoggerFactory.getLogger(PetCreditService.class);

    private final PetCreditRepository petCreditRepository;

    public PetCreditServiceV2(PetCreditRepository petCreditRepository) {
        this.petCreditRepository = petCreditRepository;
    }

    public Flux<PetCredit> findInactivos() {
        return petCreditRepository.findByIsActive(Boolean.TRUE)
                .onErrorResume(throwable -> {
                    LOGGER.error("Error al buscar Creditos inactivos ", throwable);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Creditos inactivos no encontrados").getMostSpecificCause()));
    }
    public Mono<PetCredit> saveNewPetCredit(PetCredit petcredit){
        if (updateAndSaveValidation(petcredit)){
            return petCreditRepository.save(petcredit);
        }
        return Mono.empty();

    }
    public boolean updateAndSaveValidation(PetCredit petcredit){
        return petcredit.getTotalAmount() > 0 &&
                petcredit.getAmountPaid() >= 0 &&
                petcredit.getTotalAmount() > petcredit.getAmountPaid();
    }

}
