package com.digis01.FNolascoProgramacionNCapas.RestController;

import com.digis01.FNolascoProgramacionNCapas.DAO.PaisDAOImplementation;
import com.digis01.FNolascoProgramacionNCapas.JPA.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paisapi")
public class PaisRestController {

    @Autowired
    private PaisDAOImplementation paisDAOImplementation;

    @GetMapping
    public ResponseEntity GetAll() {
        Result result = paisDAOImplementation.GetAllJPA();

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
