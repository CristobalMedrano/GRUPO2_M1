package com.example.diplomadosuno.repositories;

import java.util.List;

import com.example.diplomadosuno.models.Diplomate;

public interface DiplomateRepository {

    public List<Diplomate> getAllDiplomates();
    public Diplomate createDiplomate(Diplomate diplomate);
    public Diplomate updateDiplomate(long id, Diplomate diplomate);
    public Diplomate getById(long id);
    public List<Diplomate> deleteDiplomate(long id);
    
}
