package com.example.diplomadosuno;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class DiplomadosUnoApplicationTests {
	
	@LocalServerPort
    private int port;

    @Autowired
	private TestRestTemplate restTemplate;

	@Test
	@DisplayName("Prueba home route.")
	void contextLoads() {
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/", String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

	}

	@Test
	@DisplayName("Prueba que no existan exceptions en main.")
	void mainFunction(){
		Assertions.assertThatNoException().isThrownBy(() -> DiplomadosUnoApplication.main(new String[] {}));
	}

	

}
