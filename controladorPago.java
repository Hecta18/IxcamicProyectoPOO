package com.easyrents;

import java.time.LocalDate;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import main.java.com.easyrents.enumMetodoPago;
import main.java.com.easyrents.vistaFacturacion;

public class controladorPago {
    private Pago pay;
    private vistaFacturacion vistaPay;
    private List<Reserva> listaReserva; // Esto debería conectarse a la base de datos
    private controladorFacturacion controladorFactura;
    private Set<enumMetodoPago> metodosPagoValidos = EnumSet.allOf(enumMetodoPago.class);

    // Constructor
    public controladorPago(Pago pay, vistaPago vistaPay, controladorFacturacion controladorFactura){
        this.pay = pay;
        this.vistaPay = vistaPay;
        this.controladorFactura = controladorFactura;
    }

    // Método para validar el método de pago
    public boolean validarMetodoPago(String metodoPago) {
        //iterar set de enums
        for (enumMetodoPago metodo : metodosPagoValidos) {
            //comparar por string
            if (metodo.getMetodo().equalsIgnoreCase(metodoPago)) {
                return true;
            }
        }
        vistaPay.mostrarError("Método de pago no válido. Los métodos permitidos son: " + metodosPagoValidos);
        return false;
    }

    // Método para realizar un pay
    public void realizarPago(int reservaId, String metodoPago) {
        if (reservaId <= 0 || metodoPago == null || metodoPago.isEmpty()) {
            vistaPay.mostrarError("El ID de la reserva y el método de pay son obligatorios.");
            return;
        }
        if (!validarMetodoPago(metodoPago)) {
            vistaPay.mostrarMensaje("Método de pay no válido. Los métodos permitidos son: " + metodosPagoValidos);
            return;
        } else {
            for (Reserva reserva : listaReserva) { // Obtener reserva de la base de datos
                if (reserva.getId() == reservaId) {
                    new LocalDate();
                    // Crear el pay
                    pay = new Pago(reservaId, reserva, reserva.getMonto(), LocalDate.now(), metodoPago);
                    // Mostrar éxito en la vista
                    vistaPay.mostrarMensaje("Pago procesado exitosamente para la reserva ID: " + reservaId);
                    // Generar factura automáticamente tras el pay
                    controladorFactura.generarFactura(pay);
                    return;
                }
                // Si no se encuentra la reserva
                vistaPay.mostrarError("No se encontró la reserva con ID " + reservaId);
            }
        }
    }
}
