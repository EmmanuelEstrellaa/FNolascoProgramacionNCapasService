package com.digis01.FNolascoProgramacionNCapas.DAO;

import com.digis01.FNolascoProgramacionNCapas.JPA.Estado;
import com.digis01.FNolascoProgramacionNCapas.JPA.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EstadoDAOImplementation implements IEstadoDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Result EstadoByIdPaisJPA(int IdPais) {
        Result result = new Result();

        try {
            TypedQuery<com.digis01.FNolascoProgramacionNCapas.JPA.Estado> queryEstado = entityManager.createQuery("FROM Estado e WHERE Pais.IdPais = :idpais", com.digis01.FNolascoProgramacionNCapas.JPA.Estado.class);
            queryEstado.setParameter("idPais", IdPais);
            
            List<com.digis01.FNolascoProgramacionNCapas.JPA.Estado> estados = queryEstado.getResultList();

            for (com.digis01.FNolascoProgramacionNCapas.JPA.Estado estado : estados) {

                Estado estadoo = new Estado();

                estadoo.setIdEstado(estado.getIdEstado());
                estadoo.setNombre(estado.getNombre());

                result.object = estados;

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
