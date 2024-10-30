package main.java.com.easyrents;
//modularizacion de la enumeracion para facilitar su edicion
public enum enumMetodoPago {
    TARJETA("tarjeta"),
    PAYPAL("paypal"),
    TRANSFERENCIA("transferencia");
    //se crea un constructor para poder realizar una lista, set, de enums
    //ya que un set de enums tiene metodos practicos para validar enums
    private String metodo;

    enumMetodoPago(String metodo) {
        this.metodo = metodo;
    }

    public String getMetodo() {
        return metodo;
    }
}
