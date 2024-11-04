package main.java.com.easyrents;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class vistaFacturacion {
    private JFrame frame; // Ventana principal

    // Constructor para inicializar la ventana
    public vistaFacturacion() {
        frame = new JFrame("Pago");
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
    }
    //opciones para la factura
    String[] opciones = {"Imprimir", "Cancelar"};
    // Método para mostrar mensajes en ventanas emergente
	public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
    //mostrar mensaje de error
    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
    //mostrar ventana de opciones para la factura
    public int mostrarFactura(String mensaje, int numFactura) {
        int respuesta = JOptionPane.showOptionDialog(null, mensaje, "Factura No. " + numFactura, 0, 3, null, opciones, mensaje);
        //devuelve un int que corresponde al indice de la lista de opciones
        return respuesta;
    }
}
