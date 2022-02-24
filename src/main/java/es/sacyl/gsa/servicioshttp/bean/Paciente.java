package es.sacyl.gsa.servicioshttp.bean;

/**
 *
 * @author 06551256M
 */
public class Paciente {

    protected String numerohc;
    protected String apellidosNombre;
    protected Integer edad;

    public String getNumerohc() {
        return numerohc;
    }

    public void setNumerohc(String numerohc) {
        this.numerohc = numerohc;
    }

    public String getApellidosNombre() {
        return apellidosNombre;
    }

    public void setApellidosNombre(String apellidosNombre) {
        this.apellidosNombre = apellidosNombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    /**
     *
     * @return 1.234.567 Calcula los 3 digistos terminales del numero de
     * historia , rellenando con ceros por la izquerta hasta completar un millon
     * que son el numero de historia máximo includos provisioanesl
     */
    public String getDirectorio() {
        String directorio = "";
        String nhcConCeros = numerohc;
        while (nhcConCeros.length() < 7) {
            nhcConCeros = "0" + nhcConCeros;
        }
        directorio = nhcConCeros.substring(4, 7);
        return directorio;
    }
}
