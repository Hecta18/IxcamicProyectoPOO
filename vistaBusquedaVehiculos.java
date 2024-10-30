package com.easyrents;

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

    public vistaBusquedaVehiculos() {
        frame = new JFrame("Búsqueda de Vehículos");
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        modeloListaVehiculos = new DefaultListModel<>(); // Inicializa el modelo de lista
        JList<String> listaVehiculos = new JList<>(modeloListaVehiculos); // Crea la lista
        frame.add(new JScrollPane(listaVehiculos), BorderLayout.CENTER); // Agrega la lista al marco

        panelDetalles = new JPanel(); // Inicializa el panel de detalles
        frame.add(panelDetalles, BorderLayout.SOUTH); // Agrega el panel de detalles al marco
    }
    
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
        JOptionPane.showMessageDialog(frame, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void mostrar() {
        frame.setVisible(true);
    }

    public void mostrarResultados(Vehiculo vehiculo){
        JOptionPane.showMessageDialog(frame, vehiculo, "Aquí están los resultaods",  JOptionPane.INFORMATION_MESSAGE);

    }
}
