package main.java.com.easyrents;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class controladorVehiculo {
    private vistaBusquedaVehiculos vistaBusqueda; // Vista para la búsqueda de vehículos
    private List<Vehiculo> listaVehiculos; // Lista de vehículos cargada desde el archivo CSV

    // Constructor de la clase controladorVehiculo
    public controladorVehiculo(vistaBusquedaVehiculos vistaBusqueda) {
        this.vistaBusqueda = vistaBusqueda;
        this.listaVehiculos = cargarVehiculosDesdeCSV("vehiculos.csv"); // Cargar vehículos desde archivo CSV
    }

    // Método para cargar vehículos desde un archivo CSV
    private List<Vehiculo> cargarVehiculosDesdeCSV(String filePath) {
        List<Vehiculo> vehiculos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] valores = linea.split(",");
                String marca = valores[0];
                String modelo = valores[1];
                String tipo = valores[2];
                int id = Integer.parseInt(valores[3]);
                double tarifaDiaria = Double.parseDouble(valores[4]);
                
                // Crear un objeto Vehiculo con los datos del CSV
                Vehiculo vehiculo = new Vehiculo(id, marca, modelo, id, tipo, tarifaDiaria, false);
                vehiculos.add(vehiculo);
            }
        } catch (IOException e) {
            vistaBusqueda.mostrarError("Error al cargar vehículos desde el archivo CSV: " + e.getMessage());
        }
        return vehiculos;
    }

    // Método que busca vehículos en la lista cargada desde el archivo CSV
    public List<Vehiculo> buscarVehiculos(String marca, String modelo, String tipo, int vehiculoId) {
        List<Vehiculo> resultados = new ArrayList<>();
        for (Vehiculo vehiculo : listaVehiculos) {
            // Comprobación de coincidencias con los criterios de búsqueda
            boolean coincideMarca = (marca == null || vehiculo.getMarca().equalsIgnoreCase(marca));
            boolean coincideModelo = (modelo == null || vehiculo.getModelo().equalsIgnoreCase(modelo));
            boolean coincideTipo = (tipo == null || vehiculo.getTipo().equalsIgnoreCase(tipo));
            boolean coincideId = (vehiculoId >= 0 && vehiculo.getID() == vehiculoId);
            
            // Agregar a los resultados si cumple con al menos un criterio de búsqueda
            if (coincideMarca && coincideModelo && coincideTipo || coincideId) {
                resultados.add(vehiculo);
            }
        }
        return resultados;
    }

    // Método para mostrar los resultados de la búsqueda en la vista
    public void resultadosBusqueda(String marca, String modelo, String tipo, int vehiculoId) {
        List<Vehiculo> resultados = buscarVehiculos(marca, modelo, tipo, vehiculoId);
        if (!resultados.isEmpty()) {
            vistaBusqueda.mostrarResultadosBusqueda(resultados);
        } else {
            vistaBusqueda.mostrarError("No se encontraron vehículos que coincidan con los criterios.");
        }
    }
}