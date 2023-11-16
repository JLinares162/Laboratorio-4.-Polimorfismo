/**
 * La clase Usuario representa a un usuario del sistema.
 */
public class Usuario {
    private String username; // El nombre de usuario del usuario
    private String password; // La contraseña del usuario
    private String tipo;     // El tipo de usuario (por ejemplo, administrador, cliente, etc.)

    /**
     * Constructor de la clase Usuario.
     *
     * @param username El nombre de usuario del usuario.
     * @param password La contraseña del usuario.
     * @param tipo     El tipo de usuario.
     */
    public Usuario(String username, String password, String tipo) {
        this.username = username;
        this.password = password;
        this.tipo = tipo;
    }

    /**
     * Obtiene el nombre de usuario.
     *
     * @return El nombre de usuario.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Establece el nombre de usuario.
     *
     * @param username El nombre de usuario a establecer.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return La contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param password La contraseña a establecer.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtiene el tipo de usuario.
     *
     * @return El tipo de usuario.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de usuario.
     *
     * @param tipo El tipo de usuario a establecer.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return Una cadena que representa al objeto Usuario.
     */
    @Override
    public String toString() {
        return "Usuario [username=" + username + ", password=" + password + ", tipo=" + tipo + "]";
    }
}
