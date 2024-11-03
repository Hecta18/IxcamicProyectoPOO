package com.easyrents;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import main.java.com.easyrents.enumMetodoPago;
//import main.java.com.easyrents.vistaFacturacion;

public class controladorPago {
    private Pago pay;
    private vistaFacturacion vistaPay;
    private List<Reserva> listaReserva; // Lista cargada desde archivo CSV
    //private controladorFacturacion controladorFactura;
    private Set<enumMetodoPago> metodosPagoValidos = EnumSet.allOf(enumMetodoPago.class);

    // Constructor
    public controladorPago(Pago pay, vistaFacturacion vistaPay) {
        this.pay = pay;
        this.vistaPay = vistaPay;
        //this.controladorFactura = controladorFactura;
        this.listaReserva = cargarReservasDesdeCSV("reservas.csv"); // Cargar reservas desde CSV
    }

    // Método para cargar reservas desde un archivo CSV
    private List<Reserva> cargarReservasDesdeCSV(String filePath) {
        List<Reserva> reservas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            //String linea;
            while ((linea = br.readLine()) != null) {
                //String[] valores = linea.split(",");
                // Asumimos que el CSV tiene las columnas: id, monto, otros valores...
                //int id = Integer.parseInt(valores[0]);
                //double monto = Double.parseDouble(valores[1]);
                // Crear objeto Reserva y agregar a la lista
                //Reserva reserva = new Reserva(id, monto);
                //reservas.add(reserva);
            }
        } catch (IOException e) {
            vistaPay.mostrarError("Error al cargar reservas desde el archivo CSV: " + e.getMessage());
        }
        return reservas;
    }

    // Método para validar el método de pago
    public boolean validarMetodoPago(String metodoPago) {
        for (enumMetodoPago metodo : metodosPagoValidos) {
            if (metodo.getMetodo().equalsIgnoreCase(metodoPago)) {
                return true;
            }
        }
        vistaPay.mostrarError("Método de pago no válido. Los métodos permitidos son: " + metodosPagoValidos);
        return false;
    }

    // Método para realizar un pago
    public void realizarPago(int reservaId, String metodoPago) {
        if (reservaId <= 0 || metodoPago == null || metodoPago.isEmpty()) {
            vistaPay.mostrarError("El ID de la reserva y el método de pago son obligatorios.");
            return;
        }
        if (!validarMetodoPago(metodoPago)) {
            vistaPay.mostrarMensaje("Método de pago no válido. Los métodos permitidos son: " + metodosPagoValidos);
            return;
        } else {
            for (Reserva reserva : listaReserva) { // Obtener reserva desde la lista cargada
                if (reserva.getId() == reservaId) {
                    // Crear el pago
                    pay = new Pago(reservaId, reserva, reserva.getMonto(), LocalDate.now(), metodoPago);
                    // Mostrar éxito en la vista
                    vistaPay.mostrarMensaje("Pago procesado exitosamente para la reserva ID: " + reservaId);
                    // Generar factura automáticamente tras el pago
                    //controladorFactura.generarFactura(pay);
                    return;
                }
            }
            // Si no se encuentra la reserva
            vistaPay.mostrarError("No se encontró la reserva con ID " + reservaId);
        }
    }
}
