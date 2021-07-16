package com.example.diplomadosuno.repositories;

import com.example.diplomadosuno.models.Postulant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.DisplayName;

@SpringBootTest
class PostulantRepositoryTest {
    
    @Autowired
    private PostulantRepository postulantRepository;

    @Test
    @DisplayName("Se crea, obtiene, elimina y actualiza un Postulante")
    void createGetUpdateDeleteOnePostulantTest(){

        Postulant testPostulant = new Postulant();
        testPostulant.setName("Test Postulant");
        testPostulant.setEmail("test@postulant.com");
        testPostulant.setId_diplomate(109594);

        Postulant createdPostulant = postulantRepository.createPostulant(testPostulant);
        testPostulant.setId(createdPostulant.getId());
        Postulant retrievedPostulant = postulantRepository.getById(createdPostulant.getId());
        Assertions.assertEquals("Test Postulant", retrievedPostulant.getName());
        Assertions.assertEquals("test@postulant.com",retrievedPostulant.getEmail());
        Assertions.assertEquals(109594, retrievedPostulant.getId_diplomate());

        Postulant testUpdatePostulant = new Postulant();
        testUpdatePostulant.setName("Test Postulant UPDATED");
        testUpdatePostulant.setEmail("UPDATED@postulant.com");
        testUpdatePostulant.setId_diplomate(963532);
        postulantRepository.updatePostulant(createdPostulant.getId(), testUpdatePostulant);

        Postulant retrievedUpdatedPostulant = postulantRepository.getById(createdPostulant.getId());
        Assertions.assertEquals("Test Postulant UPDATED", retrievedUpdatedPostulant.getName());
        Assertions.assertEquals("UPDATED@postulant.com",retrievedUpdatedPostulant.getEmail());
        Assertions.assertEquals(963532, retrievedUpdatedPostulant.getId_diplomate());

        postulantRepository.deletePostulant(retrievedPostulant.getId());

    }
}
