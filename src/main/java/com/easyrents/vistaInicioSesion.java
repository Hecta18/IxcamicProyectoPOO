package main.java.com.easyrents;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class vistaInicioSesion {

    public void mostrarFormulario(JFrame frame, ArrayList<Usuario> userList, ArrayList<Vehiculo> vehicleList, controladorUsuario userControl, controladorVehiculo vehicleControl) {
		//preparar pantalla
        frame.getContentPane().removeAll();
        frame.repaint();
        //subir logo
        Image iconImage = new ImageIcon(getClass().getResource("/easyLogo.png")	).getImage();
        frame.setIconImage(iconImage);
		//espacio clave
        JPasswordField passwordField = new JPasswordField();
		passwordField.setBounds(32, 279, 280, 30);
		passwordField.setToolTipText("Contraseña");
		passwordField.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 16));
		passwordField.setBackground(Color.WHITE);
		frame.add(passwordField);
		//etiqueta bienvenida
		JLabel welcomeLbl = new JLabel("¡Bienvenid@ a EasyRents!");
		welcomeLbl.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 19));
		welcomeLbl.setLabelFor(frame);
		welcomeLbl.setBounds(68, 158, 211, 36);
		frame.getContentPane().add(welcomeLbl);
		//etiqueta visitar
		JLabel visitanosLbl = new JLabel("¡Visítanos en redes sociales!");
		visitanosLbl.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 15));
		visitanosLbl.setBounds(87, 481, 180, 36);
		frame.getContentPane().add(visitanosLbl);
		//subir logo twitter
		JLabel imgTwitter = new JLabel("");
		Image imgTwit = new ImageIcon(getClass().getResource("/twitter-icon.png")).getImage();
		imgTwitter.setIcon(new ImageIcon(imgTwit));
		imgTwitter.setBounds(180, 518, 50, 50);
		frame.getContentPane().add(imgTwitter);
		//subir logo Insta
		JLabel imgInsta = new JLabel("");
		Image imgIg = new ImageIcon(this.getClass().getResource("/insta-icon.png")).getImage();
		imgInsta.setIcon(new ImageIcon(imgIg));
		imgInsta.setBounds(110, 518, 50, 50);
		frame.getContentPane().add(imgInsta);
		//espacio email
		JTextField txtCorreoElectrnico = new JTextField();
		txtCorreoElectrnico.setToolTipText("Correo Electrónico");
		txtCorreoElectrnico.setBackground(Color.WHITE);
		txtCorreoElectrnico.setBounds(32, 224, 280, 30);
		frame.getContentPane().add(txtCorreoElectrnico);
		//etiqueta logo
		JLabel lbleasyrents = new JLabel("@EasyRents");
		lbleasyrents.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 15));
		lbleasyrents.setBounds(130, 565, 85, 36);
		frame.getContentPane().add(lbleasyrents);
		//etiqueta create account
		JLabel visitanosLbl_1 = new JLabel("¿No tienes una cuenta?");
		visitanosLbl_1.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 15));
		visitanosLbl_1.setBounds(99, 377, 199, 36);
		frame.getContentPane().add(visitanosLbl_1);
		//etiqueta email
        JLabel lblCorreoElectrnico = new JLabel("Correo electrónico:");
		lblCorreoElectrnico.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 13));
		lblCorreoElectrnico.setBounds(115, 193, 105, 36);
		frame.getContentPane().add(lblCorreoElectrnico);
		//etiqueta password
		JLabel lblContrasea = new JLabel("Contraseña:");
		lblContrasea.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 13));
		lblContrasea.setBounds(137, 250, 64, 36);
		frame.getContentPane().add(lblContrasea);
		//boton crear cuenta, redirecciona pantalla crear cuenta
		JButton createAccountBtn = new JButton("Crear Cuenta");
		createAccountBtn.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		createAccountBtn.setBounds(109, 413, 121, 30);
        createAccountBtn.setBackground(Color.WHITE);
        createAccountBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                redireccionarCrearCuenta(frame, userList, vehicleList, userControl, vehicleControl);
            }
        });
		frame.getContentPane().add(createAccountBtn);
		//boton login
		JButton loginBtn_1 = new JButton("Iniciar Sesión");
		loginBtn_1.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 15));
		loginBtn_1.setBounds(109, 320, 121, 47);
        loginBtn_1.setBackground(Color.WHITE);
		loginBtn_1.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				String passwordIngresado = new String(passwordField.getPassword());
				String correoIngresado = txtCorreoElectrnico.getText();
				if ((correoIngresado.isEmpty()) || (passwordIngresado.isEmpty())) {
					mostrarError("Debe de ingresar un correo y su contraseña respectiva.");
					return;
				} else if (!correoIngresado.contains("@")) {
					mostrarError("Debe de ingresar una dirección de correo válida");
					return;
				} else {
					if (userList.isEmpty()){
						mostrarError("No existe ningún usuario registrado actualmente");
						return;
					}
					for(Usuario u : userList){
						if(u.getCorreo().equals(correoIngresado) && new String(passwordField.getPassword()).equals(u.getContraseña())){
							redireccionarDashboard(frame, u, userList, vehicleList, userControl, vehicleControl);
							return;
						}else{
							mostrarError("La contraseña o el correo ingresado no son correctos.");
							return;
						}
					}
				}
			}
		});
		frame.getContentPane().add(loginBtn_1);

        JLabel logoImageLabel = new JLabel();
		Image logoImage = new ImageIcon(getClass().getResource("/easyLogo95x95.png")).getImage();
		logoImageLabel.setIcon(new ImageIcon(logoImage));
		logoImageLabel.setBounds(116, 7, 115, 95);
		frame.getContentPane().add(logoImageLabel);
		
		JLabel logoNameImgLbl = new JLabel("");
		Image logoNameImg = new ImageIcon(getClass().getResource("/easyRents177x50.png")).getImage();
		logoNameImgLbl.setIcon(new ImageIcon(logoNameImg));
		logoNameImgLbl.setBounds(84, 106, 177, 50);
		frame.getContentPane().add(logoNameImgLbl);

    }

	public void drawMainButtons(JFrame frame, Usuario usuarioActual, ArrayList<Usuario> userList, ArrayList<Vehiculo> vehicleList, controladorUsuario userControl, controladorVehiculo vehicleControl ) {
		frame.getContentPane().removeAll();
		frame.repaint();
		JLabel cerrarSesionLbl = new JLabel("Cerrar Sesión");
		cerrarSesionLbl.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		cerrarSesionLbl.setForeground(Color.DARK_GRAY);
		cerrarSesionLbl.setBounds(254, 11, 80, 14);
		cerrarSesionLbl.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mostrarFormulario(frame, userList, vehicleList, userControl, vehicleControl);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				cerrarSesionLbl.setForeground(Color.LIGHT_GRAY);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				cerrarSesionLbl.setForeground(Color.DARK_GRAY);
			}
		});
		frame.getContentPane().add(cerrarSesionLbl);

		JLabel franjaLbl = new JLabel("");
		franjaLbl.setBackground(new Color(213, 0, 0));
		franjaLbl.setVisible(true);
		franjaLbl.setOpaque(true);
		franjaLbl.setBounds(0, 537, 344, 64);
		
		frame.getContentPane().add(franjaLbl);

		Image profileImg = new ImageIcon(getClass().getResource("/profileIconSmall.png")).getImage();
		Image profileImgHover = new ImageIcon(getClass().getResource("/profileIconSmallHover.png")).getImage();
		JButton perfilBtn = new JButton("");
		perfilBtn.setBounds(244, 545, 50, 50);
		perfilBtn.setBorderPainted(false);
		perfilBtn.setContentAreaFilled(false);
		perfilBtn.setIcon(new ImageIcon(profileImg));
		perfilBtn.setRolloverIcon(new ImageIcon(profileImgHover));
		perfilBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				redireccionarPerfil(frame, usuarioActual, userList, vehicleList, userControl, vehicleControl);
			}
		});
		frame.getContentPane().add(perfilBtn);
		
		Image verReservasImg = new ImageIcon(getClass().getResource("/menuIcon.png")).getImage();
		Image verReservasImgHover = new ImageIcon(getClass().getResource("/menuIconHover.png")).getImage();
		JButton verReservasBtn = new JButton("");
		verReservasBtn.setBounds(41, 545, 50, 50);
		verReservasBtn.setBorderPainted(false);
		verReservasBtn.setContentAreaFilled(false);
		verReservasBtn.setIcon(new ImageIcon(verReservasImg));
		verReservasBtn.setRolloverIcon(new ImageIcon(verReservasImgHover));
		verReservasBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				redireccionarReservas(frame, usuarioActual, userList, vehicleList, userControl, vehicleControl);
			}
		});
		frame.getContentPane().add(verReservasBtn);
		
		Image homeImg = new ImageIcon(getClass().getResource("/homeIcon.png")).getImage();
		Image homeImgHover = new ImageIcon(getClass().getResource("/homeIconHover.png")).getImage();
		JButton homeBtn = new JButton();
		homeBtn.setBounds(141, 545, 50, 50);
		homeBtn.setBorderPainted(false);
		homeBtn.setContentAreaFilled(false);
		homeBtn.setIcon(new ImageIcon(homeImg));
		homeBtn.setRolloverIcon(new ImageIcon(homeImgHover));
		homeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a){
				redireccionarDashboard(frame, usuarioActual, userList, vehicleList, userControl, vehicleControl);
			}
		});
		frame.getContentPane().add(homeBtn);
		frame.getContentPane().setComponentZOrder(franjaLbl, frame.getContentPane().getComponentCount() - 1);
	}

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Ha ocurrido un error.", JOptionPane.ERROR_MESSAGE);
    }

    public void mostrarExito(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    public void redireccionarCrearCuenta(JFrame frame, ArrayList<Usuario> userList, ArrayList<Vehiculo> vehicleList, controladorUsuario userControl, controladorVehiculo vehicleControl) {
        frame.getContentPane().removeAll();
        frame.repaint();

        ArrayList<Object> entryList = new ArrayList<>();//posible unchecked

        JButton backBtn = new JButton("Regresar");
		backBtn.setBackground(Color.WHITE);
		backBtn.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 13));
		backBtn.setBounds(264, 556, 80, 45);
        backBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                mostrarFormulario(frame, userList, vehicleList, userControl, vehicleControl);
            }
        });
		frame.add(backBtn);
		
		JLabel titleLbl = new JLabel("Creación de Cuenta");
		titleLbl.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 22));
		titleLbl.setBounds(78, 11, 189, 45);
		frame.add(titleLbl);

        JLabel instruccionesLbl = new JLabel("Llene los siguientes campos para crear su cuenta:");
		instruccionesLbl.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		instruccionesLbl.setBounds(29, 47, 292, 29);
		frame.getContentPane().add(instruccionesLbl);
		
		JLabel nombreLbl = new JLabel("Nombre:");
		nombreLbl.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		nombreLbl.setBounds(10, 109, 94, 29);
		frame.getContentPane().add(nombreLbl);
		
		JTextField nombreEntry = new JTextField();
		nombreEntry.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		nombreEntry.setBounds(98, 111, 236, 29);
		frame.getContentPane().add(nombreEntry);
		
		JLabel correoLbl = new JLabel("Correo:");
		correoLbl.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		correoLbl.setBounds(10, 160, 52, 29);
		frame.getContentPane().add(correoLbl);
		
		JTextField correoEntry = new JTextField();
		correoEntry.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		correoEntry.setColumns(10);
		correoEntry.setBounds(98, 162, 236, 29);
		frame.getContentPane().add(correoEntry);
		
		JLabel contrasenaLabel = new JLabel("Contraseña:");
		contrasenaLabel.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		contrasenaLabel.setBounds(10, 272, 94, 29);
		frame.getContentPane().add(contrasenaLabel);
		
		JPasswordField passwordField = new JPasswordField();
		passwordField.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		passwordField.setBounds(98, 214, 236, 26);
		frame.getContentPane().add(passwordField);
        entryList.add(passwordField);
		
		JPasswordField passwordFieldConfirmar = new JPasswordField();
		passwordFieldConfirmar.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		passwordFieldConfirmar.setBounds(98, 265, 236, 26);
		frame.getContentPane().add(passwordFieldConfirmar);
		
		JLabel confirmarContLbl1 = new JLabel("Confirmar");
		confirmarContLbl1.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		confirmarContLbl1.setBounds(10, 251, 94, 29);
		frame.getContentPane().add(confirmarContLbl1);
		
		JLabel confirmarContLbl2 = new JLabel("Contraseña:");
		confirmarContLbl2.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		confirmarContLbl2.setBounds(10, 211, 94, 29);
		frame.getContentPane().add(confirmarContLbl2);
		
		JLabel motivoLbl1 = new JLabel("¿Cuál es su motivo para");
		motivoLbl1.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		motivoLbl1.setBounds(10, 418, 172, 29);
		frame.getContentPane().add(motivoLbl1);
		
		JLabel motivoLbl2 = new JLabel("utilizar nuestra app?");
		motivoLbl2.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		motivoLbl2.setBounds(10, 439, 172, 29);
		frame.getContentPane().add(motivoLbl2);

        JLabel dpiLabel = new JLabel("Teléfono:");
		dpiLabel.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		dpiLabel.setBounds(10, 310, 94, 29);
		frame.getContentPane().add(dpiLabel);
		
		JTextField dpiEntry = new JTextField(); // ES REALMENTE EL TELÉFONO
		dpiEntry.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		dpiEntry.setBounds(98, 318, 236, 26);
		frame.getContentPane().add(dpiEntry);
		
		JLabel lblsinGuiones = new JLabel("(sin guiones)");
		lblsinGuiones.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 12));
		lblsinGuiones.setBounds(10, 326, 94, 29);
		frame.getContentPane().add(lblsinGuiones);

		JLabel lblsinGuiones2 = new JLabel("(sin guiones)");
		lblsinGuiones2.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 12));
		lblsinGuiones2.setBounds(10, 326, 94, 29);
		frame.getContentPane().add(lblsinGuiones);
		
		JLabel lblsinGuiones_2 = new JLabel("(sin guiones)");
		lblsinGuiones_2.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 12));
		lblsinGuiones_2.setBounds(10, 382, 94, 29);
		frame.getContentPane().add(lblsinGuiones_2);
		
		JLabel licenciaLbl1 = new JLabel("No. Licencia:");
		licenciaLbl1.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		licenciaLbl1.setBounds(10, 366, 94, 29);
		frame.getContentPane().add(licenciaLbl1);

		JTextField licenciaEntry = new JTextField();
		licenciaEntry.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		licenciaEntry.setBounds(98, 374, 236, 26);
		frame.getContentPane().add(licenciaEntry);

        JLabel razonExtraLbl = new JLabel("Especifique:");
		razonExtraLbl.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 15));
		razonExtraLbl.setVisible(false);
		razonExtraLbl.setBounds(24, 426, 80, 20);
		frame.getContentPane().add(razonExtraLbl);
		
		JTextField razonExtra = new JTextField();
		razonExtra.setVisible(false);
		razonExtra.setBounds(105, 425, 217, 29);
		frame.getContentPane().add(razonExtra);

        JButton crearCuentaBtn = new JButton("Crear Cuenta");

        @SuppressWarnings("rawtypes")
        JComboBox tipoUsuarioDropDown = new JComboBox();
		tipoUsuarioDropDown.setToolTipText("Escoja una de las opciones siguientes");
		tipoUsuarioDropDown.setModel(new DefaultComboBoxModel<String>(new String[] {"Turismo (cualquier índole)", "Trabajo", "Estudios", "Visitas a Familiares", "Residencia Temporal", "Otro (Especifique)"}));
		tipoUsuarioDropDown.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
        tipoUsuarioDropDown.setBackground(Color.WHITE);
		tipoUsuarioDropDown.setBounds(170, 428, 164, 29);
		frame.getContentPane().add(tipoUsuarioDropDown);

		crearCuentaBtn.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 22));
        crearCuentaBtn.setBackground(Color.WHITE);
		crearCuentaBtn.setBounds(98, 521, 155, 54);
		int randomID = new Random().nextInt(999999998) + 1;
        crearCuentaBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                ArrayList<String> values = new ArrayList<>();
                values.add(nombreEntry.getText());
                values.add(correoEntry.getText());
                values.add(new String(passwordField.getPassword()));
                values.add(new String(passwordFieldConfirmar.getPassword()));
                values.add(tipoUsuarioDropDown.getSelectedItem().toString());
				values.add(String.valueOf(Long.parseLong(licenciaEntry.getText())));
                values.add(String.valueOf(Integer.parseInt(dpiEntry.getText()))); // ESTO ES REALMENTE EL TELÉFONO

                for(String s : values){
                    if (s.replaceAll("\\s+","").equals("")){
                        mostrarError("Debe de llenar todos los campos solicitados");
                        return;
                    }
                }

                // Verificar que el correo sea válido (que tenga una @ por lo menos):
                if(!values.get(1).contains("@")){
                    mostrarError("Debe de ingresar una dirección de correo válida");
                    return;
                }

                // Comparar si ambas contraseñas ingresadas son iguales
                if (!values.get(2).equals(values.get(3))){
                    mostrarError("Debe de ingresar la misma contraseña en ambos campos");
                    return;
                }

                if(razonExtra.isVisible()){
                    if (razonExtra.getText().replaceAll("\\s+","").equals("")){
                        mostrarError("Debe de llenar todos los campos solicitados.");
                        return;
                    }else{
                        values.add(razonExtra.getText());
                    }
                    // FALTA IMPLEMENTAR LÓGICA DE CREAR USUARIO Y GUARDARLO EN LA BASE DE DATOS.
                    mostrarExito("Se ha creado su cuenta exitosamente!");
					Usuario nuevoUsuario = new Usuario(randomID, values.get(0), values.get(1), values.get(2), values.get(4), Long.parseLong(values.get(5)), Integer.parseInt(values.get(6)), new ArrayList<Reserva>());
					userList.add(nuevoUsuario);
					userControl.guardarUsuarioEnCSV(nuevoUsuario);
                    return;
                }else{
					mostrarExito("Se ha creado su cuenta exitosamente!");
                    Usuario nuevoUsuario = new Usuario(randomID, values.get(0), values.get(1), values.get(2), razonExtra.getText(), Long.parseLong(values.get(5)), Integer.parseInt(values.get(6)), new ArrayList<Reserva>());
					userList.add(nuevoUsuario);
					userControl.guardarUsuarioEnCSV(nuevoUsuario);
                    return;
                }
            }
        });
		frame.getContentPane().add(crearCuentaBtn);		
    }

    public void redireccionarDashboard(JFrame frame, Usuario currentUsuario, ArrayList<Usuario> userList, ArrayList<Vehiculo> vehicleList, controladorUsuario userControl, controladorVehiculo vehicleControl) {
		drawMainButtons(frame, currentUsuario, userList, vehicleList, userControl, vehicleControl);
		
		JLabel dashboardTitleLbl = new JLabel("Página Principal");
		dashboardTitleLbl.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 23));
		dashboardTitleLbl.setBounds(91, 37, 166, 38);
		frame.getContentPane().add(dashboardTitleLbl);

        JLabel optionLbl = new JLabel("¿Qué tipo de vehículo desea reservar hoy?");
		optionLbl.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 16));
		optionLbl.setBounds(28, 86, 288, 38);
		frame.getContentPane().add(optionLbl);

		// EN EL SCROLLPANE IRÁN TODOS LOS RESULTADOS DE BUSCAR EN LA BASE DE DATOS EL TIPO DE VEHÍCULO CON EL COMBOBOX
        @SuppressWarnings("rawtypes")
		JComboBox<String> tipoVehiculoDropDown = new JComboBox();
		tipoVehiculoDropDown.setModel(new DefaultComboBoxModel(new String[] {"Motocicleta", "Automovil particular", "Bus particular"}));
        tipoVehiculoDropDown.setBackground(Color.WHITE);
		tipoVehiculoDropDown.setSelectedItem(null);

		JList<String> vehiculoDropDown = new JList<String>();
		vehiculoDropDown.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 16));
		vehiculoDropDown.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane resultsScrollPane = new JScrollPane(vehiculoDropDown);
		resultsScrollPane.setViewportBorder(null);
		resultsScrollPane.setBounds(10, 180, 324, 262);
		frame.getContentPane().add(resultsScrollPane);

        tipoVehiculoDropDown.addActionListener(e -> {
			String seleccionado = tipoVehiculoDropDown.getSelectedItem().toString();
			ArrayList<Vehiculo> datos = vehicleControl.buscarVehiculos(seleccionado, vehicleList);
			ArrayList<Vehiculo> datosFiltradosMotos = vehicleControl.buscarVehiculos("Motocicleta", vehicleList);
			ArrayList<Vehiculo> datosFiltradosAutos = vehicleControl.buscarVehiculos("Automovil particular", vehicleList);
			ArrayList<Vehiculo> datosFiltradosBuses = vehicleControl.buscarVehiculos("Bus particular", vehicleList);
			int sizeMotos = datosFiltradosMotos.size();
			int sizeAutos = datosFiltradosAutos.size();
			int sizeBuses = datosFiltradosBuses.size();
			String[] datosFiltradosMotosArray = new String[sizeMotos];
			for(int i = 0; i < sizeMotos; i++){
				datosFiltradosMotosArray[i] = datosFiltradosMotos.get(i).getMarca() + " " + datosFiltradosMotos.get(i).getModelo() + " " + datosFiltradosMotos.get(i).getAño() + " Q." + datosFiltradosMotos.get(i).getTarifaDiaria() + "/día";
			}
			String[] datosFiltradosAutosArray = new String[sizeAutos];
			for(int i = 0; i < sizeAutos; i++){
				datosFiltradosAutosArray[i] = datosFiltradosAutos.get(i).getMarca() + " " + datosFiltradosAutos.get(i).getModelo() + " " + datosFiltradosAutos.get(i).getAño() + " Q." + datosFiltradosAutos.get(i).getTarifaDiaria() + "/día";
			}
			String[] datosFiltradosBusesArray = new String[sizeBuses];
			for(int i = 0; i < sizeBuses; i++){
				datosFiltradosBusesArray[i] = datosFiltradosBuses.get(i).getMarca() + " " + datosFiltradosBuses.get(i).getModelo() + " " + datosFiltradosBuses.get(i).getAño() + " Q." + datosFiltradosBuses.get(i).getTarifaDiaria() + "/día";
			}
			vehiculoDropDown.removeAll();
			vehiculoDropDown.revalidate();
			vehiculoDropDown.repaint();
			frame.getContentPane().revalidate();
			frame.repaint();
			resultsScrollPane.revalidate();
			resultsScrollPane.repaint();
			switch (seleccionado){
				case "Motocicleta":
					vehiculoDropDown.setListData(datosFiltradosMotosArray);
					break;
				case "Automóvil particular":
					vehiculoDropDown.setListData(datosFiltradosAutosArray);
					break;
				case "Bus particular":
					vehiculoDropDown.setListData(datosFiltradosBusesArray);
					break;
			}
		});
		tipoVehiculoDropDown.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 16));
		tipoVehiculoDropDown.setBounds(74, 135, 191, 30);
		frame.getContentPane().add(tipoVehiculoDropDown);
		
		JButton reservarBtn = new JButton("Ver detalles");
		reservarBtn.setBackground(Color.WHITE);
		reservarBtn.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 25));
		reservarBtn.setBounds(90, 457, 156, 65);
		reservarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				if(tipoVehiculoDropDown.getSelectedItem() == null || vehiculoDropDown.getSelectedValue() == null){
					mostrarError("Debe de seleccionar un tipo de vehículo y un modelo.");
					return;
				}else{
					// Vehiculo prueba:
					// Casilla 0 -> Marca; Casilla 1 -> Modelo; Casilla 2 -> Año; Casilla 3 -> Precio
					String[] datosVehiculo = vehiculoDropDown.getSelectedValue().toString().split(" ");
					String tipoAuto = "";
					switch (tipoVehiculoDropDown.getSelectedItem().toString()){
						case "Motocicleta":
							tipoAuto = "Motocicleta";
							break;
						case "Automovil particular":
							tipoAuto = "Automovil particular";
							break;
						case "Bus particular":
							tipoAuto = "Bus particular";
							break;
					}
					int randomID = new Random().nextInt(999999998) + 1;
					Vehiculo vehiculoSeleccionado = new Vehiculo(randomID, datosVehiculo[0], datosVehiculo[1], Integer.parseInt(datosVehiculo[2]), tipoAuto, Double.parseDouble(datosVehiculo[3].replaceAll("Q.", "").replaceAll("/día", "")), true);
					vistaReservacion vistaRes = new vistaReservacion();
					vistaRes.mostrarFormularioReservacion(vehiculoSeleccionado, frame, currentUsuario, userList, vehicleList, userControl, vehicleControl);
				}
			}
		});
		frame.getContentPane().add(reservarBtn);
    }

    public void redireccionarPerfil(JFrame frame, Usuario currentUsuario, ArrayList<Usuario> userList, ArrayList<Vehiculo> vehicleList, controladorUsuario userControl, controladorVehiculo vehicleControl){
		drawMainButtons(frame, currentUsuario, userList, vehicleList, userControl, vehicleControl);

        JLabel profileTitleLbl = new JLabel("Perfil de Usuario");
		profileTitleLbl.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 23));
		profileTitleLbl.setBounds(95, 25, 166, 38);
		frame.getContentPane().add(profileTitleLbl);

        String[] userInfo = currentUsuario.getNombre().split(" ");
        String userNameLblString = "<html> ";
        int userNameLblHeight = 0;
        int i = 1;
        for(String s : userInfo){
            userNameLblString += (s + "<br/>");
            userNameLblHeight += 25 * i;
            i++;
        }
        userNameLblString += "</html>";
		JLabel userNameLbl = new JLabel(userNameLblString);
		userNameLbl.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 30));
		userNameLbl.setBounds(180, 75, 166, userNameLblHeight);
		frame.getContentPane().add(userNameLbl);

        JLabel largeProfileLbl = new JLabel();
        Image largeProfile = new ImageIcon(getClass().getResource("/profileIcon.png")).getImage();
        largeProfileLbl.setIcon(new ImageIcon(largeProfile));
		largeProfileLbl.setBounds(15,0,300,300);
        frame.getContentPane().add(largeProfileLbl);

        JLabel licenciasListLabel = new JLabel(String.valueOf(currentUsuario.getNumDocLicencia()));
		licenciasListLabel.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 16));
		licenciasListLabel.setBounds(158, 372, 139, 21);
		frame.getContentPane().add(licenciasListLabel);
		
		JLabel licenciaLbl = new JLabel("Licencias Asociadas:");
		licenciaLbl.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 17));
		licenciaLbl.setBounds(10, 366, 150, 31);
		frame.getContentPane().add(licenciaLbl);
		
		JLabel telefonoTxtLabel = new JLabel("Teléfono:");
		telefonoTxtLabel.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 17));
		telefonoTxtLabel.setBounds(10, 240, 71, 31);
		frame.getContentPane().add(telefonoTxtLabel);

        JLabel telefonoValueLbl = new JLabel(String.valueOf(currentUsuario.getNumTelefono()));
		telefonoValueLbl.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 17));
		telefonoValueLbl.setBounds(91, 240, 150, 31);
		frame.getContentPane().add(telefonoValueLbl);
		frame.setLocationRelativeTo(null);
		
		JLabel dpiTextLbl = new JLabel("No. de ID asociado:");
		dpiTextLbl.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 17));
		dpiTextLbl.setBounds(10, 282, 159, 31);
		frame.getContentPane().add(dpiTextLbl);
		
		JLabel dpiValueLbl = new JLabel(String.valueOf(currentUsuario.getID()));
		dpiValueLbl.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 17));
		dpiValueLbl.setBounds(171, 282, 150, 31);
		frame.getContentPane().add(dpiValueLbl);

        JLabel correoLbl = new JLabel("Correo electrónico:");
		correoLbl.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 17));
		correoLbl.setBounds(10, 324, 150, 31);
		frame.getContentPane().add(correoLbl);
		
		JLabel correoValueLbl = new JLabel(currentUsuario.getCorreo());
		correoValueLbl.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 13));
		correoValueLbl.setBounds(159, 326, 175, 31);
		frame.getContentPane().add(correoValueLbl);
    }

	public void redireccionarReservas(JFrame frame, Usuario currentUsuario, ArrayList<Usuario> userList, ArrayList<Vehiculo> vehicleList, controladorUsuario userControl, controladorVehiculo vehicleControl) {
		drawMainButtons(frame, currentUsuario, userList, vehicleList, userControl, vehicleControl);

		JLabel reservasTitleLbl = new JLabel("Reservas Actuales");
		reservasTitleLbl.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 23));
		reservasTitleLbl.setBounds(85, 37, 175, 38);		
		frame.getContentPane().add(reservasTitleLbl);

		String[] reservasArray = new String[currentUsuario.getReservasAsociadas().size()];
		for(int i = 0; i < currentUsuario.getReservasAsociadas().size(); i++){
			reservasArray[i] = currentUsuario.getReservasAsociadas().get(i).toString();
		}

		JList<String> listReservas = new JList<String>(reservasArray);
		listReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listReservas.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 10));

		JScrollPane reservasPane = new JScrollPane(listReservas);
		reservasPane.setBounds(10, 86, 324, 357);
		reservasPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		reservasPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		reservasPane.setBackground(Color.WHITE);
		frame.getContentPane().add(reservasPane);
		
		JButton eliminarRsrvBtn = new JButton("Eliminar");
		eliminarRsrvBtn.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		eliminarRsrvBtn.setBounds(115, 470, 117, 38);
		eliminarRsrvBtn.setBackground(Color.WHITE);
		eliminarRsrvBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				String seleccionada = listReservas.getSelectedValue().toString();
				String[] seleccionadaArray = seleccionada.split(",");
				int id = Integer.parseInt(seleccionadaArray[0]);
				int vehiculoID = Integer.parseInt(seleccionadaArray[1]);
				String marca = seleccionadaArray[2];
				String modelo = seleccionadaArray[3];
				int año = Integer.parseInt(seleccionadaArray[4]);
				LocalDate fechaInicio = LocalDate.parse(seleccionadaArray[5]);
				LocalDate fechaFin = LocalDate.parse(seleccionadaArray[6]);
				double monto = Double.parseDouble(seleccionadaArray[7]);
				Reserva reserva = new Reserva(id, new Vehiculo(vehiculoID, marca, modelo, año, null, 0, false), fechaInicio, fechaFin, monto);
				for(Reserva r : currentUsuario.getReservasAsociadas()){
					if(r.getId() == id){
						currentUsuario.getReservasAsociadas().remove(r);
						break;
					}
				}
				userControl.actualizarReservasUsuario(currentUsuario.getID(), currentUsuario.getReservasAsociadas());
				String[] reservasArray = new String[currentUsuario.getReservasAsociadas().size()];
				for(int i = 0; i < currentUsuario.getReservasAsociadas().size(); i++){
					reservasArray[i] = currentUsuario.getReservasAsociadas().get(i).toString();
				}

				listReservas.removeAll();
				listReservas.setListData(reservasArray);
				listReservas.revalidate();
				listReservas.repaint();
				frame.getContentPane().revalidate();
				frame.repaint();
				reservasPane.revalidate();
				reservasPane.repaint();
				
			}
		});
		frame.getContentPane().add(eliminarRsrvBtn);
	}
}
