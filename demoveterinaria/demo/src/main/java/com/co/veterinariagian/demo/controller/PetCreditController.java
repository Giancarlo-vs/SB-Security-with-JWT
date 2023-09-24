package com.co.veterinariagian.demo.controller;

import com.co.veterinariagian.demo.dto.DTOPetCreditDescriptionActive;
import com.co.veterinariagian.demo.model.PetCredit;
import com.co.veterinariagian.demo.repository.PetCreditRepository;
import com.co.veterinariagian.demo.service.PetCreditKafkaConsumerService;
import com.co.veterinariagian.demo.service.PetCreditSQSService;
import com.co.veterinariagian.demo.service.PetCreditService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/petcredit")
public class PetCreditController {


    PetCreditService petCreditService;
    PetCreditKafkaConsumerService petCreditKafkaConsumerService;//Inyectamos servicio de kafka

    PetCreditSQSService petCreditSQSService;

    public PetCreditController(PetCreditService petCreditService, PetCreditKafkaConsumerService petCreditKafkaConsumerService, PetCreditSQSService petCreditSQSService) {
        this.petCreditService = petCreditService;
        this.petCreditKafkaConsumerService = petCreditKafkaConsumerService;
        this.petCreditSQSService = petCreditSQSService;
    }

    @GetMapping("/get-pet-credit-by-id/{id}")
    public Mono<PetCredit> getPetCreditById(@PathVariable Integer id) {
        return petCreditService.findPetCreditById(id);
    }

    @GetMapping("/find-all-pet-credits")
    public Flux<PetCredit> findAll() {
        return petCreditService.findAllPetCredits();

    }

    @PostMapping("/save")
    public Mono<PetCredit> createPetCredit(@RequestBody PetCredit petCredit) {

        return petCreditService.saveNewPetCredit(petCredit);

    }

    @PutMapping("/update/{id}")
    public Mono<PetCredit> updatePetCredit(@PathVariable Integer id, @RequestBody PetCredit petCredit) {

        return petCreditService.updatePetCredit(id, petCredit);

    }

    @DeleteMapping("/delete-pet-credit-by-id/{id}")
    public Mono<PetCredit> deletePetCreditById(@PathVariable Integer id) {
        return petCreditService.deletePetCreditById(id);

    }

    @DeleteMapping("/delete-all")
    public Mono<Void> deleteAllPetCredits() {
        return petCreditService.deleteAllPetCredits();

    }

    @GetMapping("/find-by-active/{isActive}")
    public Flux<PetCredit> getByActive(@PathVariable Boolean isActive) {
        return petCreditService.findAllPetCreditsByActive(isActive);

    }

    @PostMapping("/find-by-description")
    public Flux<PetCredit> getPetCreditsByDescription(@RequestBody DTOPetCreditDescriptionActive dtoCreditoDescripcionActivo) {
        return petCreditService.findByDescripcion(dtoCreditoDescripcionActivo.description());
    }

    //Kafka EndPoints:
    @GetMapping("/topico-kafka/{topico}")
    public Mono<String> getPetCreditFromTopicoKafka(@PathVariable String topico) {
        return Mono.just(petCreditKafkaConsumerService.getLastPetCredit(topico));
    }

    // AWS Endpoints:
    @PostMapping("/aws/create-queue")
    public Mono<String> postCreateQueue(@RequestBody Map<String, Object> requestBody) {
        return Mono.just(petCreditSQSService.createStandardQueue((String) requestBody.get("queueName")));//queueName, es la var con el nombre de la cola
    }


    @PostMapping("/aws/post-message-queue/{queueName}")
    public Mono<String> postMessageQueue(@RequestBody PetCredit petCredit, @PathVariable String queueName) {
        return Mono.just(petCreditSQSService.publishStandardQueueMessage(
                queueName,
                2,
                petCredit));
    }

    @PostMapping("/aws/process-credito-by-description")
    public Mono<PetCredit> deletePetcreditFromQueueByDescription(@RequestBody Map<String, Object> requestBody) {
        return petCreditSQSService.deletePetCreditMessageInQueue((String) requestBody.get("queueName"),
                (Integer) requestBody.get("maxNumberMessages"),
                (Integer) requestBody.get("waitTimeSeconds"),
                (String) requestBody.get("descripcionCredito"));
    }


}
