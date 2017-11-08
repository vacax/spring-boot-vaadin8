package edu.pucm.springbootvaadin8.services;

import edu.pucm.springbootvaadin8.orm.Usuario;
import org.springframework.stereotype.Service;

/**
 * TODO: pendiente completar
 */
@Service
public class SeguridadService {


    /**
     * Validando el usuario...
     * @param usuario
     * @param contrasena
     * @return
     */
    public Usuario validarUsuario(String usuario, String contrasena){
       if(usuario.equalsIgnoreCase("admin") && contrasena.equalsIgnoreCase("admin")){
         return new Usuario("admin", "admin", "Administrador");
       }
       return null;
    }

}
