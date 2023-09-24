package com.co.veterinariagian.demo.controller;

import com.co.veterinariagian.demo.model.PetCredit;
import com.co.veterinariagian.demo.service.PetCreditService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class PetCreditControllerTest {

    @InjectMocks
    private PetCreditService petCreditService;

    @Test
    public void testUpdateAndSaveValidationReturnsTrue() {
        PetCredit petCredit = new PetCredit(540, true, 1000D, 0D, "Test Credito");

        boolean result = petCreditService.updateAndSaveValidation(petCredit);

        assertThat(result).isTrue();
    }

    @Test
    public void testUpdateAndSaveValidation_totalAmount_ReturnsFalse() {

        PetCredit petCredit = new PetCredit(540, true, 0D, 0D, "Test Credito");


        boolean result = petCreditService.updateAndSaveValidation(petCredit);


        assertThat(result).isFalse();
    }

    @Test
    public void testUpdateAndSaveValidation_NegativeAmountPaid_ReturnsFalse() {

        PetCredit petCredit = new PetCredit(540, true, 1000D, -100D, "Test Credito");


        boolean result = petCreditService.updateAndSaveValidation(petCredit);


        assertThat(result).isFalse();
    }

    @Test
    public void testUpdateAndSaveValidation_AmountPaidGreaterThanTotalAmount_ReturnsFalse() {

        PetCredit petCredit = new PetCredit(540, true, 1000D, 1500D, "Test Credito");


        boolean result = petCreditService.updateAndSaveValidation(petCredit);


        assertThat(result).isFalse();
    }
}

