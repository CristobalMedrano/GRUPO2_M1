package com.example.diplomadosuno.repositories;

import java.util.Collections;
import java.util.List;

import com.example.diplomadosuno.models.Postulant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

@Repository
public class PostulantRepositoryImp implements PostulantRepository {

    @Autowired
    private Sql2o sql2o;

    @Override
    public Postulant createPostulant(Postulant postulant) {
        
        String query = "INSERT INTO postulants (name, email, id_diplomate) values (:vName, :vEmail, :vIdDiplomate)";
        Connection conn = sql2o.open();

   
        try(Query sqlQuery = conn.createQuery(query, true)
            .addParameter("vName", postulant.getName())
            .addParameter("vEmail", postulant.getEmail())
            .addParameter("vIdDiplomate", postulant.getId_diplomate()))
        {

            long insertedId = (long) sqlQuery.executeUpdate().getKey();
            postulant.setId(insertedId);

        }catch(Exception e){
            return null;
        }finally{
            conn.close();
        }            
        return postulant;
    
    }

    @Override
    public List<Postulant> getAllPostulants() {

        Connection conn = sql2o.open();
        try(Query sqlQuery = conn.createQuery("SELECT id, name, email, id_diplomate FROM postulants")){
            return sqlQuery.executeAndFetch(Postulant.class);
        }catch(Exception e){
            return Collections.emptyList();
        }finally{
            conn.close();
            
        }
    }

    @Override
    public List<Postulant> deletePostulant(long id) {
        
        Connection conn = sql2o.open();

        try(Query sqlQuery = conn.createQuery("DELETE FROM postulants WHERE id = :deleteId")
            .addParameter("deleteId", id))
        {   
            sqlQuery.executeUpdate();
            return getAllPostulants();
        }catch(Exception e){
            return Collections.emptyList();

        }finally{
            conn.close();
        }    
        
    }

    @Override
    public Postulant updatePostulant(long id, Postulant postulant) {
        
        Connection conn = sql2o.open();

        try(Query sqlQuery = conn.createQuery("UPDATE postulants SET email = :eMail, name = :uName, id_diplomate = :uIdDiplomate WHERE id = :uId")
            .addParameter("eMail", postulant.getEmail())
            .addParameter("uName", postulant.getName())
            .addParameter("uIdDiplomate", postulant.getId_diplomate())
            .addParameter("uId", id))
        {            
            sqlQuery.executeUpdate();
            return postulant;
        }catch(Exception e){
            return null;
        }finally{
            conn.close();           
        }
     
    }

    @Override
    public Postulant getById(long id) {
        Connection conn = sql2o.open();
        try(Query sqlQuery = conn.createQuery("SELECT id, name, email, id_diplomate FROM postulants WHERE id = :nId")
            .addParameter("nId", id)){
            return sqlQuery.executeAndFetchFirst(Postulant.class);                
        }catch(Exception e){
            return null;
        }finally{
            conn.close();
        }
        
    }
}
