package es.sacyl.gsa.servicioshttp.dao;

import es.sacyl.gsa.servicioshttp.utils.Utilidades;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author 06551256M
 */
public abstract class ConexionMaster {

    private static final Logger LOGGER = LogManager.getLogger(EpisodioDao.class);

    protected DataSource dataSource = null;
    protected InitialContext ctx;

    protected String persistencia = null;

    protected String sql;

    protected final static String ERROR_BBDD_SIN_CONEXION = "No se ha podido establecer la conexión con la base de datos ";

    protected final static String ERROR_BBDD_SQL = "Error en sentencia SQL. ";

    protected final static String ERROR_CLOSE_BBDD_SQL = "Error cerrando conexión. ";

    protected final static String ERROR_BBDD_CONTEXTO = "Error iniciando contexto.";

    protected static final String ERRORDRIVERNOECONTRADO = "Error conexion his, clase no contrada.";
    protected static final String ERRORCONEXION = "Problemas de conexion a HIS";
    protected static final String ERRORPARAMETRO = "Error no se encuentra dato para el parametro .";

    protected static int BBDD_ACTIVOSI = 1;
    protected static int BBDD_ACTIVONO = 0;

    protected DateTimeFormatter formatterdd_mm_yyyy = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    protected DateTimeFormatter formatterdd_mm_yyyy_hh_mm = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public abstract Connection conecta();

    /**
     *
     * @param rs
     * @param columnName
     * @return
     * @throws SQLException Comprueba si en el resulset pasado como parámetro
     * hay una columna con el nombre pasado como parámetro
     */
    protected static boolean hasColumn(ResultSet rs, String columnName) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int columns = rsmd.getColumnCount();
        for (int x = 1; x <= columns; x++) {
            if (columnName.equals(rsmd.getColumnName(x))) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param connection
     */
    public void doCierraConexion(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            LOGGER.error(Utilidades.getStackTrace(e));
        }
    }

    public void doCierraConexion(Connection connection, Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            LOGGER.error(Utilidades.getStackTrace(e));
        }
    }

}
