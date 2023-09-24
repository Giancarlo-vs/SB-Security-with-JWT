package com.co.veterinariagian.demo.service;

import com.co.veterinariagian.demo.config.KafkaConfig;
import com.co.veterinariagian.demo.model.PetCredit;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Objects;

@Service
public class PetCreditKafkaConsumerService {
    private final Logger LOGGER = LoggerFactory.getLogger(PetCreditKafkaConsumerService.class); //para obtener los logs
    private final KafkaTemplate<String, String> kafkaTemplate;

    public PetCreditKafkaConsumerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public String getLastPetCredit(String topico) {
        ConsumerRecord<String, String> ultimoCredito;
        KafkaConfig kafkaConfig = new KafkaConfig();
        kafkaTemplate.setConsumerFactory(kafkaConfig.consumerFactory());
        ultimoCredito = kafkaTemplate.receive(topico, 0, 1);
        String creditoRecibido = Objects.requireNonNull(ultimoCredito.value());

        return creditoRecibido;
    }

}
