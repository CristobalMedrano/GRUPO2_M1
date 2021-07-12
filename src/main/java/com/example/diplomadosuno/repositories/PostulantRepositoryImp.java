package com.example.diplomadosuno.repositories;

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
        try (Connection conn = sql2o.open()) {
            String query = "INSERT INTO postulants (name, email) values (:vName, :vEmail)";
            long insertedId = (long) conn.createQuery(query, true)
                .addParameter("vName", postulant.getName())
                .addParameter("vEmail", postulant.getEmail())
                .executeUpdate().getKey();
            postulant.setId(insertedId);
            return postulant;
                            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
