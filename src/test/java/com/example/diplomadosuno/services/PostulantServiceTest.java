package com.example.diplomadosuno.services;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PostulantServiceTest {
    
    @LocalServerPort
    private int port;

    @Autowired
	private TestRestTemplate restTemplate;

    @Test
    void getAllPostulantsTest() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/api/v1/postulants", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    } 

    @Test
    void getOnePostulant() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/api/v1/postulants/-1", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    
}
