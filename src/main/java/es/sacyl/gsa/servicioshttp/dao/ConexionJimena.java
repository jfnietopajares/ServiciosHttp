package es.sacyl.gsa.servicioshttp.dao;

import es.sacyl.gsa.servicioshttp.bean.ParametroBean;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author 06551256M
 */
public class ConexionJimena extends ConexionMaster implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LogManager.getLogger(ConexionJimena.class);

    public Connection conecta() {
        Connection conn = null;
        String dbURL2;
        String username;
        String password;
        ParametroBean parametroBean = new ParametroDao().getPorCodigo(ParametroBean.URL_CONEXION_JIMENA);
        if (parametroBean == null) {
            LOGGER.error(" ");
        } else {
            String cadena = new ParametroDao().getPorCodigo(ParametroBean.URL_CONEXION_JIMENA).getValor();
            String[] conex = cadena.split("\\|");
            dbURL2 = "jdbc:oracle:thin:@" + conex[0];
            username = conex[1];
            password = conex[2];
            try {
                Class.forName("oracle.jdbc.OracleDriver");
                conn = DriverManager.getConnection(dbURL2, username, password);
                LOGGER.debug("Conexion con bbdd" + dbURL2 + " realizada.");
            } catch (ClassNotFoundException ex) {
                LOGGER.error("Error de conexión con bbdd ", ex);
            } catch (SQLException ex) {
                LOGGER.error("Error de conexión sql con bbdd ", ex);
            }
        }
        return conn;
    }
}
