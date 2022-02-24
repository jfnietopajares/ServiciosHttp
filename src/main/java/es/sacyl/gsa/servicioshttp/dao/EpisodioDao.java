package es.sacyl.gsa.servicioshttp.dao;

import es.sacyl.gsa.servicioshttp.bean.EpisodioUrg;
import es.sacyl.gsa.servicioshttp.utils.Utilidades;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author 06551256M
 */
public class EpisodioDao extends ConexionHis implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LogManager.getLogger(EpisodioDao.class);

    public ArrayList<EpisodioUrg> getEnUrgencias() {
        String sql = "";
        Connection connection = null;
        Statement statement = null;
        ArrayList<EpisodioUrg> listaDatos = new ArrayList<>();
        try {
            connection = this.conecta();
            sql = " SELECT s.numicu, s.numerohc,to_char(s.dataingr,'%d/%m/%Y')  AS fechainicio,s.horaing AS horainicio,s.obsvaten as observaciones  "
                    + " ,p.nombre,p.apellid1,p.apellid2, to_char(p.fechanac,'%d/%m/%Y') as fechanac  "
                    + " ,v.codserv as codServicioRealizador,v.nomserv as nomServicioRealizador  "
                    + " FROM sub_ficus s  "
                    + " JOIN pacientes p ON p.numerohc=s.numerohc   "
                    + " JOIN servicios v On v.codserv=s.servreal  ";

            LOGGER.debug(sql);
            statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            while (resulSet.next()) {
                EpisodioUrg epi = new EpisodioUrg();
                epi.setNumicu(resulSet.getString("numicu"));
                epi.setNumerohc(resulSet.getString("numerohc"));
                String paciente = "";
                if (resulSet.getString("apellid1") != null) {
                    paciente = paciente.concat(" " + resulSet.getString("apellid1").trim());
                }
                if (resulSet.getString("apellid2") != null) {
                    paciente = paciente.concat(" " + resulSet.getString("apellid2").trim());
                }

                if (resulSet.getString("nombre") != null) {
                    paciente = paciente.concat(", " + resulSet.getString("nombre").trim());
                }
                epi.setApellidosNombre(paciente);

                epi.setEdad(0);
                if (resulSet.getString("fechanac") != null) {
                    LocalDate naci = Utilidades.getFechaLocalDateDMA(resulSet.getString("fechanac"));
                    if (naci != null) {
                        int edad = Period.between(naci, LocalDate.now()).getYears();
                        epi.setEdad(edad);
                    }
                }
                String inicio = "";
                if (resulSet.getString("fechainicio") != null) {
                    inicio = inicio.concat(" " + resulSet.getString("fechainicio").trim());
                }
                if (resulSet.getString("horainicio") != null) {
                    inicio = inicio.concat(" " + Utilidades.getHoraHH_MM(resulSet.getInt("horainicio")));
                }
                epi.setInicio(inicio);

                if (resulSet.getString("observaciones") != null) {
                    epi.setObservaciones(resulSet.getString("observaciones").trim());
                } else {
                    epi.setObservaciones(" ");
                }
                if (resulSet.getString("codServicioRealizador") != null) {
                    epi.setServicio(resulSet.getString("codServicioRealizador"));
                } else {
                    epi.setServicio(" ");
                }

                listaDatos.add(epi);
            }
            if (listaDatos.size() == 1) {
                listaDatos = new ArrayList<>();
            }
        } catch (SQLException e) {
            LOGGER.error(sql, e);
        } catch (Exception e) {
            LOGGER.error(Utilidades.getStackTrace(e));
        } finally {
            doCierraConexion(connection, statement);
        }
        return listaDatos;
    }
}
