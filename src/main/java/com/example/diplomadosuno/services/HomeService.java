package com.example.diplomadosuno.services;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin("*")
public class HomeService {

    @GetMapping("/")
    public @ResponseBody String greeting(){
        return "Microservicio 1 - Grupo 2 Taller de Ingeniería de Software ";
    }
    
}
