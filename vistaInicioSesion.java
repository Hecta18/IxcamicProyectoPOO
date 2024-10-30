package com.easyrents;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class vistaInicioSesion {
	boolean credentialisValidated = false;

    public void mostrarFormulario(JFrame frame) {
		//preparar pantalla
        frame.getContentPane().removeAll();
        frame.repaint();
        //subir logo
        Image iconImage = new ImageIcon(this.getClass().getResource("/resources/img/easyLogo.png")).getImage();
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
		Image imgTwit = new ImageIcon(this.getClass().getResource("resources/img/twitter-icon.png")).getImage();
		imgTwitter.setIcon(new ImageIcon(imgTwit));
		imgTwitter.setBounds(180, 518, 50, 50);
		frame.getContentPane().add(imgTwitter);
		//subir logo Insta
		JLabel imgInsta = new JLabel("");
		Image imgIg = new ImageIcon(this.getClass().getResource("/resources/img/insta-icon.png")).getImage();
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
                redireccionarCrearCuenta(frame);
            }
        });
		frame.getContentPane().add(createAccountBtn);
		//boton login
		JButton loginBtn_1 = new JButton("Iniciar Sesión");
		loginBtn_1.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 15));
		loginBtn_1.setBounds(109, 320, 121, 47);
        loginBtn_1.setBackground(Color.WHITE);
        // CONDICIONAL QUE VENDRÁ DE VALIDAR LOS DATOS DEL USUARIO, FALTA IMPLEMENTAR
		Usuario usuarioActual = new Usuario(298649667, "Santiago Cordero Quirós", "cor24472@uvg.edu.gt", "pepe123", "Turismo", 22000000);
		//usuario actual, prueba momentanea
		loginBtn_1.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e){
				String passwordIngresado = new String(passwordField.getPassword());
				String correoIngresado = txtCorreoElectrnico.getText();
				if((correoIngresado.equals("")) || (passwordIngresado.equals(""))){
					mostrarError("Debe de ingresar un correo y su contraseña respectiva.");
					return;
				}else if(!correoIngresado.contains("@")){
					mostrarError("Debe de ingresar una dirección de correo válida");
					return;
				}else if(!(correoIngresado.equals(usuarioActual.getCorreo()) && passwordIngresado.equals(usuarioActual.getContraseña()))){
					mostrarError("Las credenciales ingresadas no son correctas, vuelva a intentar.");
					return;
				}else{
					redireccionarDashboard(frame, usuarioActual);
				}
			}
			public void actionPerformed2(ActionEvent e){
				if((txtCorreoElectrnico.getText().replaceAll("\\s+","").equals("")) || (passwordField.getPassword().toString().replaceAll("\\s+","").equals(""))){
					mostrarError("Debe de ingresar un correo y su contraseña respectiva.");
					return;
					//debe crearse el metodo para validar credenciales!!!!!!!!
				// }else if(!credentialsValidated){
				// 	mostrarError("Las credenciales ingresadas no son correctas, vuelva a intentar.");
				// 	return;
				}else if(!txtCorreoElectrnico.getText().contains("@")){
					mostrarError("Debe de ingresar una dirección de correo válida");
					return;
				}else{
					// USUARIO DE PRUEBA: (FALTA HACER LA LOGIN CON LA BASE DE DATOS Y VALIDAR)
					Usuario usuarioActual = new Usuario(00000000, "Usuario Prueba", "correodelusuario@gmail.com", "pepe123", "Turismo", 12345678);
					redireccionarDashboard(frame, usuarioActual);
					return;
				}
			}
		});
		frame.getContentPane().add(loginBtn_1);

        JLabel logoImageLabel = new JLabel();
		Image logoImage = new ImageIcon(this.getClass().getResource("/resources/img/easyLogo95x95.png")).getImage();
		logoImageLabel.setIcon(new ImageIcon(logoImage));
		logoImageLabel.setBounds(116, 7, 115, 95);
		frame.getContentPane().add(logoImageLabel);
		
		JLabel logoNameImgLbl = new JLabel("");
		Image logoNameImg = new ImageIcon(this.getClass().getResource("/resources/img/easyRents177x50.png")).getImage();
		logoNameImgLbl.setIcon(new ImageIcon(logoNameImg));
		logoNameImgLbl.setBounds(84, 106, 177, 50);
		frame.getContentPane().add(logoNameImgLbl);

    }

	public void drawMainButtons(JFrame frame, Usuario usuarioActual) {
		frame.getContentPane().removeAll();
		frame.repaint();
		JLabel cerrarSesionLbl = new JLabel("Cerrar Sesión");
		cerrarSesionLbl.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		cerrarSesionLbl.setForeground(Color.DARK_GRAY);
		cerrarSesionLbl.setBounds(254, 11, 80, 14);
		cerrarSesionLbl.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mostrarFormulario(frame);
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

		Image profileImg = new ImageIcon(this.getClass().getResource("/resources/img/profileIconSmall.png")).getImage();
		Image profileImgHover = new ImageIcon(this.getClass().getResource("/resources/img/profileIconSmallHover.png")).getImage();
		JButton perfilBtn = new JButton("");
		perfilBtn.setBounds(244, 545, 50, 50);
		perfilBtn.setBorderPainted(false);
		perfilBtn.setContentAreaFilled(false);
		perfilBtn.setIcon(new ImageIcon(profileImg));
		perfilBtn.setRolloverIcon(new ImageIcon(profileImgHover));
		perfilBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				redireccionarPerfil(frame, usuarioActual);
			}
		});
		frame.getContentPane().add(perfilBtn);
		
		Image verReservasImg = new ImageIcon(this.getClass().getResource("/resources/img/menuIcon.png")).getImage();
		Image verReservasImgHover = new ImageIcon(this.getClass().getResource("/resources/img/menuIconHover.png")).getImage();
		JButton verReservasBtn = new JButton("");
		verReservasBtn.setBounds(41, 545, 50, 50);
		verReservasBtn.setBorderPainted(false);
		verReservasBtn.setContentAreaFilled(false);
		verReservasBtn.setIcon(new ImageIcon(verReservasImg));
		verReservasBtn.setRolloverIcon(new ImageIcon(verReservasImgHover));
		verReservasBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				redireccionarReservas(frame, usuarioActual);
			}
		});
		frame.getContentPane().add(verReservasBtn);
		
		Image homeImg = new ImageIcon(this.getClass().getResource("/resources/img/homeIcon.png")).getImage();
		Image homeImgHover = new ImageIcon(this.getClass().getResource("/resources/img/homeIconHover.png")).getImage();
		JButton homeBtn = new JButton();
		homeBtn.setBounds(141, 545, 50, 50);
		homeBtn.setBorderPainted(false);
		homeBtn.setContentAreaFilled(false);
		homeBtn.setIcon(new ImageIcon(homeImg));
		homeBtn.setRolloverIcon(new ImageIcon(homeImgHover));
		homeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a){
				redireccionarDashboard(frame, usuarioActual);
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

    public void redireccionarCrearCuenta(JFrame frame) {
        frame.getContentPane().removeAll();
        frame.repaint();

        ArrayList<Object> entryList = new ArrayList<>();//posible unchecked

        JButton backBtn = new JButton("Regresar");
		backBtn.setBackground(Color.WHITE);
		backBtn.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 15));
		backBtn.setBounds(257, 550, 87, 51);
        backBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                mostrarFormulario(frame);
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
		motivoLbl1.setBounds(10, 366, 172, 29);
		frame.getContentPane().add(motivoLbl1);
		
		JLabel motivoLbl2 = new JLabel("utilizar nuestra app?");
		motivoLbl2.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		motivoLbl2.setBounds(10, 387, 172, 29);
		frame.getContentPane().add(motivoLbl2);

        JLabel dpiLabel = new JLabel("No. de DPI");
		dpiLabel.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		dpiLabel.setBounds(10, 310, 94, 29);
		frame.getContentPane().add(dpiLabel);
		
		JTextField dpiEntry = new JTextField();
		dpiEntry.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		dpiEntry.setBounds(98, 318, 236, 26);
		frame.getContentPane().add(dpiEntry);
		
		JLabel lblsinGuiones = new JLabel("(sin guiones)");
		lblsinGuiones.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 12));
		lblsinGuiones.setBounds(10, 326, 94, 29);
		frame.getContentPane().add(lblsinGuiones);

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
		tipoUsuarioDropDown.setBounds(162, 376, 170, 29);
        tipoUsuarioDropDown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(tipoUsuarioDropDown.getSelectedItem().toString() == "Otro (Especifique)"){
                    razonExtra.setVisible(true);
                    razonExtraLbl.setVisible(true);
                    crearCuentaBtn.setLocation(91, 470);
                    
                }else{
                    razonExtra.setVisible(false);
                    razonExtraLbl.setVisible(false);
                    crearCuentaBtn.setLocation(91, 435);
                }
            }
        });
		frame.getContentPane().add(tipoUsuarioDropDown);

		crearCuentaBtn.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 22));
        crearCuentaBtn.setBackground(Color.WHITE);
		crearCuentaBtn.setBounds(91, 435, 165, 52);
        crearCuentaBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                ArrayList<String> values = new ArrayList<>();//posible unchecked
                values.add(nombreEntry.getText());
                values.add(correoEntry.getText());
                values.add(new String(passwordField.getPassword()));
                values.add(new String(passwordFieldConfirmar.getPassword()));
                values.add(tipoUsuarioDropDown.getSelectedItem().toString());
                values.add(String.valueOf(Long.parseLong(dpiEntry.getText())));

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
					// listaUsuarios.add(usuarioActual);
                    return;
                }else{
                    values.add(razonExtra.getText());
                    mostrarExito("Se ha creado su cuenta exitosamente!");
					// listaUsuarios.add(usuarioActual);
                    return;
                }
            }
        });
		frame.getContentPane().add(crearCuentaBtn);		
    }

    public void redireccionarDashboard(JFrame frame, Usuario currentUsuario) {
		drawMainButtons(frame, currentUsuario);
		
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
		tipoVehiculoDropDown.setModel(new DefaultComboBoxModel(new String[] {"Motocicleta", "Automóvil particular", "Bus particular"}));
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
			String[] datos = new String[2]; // Sacar de la base de datos todos los datos de los vehículos parseados como una lista de Strings
			// Filtrar los datos por cada uno de los tipos
			String[] datosFiltradosMotos = {"Ducati Panigale 2016 Q.350/día", "Harley Davidson 2024 Q.250/día"};
			String[] datosFiltradosAutos = {"Audi R8 2024 Q.500/día", "BMW M3 2023 Q.600/día"};
			String[] datosFiltradosBuses = {"Audi RS99 2015 Q.1250/día", "Mazda T3500 2017 Q.950/día"};
			vehiculoDropDown.removeAll();
			vehiculoDropDown.revalidate();
			vehiculoDropDown.repaint();
			frame.getContentPane().revalidate();
			frame.repaint();
			resultsScrollPane.revalidate();
			resultsScrollPane.repaint();
			switch (seleccionado){
				case "Motocicleta":
					vehiculoDropDown.setListData(datosFiltradosMotos);
					break;
				case "Automóvil particular":
					vehiculoDropDown.setListData(datosFiltradosAutos);
					break;
				case "Bus particular":
					vehiculoDropDown.setListData(datosFiltradosBuses);
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
						case "Automóvil particular":
							tipoAuto = "Automóvil particular";
							break;
						case "Bus particular":
							tipoAuto = "Bus particular";
							break;
					}
					Vehiculo vehiculoSeleccionado = new Vehiculo(1, datosVehiculo[0], datosVehiculo[1], Integer.parseInt(datosVehiculo[2]), tipoAuto, Double.parseDouble(datosVehiculo[3].replaceAll("Q.", "").replaceAll("/día", "")), true);
					vistaReservacion vistaRes = new vistaReservacion();
					vistaRes.mostrarFormularioReservacion(vehiculoSeleccionado, frame, currentUsuario);
				}
			}
		});
		frame.getContentPane().add(reservarBtn);

    }

    public void redireccionarPerfil(JFrame frame, Usuario currentUsuario){
		drawMainButtons(frame, currentUsuario);

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
        Image largeProfile = new ImageIcon(this.getClass().getResource("/resources/img/profileIcon.png")).getImage();
        largeProfileLbl.setIcon(new ImageIcon(largeProfile));
		largeProfileLbl.setBounds(15,0,300,300);
        frame.getContentPane().add(largeProfileLbl);

        String[] licencias = {"29518261", "52912112", "1582925"}; // DEBERÍA DE SER usuario.getLicencias(), TODAVIA NO EXISTE EL MÉTODO.
        String licenciasLblString = "<html>";
        int licenciasLblHeight = 0;
        int j = 0;
        for(String l : licencias){
            licenciasLblString += ("<br/> - " + l);
            licenciasLblHeight += 28 * j;
            j++;
        }
		licenciasLblString += "</html>";

        JLabel licenciasListLabel = new JLabel(licenciasLblString);
		licenciasListLabel.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 16));
		licenciasListLabel.setBounds(46, 380, 139, licenciasLblHeight);
		frame.getContentPane().add(licenciasListLabel);
		
		JLabel licenciaLbl = new JLabel("Licencias Asociadas:");
		licenciaLbl.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 17));
		licenciaLbl.setBounds(10, 366, 150, 31);
		frame.getContentPane().add(licenciaLbl);
		
		JLabel telefonoTxtLabel = new JLabel("Teléfono:");
		telefonoTxtLabel.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 17));
		telefonoTxtLabel.setBounds(10, 240, 71, 31);
		frame.getContentPane().add(telefonoTxtLabel);

        JLabel telefonoValueLbl = new JLabel("42165624"); // FALTA COLOCAR .getTelefono() COMO MÉTODO DE USUARIO
		telefonoValueLbl.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 17));
		telefonoValueLbl.setBounds(91, 240, 150, 31);
		frame.getContentPane().add(telefonoValueLbl);
		frame.setLocationRelativeTo(null);
		
		JLabel dpiTextLbl = new JLabel("No. de DPI asociado:");
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

	public void redireccionarReservas(JFrame frame, Usuario currentUsuario) {
		drawMainButtons(frame, currentUsuario);

		JLabel reservasTitleLbl = new JLabel("Reservas Actuales");
		reservasTitleLbl.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 23));
		reservasTitleLbl.setBounds(85, 37, 175, 38);		
		frame.getContentPane().add(reservasTitleLbl);

		// FALTA IMPLEMENTAR LÓGICA DE OBTENER LOS DATOS DE LAS RESERVAS Y MOSTRARLAS COMO LISTA.
		/*
		String[] reservas = currentUsuario.getReservas();
        String reservasLblString = "<html> ";
        int reservasLblHeight = 0;
		int k = 0;
        for(String l : reservas){
            reservasLblString += (l + "<br/>");
			reservasLblHeight += 25 * k;
			k++;
        }
		*/
		
		// ESTA DECLARACIÓN DE SCROLLPANE SERÍA:
		// JScrollPane reservasPane = new JScrollPane(new JLabel(reservasLblString));
		JScrollPane reservasPane = new JScrollPane();
		reservasPane.setBounds(10, 85, 324, 445);
		reservasPane.setViewportView(new JLabel("Reservas"));
		reservasPane.setBorder(null);
		reservasPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		reservasPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		frame.getContentPane().add(reservasPane);
	}
}
