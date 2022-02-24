package es.sacyl.gsa.servicioshttp.dao;

import es.sacyl.gsa.servicioshttp.bean.ParametroBean;
import es.sacyl.gsa.servicioshttp.utils.Utilidades;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author 06551256M
 */
public class ParametroDao extends ConexionHnss implements Serializable {

    private static final Logger LOGGER = LogManager.getLogger(ParametroDao.class);
    private static final long serialVersionUID = 1L;

    public ParametroDao() {
        super();
    }

    /**
     *
     * @param rs
     * @return
     */
    public ParametroBean getRegistroResulset(ResultSet rs) {
        ParametroBean parametroBean = new ParametroBean();
        try {
            parametroBean.setId(rs.getLong("id"));
            parametroBean.setCodigo(rs.getString("codigo").trim());
            parametroBean.setDescripcion(rs.getString("descripcion").trim());
            parametroBean.setValor(rs.getString("valor").trim());
        } catch (SQLException e) {
            LOGGER.error(Utilidades.getStackTrace(e));
        }
        return parametroBean;
    }

    /**
     *
     * @param codigo
     * @return
     */
    public ParametroBean getPorCodigo(String codigo) {
        Connection connection = null;
        Statement statement = null;
        if (codigo == null || codigo.isEmpty()) {
            return null;
        }
        ParametroBean parametroBean = null;

        try {
            connection = super.conecta();
            sql = " SELECT * FROM parametros WHERE  1=1 ";
            if (codigo != null && !codigo.isEmpty()) {
                sql = sql.concat(" AND codigo='" + codigo.trim() + "'");
            }
            statement = connection.createStatement();
            ResultSet resulSet = statement.executeQuery(sql);
            if (resulSet.next()) {
                parametroBean = getRegistroResulset(resulSet);
            }
            statement.close();
            LOGGER.debug(sql);
        } catch (SQLException e) {
            LOGGER.error(sql + Utilidades.getStackTrace(e));
        } catch (Exception e) {
            LOGGER.error(Utilidades.getStackTrace(e));
        } finally {
            this.doCierraConexion(connection, statement);
        }

        return parametroBean;
    }

}
