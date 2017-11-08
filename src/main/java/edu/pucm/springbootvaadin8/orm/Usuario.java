package edu.pucm.springbootvaadin8.orm;

/**
 * Pendiente de completar a JPA.
 */
public class Usuario {

    String username;
    String contrasena;
    String nombre;

    public Usuario() {
    }

    public Usuario(String username, String contrasena, String nombre) {
        this.username = username;
        this.contrasena = contrasena;
        this.nombre = nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
