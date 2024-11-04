package main.java.com.easyrents;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;  


public class EasyRents {

	private static JFrame frmEasyrents;
	// private static ArrayList<Reserva> listaReservas;
	// private static ArrayList<Pago> listaPagos;
	// private static ArrayList<Usuario> listaUsuarios;
	// private static ArrayList<Vehiculo> listaVehiculos;

	public static void main(String[] args) {
		/*
		try {

			Connection conn = DatabaseConnection.getConnection();
			//devuelve DatabaseConnection cannot be resolved
			Statement stmt = conn.createStatement();
			String query = "INSERT INTO userslist (correo, contraseña, dpi, nombre, apellido, edad, numreservas_activas, numreservas_pasadas, licencia_moto, licencia_carro, licencia_bus) " + "VALUES ('emailforexample@gmail.com', '12345678', " + 1784567898 + ", 'Nombre prueba', 'apellido prueba', " + 29 + ", " + 0 + ", " + 0 + "," + true + "," + true + "," + true + ")";
			String query2 ="DELETE FROM userslist WHERE dpi = 1234567898";
			stmt.executeUpdate(query);

			JOptionPane.showMessageDialog(null, "USUARIO GUARADADO CON EXITO");
			stmt.executeUpdate(query2);
			JOptionPane.showMessageDialog(null, "USUARIO Eliminado CON EXITO");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		*/

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
					window.mostrarFormulario(frmEasyrents);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
