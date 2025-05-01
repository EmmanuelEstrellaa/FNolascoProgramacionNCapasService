
package com.digis01.FNolascoProgramacionNCapas.DAO;

import com.digis01.FNolascoProgramacionNCapas.JPA.Result;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class ColoniaDAOImplementation implements IColoniaDAO{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
     public Result ColoniaByIdMunicipioJPA(int IdMunicipio) {
        Result result = new Result();


        return result;
    }
     
     @Override
     public Result ColoniaByCPJPA(int CodigoPostal) {
        Result result = new Result();



        return result;
    }
}
