/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.gsa.servicioshttp.dao;

import es.sacyl.gsa.servicioshttp.bean.JimenaInformeBean;
import es.sacyl.gsa.servicioshttp.utils.Utilidades;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author 06551256M
 */
public class InformesDao extends ConexionJimena implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LogManager.getLogger(InformesDao.class);

    private DateTimeFormatter fechadma = DateTimeFormatter.ofPattern("dd/MM/YYYY");

    public ArrayList<JimenaInformeBean> getListaInformesPaciente(String numerohc) {
        String sql = "";
        Connection connection = this.conecta();
        Statement statement = null;
        ArrayList<JimenaInformeBean> listaInformeBeans = new ArrayList<JimenaInformeBean>();

        sql = " SELECT  i.id,i.fecha,i.hora,i.descripcion, s.codigo as peticionario,r.codigo as realizador    "
                + "FROM informes i  "
                + " JOIN servicios s ON s.id=i.servicio "
                + "  JOIN servicios r ON r.id=i.servicio_realizador "
                + " WHERE estado=2 AND i.paciente IN  "
                + " ( SELECT paciente FROM historias WHERE nhc='" + numerohc + "') "
                + " ORDER BY FECHA DESC ";
        try {
            LOGGER.debug(sql);
            statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            int contador = 0;
            while (resulSet.next()) {
                JimenaInformeBean informe = new JimenaInformeBean();
                informe.setId(resulSet.getLong("id"));
                String fechaHora = fechadma.format(Utilidades.getFechaLocalDate(resulSet.getLong("fecha")));
                fechaHora = fechaHora.concat(" " + Utilidades.getHoraHH_MM(resulSet.getInt("hora")));
                informe.setFechahora(fechaHora);
                informe.setPeticionario(resulSet.getString("peticionario"));
                informe.setRealizador(resulSet.getString("realizador"));
                informe.setDescripcion(resulSet.getString("descripcion"));
                listaInformeBeans.add(informe);
            }
        } catch (SQLException e) {
            LOGGER.error(sql + Utilidades.getStackTrace(e));
        } catch (Exception e) {
            LOGGER.error(Utilidades.getStackTrace(e));
        } finally {
            doCierraConexion(connection, statement);
        }
        return listaInformeBeans;
    }
}
