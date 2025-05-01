package com.digis01.FNolascoProgramacionNCapas.DAO;

import com.digis01.FNolascoProgramacionNCapas.JPA.Result;
import com.digis01.FNolascoProgramacionNCapas.JPA.Roll;
import jakarta.persistence.TypedQuery;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;

@Repository
public class RollDAOImplementation implements IRollDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Result GetAllJPA() {
        Result result = new Result();

        try {
            TypedQuery<com.digis01.FNolascoProgramacionNCapas.JPA.Roll> queryRoll = entityManager.createQuery("FROM Roll", com.digis01.FNolascoProgramacionNCapas.JPA.Roll.class);
            List<com.digis01.FNolascoProgramacionNCapas.JPA.Roll> rolles = queryRoll.getResultList();

            for (com.digis01.FNolascoProgramacionNCapas.JPA.Roll roll : rolles) {

                Roll rolls = new Roll();

                rolls.setIdRoll(rolls.getIdRoll());
                rolls.setNombre(rolls.getNombre());

                result.object = rolles; //Object: Solo puede mostrar muestra 1 objeto
//                result.objects.add(rolles); //Objects: Muestra muchos objetos, como un Get All

            }

            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

}
