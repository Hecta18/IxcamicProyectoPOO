package com.easyrents;

import java.time.LocalDate;
import java.util.Objects;

public class Reserva {
    private int id;
    private Usuario usuario;
    private Vehiculo vehiculo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double monto;
    private String estado;

    //METODO CONSTRUCTOR
    public Reserva(int id, Usuario usuario, Vehiculo vehiculo, LocalDate fechaInicio, LocalDate fechaFin, double monto) {
        this.id = id;
        this.usuario = usuario;
        this.vehiculo = vehiculo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.monto = monto;
        this.estado = "activa";
        //validacion
        setFechaFin(fechaFin);
        setMonto(monto);
    }
    
    //GETTERS
    public int getId() {return id;}
    public Usuario getUsuario() {return usuario;}
    public Vehiculo getVehiculo() {return vehiculo;}
    public LocalDate getFechaInicio() {return fechaInicio;}
    public LocalDate getFechaFin() {return fechaFin;}
    public double getMonto() {return monto;}
    public String getEstado() {return estado;}
    
    //SETTERS
    public void setId(int id) {this.id = id;}    
    public void setUsuario(Usuario usuario) {this.usuario = usuario;}
    public void setVehiculo(Vehiculo vehiculo) {this.vehiculo = vehiculo;}
    public void setFechaInicio(LocalDate fechaInicio) {this.fechaInicio = fechaInicio;}
    public void setEstado(String estado) {this.estado = estado;}
    //validar fecha final despues de inicial
    public void setFechaFin(LocalDate fechaFin) {
        if (fechaFin.isAfter(fechaInicio)) {
            this.fechaFin = fechaFin;
        } else {
            throw new IllegalArgumentException("La fecha final debe ser posterior a la fecha de inicio");
        }
    }
    //validar monto mayor 0
    public void setMonto(double monto) {
        if (monto > 0) {
            this.monto = monto;
        } else {
            throw new IllegalArgumentException("El monto ingresado no es valido");
        }
    }
    // Método para cancelar la reserva
    public void cancelar() {
        this.estado = "cancelada"; // Cambiar el estado a "cancelada"
    }
    // Sobrescribir equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reserva reserva = (Reserva) o;
        return id == reserva.id &&
               Double.compare(reserva.monto, monto) == 0 &&
               Objects.equals(usuario, reserva.usuario) &&
               Objects.equals(vehiculo, reserva.vehiculo) &&
               Objects.equals(fechaInicio, reserva.fechaInicio) &&
               Objects.equals(fechaFin, reserva.fechaFin);
    }

    // Sobrescribir hashCode
    @Override
    public int hashCode() {
        return Objects.hash(id, usuario, vehiculo, fechaInicio, fechaFin, monto);
    }
    //TOSTRING
    @Override
    public String toString() {
        return "Reserva [id=" + id + ", usuario=" + usuario + ", vehiculo=" + vehiculo + ", fechaInicio=" + fechaInicio
                + ", fechaFin=" + fechaFin + ", monto=" + monto + "]";
    }
}