package main.java.com.easyrents;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class controladorUsuario {
    private vistaInicioSesion vistaInicioSesion; // Vista para el inicio de sesión
    private ArrayList<Usuario> listaUsuarios; // Lista de usuarios cargada desde el archivo CSV
    
        // Método para cargar usuarios desde un archivo CSV
        // Los usuarios se guardarán de la siguiente forma:
        // ID, nombre, correo, contraseña, tipoUsuario, numDocLicencia, numTelefono, reservasActuales
        public ArrayList<Usuario> cargarUsuariosDesdeCSV() {
            ArrayList<Usuario> usuarios = new ArrayList<>();
            File archivo = new File("usuarios.csv");
            if (!archivo.exists() || archivo.length() == 0) {
                return usuarios;
            }else{
                try (BufferedReader br = new BufferedReader(new FileReader("usuarios.csv"))) {
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        String[] valores = linea.split(",", 8);
                        int ID = Integer.parseInt(valores[0]);
                        String nombre = valores[1];
                        String correo = valores[2];
                        String contraseña = valores[3];
                        String tipoUsuario = valores[4];
                        long numDocLicencia = Long.parseLong(valores[5]);
                        int numTelefono = Integer.parseInt(valores[6]);
                        String reservasArray = valores[7].substring(1, valores[7].length() - 1); // Quitar los {} de reservas

                        ArrayList<Reserva> reservas = new ArrayList<>();
                        String[] reservasStrings = reservasArray.split("},\\{");
                        
                        for (String reservaStr : reservasStrings) {
                            String[] reservaData = reservaStr.split(",", 6);
                            int reservaID = Integer.parseInt(reservaData[0]);
                            
                            // Datos del vehículo
                            String[] vehiculoData = reservaData[1].substring(1, reservaData[1].length() - 1).split(",");
                            int vehiculoID = Integer.parseInt(vehiculoData[0]);
                            String marca = vehiculoData[1];
                            String modelo = vehiculoData[2];
                            int año = Integer.parseInt(vehiculoData[3]);
                            String tipo = vehiculoData[4];
                            double tarifaDiaria = Double.parseDouble(vehiculoData[5]);
                            boolean disponible = Boolean.parseBoolean(vehiculoData[6]);

                            // Instanciar el vehículo y la reserva
                            Vehiculo vehiculo = new Vehiculo(vehiculoID, marca, modelo, año, tipo, tarifaDiaria, disponible);
                            LocalDate fechaInicio = LocalDate.parse(reservaData[2]);
                            LocalDate fechaFin = LocalDate.parse(reservaData[3]);
                            double monto = Double.parseDouble(reservaData[4]);
                            String estado = reservaData[5];

                            Reserva reserva = new Reserva(reservaID, vehiculo, fechaInicio, fechaFin, monto);
                            reservas.add(reserva);
                        }

                        Usuario usuario = new Usuario(ID, nombre, correo, contraseña, tipoUsuario, numDocLicencia, numTelefono, reservas);
                        usuarios.add(usuario);
                        }
                    }catch (IOException e) {
                    JOptionPane.showMessageDialog(null,"Error al cargar usuarios desde el archivo CSV: " + e.getMessage());
                }
                return usuarios;
            }
        }
    
        // Método para guardar un nuevo usuario en el archivo CSV
        // Los usuarios se guardarán de la siguiente forma:
        // ID, nombre, correo, contraseña, tipoUsuario, numDocLicencia, numTelefono
        public void guardarUsuarioEnCSV(Usuario usuario) {
            String filePath = "usuarios.csv";
            // Convertir reservas y vehículos asociados en un solo string
            String reservasString = usuario.getReservasAsociadas().stream()
                .map(reserva -> {
                    Vehiculo vehiculo = reserva.getVehiculo();
                    // Cambiar de una vez a los tipos de datos que se necesiten:
                    return String.format("{%d,{%d,%s,%s,%d,%s,%.2f,%b},%s,%s,%.2f,%s}",
                        reserva.getId(),
                        vehiculo.getID(),
                        vehiculo.getMarca(),
                        vehiculo.getModelo(),
                        vehiculo.getAño(),
                        vehiculo.getTipo(),
                        vehiculo.getTarifaDiaria(),
                        vehiculo.getDisponible(),
                        reserva.getFechaInicio(),
                        reserva.getFechaFin(),
                        reserva.getMonto(),
                        reserva.getEstado()
                    );
                }).reduce((res1, res2) -> res1 + "," + res2).orElse("");

            // Convertir el usuario en un string siguiendo el formato especificado
            String userDataString = String.format("%d,%s,%s,%s,%s,%d,%d,{%s}",
                usuario.getID(),
                usuario.getNombre(),
                usuario.getCorreo(),
                usuario.getContraseña(),
                usuario.getTipoUsuario(),
                usuario.getNumDocLicencia(),
                usuario.getNumTelefono(),
                reservasString
            );
            System.out.println(usuario.getTipoUsuario());

            try (BufferedWriter br = new BufferedWriter(new FileWriter(filePath, true))) {
                br.write(userDataString);
                br.newLine();
            } catch (IOException e) {
                throw new RuntimeException("Error al guardar el usuario en el archivo CSV: " + e.getMessage());
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

    //metodo para actualizar la reserva de un usuario 
    public void actualizarReservasUsuario(int userID,  ArrayList<Reserva> nuevasReservas){
        //buscar el usuario dentro de la lista
        Usuario usuario = null;
        for (Usuario u : listaUsuarios) {
            if (u.getID() == userID) {
                usuario = u;
                break;
            }
        }
    
        if (usuario != null) {
            // Actualizar las reservas del usuario encontrado
            usuario.setReservasAsociadas(nuevasReservas);
    
            // Guardar la lista completa de usuarios en el archivo CSV
            guardarListaUsuariosEnCSV(listaUsuarios);
            
            JOptionPane.showMessageDialog(null, "Reservas actualizadas exitosamente para el usuario con ID " + userID);
        } else {
            JOptionPane.showMessageDialog(null, "Usuario con ID " + userID + " no encontrado.");
        }
    }

    public void guardarListaUsuariosEnCSV(ArrayList<Usuario> usuarios) {
        String filePath = "usuarios.csv";
        try (BufferedWriter br = new BufferedWriter(new FileWriter(filePath))) {
            for (Usuario usuario : usuarios) {
                // Convertir las reservas y vehículos del usuario en un solo string
                String reservasString = usuario.getReservasAsociadas().stream()
                    .map(reserva -> {
                        Vehiculo vehiculo = reserva.getVehiculo();
                        return String.format("{%d,{%d,%s,%s,%d,%s,%.2f,%b},%s,%s,%.2f,%s}",
                            reserva.getId(),
                            vehiculo.getID(),
                            vehiculo.getMarca(),
                            vehiculo.getModelo(),
                            vehiculo.getAño(),
                            vehiculo.getTipo(),
                            vehiculo.getTarifaDiaria(),
                            vehiculo.getDisponible(),
                            reserva.getFechaInicio(),
                            reserva.getFechaFin(),
                            reserva.getMonto(),
                            reserva.getEstado()
                        );
                    }).reduce((res1, res2) -> res1 + "," + res2).orElse("");
    
                // Convertir los datos del usuario en un string conforme al formato
                String userDataString = String.format("%d,%s,%s,%s,%s,%d,%d,{%s}",
                    usuario.getID(),
                    usuario.getNombre(),
                    usuario.getCorreo(),
                    usuario.getContraseña(),
                    usuario.getTipoUsuario(),
                    usuario.getNumDocLicencia(),
                    usuario.getNumTelefono(),
                    reservasString
                );
    
                br.write(userDataString);
                br.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar la lista de usuarios en el archivo CSV: " + e.getMessage());
        }
    }
}
