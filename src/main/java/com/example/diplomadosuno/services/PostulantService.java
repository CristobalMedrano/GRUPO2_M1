package com.example.diplomadosuno.services;

import java.util.List;

import com.example.diplomadosuno.models.Postulant;
import com.example.diplomadosuno.repositories.PostulantRepository;

import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
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

    @GetMapping("/postulants/{id}")
    public Postulant getPostulantById(@PathVariable long id){
        return postulantRepository.getById(id);
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
