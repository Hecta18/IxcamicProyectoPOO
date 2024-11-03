package com.easyrents;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class controladorReservacion {
    private vistaReservacion vistaReservacion; // Vista que interactúa con el usuario para la reservación
    private List<Reserva> listaReserva; // Lista de reservas cargada desde un archivo CSV
    private int idReserva = 0; // Contador de identificador de reserva

    // Constructor que recibe la vista de reservaciones
    public controladorReservacion(vistaReservacion vistaReservacion) {
        this.vistaReservacion = vistaReservacion;
        this.listaReserva = cargarReservasDesdeCSV("reservas.csv"); // Cargar reservas desde CSV
    }

    // Método para cargar reservas desde un archivo CSV
    private List<Reserva> cargarReservasDesdeCSV(String filePath) {
        List<Reserva> reservas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] valores = linea.split(",");
                int id = Integer.parseInt(valores[0]);
                String usuario = valores[1];
                String vehiculo = valores[2];
                LocalDate fechaInicio = LocalDate.parse(valores[3]);
                LocalDate fechaFin = LocalDate.parse(valores[4]);
                double monto = Double.parseDouble(valores[5]);
                Reserva reserva = new Reserva(id, usuario, vehiculo, fechaInicio, fechaFin, monto);
                reservas.add(reserva);
            }
        } catch (IOException e) {
            vistaReservacion.mostrarError("Error al cargar reservas desde el archivo CSV: " + e.getMessage());
        }
        return reservas;
    }

    // Método para guardar reservas en el archivo CSV
    private void guardarReservasEnCSV(String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Reserva reserva : listaReserva) {
                bw.write(reserva.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            vistaReservacion.mostrarError("Error al guardar reservas en el archivo CSV: " + e.getMessage());
        }
    }

    // Método para calcular el monto total de la reservación basado en los días y la tarifa diaria del vehículo
    private double calcularTotal(LocalDate fechaInicio, LocalDate fechaFin, double tarifaDiaria) {
        long dias = java.time.temporal.ChronoUnit.DAYS.between(fechaInicio, fechaFin);
        return dias * tarifaDiaria;
    }

    // Método para crear una nueva reservación
    public Optional<Reserva> crearReservacion(Usuario usuario, Vehiculo vehiculo, LocalDate fechaInicio, LocalDate fechaFin) {
        if (usuario == null || vehiculo == null || fechaInicio == null || fechaFin == null) {
            vistaReservacion.mostrarError("Por favor, complete todos los campos requeridos.");
            return Optional.empty();
        }

        double monto = calcularTotal(fechaInicio, fechaFin, vehiculo.getTarifaDiaria());
        if (monto <= 0) {
            vistaReservacion.mostrarError("El monto total no puede ser cero o negativo.");
            return Optional.empty();
        }

        idReserva++;
        Reserva reserva = new Reserva(idReserva, usuario, vehiculo, fechaInicio, fechaFin, monto);
        listaReserva.add(reserva); // Agregar la reserva a la lista
        guardarReservasEnCSV("reservas.csv"); // Guardar cambios en CSV
        vistaReservacion.mostrarConfirmacion("Reservación creada exitosamente.");
        return Optional.of(reserva);
    }

    // Método para modificar una reservación existente
    public void modificarReserva(int idReserva, Vehiculo nuevoVehiculo, LocalDate nuevaFechaInicio, LocalDate nuevaFechaFin) {
        if (idReserva <= 0 || nuevoVehiculo == null || nuevaFechaInicio == null || nuevaFechaFin == null) {
            vistaReservacion.mostrarError("Datos inválidos. Por favor, inténtelo de nuevo.");
            return;
        }

        double nuevoMonto = calcularTotal(nuevaFechaInicio, nuevaFechaFin, nuevoVehiculo.getTarifaDiaria());

        for (Reserva reserva : listaReserva) {
            if (reserva.getId() == idReserva) {
                reserva.setVehiculo(nuevoVehiculo);
                reserva.setFechaInicio(nuevaFechaInicio);
                reserva.setFechaFin(nuevaFechaFin);
                reserva.setMonto(nuevoMonto);
                guardarReservasEnCSV("reservas.csv"); // Guardar cambios en CSV
                vistaReservacion.mostrarConfirmacion("La reservación ha sido modificada con éxito.");
                return;
            }
        }
        vistaReservacion.mostrarError("No se encontró la reservación con ID: " + idReserva);
    }

    // Método para cancelar una reservación existente
    public void cancelarReserva(int idReserva) {
        if (idReserva <= 0) {
            vistaReservacion.mostrarError("ID de reserva inválido.");
            return;
        }

        for (Reserva reserva : listaReserva) {
            if (reserva.getId() == idReserva) {
                reserva.cancelar(); // Cambiar el estado a cancelado
                guardarReservasEnCSV("reservas.csv"); // Guardar cambios en CSV
                vistaReservacion.mostrarConfirmacion("La reservación ha sido cancelada exitosamente.");
                return;
            }
        }
        vistaReservacion.mostrarError("No se encontró la reservación con ID: " + idReserva);
    }
}