import java.util.Random;

/**
 * La clase Gratis representa una reserva gratuita en Kayac.
 * Extiende la clase Reserva e implementa la interfaz iReserva.
 */
class Gratis extends Reserva implements iReserva {

    /**
     * Constructor para la reserva gratuita.
     * 
     * @param fechaVuelo       La fecha del vuelo.
     * @param tipoDeVuelo      El tipo de vuelo (ida y vuelta o solo ida).
     * @param cantidadBoletos  La cantidad de boletos reservados.
     * @param aerolinea        La aerolínea del vuelo.
     * @param numeroTarjeta    El número de tarjeta para la reserva.
     * @param cuotas           La cantidad de cuotas para el pago.
     * @param claseVuelo       La clase de vuelo seleccionada.
     * @param numeroAsiento    El número de asiento seleccionado.
     * @param cantidadMaletas  La cantidad de maletas para el vuelo.
     * @param usuario          El usuario asociado a la reserva.
     */
    public Gratis(String fechaVuelo, boolean tipoDeVuelo, int cantidadBoletos, String aerolinea, String numeroTarjeta,
            int cuotas, String claseVuelo, int numeroAsiento, int cantidadMaletas, Usuario usuario) {
        super(fechaVuelo, tipoDeVuelo, cantidadBoletos, aerolinea, numeroTarjeta, cuotas, claseVuelo, numeroAsiento,
                cantidadMaletas, usuario);
    }

    /*Numero promedio de asientos en un avion comercial
        clase Coach 200
        clase Premium 20 a 80
    */ 

     /**
     * Genera un número de asiento aleatorio para la reserva gratuita.
     * El número de asiento se encuentra en un rango específico según la clase.
     */
    @Override
    public void seleccionarNumeroDeAsiento(){
        // Crea una instancia de la clase Random
        Random random = new Random();

        // Genera un número aleatorio entre 81 y 200 (para clase Coach)
        int numeroAsiento = random.nextInt(120) + 81;
        System.out.println("Número de asiento seleccionado: " + numeroAsiento);
    }

   /**
     * Establece la cantidad de maletas para la reserva gratuita.
     */
    @Override
    public void cantidadDeMaletas() {
        int cantidadDeMaletas = 1;
        setCantidadMaletas(cantidadDeMaletas);
        System.out.println("El número de maletas para usuarios No Premium es 1");
    }

    /**
     * Establece la nueva clase de vuelo para la reserva gratuita.
     * 
     * @param nuevaClase La nueva clase de vuelo.
     */
    public void seleccionarClase(String nuevaClase) {
        // Establece la nueva clase
        setClaseVuelo(nuevaClase);
        System.out.println("Clase de vuelo seleccionada: " + nuevaClase);
    }

    /**
     * Constructor alternativo para la reserva gratuita.
     * 
     * @param fechaVuelo       La fecha del vuelo.
     * @param tipoVuelo        El tipo de vuelo (ida y vuelta o solo ida).
     * @param cantidadBoletos  La cantidad de boletos reservados.
     * @param aerolinea        La aerolínea del vuelo.
     * @param username         El nombre de usuario.
     * @param numeroTarjeta    El número de tarjeta para la reserva.
     * @param cuotas           La cantidad de cuotas para el pago.
     * @param claseVuelo       La clase de vuelo seleccionada.
     * @param cantidadMaletas  La cantidad de maletas para el vuelo.
     * @param usuario          El usuario asociado a la reserva.
     */
    public Gratis(String fechaVuelo, boolean tipoVuelo, int cantidadBoletos, String aerolinea, String username, int numeroTarjeta, int cuotas, int claseVuelo, int cantidadMaletas, Usuario usuario) {
        super(fechaVuelo, tipoVuelo, cantidadBoletos, aerolinea, numeroTarjeta, cuotas, claseVuelo, cantidadMaletas, usuario);
    }

    /**
     * Obtiene la clase de vuelo de la reserva gratuita.
     * 
     * @return La clase de vuelo.
     */
    public String getClaseVuelo() {
        return claseVuelo;
    }
}