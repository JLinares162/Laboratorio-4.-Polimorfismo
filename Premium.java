import java.util.Scanner;

/**
 * La clase Premium representa una reserva de vuelo de tipo Premium.
 * Hereda de la clase Reserva e implementa la interfaz iReserva.
 */
public class Premium extends Reserva implements iReserva {

    /**
     * Constructor para una reserva de vuelo Premium.
     *
     * @param fechaVuelo       La fecha del vuelo.
     * @param tipoDeVuelo      El tipo de vuelo (ida y vuelta o solo ida).
     * @param cantidadBoletos  La cantidad de boletos reservados.
     * @param aerolinea        La aerolínea del vuelo.
     * @param numeroTarjeta    El número de tarjeta de crédito/debito para pagar la reserva.
     * @param cuotas           La cantidad de cuotas para el pago.
     * @param claseVuelo       La clase de vuelo (ejemplo: económica, ejecutiva, etc.).
     * @param numeroAsiento    El número de asiento reservado.
     * @param cantidadMaletas  La cantidad de maletas para el vuelo.
     * @param usuario          El usuario que realiza la reserva.
     */
    public Premium(String fechaVuelo, boolean tipoDeVuelo, int cantidadBoletos, String aerolinea, String numeroTarjeta,
                   int cuotas, String claseVuelo, int numeroAsiento, int cantidadMaletas, Usuario usuario) {
        super(fechaVuelo, tipoDeVuelo, cantidadBoletos, aerolinea, numeroTarjeta, cuotas, claseVuelo, numeroAsiento,
                cantidadMaletas, usuario);
    }

    /**
     * Método para seleccionar el número de asiento de clase Premium.
     * Pide al usuario ingresar el número de asiento entre 1 y 80.
     */
    @Override
    public void seleccionarNumeroDeAsiento() {
        Scanner scanner = new Scanner(System.in);
        int numeroAsiento;

        do {
            System.out.print("Ingrese el número de asiento de clase Premium (1 al 80): ");
            numeroAsiento = scanner.nextInt();

            if (numeroAsiento < 1 || numeroAsiento > 80) {
                System.out.println("Número de asiento no válido. Por favor, ingrese un número entre 1 y 80.");
            }
        } while (numeroAsiento < 1 || numeroAsiento > 80);
        System.out.println("Número de asiento seleccionado: " + numeroAsiento);
    }

    /**
     * Método para ingresar la cantidad de maletas para la reserva Premium.
     * Solicita al usuario ingresar la cantidad de maletas.
     */
    @Override
    public void cantidadDeMaletas() {
        Scanner scanner = new Scanner(System.in);
        int cantidadMaletas;

        System.out.print("Ingrese la cantidad de maletas: ");
        cantidadMaletas = scanner.nextInt();
        System.out.println("Cantidad de maletas ingresada: " + cantidadMaletas);
    }

    /**
     * Método para seleccionar la clase de vuelo de tipo Premium.
     * Establece la clase de vuelo recibida como parámetro.
     *
     * @param claseVuelo La clase de vuelo a establecer.
     */
    @Override
    public void seleccionarClase(String claseVuelo) {
        setClaseVuelo(claseVuelo);
        System.out.println("Clase de vuelo seleccionada: " + claseVuelo);
    }

    /**
     * Segundo constructor para la clase Premium.
     * Este constructor establece la clase de vuelo como "Primera Clase" por defecto.
     *
     * @param fechaVuelo       La fecha del vuelo.
     * @param tipoVuelo        El tipo de vuelo (ida y vuelta o solo ida).
     * @param cantidadBoletos  La cantidad de boletos reservados.
     * @param aerolinea        La aerolínea del vuelo.
     * @param username         El nombre de usuario.
     * @param numeroTarjeta    El número de tarjeta de crédito/debito para pagar la reserva.
     * @param cuotas           La cantidad de cuotas para el pago.
     * @param claseVuelo       La clase de vuelo.
     * @param cantidadMaletas  La cantidad de maletas para el vuelo.
     * @param usuario          El usuario que realiza la reserva.
     */
    public Premium(String fechaVuelo, boolean tipoVuelo, int cantidadBoletos, String aerolinea, String username,
                   int numeroTarjeta, int cuotas, int claseVuelo, int cantidadMaletas, Usuario usuario) {
        super(fechaVuelo, tipoVuelo, cantidadBoletos, aerolinea, numeroTarjeta, cuotas, claseVuelo, cantidadMaletas, usuario);
    }

    /**
     * Método para obtener la clase de vuelo.
     * Establece la clase de vuelo como "Primera Clase" y la devuelve.
     *
     * @return La clase de vuelo (en este caso, "Primera Clase").
     */
    public String getClaseVuelo() {
        claseVuelo = "Primera Clase";
        return claseVuelo;
    }
}
