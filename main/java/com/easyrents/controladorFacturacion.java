package com.easyrents;

// Importamos las clases necesarias de iText para crear el PDF
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class controladorFacturacion {
    private vistaFacturacion vistaFactura;

    // CONSTRUCTOR que recibe una instancia de la vista de facturación
    public controladorFacturacion(vistaFacturacion vistaFactura) {
        this.vistaFactura = vistaFactura;
    }

    // Método para imprimir la factura en un archivo PDF
    public void imprimirFactura(String factura, Pago pago) {
        try {
            // Definimos el nombre del archivo PDF con el ID del pago
            PdfWriter writer = new PdfWriter("factura" + pago.getID() + ".pdf");
            
            // Creamos el documento PDF y vinculamos el writer
            PdfDocument pdf = new PdfDocument(writer);
            
            // Agregamos un documento para escribir contenido
            Document document = new Document(pdf);
            
            // Añadimos el texto de la factura al documento
            document.add(new Paragraph(factura));
            
            // Cerramos el documento para guardar los cambios
            document.close();
        } catch (Exception e) {
            // En caso de error, mostramos un mensaje en la vista
            vistaFactura.mostrarError("Error al generar el PDF de la factura: " + e.getMessage());
        }
    }

    // Método para generar la factura a partir de un objeto Pago
    public void generarFactura(Pago pago) {
        if (pago == null) {
            // Validación: si el pago es nulo, se muestra un error
            vistaFactura.mostrarError("No se encontró el pago.");
            return;
        }

        // Creación del texto de la factura usando los datos del pago
        String factura = "Factura para el pago ID: " + pago.getID() + "\n" +
                         "Reserva asociada: " + pago.getReserva().getId() + "\n" +
                         "Método de pago: " + pago.getMetodoPago() + "\n" +
                         "Monto: $" + pago.getMonto() + "\n" +
                         "Fecha: " + pago.getFechaPago();
        
        // Mostramos la factura en la interfaz gráfica y verificamos si el usuario desea imprimirla
        if (vistaFactura.mostrarFactura(factura, pago.getID()) == 0) {
            // Llamamos al método para crear el archivo PDF si se confirma la impresión
            imprimirFactura(factura, pago);
        } else {
            // En caso contrario, mostramos un mensaje de error
            vistaFactura.mostrarError("Error al mostrar la factura.");
        }
    }
}
