package com.example.diplomadosuno.repositories;

import java.util.List;

import com.example.diplomadosuno.models.Postulant;

public interface PostulantRepository {

    public Postulant createPostulant(Postulant postulant);
    public List<Postulant> getAllPostulants();
    public List<Postulant> deletePostulant(long id);
    public Postulant updatePostulant(long id, Postulant postulant);
    public Postulant getById(long id);


}
