package com.example.diplomadosuno.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class DiplomateServiceTest {

    @LocalServerPort
    private int port;

    @Autowired
	private TestRestTemplate restTemplate;

    @Test
    @DisplayName("Prueba obtener todos los diplomados con endpoint.")
    void getAllDiplomatesTest() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/api/v1/diplomates", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("Prueba obtener un diplomado con endpoint.")
    void getOneDiplomate() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/api/v1/diplomates/-1", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
        
}
