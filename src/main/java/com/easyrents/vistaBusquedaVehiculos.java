package main.java.com.easyrents;
import java.awt.BorderLayout;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class vistaBusquedaVehiculos {
    private DefaultListModel<String> modeloListaVehiculos; // Modelo de lista para mostrar vehículos
    private JPanel panelDetalles; // Panel para mostrar detalles del vehículo
    
    public void mostrarResultadosBusqueda(List<Vehiculo> vehiculos) {
        modeloListaVehiculos.clear(); // Limpiar la lista antes de mostrar nuevos resultados
        for (Vehiculo vehiculo : vehiculos) {
            modeloListaVehiculos.addElement(vehiculo.toString()); // Mostrar el modelo del vehículo
        }
    }
    
    public void mostrarDetallesVehiculo(Vehiculo vehiculo) {
        // Limpiar el panel de detalles antes de mostrar nueva información
        panelDetalles.removeAll();

        // Agregar información del vehículo al panel
        panelDetalles.add(new JLabel("Marca: " + vehiculo.getMarca()));
        panelDetalles.add(new JLabel("Modelo: " + vehiculo.getModelo()));
        panelDetalles.add(new JLabel("Año: " + vehiculo.getAño()));
        panelDetalles.add(new JLabel("Precio por dia: $" + vehiculo.getTarifaDiaria()));

        // Refrescar el panel para mostrar los nuevos datos
        panelDetalles.revalidate();
        panelDetalles.repaint();
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }


    public void mostrarResultados(Vehiculo vehiculo){
        JOptionPane.showMessageDialog(null, vehiculo, "Aquí están los resultaods:",  JOptionPane.INFORMATION_MESSAGE);

    }
}
