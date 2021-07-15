package com.example.diplomadosuno.services;

import java.util.List;

import com.example.diplomadosuno.models.Postulant;
import com.example.diplomadosuno.repositories.PostulantRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public Postulant createPostulant(@RequestBody Postulant postulant){
        return postulantRepository.createPostulant(postulant);

    }

    @GetMapping("/postulants")
    public List<Postulant> getAllPostulants(){
        return postulantRepository.getAllPostulants();
    }

    @GetMapping("/postulants/{email}")
    public List<Postulant> getPostulantByEmail(@PathVariable String email){
        return postulantRepository.getByEmail(email);
    }

    @DeleteMapping("/postulants/{id}")
    public List<Postulant> deletePostulant(@PathVariable long id){
        return postulantRepository.deletePostulant(id);
    }

    @PutMapping("/postulants/{id}")
    @ResponseBody
    public Postulant updatePostulant(@PathVariable long id, @RequestBody Postulant postulant){
        return postulantRepository.updatePostulant(id, postulant);
    }




}
