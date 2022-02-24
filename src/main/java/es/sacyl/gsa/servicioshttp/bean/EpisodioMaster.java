package es.sacyl.gsa.servicioshttp.bean;

/**
 *
 * @author 06551256M
 */
public class EpisodioMaster extends Paciente {

    protected String numicu;
    protected String inicio;
    protected String servicio;

    protected String observaciones;

    public static final String TIPO_URG = "URG";
    public static final String TIPO_ADM = "ADM";
    public static final String TIPO_CEX = "CEX";

    public EpisodioMaster() {
        super();
    }

    public String getNumicu() {
        return numicu;
    }

    public void setNumicu(String numicu) {
        this.numicu = numicu;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public void setObservaciones(String observaciones) {
        if (observaciones != null) {
            this.observaciones = observaciones;
        } else {
            this.observaciones = "";
        }

    }

}
