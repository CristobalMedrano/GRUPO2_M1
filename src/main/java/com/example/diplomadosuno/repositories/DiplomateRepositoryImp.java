package com.example.diplomadosuno.repositories;

import java.util.List;

import com.example.diplomadosuno.models.Diplomate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

@Repository
public class DiplomateRepositoryImp implements DiplomateRepository {

    @Autowired
    private Sql2o sql2o;

    @Override
    public List<Diplomate> getAllDiplomates() {
        Connection conn = sql2o.open();
        try{
            return conn.createQuery("SELECT * FROM diplomates")
                .executeAndFetch(Diplomate.class);
        }catch(Exception e){
            //System.out.println(e.getMessage());
            return null;
        }finally{
            conn.close();
        }
    }

    @Override
    public Diplomate createDiplomate(Diplomate diplomate) {
        Connection conn = sql2o.open();
        try {
            String query = "INSERT INTO diplomates (title, image, description) values (:nTitle, :nImage, :nDescription)";
            long insertedId = (long) conn.createQuery(query, true)
                .addParameter("nTitle", diplomate.getTitle())
                .addParameter("nImage", diplomate.getImage())
                .addParameter("nDescription", diplomate.getDescription())
                .executeUpdate().getKey();
                diplomate.setId(insertedId);
            return diplomate;
                            
        } catch (Exception e) {
            //System.out.println(e.getMessage());
            return null;
        }finally{
            conn.close();
        }

    }

    @Override
    public Diplomate updateDiplomate(long id, Diplomate diplomate) {
        
        Connection conn = sql2o.open();
        try{
            conn.createQuery("UPDATE diplomates SET title = :nTitle, image = :nImage, description = :nDescription WHERE id = :uId")
                .addParameter("nTitle", diplomate.getTitle())
                .addParameter("nImage", diplomate.getImage())
                .addParameter("nDescription", diplomate.getDescription())
                .addParameter("uId", id)
                .executeUpdate();
            return diplomate;
        }catch(Exception e){
            return null;
        }finally{
            conn.close();           
        }
        
    }

    @Override
    public Diplomate getById(long id) {
        Connection conn = sql2o.open();
        try{
            return conn.createQuery("SELECT * FROM diplomates WHERE id = :nId")
                .addParameter("nId", id)
                .executeAndFetchFirst(Diplomate.class);
    
        }catch(Exception e){
            //System.out.println(e.getMessage());
            return null;
        }finally{
            conn.close();
        }
     
    }

    @Override
    public List<Diplomate> deleteDiplomate(long id) {
        Connection conn = sql2o.open();

        try{
            conn.createQuery("DELETE FROM diplomates WHERE id = :deleteId")
                .addParameter("deleteId", id)
                .executeUpdate();
            return getAllDiplomates();

        }catch(Exception e){
            return null;

        }finally{
            conn.close();
        }
    }
    
}