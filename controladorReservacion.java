package com.easyrents;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class controladorReservacion {
    private vistaReservacion vistaReservacion; // Vista que interactúa con el usuario para la reservación
    private List<Reserva> listaReserva; // Lista de reservas, idealmente obtenida desde la base de datos
    private int idReserva = 0; // Contador de identificador de reserva, incrementa con cada nueva reservación

    // Constructor que recibe la vista de reservaciones
    public controladorReservacion(vistaReservacion vistaReservacion) {
        this.vistaReservacion = vistaReservacion;
    }

    // Método para calcular el monto total de la reservación basado en los días y la tarifa diaria del vehículo
    private double calcularTotal(LocalDate fechaInicio, LocalDate fechaFin, double tarifaDiaria) {
        long dias = java.time.temporal.ChronoUnit.DAYS.between(fechaInicio, fechaFin); // Calcula el número de días entre las fechas
        return dias * tarifaDiaria; // Devuelve el monto total multiplicando días por la tarifa diaria
    }

    // Método para crear una nueva reservación
    public Optional<Reserva> crearReservacion(Usuario usuario, Vehiculo vehiculo, LocalDate fechaInicio, LocalDate fechaFin) {
        // Validación de que todos los datos necesarios están presentes
        if (usuario == null || vehiculo == null || fechaInicio == null || fechaFin == null) {
            vistaReservacion.mostrarError("Por favor, complete todos los campos requeridos.");
            return Optional.empty(); // Retorna vacío si faltan datos
        }

        // Calcula el monto total de la reservación
        double monto = calcularTotal(fechaInicio, fechaFin, vehiculo.getTarifaDiaria());
        // Validación para evitar montos negativos o cero
        if (monto <= 0) {
            vistaReservacion.mostrarError("El monto total no puede ser cero o negativo.");
            return Optional.empty();
        }

        idReserva++; // Incrementa el contador de ID para la nueva reserva
        // Crea una nueva instancia de Reserva con los datos proporcionados
        Reserva reserva = new Reserva(idReserva, usuario, vehiculo, fechaInicio, fechaFin, monto);
        vistaReservacion.mostrarConfirmacion("Reservación creada exitosamente."); // Mensaje de éxito
        return Optional.of(reserva); // Retorna la reservación creada
    }

    // Método para modificar una reservación existente
    public void modificarReserva(int idReserva, Vehiculo nuevoVehiculo, LocalDate nuevaFechaInicio, LocalDate nuevaFechaFin) {
        // Validación de que los datos proporcionados son válidos
        if (idReserva <= 0 || nuevoVehiculo == null || nuevaFechaInicio == null || nuevaFechaFin == null) {
            vistaReservacion.mostrarError("Datos inválidos. Por favor, inténtelo de nuevo.");
            return;
        }

        // Calcula el nuevo monto total con los cambios en fechas o vehículo
        double nuevoMonto = calcularTotal(nuevaFechaInicio, nuevaFechaFin, nuevoVehiculo.getTarifaDiaria());

        // Recorre la lista de reservas para encontrar la reserva que se desea modificar
        for (Reserva reserva : listaReserva) {
            if (reserva.getId() == idReserva) {
                // Actualiza los datos de la reservación con los nuevos valores
                reserva.setVehiculo(nuevoVehiculo);
                reserva.setFechaInicio(nuevaFechaInicio);
                reserva.setFechaFin(nuevaFechaFin);
                reserva.setMonto(nuevoMonto);
                vistaReservacion.mostrarConfirmacion("La reservación ha sido modificada con éxito."); // Mensaje de éxito
                return;
            }
        }
        // Si no se encuentra la reserva con el ID proporcionado
        vistaReservacion.mostrarError("No se encontró la reservación con ID: " + idReserva);
    }

    // Método para cancelar una reservación existente
    public void cancelarReserva(int idReserva) {
        // Validación del ID de reserva
        if (idReserva <= 0) {
            vistaReservacion.mostrarError("ID de reserva inválido.");
            return;
        }

        // Recorre la lista de reservas para encontrar la que se desea cancelar
        for (Reserva reserva : listaReserva) {
            if (reserva.getId() == idReserva) {
                // Lógica para cancelar la reservación. Esto debería reflejarse en la base de datos
                reserva.cancelar(); // Llama al método de la clase `Reserva` que cambia el estado a "cancelado"
                vistaReservacion.mostrarConfirmacion("La reservación ha sido cancelada exitosamente."); // Mensaje de éxito
                return;
            }
        }
        // Si no se encuentra la reserva con el ID proporcionado
        vistaReservacion.mostrarError("No se encontró la reservación con ID: " + idReserva);
    }
}
