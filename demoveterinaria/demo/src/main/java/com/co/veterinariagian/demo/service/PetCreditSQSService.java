package com.co.veterinariagian.demo.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.*;
import com.co.veterinariagian.demo.model.PetCredit;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PetCreditSQSService {
    private final AmazonSQS clienteSQS;

    public PetCreditSQSService(AmazonSQS clienteSQS) {
        this.clienteSQS = clienteSQS;
    }


    public String createStandardQueue(String queueName){

        CreateQueueRequest createQueueRequest = new CreateQueueRequest(queueName);
        return clienteSQS.createQueue(createQueueRequest).getQueueUrl(); //Retornamos la url de la cola
    }

    private String getQueueUrl(String queueName){
        System.out.println("COLA***"+clienteSQS.getQueueUrl(queueName).getQueueUrl());
        return clienteSQS.getQueueUrl(queueName).getQueueUrl();
    }


    public String publishStandardQueueMessage(String queueName, Integer delaySeconds, PetCredit petCredit){
        Map<String, MessageAttributeValue> atributosMensaje = new HashMap<>();

        atributosMensaje.put("idPet",
                new MessageAttributeValue()
                        .withStringValue(Optional.ofNullable(petCredit.getIdPet()).orElse(-301).toString())//Usamos un Optional, para enviar un id x defecto en caso de que en el boy no enviemos un id
                        .withDataType("Number"));
        atributosMensaje.put("isActive",
                new MessageAttributeValue()
                        .withStringValue(Optional.ofNullable(petCredit.getIsActive()).orElse(Boolean.FALSE).toString())
                        .withDataType("String"));
        atributosMensaje.put("totalAmount",
                new MessageAttributeValue()
                        .withStringValue(petCredit.getTotalAmount().toString())
                        .withDataType("String"));
        atributosMensaje.put("amountPaid",
                new MessageAttributeValue()
                        .withStringValue(Optional.ofNullable(petCredit.getAmountPaid()).orElse(0D).toString())
                        .withDataType("String"));
        atributosMensaje.put("description",
                new MessageAttributeValue()
                        .withStringValue(petCredit.getDescription())
                        .withDataType("String"));

        SendMessageRequest sendMessageRequest = new SendMessageRequest()
                .withQueueUrl(this.getQueueUrl(queueName))
                .withMessageBody(petCredit.getDescription())
                .withDelaySeconds(delaySeconds)
                .withMessageAttributes(atributosMensaje);

        return clienteSQS.sendMessage(sendMessageRequest).getMessageId();
    }


    public void publishStandardQueueMessage(String queueName, Integer delaySeconds, List<PetCredit> petCredits){
        for (PetCredit petCredit : petCredits){
            publishStandardQueueMessage(queueName, delaySeconds, petCredit);
        }
    }

    private List<Message> receiveMessagesFromQueue(String queueName, Integer maxNumberMessages, Integer waitTimeSeconds){
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(this.getQueueUrl(queueName))
                .withMaxNumberOfMessages(maxNumberMessages)
                .withMessageAttributeNames(List.of("All"))
                .withWaitTimeSeconds(waitTimeSeconds);
        return clienteSQS.receiveMessage(receiveMessageRequest).getMessages();
    }

    public Mono<PetCredit> deletePetCreditMessageInQueue(String queueName, Integer maxNumberMessages,
                                                     Integer waitTimeSeconds, String petCreditdescription){
        List<Message> petCreditMessages = receiveMessagesFromQueue(queueName, maxNumberMessages, waitTimeSeconds);
        for(Message message : petCreditMessages){
            if(!message.getMessageAttributes().isEmpty()) {
                if (message.getMessageAttributes().get("description").getStringValue().equals(petCreditdescription)) {
                    PetCredit petCredit = new PetCredit(Integer.valueOf(message.getMessageAttributes().get("idPet").getStringValue()),
                            Boolean.getBoolean(message.getMessageAttributes().get("isActive").getStringValue()),
                            Double.valueOf(message.getMessageAttributes().get("totalAmount").getStringValue()),
                            Double.valueOf(message.getMessageAttributes().get("amountPaid").getStringValue()),
                            message.getMessageAttributes().get("description").getStringValue());
                    clienteSQS.deleteMessage(this.getQueueUrl(queueName), message.getReceiptHandle());
                    return Mono.just(petCredit);
                }
            }
        }
        return Mono.empty();
    }
}
