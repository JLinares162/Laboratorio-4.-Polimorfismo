/**
 * La interfaz iReserva define métodos para la selección de asiento, manejo de maletas
 * y selección de clase de vuelo en una reserva.
 */
interface iReserva {
    /**
     * Método para seleccionar el número de asiento en una reserva de vuelo.
     */
    void seleccionarNumeroDeAsiento();

    /**
     * Método para establecer la cantidad de maletas en una reserva de vuelo.
     */
    void cantidadDeMaletas();

    /**
     * Método para seleccionar la clase de vuelo en una reserva.
     *
     * @param claseVuelo La clase de vuelo a seleccionar.
     */
    void seleccionarClase(String claseVuelo);
}