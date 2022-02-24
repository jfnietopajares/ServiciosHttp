/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.gsa.servicioshttp.dao;

import es.sacyl.gsa.servicioshttp.bean.ParametroBean;
import es.sacyl.gsa.servicioshttp.utils.Utilidades;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author 06551256M
 */
public class ConexionHis extends ConexionMaster {

    protected static final Logger LOGGER = LogManager.getLogger(ConexionHis.class);

    @Override
    public Connection conecta() {
        Connection conn = null;
        String cadena = new ParametroDao().getPorCodigo(ParametroBean.URL_CONEXION_CLINICA).getValor();
        if (cadena != null) {
            String dbURL2 = "jdbc:informix-sqli://" + cadena;
            try {
                Class.forName("com.informix.jdbc.IfxDriver");
                conn = DriverManager.getConnection(dbURL2);
            } catch (ClassNotFoundException ex) {
                LOGGER.error(ERRORDRIVERNOECONTRADO, ex);
            } catch (SQLException ex) {
                LOGGER.error(Utilidades.getStackTrace(ex));
            }
        } else {
            LOGGER.error(ERRORPARAMETRO, ParametroBean.URL_CONEXION_CLINICA);
        }
        return conn;
    }

}
