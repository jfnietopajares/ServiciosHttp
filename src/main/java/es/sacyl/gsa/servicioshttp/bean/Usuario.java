package es.sacyl.gsa.servicioshttp.bean;

/**
 *
 * @author 06551256M
 */
public class Usuario {

    private String dni;
    private String apellidosnombre;
    private String categoria;
    private String servicio;

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getApellidosnombre() {
        return apellidosnombre;
    }

    public void setApellidosnombre(String apellidosnombre) {
        this.apellidosnombre = apellidosnombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

}
