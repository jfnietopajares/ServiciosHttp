package es.sacyl.gsa.servicioshttp.dao;

import es.sacyl.gsa.servicioshttp.bean.Servicio;
import es.sacyl.gsa.servicioshttp.utils.Utilidades;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author 06551256M
 */
public class ServicioDao extends ConexionHis implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LogManager.getLogger(ServicioDao.class);

    public Servicio getServicioPeticionarioRs(ResultSet rs) {
        Servicio servicio = null;
        try {
            servicio = new Servicio();
            if (rs.getString("codServicioPeticonario") != null && !rs.getString("codServicioPeticonario").isEmpty()) {
                servicio.setCodigo(rs.getString("codServicioPeticonario").trim());
            }
            if (rs.getString("nomServicioPeticonario") != null && !rs.getString("nomServicioPeticonario").isEmpty()) {
                servicio.setCodigo(rs.getString("nomServicioPeticonario").trim());
            }
        } catch (SQLException ex) {
            LOGGER.error(Utilidades.getStackTrace(ex));
        }
        return servicio;
    }

    public Servicio getServicioRealizadoRs(ResultSet rs) {
        Servicio servicio = null;
        try {
            servicio = new Servicio();
            if (rs.getString("codServicioRealizador") != null && !rs.getString("codServicioRealizador").isEmpty()) {
                servicio.setCodigo(rs.getString("codServicioRealizador"));
            }
            if (rs.getString("nomServicioRealizador") != null && !rs.getString("nomServicioRealizador").isEmpty()) {
                servicio.setCodigo(rs.getString("nomServicioRealizador"));
            }
        } catch (SQLException ex) {
            LOGGER.error(Utilidades.getStackTrace(ex));
        }
        return servicio;
    }
}
