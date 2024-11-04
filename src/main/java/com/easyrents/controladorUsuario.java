package main.java.com.easyrents;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class controladorUsuario {
    private vistaInicioSesion vistaInicioSesion; // Vista para el inicio de sesión
    private List<Usuario> listaUsuarios; // Lista de usuarios cargada desde el archivo CSV

    // Método para cargar usuarios desde un archivo CSV
    // Los usuarios se guardarán de la siguiente forma:
    // ID, nombre, correo, contraseña, tipoUsuario, numDocLicencia, numTelefono
    public ArrayList<Usuario> cargarUsuariosDesdeCSV() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("usuarios.csv"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] valores = linea.split(",");
                int ID = Integer.parseInt(valores[0]);
                String nombre = valores[1];
                String correo = valores[2];
                String contraseña = valores[3];
                String tipoUsuario = valores[4];
                long numDocLicencia = Long.parseLong(valores[5]);
                int numTelefono = Integer.parseInt(valores[6]);
                
                //crear lista de reservas desde el último campo
                List<String> reservas = Arrays.asList(valores[7].split(";"));

                //crear usuario con datos del csv
                Usuario usuario = new Usuario(ID, nombre, correo, contraseña, tipoUsuario, numDocLicencia, numTelefono, null);
                usuarios.add(usuario);
            }
        } catch (IOException e) {
            vistaInicioSesion.mostrarError("Error al cargar usuarios desde el archivo CSV: " + e.getMessage());
        }
        return usuarios;
    }

    // Método para guardar un nuevo usuario en el archivo CSV
    // Los usuarios se guardarán de la siguiente forma:
    // ID, nombre, correo, contraseña, tipoUsuario, numDocLicencia, numTelefono
    public void guardarUsuarioEnCSV(Usuario usuario) {
        String filePath = "usuarios.csv";
        File file = new File(filePath);
        //convierte las reservaciones en un solo String
        String reservasString = usuario.getReservasAsociadas()
                                        .stream()
                                        .map(Reserva::toString)
                                        .reduce((res1, res2) -> res1 + "," + res2)
                                        .orElse("");

        String userDataString = usuario.getID() + "," + usuario.getNombre() + "," + usuario.getCorreo() + "," +
                                usuario.getContraseña() + "," + usuario.getTipoUsuario() + "," + 
                                usuario.getNumDocLicencia() + "," + usuario.getNumTelefono() + "," + reservasString;
    
        if(file.exists()) {
            ArrayList<String> data = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    data.add(line);
                }
            } catch (IOException e) {
                System.out.println("Error al abrir el archivo, revise el nombre ingresado.");
            }
            data.add(userDataString);
            try (BufferedWriter br = new BufferedWriter(new FileWriter(filePath))) {
                for (String u : data) {
                    br.write(u);
                    br.newLine();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try (BufferedWriter br = new BufferedWriter(new FileWriter(filePath))) {
                br.write(userDataString);
                br.newLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Método para gestionar el inicio de sesión de un usuario
    public void iniciarSesion(String correo, String contraseña) {
        if (correo == null || contraseña == null || correo.isEmpty() || contraseña.isEmpty()) {
            vistaInicioSesion.mostrarError("El correo y la contraseña son obligatorios.");
            return;
        }
        // Nombre del archivo default: usuarios.csv
        ArrayList<Usuario> listaUsuarios = cargarUsuariosDesdeCSV();
        for (Usuario u : listaUsuarios){
            if(u.getCorreo().equals(correo) && contraseña.equals(u.getContraseña())){
                // FALTA
            }
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
            guardarUsuarioEnCSV(nuevoUsuario); // Guardar en CSV
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
