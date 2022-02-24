package es.sacyl.gsa.servicioshttp.dao;

import es.sacyl.gsa.servicioshttp.bean.Usuario;
import es.sacyl.gsa.servicioshttp.utils.Utilidades;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author 06551256M
 */
public class UsuarioDao extends ConexionHis implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LogManager.getLogger(PacienteDao.class);

    public Usuario getusuarioDni(String dni) {
        Connection connection = null;
        PreparedStatement statement = null;
        Usuario usuario = null;
        try {
            connection = super.conecta();
            sql = sql = " SELECT  trim(p.apellido1)||' ' ||trim(p.apellido2)||','||trim(p.nomb) as apellidosnombre "
                    + ",c.descat as categoria "
                    + " FROM  persclin p "
                    + " LEFT JOIN cateclin  c On c.codcat=p.categoria"
                    + " WHERE dni=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, dni);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setDni(dni);
                usuario.setApellidosnombre(rs.getString("apellidosnombre"));
                usuario.setCategoria(rs.getString("categoria"));
            }
            LOGGER.debug(sql);
        } catch (SQLException e) {
            LOGGER.error(sql + Utilidades.getStackTrace(e));
        } catch (Exception e) {
            LOGGER.error(Utilidades.getStackTrace(e));
        } finally {
            this.doCierraConexion(connection, statement);
        }
        return usuario;
    }

}
