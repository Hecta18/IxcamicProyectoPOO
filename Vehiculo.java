package com.easyrents;

import java.time.Year;
import java.util.Objects;

public class Vehiculo {
    private int id;
    private String marca;
    private String modelo;
    private int año;
    private String tipo;
    private double tarifaDiaria;
    private boolean disponible;

    //METODO CONSTRUCTOR
    public Vehiculo(int id, String marca, String modelo, int año, String tipo, double tarifaDiaria, boolean disponible) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        //ingreso por set para validacion
        setAño(año);
        this.tipo = tipo;
        //ingreso por set para validacion
        setTarifaDiaria(tarifaDiaria);
        this.disponible = disponible;
    }
    
    //GETTERS
    public int getID(){return id;}
    public String getMarca(){return marca;}
    public String getModelo(){return modelo;}
    public int getAño(){return año;}
    public String getTipo(){return tipo;}
    public double getTarifaDiaria(){return tarifaDiaria;}
    public boolean getDisponible(){return disponible;}

    //SETTERS
    public void setID(int id){this.id = id;}
    public void setMarca(String marca){this.marca = marca;}
    public void setModelo(String modelo){this.modelo = modelo;}
    //valida que el año sea mayor a 1885 y menor o igual al actual
    public void setAño(int año){
        if (año <= Year.now().getValue() && año >= 1885) {
            this.año = año;
        } else {
            throw new IllegalArgumentException("El año que ingreso no es valido");
        }
    }
    public void setTipo(String tipo){this.tipo = tipo;}
    //valida tarifaDiaria mayor a 0
    public void setTarifaDiaria(double tarifaDiaria){
        if (tarifaDiaria > 0) {
            this.tarifaDiaria = tarifaDiaria;
        } else {
            throw new IllegalArgumentException("El tarifaDiaria ingresado no es valido");
        }
    }
    public void setDisponible(boolean disponible){this.disponible = disponible;}

    //EQUALS
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Mismo objeto
        if (!(obj instanceof Vehiculo)) return false; // Comparar con otro tipo

        Vehiculo other = (Vehiculo) obj; // Convertir a Vehiculo
        return id == other.id &&
            marca.equals(other.marca) &&
            modelo.equals(other.modelo) &&
            año == other.año; // Considerando año como parte de la igualdad
    }

    //HASHCODE
    @Override
    public int hashCode() {
        return Objects.hash(id, marca, modelo, año, tipo, tarifaDiaria, disponible);
    }
    
    //TOSTRING
    @Override
    public String toString() {
        return "Vehiculo [id=" + id + ", marca=" + marca + ", modelo=" + modelo + ", año=" + año + ", tipo=" + tipo
                + ", tarifaDiaria=" + tarifaDiaria + ", disponible=" + disponible + "]";
    }
}