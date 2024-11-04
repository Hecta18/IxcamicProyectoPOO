package com.easyrents;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.kernel.pdf.PdfDocument;

public class ControladorFacturacion {
    private VistaFacturacion vistaFactura;

    // CONSTRUCTOR
    public ControladorFacturacion(VistaFacturacion vistaFactura) {
        this.vistaFactura = vistaFactura;
    }

    // Imprimir factura en un PDF
    public void imprimirFactura(String factura, Pago pago) {
        try {
            // Métodos y objetos de la dependencia itextpdf
            // La dependencia es cargada en el pom.xml, por maven
            PdfWriter writer = new PdfWriter("factura" + pago.getId() + ".pdf");
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);
            document.add(new Paragraph(factura));
            document.close();
        } catch (Exception e) {
            vistaFactura.mostrarError("Error al generar el PDF de la factura: " + e.getMessage());
        }
    }

    // Generar factura para un pago específico
    public void generarFactura(Pago pago) {
        if (pago == null) {
            vistaFactura.mostrarError("No se encontró el pago.");
            return;
        }
        
        // Creación del texto de la factura
        String factura = "Factura para el pago ID: " + pago.getId() + "\n" +
                         "Reserva asociada: " + pago.getReserva().getId() + "\n" +
                         "Método de pago: " + pago.getMetodoPago() + "\n" +
                         "Monto: $" + pago.getMonto() + "\n" +
                         "Fecha: " + pago.getFechaPago();
        
        // Mostrar la factura en la vista de facturación
        if (vistaFactura.mostrarFactura(factura, pago.getId()) == 0) {
            imprimirFactura(factura, pago);
        } else {
            vistaFactura.mostrarError("Error al mostrar la factura.");
        }
    }
}
