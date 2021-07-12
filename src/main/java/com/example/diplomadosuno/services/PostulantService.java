package com.example.diplomadosuno.services;

import com.example.diplomadosuno.models.Postulant;
import com.example.diplomadosuno.repositories.PostulantRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class PostulantService {
    
    private final PostulantRepository postulantRepository;

    PostulantService(PostulantRepository postulantRepository){
        this.postulantRepository = postulantRepository;
    }

    @PostMapping("/postulants")
    @ResponseBody
    public Postulant createVoluntario(@RequestBody Postulant postulant){
        Postulant result = postulantRepository.createPostulant(postulant);
        return result;
    }
}
