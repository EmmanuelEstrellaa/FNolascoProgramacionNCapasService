package com.digis01.FNolascoProgramacionNCapas.RestController;

import com.digis01.FNolascoProgramacionNCapas.DAO.EstadoDAOImplementation;
import com.digis01.FNolascoProgramacionNCapas.JPA.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estadoapi")
public class EstadoRestController {

    @Autowired
    private EstadoDAOImplementation estadoDAOImplementation;

    @GetMapping("/byidpais/{IdPais}")
    public ResponseEntity GetByIdPais(@PathVariable int IdPais) {
        Result result = estadoDAOImplementation.EstadoByIdPaisJPA(IdPais);
        
        if (result.correct) {
            if (result.objects.isEmpty()) {
                return ResponseEntity.status(204).body(null);
            } else {
                return ResponseEntity.ok(result);
            }
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }

}
