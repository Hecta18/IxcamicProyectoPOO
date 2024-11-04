package main.java.com.easyrents;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;  


public class EasyRents {

	private static JFrame frmEasyrents;
	private static ArrayList<Usuario> listaUsuarios;
	private static ArrayList<Vehiculo> listaVehiculos;

	public static void main(String[] args) {
		controladorUsuario userControl = new controladorUsuario();
		controladorVehiculo vehicleControl = new controladorVehiculo();

		listaUsuarios = userControl.cargarUsuariosDesdeCSV();
		listaVehiculos = vehicleControl.cargarVehiculosDesdeCSV();

		frmEasyrents = new JFrame();
		frmEasyrents.setVisible(true);
		frmEasyrents.setTitle("EasyRents");
		frmEasyrents.setSize(360, 640);
		frmEasyrents.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEasyrents.setLocationRelativeTo(null);
        frmEasyrents.setLayout(null);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vistaInicioSesion window = new vistaInicioSesion();
					window.mostrarFormulario(frmEasyrents, listaUsuarios, listaVehiculos, userControl, vehicleControl);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
