package com.easyrents;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Clase controladorVehiculo que maneja la lógica de búsqueda de vehículos
// y la interacción con la vista de búsqueda de vehículos.
public class controladorVehiculo {
    private vistaBusquedaVehiculos vistaBusqueda; // Vista para la búsqueda de vehículos
    //LA LISTA DE VEHICULOS SE DEBE DE OBTENER DE LA BASE DE DATOS EN EL MAIN
    //POR ELLO SE BORRO DE ESTA CLASE

    // Constructor de la clase controladorVehiculo
    public controladorVehiculo(vistaBusquedaVehiculos vistaBusqueda) {
        this.vistaBusqueda = vistaBusqueda; // Inicializa la vista de búsqueda
    }

    //compara los datos ingresados con los datos de los carros en el sistema y devuelve una lista con todos los carros que compartan datos
    public Optional<Vehiculo> buscarVehiculos(String marca, String modelo, String tipo, int vehiculoId, List<Vehiculo> listaVehiculos) {
        //metodo devuelve una lista de vehiculos
        List<Vehiculo> resultados = new ArrayList<>();
        for (Vehiculo vehiculo : listaVehiculos) {
            //Comprueba si el vehículo coincide con los criterios de búsqueda (marca, modelo, tipo)
            boolean coincideMarca = (marca == null || vehiculo.getMarca().equalsIgnoreCase(marca));
            boolean coincideModelo = (modelo == null || vehiculo.getModelo().equalsIgnoreCase(modelo));
            boolean coincideTipo = (tipo == null || vehiculo.getTipo().equalsIgnoreCase(tipo));
            boolean coincideId = (vehiculoId >= 0 && vehiculo.getID() == vehiculoId);
            //Si coincide con al menos un criterio, añadir a la lista de resultados
            if (coincideMarca && coincideModelo && coincideTipo || coincideId) {
                resultados.add(vehiculo);
            }
        }
        return Optional.of(resultados);
    }


    //validar la busqueda de vehiculos y mostrar sus resultados
    public void resultadosBusqueda(String marca, String modelo, String tipo, int vehiculoId, List<Vehiculo> listaVehiculos){
        Optional<Vehiculo> resultados = buscarVehiculos(marca, modelo, tipo, vehiculoId, listaVehiculos);
        if (resultados.isPresent()) {
            vistaBusqueda.mostrarResultados(resultados.get());
        } else {
            vistaBusqueda.mostrarError(tipo);
        }
    }
}