package com.example.diplomadosuno.repositories;

import com.example.diplomadosuno.models.Diplomate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.DisplayName;

@SpringBootTest
class DiplomateRepositoryTest {

    @Autowired
    private DiplomateRepository diplomateRepository;

    @Test
    @DisplayName("Se crea, obtiene, elimina y actualiza un Diplomado")
    void createGetUpdateDeleteOneDiplomateTest(){
        Diplomate testDiplomate = new Diplomate();
        testDiplomate.setDescription("Test Diplomate");
        testDiplomate.setImage("http://google.com");
        testDiplomate.setTitle("Test Diplomate Title");

        Diplomate createdDiplomate = diplomateRepository.createDiplomate(testDiplomate);
        testDiplomate.setId(createdDiplomate.getId());
        Diplomate retrievedDiplomate = diplomateRepository.getById(createdDiplomate.getId());
        Assertions.assertEquals("Test Diplomate Title", retrievedDiplomate.getTitle());
        Assertions.assertEquals("Test Diplomate",retrievedDiplomate.getDescription());
        Assertions.assertEquals("http://google.com", retrievedDiplomate.getImage());

        Diplomate testUpdateDiplomate = new Diplomate();
        testUpdateDiplomate.setDescription("Test Diplomate UPDATED");
        testUpdateDiplomate.setImage("http://UPDATED.com");
        testUpdateDiplomate.setTitle("Test Diplomate Title UPDATED");

        diplomateRepository.updateDiplomate(createdDiplomate.getId(), testUpdateDiplomate);
        Diplomate retrievedUpdatedDiplomate = diplomateRepository.getById(createdDiplomate.getId());
        Assertions.assertEquals("Test Diplomate Title UPDATED", retrievedUpdatedDiplomate.getTitle());
        Assertions.assertEquals("Test Diplomate UPDATED",retrievedUpdatedDiplomate.getDescription());
        Assertions.assertEquals("http://UPDATED.com", retrievedUpdatedDiplomate.getImage());
        
        diplomateRepository.deleteDiplomate(retrievedDiplomate.getId());

    }    
}
