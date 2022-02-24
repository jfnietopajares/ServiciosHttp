package es.sacyl.gsa.servicioshttp.bean;

import java.time.LocalDate;

/**
 *
 * @author 06551256M
 */
public class PacienteHis extends Paciente {

    private LocalDate fechanacimiento;
    private String domicilio;
    private String poblacion;
    private String provincia;
    private String telefono1;
    private String telefono2;
    private String telefono3;
    private String cipa;
    private String numtis;

    public PacienteHis() {
        super();
    }

    public LocalDate getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(LocalDate fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String getTelefono3() {
        return telefono3;
    }

    public void setTelefono3(String telefono3) {
        this.telefono3 = telefono3;
    }

    public String getCipa() {
        return cipa;
    }

    public void setCipa(String cipa) {
        this.cipa = cipa;
    }

    public String getNumtis() {
        return numtis;
    }

    public void setNumtis(String numtis) {
        this.numtis = numtis;
    }

}
