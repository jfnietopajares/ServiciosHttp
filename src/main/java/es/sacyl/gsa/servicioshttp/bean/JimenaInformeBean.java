package es.sacyl.gsa.servicioshttp.bean;

import java.io.Serializable;

/**
 * The Class Informe. *
 *
 * @author Juan Nieto
 * @version 23.5.2018
 */
public class JimenaInformeBean extends Paciente implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String descripcion;

    private String peticionario;
    private String realizador;

    private String fechahora;

    private String medico;

    private String[] pdf;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPeticionario() {
        return peticionario;
    }

    public void setPeticionario(String peticionario) {
        this.peticionario = peticionario;
    }

    public String getRealizador() {
        return realizador;
    }

    public void setRealizador(String realizador) {
        this.realizador = realizador;
    }

    public String getFechahora() {
        return fechahora;
    }

    public void setFechahora(String fechahora) {
        this.fechahora = fechahora;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String[] getPdf() {
        return pdf;
    }

    public void setPdf(String[] pdf) {
        this.pdf = pdf;
    }

}
