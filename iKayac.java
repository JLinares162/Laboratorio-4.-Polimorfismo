/**
 * La interfaz iKayac define métodos para gestionar usuarios, reservas y operaciones relacionadas en Kayac.
 */
interface iKayac {
    /**
     * Método para iniciar sesión en Kayac.
     *
     * @param username El nombre de usuario.
     * @param password La contraseña del usuario.
     * @return El usuario que inicia sesión si es válido; de lo contrario, null.
     */
    public Usuario login(String username, String password);

    /**
     * Método para registrar un nuevo usuario en Kayac.
     *
     * @param username El nombre de usuario.
     * @param password La contraseña del usuario.
     * @param tipo     El tipo de usuario (premium, gratis, etc.).
     */
    public void registroUsuario(String username, String password, String tipo);

    /**
     * Método para cambiar la contraseña del usuario actual en Kayac.
     *
     * @param nuevaPassword La nueva contraseña a establecer.
     */
    public void cambiarPassword(String nuevaPassword);

    /**
     * Método para cambiar el tipo de usuario (gratis, premium, etc.) del usuario actual en Kayac.
     */
    public void cambiarTipoUsuario();

    /**
     * Método para realizar una reserva de vuelo en Kayac.
     *
     * @param fechaVuelo      La fecha del vuelo.
     * @param tipoVuelo       El tipo de vuelo (ida y vuelta o solo ida).
     * @param cantidadBoletos La cantidad de boletos reservados.
     * @param areolinea       La aerolínea del vuelo.
     * @param username        El nombre de usuario.
     */
    public void reservacion(String fechaVuelo, boolean tipoVuelo, int cantidadBoletos, String areolinea, String username);

    /**
     * Método para confirmar una reserva en Kayac.
     *
     * @param numeroTarjeta    El número de tarjeta para la reserva.
     * @param cuotas           La cantidad de cuotas para el pago.
     * @param claseVuelo       La clase de vuelo seleccionada.
     * @param numeroAsiento    El número de asiento seleccionado.
     * @param cantidadMaletas  La cantidad de maletas para el vuelo.
     */
    public void confirmación(String numeroTarjeta, int cuotas, String claseVuelo, int numeroAsiento, int cantidadMaletas);

    /**
     * Método para obtener el itinerario de la última reserva realizada en Kayac.
     *
     * @return El itinerario de la última reserva.
     */
    public String itinerario();

    /**
     * Método para guardar la información de usuarios en Kayac.
     */
    public void guardarUsuario();

    /**
     * Método para guardar la información de reservas en Kayac.
     */
    public void guardarReserva();

    /**
     * Método para leer la información de usuarios almacenada en Kayac.
     */
    public void leerUsuario();

    /**
     * Método para leer la información de reservas almacenada en Kayac.
     */
    public void leerReserva();
}