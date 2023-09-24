package com.co.veterinariagian.demo.model;

public interface PetActions {
    default boolean payPetCredit() {
        return false;
    }
}
