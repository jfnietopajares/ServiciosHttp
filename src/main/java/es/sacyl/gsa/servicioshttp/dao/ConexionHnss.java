package es.sacyl.gsa.servicioshttp.dao;

/**
 *
 * @author 06551256M
 */
import es.sacyl.gsa.servicioshttp.utils.Utilidades;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Class ConexionHnss.
 *
 * @author JuanNieto
 *
 *
 *
 */
public class ConexionHnss extends ConexionMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = LogManager.getLogger(ConexionHnss.class);

    /**
     * Gets the conexion BBDD.
     *
     * @return the conexion BBDD
     */
    @Override
    public Connection conecta() {
        Connection co = null;
        try {
            if (dataSource == null) {
                ctx = new InitialContext();
                if (ctx == null) {
                    throw new Exception(ConexionHnss.ERROR_BBDD_CONTEXTO);
                } else {
                    dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/dataSourceHnss");
                    if (dataSource != null) {
                        return dataSource.getConnection();
                    } else {
                        LOGGER.error(ConexionHnss.ERROR_BBDD_SIN_CONEXION);
                    }
                }
            } else {
                co = dataSource.getConnection();
            }
        } catch (Exception e) {
            LOGGER.error(Utilidades.getStackTrace(e));

        }
        return co;
    }

    /**
     * Método para verificar que la coenxión con la BBDD está activa
     *
     * @return
     */
    public Boolean isTestConexion() {
        Connection connection = null;
        Boolean resultado = false;
        String sql = null;
        try {
            connection = conecta();
            sql = " SELECT * FROM dual ";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                resultado = statement.executeUpdate() > 0;
                statement.close();
            }
            LOGGER.debug(sql);
        } catch (SQLException e) {
            LOGGER.error(sql, Utilidades.getStackTrace(e));
        } catch (Exception e) {
            LOGGER.error(Utilidades.getStackTrace(e));
        } finally {
            this.doCierraConexion(connection);
        }
        return resultado;
    }

}
