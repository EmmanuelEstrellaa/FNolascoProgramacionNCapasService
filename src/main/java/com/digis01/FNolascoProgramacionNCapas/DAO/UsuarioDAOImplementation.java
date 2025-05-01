package com.digis01.FNolascoProgramacionNCapas.DAO;

import com.digis01.FNolascoProgramacionNCapas.JPA.Colonia;
import com.digis01.FNolascoProgramacionNCapas.JPA.Direccion;
import com.digis01.FNolascoProgramacionNCapas.JPA.Result;
import com.digis01.FNolascoProgramacionNCapas.JPA.Usuario;
import com.digis01.FNolascoProgramacionNCapas.JPA.UsuarioDireccion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDAOImplementation implements IUsuarioDAO {

    @Autowired
    private EntityManager entityManager;

//    @Override
//    public Result UsuarioGetById(int IdUsuario) {
//        Result result = new Result();
//
//        try {
//
//            jdbcTemplate.execute("CALL UsuarioGetById(?,?)", (CallableStatementCallback<Integer>) callableStatement -> {
//
//                callableStatement.setInt(1, IdUsuario);
//                callableStatement.registerOutParameter(2, Types.REF_CURSOR);
//                callableStatement.execute();
//
//                ResultSet resultSet = (ResultSet) callableStatement.getObject(2);
//
//                if (resultSet.next()) {
//                    UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
//                    usuarioDireccion.Usuario = new Usuario();
//                    usuarioDireccion.Usuario.setIdUsuario(resultSet.getInt("IdUsuario"));
//                    usuarioDireccion.Usuario.setUserName(resultSet.getString("Username"));
//                    usuarioDireccion.Usuario.setNombre(resultSet.getString("Nombre"));
//                    usuarioDireccion.Usuario.setApellidoPaterno(resultSet.getString("ApellidoPaterno"));
//                    usuarioDireccion.Usuario.setEmail(resultSet.getString("Email"));
//                    usuarioDireccion.Usuario.setSexo(resultSet.getString("Sexo"));
//                    usuarioDireccion.Usuario.setTelefono(resultSet.getString("Telefono"));
//                    usuarioDireccion.Usuario.setCelular(resultSet.getString("Celular"));
//                    usuarioDireccion.Usuario.setCurp(resultSet.getString("Curp"));
//                    usuarioDireccion.Usuario.setApellidoMaterno(resultSet.getString("ApellidoMaterno"));
//                    usuarioDireccion.Usuario.setPassword(resultSet.getString("Password"));
//                    usuarioDireccion.Usuario.setFechaNacimiento(resultSet.getDate("FechaNacimiento"));
//                    usuarioDireccion.Usuario.setImagen(resultSet.getString("Imagen"));
//                    usuarioDireccion.Usuario.Roll = new Roll();
//                    usuarioDireccion.Usuario.Roll.setIdRoll(resultSet.getInt("IdRoll"));
//                    usuarioDireccion.Usuario.Roll.setNombre(resultSet.getString("NombreRoll"));
//
//                    result.object = usuarioDireccion;
//                }
//
//                result.correct = false;
//                return 1;
//            });
//
//        } catch (Exception ex) {
//            result.correct = false;
//            result.errorMessage = ex.getLocalizedMessage();
//            result.ex = ex;
//        }
//
//        return result;
//
//    }
    @Override
    public Result GetAllJPA() {

        Result result = new Result();
        try {
            TypedQuery<com.digis01.FNolascoProgramacionNCapas.JPA.Usuario> queryUsuarios = entityManager.createQuery("FROM Usuario", com.digis01.FNolascoProgramacionNCapas.JPA.Usuario.class);
            List<com.digis01.FNolascoProgramacionNCapas.JPA.Usuario> usuarios = queryUsuarios.getResultList();

            result.objects = new ArrayList<>();
            for (com.digis01.FNolascoProgramacionNCapas.JPA.Usuario usuario : usuarios) {

                UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
                usuarioDireccion.usuario = usuario;

                TypedQuery<com.digis01.FNolascoProgramacionNCapas.JPA.Direccion> queryDireccion = entityManager.createQuery("FROM Direccion WHERE Usuario.IdUsuario = :idusuario", com.digis01.FNolascoProgramacionNCapas.JPA.Direccion.class);
                queryDireccion.setParameter("idusuario", usuario.getIdUsuario());

                List<com.digis01.FNolascoProgramacionNCapas.JPA.Direccion> direccionesJPA = queryDireccion.getResultList();
                usuarioDireccion.Direcciones = direccionesJPA;

                result.objects.add(usuarioDireccion);

            }

            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;

    }

//    @Transactional
//    @Override
//    public Result AddJPA(UsuarioDireccion usuarioDireccion) {
//        Result result = new Result();
//
//        try {
//            //llenado de usuarioJPA con el usuario ML
//            com.digis01.FNolascoProgramacionNCapas.JPA.Usuario usuarioJPA = new com.digis01.FNolascoProgramacionNCapas.JPA.Usuario();
//            usuarioJPA.setUserName(usuarioDireccion.Usuario.getUserName());
//            usuarioJPA.setNombre(usuarioDireccion.Usuario.getNombre());
//            usuarioJPA.setApellidoPaterno(usuarioDireccion.Usuario.getApellidoPaterno());
//            usuarioJPA.setEmail(usuarioDireccion.Usuario.getEmail());
//            usuarioJPA.setSexo(usuarioDireccion.Usuario.getSexo());
//            usuarioJPA.setTelefono(usuarioDireccion.Usuario.getTelefono());
//            usuarioJPA.setCelular(usuarioDireccion.Usuario.getCelular());
//            usuarioJPA.setCurp(usuarioDireccion.Usuario.getCurp());
//            usuarioJPA.setApellidoMaterno(usuarioDireccion.Usuario.getApellidoMaterno());
//            usuarioJPA.setPassword(usuarioDireccion.Usuario.getPassword());
//            usuarioJPA.setFechaNacimiento(usuarioDireccion.Usuario.getFechaNacimiento());
//            usuarioJPA.setImagen(usuarioDireccion.Usuario.getImagen());
//
//            usuarioJPA.Roll = new com.digis01.FNolascoProgramacionNCapas.JPA.Roll();
//            usuarioJPA.Roll.setIdRoll(usuarioDireccion.Usuario.Roll.getIdRoll());
//
//            entityManager.persist(usuarioJPA);
//
//            /*inserción de dirección*/
//            com.digis01.FNolascoProgramacionNCapas.JPA.Direccion direccionJPA
//                    = new com.digis01.FNolascoProgramacionNCapas.JPA.Direccion();
//            direccionJPA.setCalle(usuarioDireccion.Direccion.getCalle());
//            direccionJPA.setNumeroExterior(usuarioDireccion.Direccion.getNumeroExterior());
//            direccionJPA.setNumeroInterior(usuarioDireccion.Direccion.getNumeroInterior());
//
//            direccionJPA.Colonia = new com.digis01.FNolascoProgramacionNCapas.JPA.Colonia();
//            direccionJPA.Colonia.setIdColonia(usuarioDireccion.Direccion.Colonia.getIdColonia());
//
//            direccionJPA.Usuario = usuarioJPA;
//
//            /*direccionJPA.Alumno = new com.digis01.DGarciaPorgramacionNCapasMarzo25.JPA.Alumno();
//            direccionJPA.Alumno.setIdAlumno(alumnoJPA.getIdAlumno());*/
//            entityManager.persist(direccionJPA);
//
//            System.out.println("");
//
//            result.correct = true;
//
//        } catch (Exception ex) {
//            result.correct = false;
//            result.errorMessage = ex.getLocalizedMessage();
//            result.ex = ex;
//        }
//
//        return result;
//    }
//
    @Override
    public Result UsuaDirByIdJPA(int IdUsuario) {
        Result result = new Result();

        try {
            TypedQuery<com.digis01.FNolascoProgramacionNCapas.JPA.Usuario> queryUsuarios = entityManager.createQuery("FROM Usuario WHERE IdUsuario = :idusuario", com.digis01.FNolascoProgramacionNCapas.JPA.Usuario.class);
            queryUsuarios.setParameter("idusuario", IdUsuario);

            com.digis01.FNolascoProgramacionNCapas.JPA.Usuario usuario = queryUsuarios.getSingleResult();

            UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
            usuarioDireccion.usuario = usuario;
            TypedQuery<com.digis01.FNolascoProgramacionNCapas.JPA.Direccion> queryDireccion = entityManager.createQuery("FROM Direccion WHERE Usuario.IdUsuario = :idusuario", com.digis01.FNolascoProgramacionNCapas.JPA.Direccion.class);
            queryDireccion.setParameter("idusuario", usuario.getIdUsuario());
            usuarioDireccion.Direcciones = queryDireccion.getResultList();
            result.object = usuarioDireccion;

            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }
//
//    @Transactional
//    @Override
//    public Result AddDireccionJPA(UsuarioDireccion usuarioDireccion) {
//        Result result = new Result();
//
//        try {
//
//            /*inserción de dirección*/
//            com.digis01.FNolascoProgramacionNCapas.JPA.Direccion direccionJPA
//                    = new com.digis01.FNolascoProgramacionNCapas.JPA.Direccion();
//            direccionJPA.setCalle(usuarioDireccion.Direccion.getCalle());
//            direccionJPA.setNumeroExterior(usuarioDireccion.Direccion.getNumeroExterior());
//            direccionJPA.setNumeroInterior(usuarioDireccion.Direccion.getNumeroInterior());
//
//            direccionJPA.Colonia = new com.digis01.FNolascoProgramacionNCapas.JPA.Colonia();
//            direccionJPA.Colonia.setIdColonia(usuarioDireccion.Direccion.Colonia.getIdColonia());
//
//            direccionJPA.Usuario = new com.digis01.FNolascoProgramacionNCapas.JPA.Usuario();
//            direccionJPA.Usuario.setIdUsuario(usuarioDireccion.Usuario.getIdUsuario());
//            entityManager.persist(direccionJPA);
//
//            System.out.println("");
//
//            result.correct = true;
//
//        } catch (Exception ex) {
//            result.correct = false;
//            result.errorMessage = ex.getLocalizedMessage();
//            result.ex = ex;
//        }
//
//        return result;
//    }
//
//    @Transactional
//    @Override
//    public Result UsuarioUpdateJPA(Usuario usuario) {
//        Result result = new Result();
//
//        try {
//
//            com.digis01.FNolascoProgramacionNCapas.JPA.Usuario usuarioJPA = new com.digis01.FNolascoProgramacionNCapas.JPA.Usuario();
//            usuarioJPA = entityManager.find(com.digis01.FNolascoProgramacionNCapas.JPA.Usuario.class, usuario.getIdUsuario());
//
//            usuarioJPA.setIdUsuario(usuario.getIdUsuario());
//            usuarioJPA.setUserName(usuario.getUserName());
//            usuarioJPA.setNombre(usuario.getNombre());
//            usuarioJPA.setApellidoPaterno(usuario.getApellidoPaterno());
//            usuarioJPA.setEmail(usuario.getEmail());
//            usuarioJPA.setSexo(usuario.getSexo());
//            usuarioJPA.setTelefono(usuario.getTelefono());
//            usuarioJPA.setCelular(usuario.getCelular());
//            usuarioJPA.setCurp(usuario.getCurp());
//            usuarioJPA.setApellidoMaterno(usuario.getApellidoMaterno());
//            usuarioJPA.setPassword(usuario.getPassword());
//            usuarioJPA.setFechaNacimiento(usuario.getFechaNacimiento());
//            usuarioJPA.setImagen(usuario.getImagen());
//
//            usuarioJPA.Roll = new com.digis01.FNolascoProgramacionNCapas.JPA.Roll();
//            usuarioJPA.Roll.setIdRoll(usuario.Roll.getIdRoll());
//
//            //vaciar alumno ML a alumno JPA
//            entityManager.merge(usuarioJPA);
//
//            System.out.println("");
//
//            result.correct = true;
//
//        } catch (Exception ex) {
//            result.correct = false;
//            result.errorMessage = ex.getLocalizedMessage();
//            result.ex = ex;
//        }
//
//        return result;
//    }
//
//    @Transactional
//    @Override
//    public Result DieccionUpdateJPA(UsuarioDireccion usuarioDireccion) {
//        Result result = new Result();
//
//        try {
//
//            com.digis01.FNolascoProgramacionNCapas.JPA.Direccion direccionJPA = new com.digis01.FNolascoProgramacionNCapas.JPA.Direccion();
////            direccionJPA = entityManager.find(com.digis01.FNolascoProgramacionNCapas.JPA.Direccion.class, direccion.getIdDireccion());
//
//            direccionJPA.setIdDireccion(usuarioDireccion.Direccion.getIdDireccion());
//            direccionJPA.setCalle(usuarioDireccion.Direccion.getCalle());
//            direccionJPA.setNumeroExterior(usuarioDireccion.Direccion.getNumeroExterior());
//            direccionJPA.setNumeroInterior(usuarioDireccion.Direccion.getNumeroInterior());
//
//            direccionJPA.Colonia = new com.digis01.FNolascoProgramacionNCapas.JPA.Colonia();
//            direccionJPA.Colonia.setIdColonia(usuarioDireccion.Direccion.Colonia.getIdColonia());
//            direccionJPA.Colonia.setNombre(usuarioDireccion.Direccion.Colonia.getNombre());
//            direccionJPA.Colonia.setCodigoPostal(usuarioDireccion.Direccion.Colonia.getCodigoPostal());
//
//            direccionJPA.Colonia.Municipio = new com.digis01.FNolascoProgramacionNCapas.JPA.Municipio();
//            direccionJPA.Colonia.Municipio.setIdMunicipio(usuarioDireccion.Direccion.Colonia.Municipio.getIdMunicipio());
//
//            direccionJPA.Colonia.Municipio.Estado = new com.digis01.FNolascoProgramacionNCapas.JPA.Estado();
//            direccionJPA.Colonia.Municipio.Estado.setIdEstado(usuarioDireccion.Direccion.Colonia.Municipio.Estado.getIdEstado());
//
//            direccionJPA.Colonia.Municipio.Estado.Pais = new com.digis01.FNolascoProgramacionNCapas.JPA.Pais();
//            direccionJPA.Colonia.Municipio.Estado.Pais.setIdPais(usuarioDireccion.Direccion.Colonia.Municipio.Estado.Pais.getIdPais());
//
//            direccionJPA.Usuario = new com.digis01.FNolascoProgramacionNCapas.JPA.Usuario();
//            direccionJPA.Usuario.setIdUsuario(usuarioDireccion.Usuario.getIdUsuario());
//
//            //vaciar alumno ML a alumno JPA
//            entityManager.merge(direccionJPA);
//
//            System.out.println("");
//
//            result.correct = true;
//
//        } catch (Exception ex) {
//            result.correct = false;
//            result.errorMessage = ex.getLocalizedMessage();
//            result.ex = ex;
//        }
//
//        return result;
//    }
//
//    @Transactional
//    @Override
//    public Result DireccionDeleteJPA(int IdDireccion) {
//        Result result = new Result();
//
//        try {
//
//            com.digis01.FNolascoProgramacionNCapas.JPA.Direccion direccion = new com.digis01.FNolascoProgramacionNCapas.JPA.Direccion();
//            direccion = entityManager.find(com.digis01.FNolascoProgramacionNCapas.JPA.Direccion.class, IdDireccion);
//
//            entityManager.remove(direccion);
//
//        } catch (Exception ex) {
//            result.correct = false;
//            result.errorMessage = ex.getLocalizedMessage();
//            result.ex = ex;
//        }
//
//        return result;
//    }
//
//    @Transactional
//    @Override
//    public Result DeleteUsuarioDireccionJPA(int IdUsuario) {
//        Result result = new Result();
//
//        try {
//          
//            com.digis01.FNolascoProgramacionNCapas.JPA.Usuario usuario = new com.digis01.FNolascoProgramacionNCapas.JPA.Usuario();
//            usuario = entityManager.find(com.digis01.FNolascoProgramacionNCapas.JPA.Usuario.class, IdUsuario);
//            
//            if(usuario != null){
//                TypedQuery<com.digis01.FNolascoProgramacionNCapas.JPA.Direccion> queryDirecciones = 
//                        entityManager.createQuery("FROM Direccion WHERE Usuario.IdUsuario = :IdUsuario", com.digis01.FNolascoProgramacionNCapas.JPA.Direccion.class);
//                queryDirecciones.setParameter("IdUsuario", IdUsuario);
//                
//                List<com.digis01.FNolascoProgramacionNCapas.JPA.Direccion> direcciones = queryDirecciones.getResultList();
//                
//                for(com.digis01.FNolascoProgramacionNCapas.JPA.Direccion direccion : direcciones){
//                    if(!entityManager.contains(direccion)){
//                        direccion = entityManager.merge(direccion);
//                    }
//                    entityManager.remove(direccion);
//                }
//                
//                if(!entityManager.contains(usuario)){
//                    usuario = entityManager.merge(usuario);
//                }
//                entityManager.remove(usuario);
//                
//                }
//            
//
//        } catch (Exception ex) {
//            result.correct = false;
//            result.errorMessage = ex.getLocalizedMessage();
//            result.ex = ex;
//        }
//

}
