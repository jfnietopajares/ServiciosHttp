package es.sacyl.gsa.servicioshttp.dao;

import es.sacyl.gsa.servicioshttp.bean.Paciente;
import es.sacyl.gsa.servicioshttp.bean.PacienteHis;
import es.sacyl.gsa.servicioshttp.utils.Utilidades;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author 06551256M
 */
public class PacienteDao extends ConexionHis implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LogManager.getLogger(PacienteDao.class);

    public Paciente getPacienteRs(ResultSet rs) {
        Paciente paciente = null;
        /**
         * entendemos que si tiene numerohc tiene el resto de campos del
         * paciente
         */
        try {
            if (hasColumn(rs, "numerohc") == true) {
                paciente = new Paciente();
                paciente.setNumerohc(rs.getString("numerohc"));
                paciente.setNumerohc(rs.getString("apellid1"));
                paciente.setNumerohc(rs.getString("apellid2"));
                paciente.setNumerohc(rs.getString("nombre"));
            }
        } catch (SQLException ex) {
            LOGGER.error(Utilidades.getStackTrace(ex));
        }
        return paciente;
    }

    public PacienteHis getPacienteHnc(String numerohc) {
        Connection connection = null;
        PreparedStatement statement = null;
        PacienteHis paciente = null;
        try {
            connection = super.conecta();
            sql = sql = " SELECT  trim(p.apellid1) ||' ' || trim(p.apellid2)||','|| trim(p.nombre) as apellidosnombre "
                    + ",p.numerohc, to_char(p.fechanac,'%d/%m/%Y') as fechanac   "
                    + " , year(p.fechanac) as ano "
                    + " , month(p.fechanac) as mes "
                    + " , day(p.fechanac) as dia "
                    + " , p.domiresi as domicilio "
                    + " , r.descprov as provincia "
                    + " ,b.codpobla as poblacion "
                    + " ,telefono as telefono1 "
                    + " ,telefono2 as telefono2 "
                    + " ,telecont as telefono3 "
                    + " ,p.numtis "
                    + " ,p.cip_auto as cipa "
                    + " FROM  pacientes p "
                    + " LEFT JOIN provincias r On r.codprov=p.provresi "
                    + " LEFT JOIN poblacion b On b.codiprov=p.provresi and p.poblares=b.codpobla "
                    + " WHERE numerohc=?  ";
            statement = connection.prepareStatement(sql);
            statement.setString(1, numerohc);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                paciente = new PacienteHis();
                paciente.setNumerohc(rs.getString("numerohc"));
                paciente.setApellidosNombre(rs.getString("apellidosnombre"));
                paciente.setDomicilio(rs.getString("domicilio"));
                paciente.setProvincia(rs.getString("provincia"));
                paciente.setPoblacion(rs.getString("poblacion"));
                paciente.setTelefono1(rs.getString("telefono1"));
                paciente.setTelefono2(rs.getString("telefono2"));
                paciente.setTelefono3(rs.getString("telefono3"));
                try {
                    paciente.setFechanacimiento(LocalDate.of(rs.getInt("ano"), rs.getInt("mes"), rs.getInt("dia")));
                    paciente.setEdad(0);
                    if (paciente.getFechanacimiento() != null) {
                        int edad = Period.between(paciente.getFechanacimiento(), LocalDate.now()).getYears();
                        paciente.setEdad(edad);
                    }
                } catch (Exception ex) {
                    LOGGER.error("Error en la fecha de nacimento" + Utilidades.getStackTrace(ex));
                }
                paciente.setNumtis(rs.getString("numtis"));
                paciente.setCipa(rs.getString("cipa"));
            }
            LOGGER.debug(sql);
        } catch (SQLException e) {
            LOGGER.error(sql + Utilidades.getStackTrace(e));
        } catch (Exception e) {
            LOGGER.error(Utilidades.getStackTrace(e));
        } finally {
            this.doCierraConexion(connection, statement);
        }
        return paciente;
    }

}
