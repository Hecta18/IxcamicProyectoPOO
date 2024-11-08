package main.java.com.easyrents;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;


public class EasyRents {

	private static JFrame frmEasyrents;
	private static ArrayList<Usuario> listaUsuarios;
	private static ArrayList<Vehiculo> listaVehiculos;

	public static void main(String[] args) {
		controladorUsuario userControl = new controladorUsuario();
		controladorVehiculo vehicleControl = new controladorVehiculo();

		listaUsuarios = userControl.cargarUsuariosDesdeCSV();
		listaVehiculos = vehicleControl.cargarVehiculosDesdeCSV();

		// Cambiar las propiedades del UIManager que afectan a todos los componentes
		// Arrows y Scrollbars
		
		UIManager.put("arrowButton.background", Color.WHITE);
		UIManager.put("arrowButton.foreground", Color.BLACK);
		UIManager.put("ScrollBar.arrowButton.background", Color.WHITE);
		UIManager.put("ScrollBar.arrowButton.border", new LineBorder(Color.WHITE, 1));
		UIManager.put("ScrollBar.foreground", Color.LIGHT_GRAY);
		UIManager.put("ScrollBar.background", Color.WHITE);
		UIManager.put("ScrollBar.thumb", Color.LIGHT_GRAY);
		
		
		// ComboBoxes
		UIManager.put("ComboBox.background", Color.WHITE);
		UIManager.put("ComboBox.selectionBackground", Color.LIGHT_GRAY);
		UIManager.put("ComboBox.SrollBar.foreground", Color.LIGHT_GRAY);

		// Lists (Jlist incluida)
		UIManager.put("List.background", Color.WHITE);
		UIManager.put("List.selectionBackground", Color.LIGHT_GRAY);
		UIManager.put("List.ScrollBar.foreground", Color.LIGHT_GRAY);

		// Tooltips
		UIManager.put("ToolTip.background", Color.WHITE);
		UIManager.put("ToolTip.foreground", Color.BLACK);     
		UIManager.put("ToolTip.font", new Font("YU Gothic UI Light", Font.PLAIN, 14));

	
		// Botones
		UIManager.put("Button.select", Color.LIGHT_GRAY);
		UIManager.put("Button.focus", Color.LIGHT_GRAY);
		UIManager.put("Button.rollover.enabled", true);
		UIManager.put("Button.rollover.background", Color.LIGHT_GRAY);

		/*
		// Borders
		UIManager.put("ToolTip.border", new LineBorder(Color.GRAY, 1));
		UIManager.put("TextField.border", new LineBorder(Color.GRAY, 1));
		UIManager.put("PasswordField.border", new LineBorder(Color.GRAY, 1));
		UIManager.put("Button.rollover.border", new LineBorder(Color.GRAY, 1));
		UIManager.put("List.border", new LineBorder(Color.GRAY, 1));
		UIManager.put("List.selectionBorder", new LineBorder(Color.GRAY, 1));
		UIManager.put("ComboBox.border", new LineBorder(Color.GRAY, 1));
		UIManager.put("Scrollpane.border", new LineBorder(Color.GRAY, 1));
		*/
		

		// new Color(255, 128, 128) -> Color rojo claro
		
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
