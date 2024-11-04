package main.java.com.easyrents;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class controladorVehiculo {

    // Método para cargar vehículos desde un archivo CSV
    // Los vehículos se almacenarán en el CSV de la siguiente forma:
    // ID, marca, modelo, año, tipo, tarifaDiaria, disponible

    public ArrayList<Vehiculo> cargarVehiculosDesdeCSV() {
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("vehiculos.csv"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] valores = linea.split(",");
                Integer id = Integer.parseInt(valores[0]);
                String marca = valores[1];
                String modelo = valores[2];
                int año = Integer.parseInt(valores[3]);
                String tipo = valores[4];
                double tarifaDiaria = Float.parseFloat(valores[5]);
                boolean disponible = Boolean.parseBoolean(valores[6]);
                
                // Crear un objeto Vehiculo con los datos del CSV
                Vehiculo vehiculo = new Vehiculo(id, marca, modelo, año, tipo, tarifaDiaria, disponible);
                vehiculos.add(vehiculo);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar vehículos desde el archivo CSV: " + e.getMessage());
        }
        return vehiculos;
    }

    // Método que busca vehículos en la lista cargada desde el archivo CSV
    public ArrayList<Vehiculo> buscarVehiculos(String tipo, ArrayList<Vehiculo> vehiculosList) {
        ArrayList<Vehiculo> resultados = new ArrayList<>();
        for (Vehiculo vehiculo : vehiculosList) {
            // Comprobación de coincidencias con los criterios de búsqueda
            boolean coincideTipo = (tipo == null || vehiculo.getTipo().equalsIgnoreCase(tipo));
            
            // Agregar a los resultados si cumple con al menos un criterio de búsqueda
            if (coincideTipo) {
                resultados.add(vehiculo);
            }
        }
        return resultados;
    }
}