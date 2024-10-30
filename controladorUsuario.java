package com.easyrents;

public class controladorUsuario {
    // Usuario actualmente autenticado en el sistema
    
    // Vista relacionada con el inicio de sesión (interfaz gráfica o de consola)
    private vistaInicioSesion vistaInicioSesion;

    // Constructor del controlador que inicializa la vista del inicio de sesión
    public controladorUsuario(vistaInicioSesion vistaInicioSesion) {
        this.vistaInicioSesion = vistaInicioSesion;
    }

    // Método para gestionar el inicio de sesión de un usuario
    public void iniciarSesion(String correo, String contraseña) {
        // Validar que el correo y la contraseña no sean nulos ni estén vacíos
        if (correo == null || contraseña == null || correo.isEmpty() || contraseña.isEmpty()) {
            vistaInicioSesion.mostrarError("El correo y la contraseña son obligatorios.");
            return;
        }

        // Simulación de búsqueda del usuario en la base de datos por su correo
        Usuario usuario = obtenerUsuarioPorCorreo(correo);
        
        // Verificar si el usuario existe y si la contraseña es correcta
        if (usuario != null && usuario.getContraseña().equals(contraseña)) {
            // Autenticar al usuario, guardándolo como el usuario actual
            usuarioActual = usuario;
            vistaInicioSesion.mostrarExito("Inicio de sesión exitoso."); // Asegúrate de que este método esté definido
        } else {
            // Mostrar error si el correo o la contraseña son incorrectos
            vistaInicioSesion.mostrarError("Correo o contraseña incorrectos.");
        }
    }

    // Método para registrar un nuevo usuario en el sistema
    public void registrarUsuario(Usuario nuevoUsuario) {
        // Validar que los datos del nuevo usuario no sean nulos o estén vacíos
        if (nuevoUsuario == null || nuevoUsuario.getCorreo().isEmpty() || nuevoUsuario.getContraseña().isEmpty()) {
            vistaInicioSesion.mostrarError("Datos inválidos para el registro.");
            return;
        }

        // Verificar si ya existe un usuario con el mismo correo
        Usuario usuarioExistente = obtenerUsuarioPorCorreo(nuevoUsuario.getCorreo());
        if (usuarioExistente == null) {
            // Si no existe, proceder con el registro del nuevo usuario
            // Aquí se debe implementar la lógica para guardar el nuevo usuario en la base de datos
            vistaInicioSesion.mostrarExito("Usuario registrado exitosamente.");
        } else {
            // Si el usuario ya existe, mostrar un mensaje de error
            vistaInicioSesion.mostrarError("El usuario ya existe.");
        }
    }

    // Simulación de método para buscar un usuario por correo
    // En una implementación real, aquí se realizaría la consulta a la base de datos
    private Usuario obtenerUsuarioPorCorreo(String correo) {
        // Placeholder: en una aplicación real, se buscaría el usuario en la base de datos
        return null; 
    }
}
