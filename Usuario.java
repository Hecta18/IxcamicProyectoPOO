package com.easyrents;

import java.util.Objects;

public class Usuario {
    private int id;
    private String nombre;
    private String correo;
    private String contraseña;
    private String tipoUsuario;
    private long numDocLicencia;
    private int numTelefono;
    
    //METODO CONSTRUCTOR
    public Usuario(int id, String nombre, String correo, String contraseña, String tipoUsuario, long numDocLicencia,
        int numTelefono) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        //por medio del set, para que haya una validacion de la estructura de la contrasena
        setContraseña(contraseña);
        this.tipoUsuario = tipoUsuario;
        this.numDocLicencia = numDocLicencia;
        this.numTelefono = numTelefono;
    }

    //GETTERS
    public int getID(){return id;}
    public String getNombre(){return nombre;}
    public String getCorreo(){return correo;}
    //eliminar getter de contrasena para mejorar seguridad?
    public String getContraseña(){return contraseña;}
    public String getTipoUsuario(){return tipoUsuario;}
    public long getNumDocLicencia(){return numDocLicencia;}
    public int getNumTelefono(){return numTelefono;}

    //SETTERS
    public void setID(int id){this.id = id;}
    public void setNombre(String nombre){this.nombre = nombre;}
    public void setCorreo(String correo){this.correo = correo;}
    public void setContraseña(String contraseña) { 
        // Validar contraseña
        if (contraseña.length() >= 6) {
            this.contraseña = contraseña;
        } else {
            throw new IllegalArgumentException("La contraseña debe tener al menos 6 caracteres");
        }
    }
    public void setTipoUsuario(String tipoUsuario){this.tipoUsuario = tipoUsuario;}
    public void setNumDocLicencia(long numDocLicencia){this.numDocLicencia = numDocLicencia;}
    public void setNumTelefono(int numTelefono){this.numTelefono = numTelefono;}

    //EQUALS
    @Override
    public boolean equals(Object o) {
        //valida si es el mismo objeto de la misma clase
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        //valida todos los atributos de los objetos
        return id == usuario.id &&
               numDocLicencia == usuario.numDocLicencia &&
               numTelefono == usuario.numTelefono &&
               Objects.equals(nombre, usuario.nombre) &&
               Objects.equals(correo, usuario.correo) &&
               Objects.equals(contraseña, usuario.contraseña) &&
               Objects.equals(tipoUsuario, usuario.tipoUsuario);
    }

    // HASHCODE
    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, correo, contraseña, tipoUsuario, numDocLicencia, numTelefono);
    }

    //TOSTRING
    @Override
    public String toString() {
        return "Usuario [id=" + id + ", nombre=" + nombre + ", correo=" + correo + ", contraseña=" + contraseña
                + ", tipoUsuario=" + tipoUsuario  +", numDocLicencia=" + numDocLicencia + ", numTelefono=" + numTelefono + "]";
    }
}