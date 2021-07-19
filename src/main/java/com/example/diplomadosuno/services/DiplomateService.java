package com.example.diplomadosuno.services;

import java.util.List;

import com.example.diplomadosuno.models.Diplomate;
import com.example.diplomadosuno.repositories.DiplomateRepository;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class DiplomateService {

    private final DiplomateRepository diplomateRepository;

    DiplomateService(DiplomateRepository diplomateRepository){
        this.diplomateRepository = diplomateRepository;
    }

    @PostMapping("/diplomates")
    @ResponseBody
    public Diplomate createDiplomate(@RequestBody Diplomate diplomate){
        return diplomateRepository.createDiplomate(diplomate);

    }

    @GetMapping("/diplomates")
    public List<Diplomate> getAllDiplomates(){
        return diplomateRepository.getAllDiplomates();
    }

    @GetMapping("/diplomates/{id}")
    public Diplomate getDiplomateByTitle(@PathVariable long id){
        return diplomateRepository.getById(id);
    }

    @DeleteMapping("/diplomates/{id}")
    public List<Diplomate> deleteDiplomate(@PathVariable long id){
        return diplomateRepository.deleteDiplomate(id);
    }
    
    @PutMapping("/diplomates/{id}")
    @ResponseBody
    public Diplomate updatePostulant(@PathVariable long id, @RequestBody Diplomate diplomate){
        return diplomateRepository.updateDiplomate(id, diplomate);
    }

    
}
