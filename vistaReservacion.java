package com.easyrents;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class vistaReservacion {
    public void mostrarFormularioReservacion(Vehiculo vehiculo, JFrame frame, Usuario currentUsuario) {

        vistaInicioSesion vistaInicioSesion = new vistaInicioSesion();
        frame.getContentPane().removeAll();
        frame.repaint();

        vistaInicioSesion.drawMainButtons(frame, currentUsuario);

        JLabel vehiculoImageLbl = new JLabel("");
		vehiculoImageLbl.setBounds(10, 10, 154, 154);
        switch(vehiculo.getTipo()){
            case "Motocicleta":
                vehiculoImageLbl.setIcon(new javax.swing.ImageIcon(this.getClass().getResource("/resources/img/motoIcon.png")));
                break;
            case "Automóvil particular":
                vehiculoImageLbl.setIcon(new javax.swing.ImageIcon(this.getClass().getResource("/resources/img/sedanIcon.png")));
                break;
            case "Bus particular":
                vehiculoImageLbl.setIcon(new javax.swing.ImageIcon(this.getClass().getResource("/resources/img/busIcon.png")));
                break;
        }
        frame.getContentPane().add(vehiculoImageLbl);

		String vehiculoName = "<html>" + vehiculo.getMarca() + "<br/>" + vehiculo.getModelo() + "</html>";
        JLabel vehiculoNameLbl = new JLabel(vehiculoName);
		vehiculoNameLbl.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 26));
		vehiculoNameLbl.setBounds(174, 35, 114, 100);
		frame.getContentPane().add(vehiculoNameLbl);

		JLabel especificacionesTLbl = new JLabel(vehiculo.getMarca() + " " + vehiculo.getModelo() + " " + String.valueOf(vehiculo.getAño()) + " " + vehiculo.getTipo());
		especificacionesTLbl.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 16));
		especificacionesTLbl.setBounds(10, 218, 324, 34);
		frame.getContentPane().add(especificacionesTLbl);
		
		JLabel especificacionesTitleLbl_1 = new JLabel("Especificaciones:");
		especificacionesTitleLbl_1.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 19));
		especificacionesTitleLbl_1.setBounds(10, 180, 140, 34);
		frame.getContentPane().add(especificacionesTitleLbl_1);
		
		JLabel precioTitleLbl = new JLabel("Precio por día: Q. " + vehiculo.getTarifaDiaria());
		precioTitleLbl.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 19));
		precioTitleLbl.setBounds(10, 257, 324, 34);
		frame.getContentPane().add(precioTitleLbl);
		
		String[] meses = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
		String[] years = {"2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035"};

		JComboBox diaInicioBox = new JComboBox();
		diaInicioBox.setBounds(54, 339, 42, 22);
		diaInicioBox.setBackground(Color.WHITE);
		diaInicioBox.setEnabled(false);
		frame.getContentPane().add(diaInicioBox);

		JComboBox diaFinalBox = new JComboBox();
		diaFinalBox.setBounds(230, 339, 42, 22);
		diaFinalBox.setBackground(Color.WHITE);
		diaFinalBox.setEnabled(false);
		frame.getContentPane().add(diaFinalBox);
		
		JComboBox mesInicioBox = new JComboBox();
		mesInicioBox.setBounds(10, 339, 37, 22);
		mesInicioBox.setModel(new DefaultComboBoxModel(meses));
		mesInicioBox.setBackground(Color.WHITE);
		frame.getContentPane().add(mesInicioBox);
		
		JComboBox yearInicioBox = new JComboBox();
		yearInicioBox.setBounds(100, 339, 65, 22);
		yearInicioBox.setModel(new DefaultComboBoxModel(years));
		yearInicioBox.setBackground(Color.WHITE);
		frame.getContentPane().add(yearInicioBox);
		
		JComboBox mesFinalBox = new JComboBox();
		mesFinalBox.setBounds(188, 339, 37, 22);
		mesFinalBox.setModel(new DefaultComboBoxModel(meses));
		mesFinalBox.setBackground(Color.WHITE);
		frame.getContentPane().add(mesFinalBox);
		
		JComboBox yearFinalBox = new JComboBox();
		yearFinalBox.setBounds(277, 339, 65, 22);
		yearFinalBox.setBackground(Color.WHITE);
		yearFinalBox.setModel(new DefaultComboBoxModel(years));
		frame.getContentPane().add(yearFinalBox);

		JLabel totalPagarLbl =  new JLabel("Total a pagar: Q. 0.00");
		totalPagarLbl.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 19));
		totalPagarLbl.setBounds(10, 390, 324, 34);
		frame.getContentPane().add(totalPagarLbl);

		ActionListener cambioStringPrecioIniciales = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				actualizarDiasComboBox(mesInicioBox, yearInicioBox, diaInicioBox);
				if(diaInicioBox.isEnabled() && diaFinalBox.isEnabled()){
					LocalDate fechaInicio = LocalDate.of(Integer.parseInt((String) yearInicioBox.getSelectedItem()), Integer.parseInt((String) mesInicioBox.getSelectedItem()), Integer.parseInt((String) diaInicioBox.getSelectedItem()));
					LocalDate fechaFinal = LocalDate.of(Integer.parseInt((String) yearFinalBox.getSelectedItem()), Integer.parseInt((String) mesFinalBox.getSelectedItem()), Integer.parseInt((String) diaFinalBox.getSelectedItem()));
					
					long numDias = Math.abs(calcDiasInBetween(fechaInicio, fechaFinal));
					totalPagarLbl.setText("Total a pagar: Q. " + vehiculo.getTarifaDiaria() * numDias);
					totalPagarLbl.revalidate();
					totalPagarLbl.repaint();
					frame.getContentPane().revalidate();
					frame.repaint();
				}
			}
		};

		ActionListener cambioStringPrecioFinales = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					LocalDate fechaInicio = LocalDate.of(
						Integer.parseInt((String) yearInicioBox.getSelectedItem()), 
						Integer.parseInt((String) mesInicioBox.getSelectedItem()), 
						Integer.parseInt((String) diaInicioBox.getSelectedItem())
					);
					LocalDate fechaFinal = LocalDate.of(
						Integer.parseInt((String) yearFinalBox.getSelectedItem()), 
						Integer.parseInt((String) mesFinalBox.getSelectedItem()), 
						Integer.parseInt((String) diaFinalBox.getSelectedItem())
					);
					long numDias = ChronoUnit.DAYS.between(fechaInicio, fechaFinal);
		
					LocalDate fechaInicioDate = LocalDate.from(fechaInicio.atStartOfDay(ZoneId.systemDefault()).toInstant());
					LocalDate fechaFinalDate = LocalDate.from(fechaFinal.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
					// Conversión de Date a LocalDate
					LocalDate fechaInicioLocalDate = fechaInicioDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					
					double precioPagar = vehiculo.getTarifaDiaria() * numDias;
					mostrarConfirmacion("¡Se ha creado la reserva exitosamente!");
		
					Reserva reservaNueva = new Reserva(0, currentUsuario, vehiculo, fechaInicioDate, fechaFinalDate, precioPagar);
					// currentUsuario.getReservas().add(reservaNueva);
				} catch (NumberFormatException nF) {
					mostrarError("Debe de llenar todos los campos de fecha solicitados.");
				}
			}
		};

		ActionListener cambioStringPrecioNormal = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(diaInicioBox.isEnabled() && diaFinalBox.isEnabled()){
					LocalDate fechaInicio = LocalDate.of(Integer.parseInt((String) yearInicioBox.getSelectedItem()), Integer.parseInt((String) mesInicioBox.getSelectedItem()), Integer.parseInt((String) diaInicioBox.getSelectedItem()));
					LocalDate fechaFinal = LocalDate.of(Integer.parseInt((String) yearFinalBox.getSelectedItem()), Integer.parseInt((String) mesFinalBox.getSelectedItem()), Integer.parseInt((String) diaFinalBox.getSelectedItem()));
					long numDias = Math.abs(calcDiasInBetween(fechaInicio, fechaFinal));
					totalPagarLbl.setText("Total a pagar: Q. " + vehiculo.getTarifaDiaria() * numDias);
					totalPagarLbl.revalidate();
					totalPagarLbl.repaint();
					frame.getContentPane().revalidate();
					frame.repaint();
				}
			}
		};

		mesInicioBox.addActionListener(cambioStringPrecioIniciales);
		yearInicioBox.addActionListener(cambioStringPrecioNormal);
		diaInicioBox.addActionListener(cambioStringPrecioNormal);

		mesFinalBox.addActionListener(cambioStringPrecioFinales);
		yearFinalBox.addActionListener(cambioStringPrecioNormal);
		diaFinalBox.addActionListener(cambioStringPrecioNormal);

		JButton btnReservar = new JButton("¡Reservar!");
		btnReservar.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		btnReservar.setBackground(Color.WHITE);
		btnReservar.setBounds(111, 475, 114, 45);
		btnReservar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					LocalDate fechaInicio = LocalDate.of(Integer.parseInt((String) yearInicioBox.getSelectedItem()), Integer.parseInt((String) mesInicioBox.getSelectedItem()), Integer.parseInt((String) diaInicioBox.getSelectedItem()));
					LocalDate fechaFinal = LocalDate.of(Integer.parseInt((String) yearFinalBox.getSelectedItem()), Integer.parseInt((String) mesFinalBox.getSelectedItem()), Integer.parseInt((String) diaFinalBox.getSelectedItem()));
					long numDias = ChronoUnit.DAYS.between(fechaInicio, fechaFinal);
					LocalDate fechaInicioDate = LocalDate.from(fechaInicio.atStartOfDay(ZoneId.systemDefault()).toInstant());
					LocalDate fechaFinalDate = LocalDate.from(fechaFinal.atStartOfDay(ZoneId.systemDefault()).toInstant());

					double precioPagar = vehiculo.getTarifaDiaria() * numDias;
					mostrarConfirmacion("¡Se ha creado la reserva exitosamente!");
					// Reserva de prueba
					Reserva reservaNueva = new Reserva(0, currentUsuario, vehiculo, fechaInicioDate, fechaFinalDate, precioPagar);
					// currentUsuario.getReservas().add(reservaNueva);
				}catch(NumberFormatException nF){
					mostrarError("Debe de llenar todos los campos de fecha solicitados.");
				}
				
			}
		});
		frame.getContentPane().add(btnReservar);
		
		JLabel fechaInicioLbl = new JLabel("Fecha de Inicio");
		fechaInicioLbl.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 16));
		fechaInicioLbl.setBounds(31, 305, 105, 34);
		frame.getContentPane().add(fechaInicioLbl);
		
		JLabel lblFechaDeDevolucin = new JLabel("Fecha de Devolución");
		lblFechaDeDevolucin.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 16));
		lblFechaDeDevolucin.setBounds(184, 305, 140, 34);
		frame.getContentPane().add(lblFechaDeDevolucin);

		JLabel mesLbl = new JLabel("mes");
		mesLbl.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 16));
		mesLbl.setBounds(15, 360, 27, 22);
		frame.getContentPane().add(mesLbl);

		JLabel mesLbl2 = new JLabel("mes");
		mesLbl2.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 16));
		mesLbl2.setBounds(193, 360, 27, 22);
		frame.getContentPane().add(mesLbl2);
		
		JLabel lblDia = new JLabel("dia");
		lblDia.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 16));
		lblDia.setBounds(64, 360, 27, 22);
		frame.getContentPane().add(lblDia);
		
		JLabel lblDia2 = new JLabel("dia");
		lblDia2.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 16));
		lblDia2.setBounds(242, 360, 27, 22);
		frame.getContentPane().add(lblDia2);
		
		JLabel lblYear = new JLabel("año");
		lblYear.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 16));
		lblYear.setBounds(115, 360, 39, 22);
		frame.getContentPane().add(lblYear);
		
		JLabel lblYear_1 = new JLabel("año");
		lblYear_1.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 16));
		lblYear_1.setBounds(293, 360, 27, 22);
		frame.getContentPane().add(lblYear_1);
    }

	private static void actualizarDiasComboBox(JComboBox<String> mesBox, JComboBox<String> yearBox, JComboBox<String> diaBox) {
        diaBox.removeAllItems();
        int mesSeleccionado = mesBox.getSelectedIndex() + 1;
        int yearSeleccionado = Integer.parseInt((String) yearBox.getSelectedItem());

        int diasEnMes = LocalDate.of(yearSeleccionado, mesSeleccionado, 1).lengthOfMonth();
        for (int i = 1; i <= diasEnMes; i++) {
            diaBox.addItem(String.valueOf(i));
        }
        diaBox.setEnabled(true);
    }

	public long calcDiasInBetween(LocalDate fechaInicio, LocalDate fechaFinal) {
		return ChronoUnit.DAYS.between(fechaInicio, fechaFinal);
	}

    public void mostrarConfirmacion(String message) {
        JOptionPane.showMessageDialog(null, message, "Creación exitosa", JOptionPane.INFORMATION_MESSAGE);
    }
    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
