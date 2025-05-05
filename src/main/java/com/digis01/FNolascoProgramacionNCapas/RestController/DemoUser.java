package com.digis01.FNolascoProgramacionNCapas.RestController;

import com.digis01.FNolascoProgramacionNCapas.DAO.UsuarioDAOImplementation;
import com.digis01.FNolascoProgramacionNCapas.JPA.Result;
import com.digis01.FNolascoProgramacionNCapas.JPA.UsuarioDireccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarioapi")
public class DemoUser {

    @Autowired
    private UsuarioDAOImplementation usuarioDAOImplementation;

    @GetMapping("/suma/{uno}/{dos}")
    public ResponseEntity Suma(@PathVariable int uno, @PathVariable int dos) {
        int result = uno + dos;
        return ResponseEntity.ok().body("La suma es: " + result);
    }

    @GetMapping("/resta/{uno}/{dos}")
    public ResponseEntity Resta(@PathVariable int uno, @PathVariable int dos) {
        int result = uno - dos;
        return ResponseEntity.ok().body("La resta es: " + result);
    }

    @GetMapping("/multi/{uno}/{dos}")
    public ResponseEntity Multiplicacion(@PathVariable int uno, @PathVariable int dos) {
        int result = uno * dos;
        return ResponseEntity.ok().body("La multiplicacion es: " + result);
    }

//    @GetMapping("/division/{uno}/{dos}")
    public ResponseEntity Division(@PathVariable int uno, @PathVariable int dos) {
        int result = uno / dos;
        return ResponseEntity.ok().body("La division es: " + result);
    }

    @GetMapping
    public ResponseEntity GetAll() {
        Result result = usuarioDAOImplementation.GetAllJPA();

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

    @GetMapping("/getbyid/{IdUsuario}")
    public ResponseEntity UserByID(@PathVariable int IdUsuario) {
        Result result = usuarioDAOImplementation.UsuaDirByIdJPA(IdUsuario);

        if (result.correct) {
            if (result.object == null) {
                return ResponseEntity.status(204).body(null);
            } else {
                return ResponseEntity.ok(result);
            }
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }

    @PostMapping("/Add")
    public ResponseEntity Add(@RequestBody UsuarioDireccion usuarioDireccion) {

        Result result = usuarioDAOImplementation.AddJPA(usuarioDireccion);

        if (result.correct) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }
    
    @DeleteMapping("/delete/{IdUsuario}")
    public ResponseEntity UsuarioDelete(@PathVariable int IdUsuario){
        Result result = usuarioDAOImplementation.DeleteUsuarioDireccionJPA(IdUsuario);
        
        if(result.correct){
            return ResponseEntity.ok(result);
        }else{
            return ResponseEntity.badRequest().build();
        }
        
        
    }
}
