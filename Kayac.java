import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * La clase Kayac representa la plataforma para reservas de vuelo y gestión de usuarios.
 * Implementa la interfaz iKayac.
 */

class Kayac implements iKayac {
    private String usernameActual;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Reserva> reservas;

    /**
     * Constructor de la clase Kayac.
     * Inicializa las listas de usuarios y reservas.
     */

    public Kayac() {
        this.usuarios = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }

    /**
     * Establece el nombre de usuario actual.
     *
     * @param usernameActual El nombre de usuario a establecer.
     */

    public void UsernameActual(String usernameActual) {
        this.usernameActual = usernameActual;
    }
    
    /**
     * Método para leer usuarios desde un archivo CSV.
     * Lee datos del archivo 'usuarios.csv' y los almacena en la lista de usuarios.
     */

    public void leerUsuario() {
        String csvFile = "usuarios.csv";
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length == 3) {
                    Usuario usuario = new Usuario(userData[0], userData[1], userData[2]);
                    usuarios.add(usuario);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     /**
     * Método para leer reservas desde un archivo CSV.
     * Lee datos del archivo 'reservas.csv' y los almacena en la lista de reservas.
     */

    public void leerReserva() {
        String csvFile = "reservas.csv";
    
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String line;
    
            while ((line = reader.readLine()) != null) {
                String[] reservaData = line.split(",");
                if (reservaData.length == 5) {
                    Reserva reserva = new Reserva(
                            reservaData[0],  // FechaVuelo
                            Boolean.parseBoolean(reservaData[1]),  // TipoVuelo
                            Integer.parseInt(reservaData[2]),  // CantidadBoletos
                            reservaData[3],  // Aerolinea
                            line, 0, "x", 0, 0, buscarUsuarioPorUsername(reservaData[4])  // Username
                    );
                    reservas.add(reserva);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Método para realizar el inicio de sesión.
     * Comprueba si el usuario y la contraseña coinciden con alguna cuenta existente.
     *
     * @param username El nombre de usuario.
     * @param password La contraseña del usuario.
     * @return El usuario que inicia sesión, si es válido; de lo contrario, retorna null.
     */

    @Override
    public Usuario login(String username, String password) {
        leerUsuario();
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username) && usuario.getPassword().equals(password)) {
                return usuario;
            }
        }
        UsernameActual(username);
        return null;  // Usuario no encontrado
    }

    @Override
    public void registroUsuario(String username, String password, String tipo) {
        Usuario nuevoUsuario = new Usuario(username, password, tipo);
        usuarios.add(nuevoUsuario);

        // Guardar en archivo CSV
        guardarUsuario();
    }

    public void guardarUsuario() {
        String csvFile = "usuarios.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile, false))) {
            // Escribir encabezados si el archivo está vacío
            if (usuarios.isEmpty()) {
                writer.write("Username,Password,Tipo\n");
            }

            // Escribir datos de usuarios
            for (Usuario usuario : usuarios) {
                writer.write(usuario.getUsername() + "," + usuario.getPassword() + "," + usuario.getTipo() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Usuario buscarUsuarioPorUsername(String username) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username)) {
                return usuario;
            }
        }
        return null;  // Usuario no encontrado
    }

    /**
     * Método para realizar una reserva de vuelo.
     * Crea una nueva reserva basada en el tipo de usuario y la almacena en la lista de reservas.
     *
     * @param fechaVuelo       La fecha del vuelo.
     * @param tipoVuelo        El tipo de vuelo (ida y vuelta o solo ida).
     * @param cantidadBoletos  La cantidad de boletos reservados.
     * @param aerolinea        La aerolínea del vuelo.
     * @param username         El nombre de usuario.
     */

    public void reservacion(String fechaVuelo, boolean tipoVuelo, int cantidadBoletos, String aerolinea, String username) {
        Usuario usuario = buscarUsuarioPorUsername(username);

        if (usuario != null) {
            Reserva nuevaReserva;
    
            if ("premium".equals(usuario.getTipo())) {
                nuevaReserva = new Premium(fechaVuelo, tipoVuelo, cantidadBoletos, aerolinea, " ",0, "Primera Clase", 0, 0, usuario);
                System.out.println("Reserva premium realizada");
            } else {
                nuevaReserva = new Gratis(fechaVuelo, tipoVuelo, cantidadBoletos, aerolinea, " ",0, " ", 0, 1, usuario);
                System.out.println("Reserva gratis realizada");
            } 
    
            reservas.add(nuevaReserva);
    
            // Guardar la reserva en archivo CSV
            guardarReserva();
    
            System.out.println("Reserva realizada con éxito para el usuario: " + usuario.getUsername());
            itinerario();
        } else {
            System.out.println("Usuario no encontrado. No se pudo realizar la reserva.");
        }
    }

    /**
     * Método para confirmar una reserva.
     * Confirma la reserva según el tipo (Premium o Gratis) y completa los detalles requeridos.
     *
     * @param numeroTarjeta    El número de tarjeta para la reserva.
     * @param cuotas           La cantidad de cuotas para el pago.
     * @param claseVuelo       La clase de vuelo seleccionada.
     * @param numeroAsiento    El número de asiento seleccionado.
     * @param cantidadMaletas  La cantidad de maletas para el vuelo.
     */

    @Override
    public void confirmación(String numeroTarjeta, int cuotas, String claseVuelo, int numeroAsiento, int cantidadMaletas) {
        leerReserva();
        if (reservas.isEmpty()) {
            System.out.println("No hay reservas disponibles para confirmar.");
            return;
        }
    
        Reserva ultimaReserva = reservas.get(reservas.size() - 1);
    
        if (ultimaReserva instanceof Gratis) {
            confirmarGratis((Gratis) ultimaReserva, numeroTarjeta, cuotas, claseVuelo, numeroAsiento, cantidadMaletas);
        } else if (ultimaReserva instanceof Premium) {
            confirmarPremium((Premium) ultimaReserva, numeroTarjeta, cuotas, claseVuelo, numeroAsiento, cantidadMaletas);
        } else {
            System.out.println("No se pudo determinar el tipo de reserva para la confirmación.");
        }
    }
    
    private void confirmarGratis(Gratis reserva, String numeroTarjeta, int cuotas, String claseVuelo, int numeroAsiento, int cantidadMaletas) {
        reserva.definirNumeroDeTarjeta(numeroTarjeta);
        reserva.definirCantidadCuotas(cuotas);
        reserva.seleccionarClase(claseVuelo);
        reserva.seleccionarNumeroDeAsiento();
        reserva.cantidadDeMaletas();
        // Guardar la reserva en archivo CSV
        guardarReserva();
    
        System.out.println("Confirmación realizada con éxito para la reserva gratis.");
    }
    
    private void confirmarPremium(Premium reserva, String numeroTarjeta, int cuotas, String claseVuelo, int numeroAsiento, int cantidadMaletas) {
        reserva.definirNumeroDeTarjeta(numeroTarjeta);
        reserva.definirCantidadCuotas(cuotas);
        reserva.seleccionarClase(claseVuelo);
        reserva.setNumeroAsiento(numeroAsiento);
        reserva.setCantidadMaletas(cantidadMaletas);

        // Guardar la reserva en archivo CSV
        guardarReserva();
    
        System.out.println("Confirmación realizada con éxito para la reserva premium.");
    }
    

    @Override
    public String itinerario() {
        if (reservas.isEmpty()) {
            return "No hay reservas disponibles.";
        }
    
        Reserva ultimaReserva = reservas.get(reservas.size() - 1);
    
        return ultimaReserva.imprimirItinerario();
    }

    /**
     * Método para cambiar la contraseña del usuario actual.
     *
     * @param nuevaPassword La nueva contraseña a establecer.
     */

    public void cambiarPassword(String nuevaPassword) {
        Usuario usuario = buscarUsuarioPorUsername(usernameActual);

        if (usuario != null) {
            usuario.setPassword(nuevaPassword);
            System.out.println("Contraseña cambiada con éxito para el usuario: " + usuario.getUsername());
            System.out.println(usuario);
            usuarios.add(usuario);
            String csvFile = "usuarios.csv";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile, true))) {
                // Escribir encabezados si el archivo está vacío
                if (usuarios.isEmpty()) {
                    writer.write("Username,Password,Tipo\n");
                }
                // Reescribir datos de usuarios
                    writer.write("\n" + usuario.getUsername() + "," + usuario.getPassword() + "," + usuario.getTipo());
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Usuario no encontrado. No se pudo cambiar la contraseña.");
        }
    }

     /**
     * Método para cambiar el tipo de usuario (Gratis <-> Premium) del usuario actual.
     */

    @Override
    public void cambiarTipoUsuario(){
        Usuario usuario = buscarUsuarioPorUsername(usernameActual);

        if (usuario != null) {
            if (usuario.getTipo() == "gratis"){
                usuario.setTipo("premium");
            }else{
                usuario.setTipo("gratis");
            }
            System.out.println("Tipo de usuario cambiado con éxito para el usuario: " + usuario.getUsername());
            System.out.println(usuario);
            usuarios.add(usuario);
            String csvFile = "usuarios.csv";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile, true))) {
                // Escribir encabezados si el archivo está vacío
                if (usuarios.isEmpty()) {
                    writer.write("Username,Password,Tipo\n");
                }
                // Reescribir datos de usuarios
                    writer.write("\n" + usuario.getUsername() + "," + usuario.getPassword() + "," + usuario.getTipo());
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Usuario no encontrado. No se pudo cambiar el plan.");
        }
    }

    /**
     * Método para guardar la información de las reservas en un archivo CSV.
     * Escribe los datos de las reservas en el archivo 'reservas.csv'.
     */

    @Override
    public void guardarReserva() {
        String csvFile = "reservas.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))) {
            // Escribir encabezados si el archivo está vacío
            if (reservas.isEmpty()) {
                writer.write("FechaVuelo,TipoVuelo,CantidadBoletos,Aerolinea,NumeroTarjeta,Cuotas,ClaseVuelo,NumeroAsiento,CantidadMaletas,Username\n");
            }
    
            // Escribir datos de reservas
            for (Reserva reserva : reservas) {
                String claseVuelo = "";
    
                if (reserva instanceof Premium) {
                    claseVuelo = ((Premium) reserva).getClaseVuelo();
                } else if (reserva instanceof Gratis) {
                    claseVuelo = ((Gratis) reserva).getClaseVuelo();
                }
    
                writer.write(reserva.getFechaVuelo() + "," + reserva.isTipoVuelo() + "," + reserva.getCantidadBoletos() + "," + reserva.getAerolinea() + "," 
                    + reserva.getNumeroTarjeta() + "," + reserva.getCuotas() + "," + claseVuelo + "," + reserva.getNumeroAsiento() + "," 
                        + reserva.getCantidadMaletas() + "," + reserva.getUsuario().getUsername() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}