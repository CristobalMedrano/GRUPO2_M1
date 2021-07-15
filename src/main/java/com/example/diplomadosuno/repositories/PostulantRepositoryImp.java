package com.example.diplomadosuno.repositories;

import java.util.List;

import com.example.diplomadosuno.models.Postulant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

@Repository
public class PostulantRepositoryImp implements PostulantRepository {

    @Autowired
    private Sql2o sql2o;

    @Override
    public Postulant createPostulant(Postulant postulant) {

        Connection conn = sql2o.open();
        try {
            String query = "INSERT INTO postulants (name, email) values (:vName, :vEmail)";
            long insertedId = (long) conn.createQuery(query, true)
                .addParameter("vName", postulant.getName())
                .addParameter("vEmail", postulant.getEmail())
                .executeUpdate().getKey();
            postulant.setId(insertedId);
            return postulant;
                            
        } catch (Exception e) {
            //System.out.println(e.getMessage());
            return null;
        }finally{
            conn.close();
        }
    }

    @Override
    public List<Postulant> getAllPostulants() {

        Connection conn = sql2o.open();
        try{
            return conn.createQuery("SELECT name, email FROM postulants")
                .executeAndFetch(Postulant.class);
        }catch(Exception e){
            //System.out.println(e.getMessage());
            return null;
        }finally{
            conn.close();
        }
    }

    @Override
    public List<Postulant> deletePostulant(long id) {
        Connection conn = sql2o.open();

        try{
            conn.createQuery("DELETE FROM postulants WHERE id = :deleteId")
                .addParameter("deleteId", id)
                .executeUpdate();
            return getAllPostulants();

        }catch(Exception e){
            return null;

        }finally{
            conn.close();
        }    
        
    }

    @Override
    public Postulant updatePostulant(long id, Postulant postulant) {
        Connection conn = sql2o.open();

        try{
            conn.createQuery("UPDATE postulants SET email = :eMail, name = :uName WHERE id = :uId")
                .addParameter("eMail", postulant.getEmail())
                .addParameter("uName", postulant.getName())
                .addParameter("uId", id)
                .executeUpdate();
            return postulant;
        }catch(Exception e){
            return null;
        }finally{
            conn.close();           
        }
     
    }
    @Override
    public Postulant getByEmail(String email) {
        Connection conn = sql2o.open();
        try{
            return conn.createQuery("SELECT name, email FROM postulants WHERE email = :eMail")
                .addParameter("eMail", email)
                .executeAndFetchFirst(Postulant.class);
        }catch(Exception e){
            //System.out.println(e.getMessage());
            return null;
        }finally{
            conn.close();
        }
        
    }
}
