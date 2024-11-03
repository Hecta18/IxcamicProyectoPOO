package com.easyrents;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class controladorUsuario {
    private vistaInicioSesion vistaInicioSesion; // Vista para el inicio de sesión
    private List<Usuario> listaUsuarios; // Lista de usuarios cargada desde el archivo CSV
    private Usuario usuarioActual; // Usuario actualmente autenticado

    // Constructor que inicializa la vista y carga usuarios desde el archivo CSV
    public controladorUsuario(vistaInicioSesion vistaInicioSesion) {
        this.vistaInicioSesion = vistaInicioSesion;
        this.listaUsuarios = cargarUsuariosDesdeCSV("usuarios.csv"); // Cargar usuarios desde CSV
    }

    // Método para cargar usuarios desde un archivo CSV
    private List<Usuario> cargarUsuariosDesdeCSV(String filePath) {
        List<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] valores = linea.split(",");
                String correo = valores[0];
                String contraseña = valores[1];
                Usuario usuario = new Usuario(correo, contraseña); // Crear usuario con datos del CSV
                usuarios.add(usuario);
            }
        } catch (IOException e) {
            vistaInicioSesion.mostrarError("Error al cargar usuarios desde el archivo CSV: " + e.getMessage());
        }
        return usuarios;
    }

    // Método para guardar un nuevo usuario en el archivo CSV
    private void guardarUsuarioEnCSV(Usuario usuario, String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write(usuario.toCSV()); // Asume que Usuario tiene un método `toCSV` para formateo
            bw.newLine();
        } catch (IOException e) {
            vistaInicioSesion.mostrarError("Error al guardar usuario en el archivo CSV: " + e.getMessage());
        }
    }

    // Método para gestionar el inicio de sesión de un usuario
    public void iniciarSesion(String correo, String contraseña) {
        if (correo == null || contraseña == null || correo.isEmpty() || contraseña.isEmpty()) {
            vistaInicioSesion.mostrarError("El correo y la contraseña son obligatorios.");
            return;
        }

        Usuario usuario = obtenerUsuarioPorCorreo(correo); // Buscar usuario en la lista
        if (usuario != null && usuario.getContraseña().equals(contraseña)) {
            usuarioActual = usuario; // Autenticar al usuario
            vistaInicioSesion.mostrarExito("Inicio de sesión exitoso.");
        } else {
            vistaInicioSesion.mostrarError("Correo o contraseña incorrectos.");
        }
    }

    // Método para registrar un nuevo usuario en el sistema
    public void registrarUsuario(Usuario nuevoUsuario) {
        if (nuevoUsuario == null || nuevoUsuario.getCorreo().isEmpty() || nuevoUsuario.getContraseña().isEmpty()) {
            vistaInicioSesion.mostrarError("Datos inválidos para el registro.");
            return;
        }

        Usuario usuarioExistente = obtenerUsuarioPorCorreo(nuevoUsuario.getCorreo());
        if (usuarioExistente == null) {
            listaUsuarios.add(nuevoUsuario); // Añadir usuario a la lista
            guardarUsuarioEnCSV(nuevoUsuario, "usuarios.csv"); // Guardar en CSV
            vistaInicioSesion.mostrarExito("Usuario registrado exitosamente.");
        } else {
            vistaInicioSesion.mostrarError("El usuario ya existe.");
        }
    }

    // Método para buscar un usuario por correo en la lista cargada desde el CSV
    private Usuario obtenerUsuarioPorCorreo(String correo) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getCorreo().equals(correo)) {
                return usuario;
            }
        }
        return null;
    }
}
