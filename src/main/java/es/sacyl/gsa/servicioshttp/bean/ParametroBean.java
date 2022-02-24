/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.gsa.servicioshttp.bean;

import java.io.Serializable;

/**
 *
 * @author 06551256M
 */
public class ParametroBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String codigo;
    private String descripcion;
    private String valor;

    public static final String MAIL_USER = "mail.smtp.user";	// Usuario mail del servidor
    public static final String MAIL_SENDER = "mail.smtp.mail.sender"; //Usuario remitente
    public static final String MAIL_PORT = "mail.smtp.port"; //	Puerto del correo
    public static final String MAIL_PASSWORD = "mail.password";//	Password del usuario conexión  servidor
    public static final String MAIL_HOST = "mail.smtp.host"; //	Host del correo
    public static final String MAIL_AUTH = "mail.smtp.auth"; //	Auth

    public static final String MAIL_LDAP_DESTINOLOPD = "mail.ldap.destinolopd"; //	Destinatarios correo lopd para gestion de incidencias

    public static final String URL_CONEXION_HNSS = "url.conexion.hnss";
    public static final String URL_CONEXION_FARMCIA = "url.conexion.farmacia";
    public static final String URL_CONEXION_TURNOS = "url.conexion.turnos";
    public static final String URL_CONEXION_GACELA = "url.conexion.gacela";
    public static final String URL_CONEXION_JIMENA = "url.conexion.jimena";
    public static final String URL_CONEXION_GALENO = "url.conexion.galeno";
    public static final String URL_CONEXION_CLINICA = "url.conexion.clinica";
    public static final String URL_CONEXION_PERSIGO = "url.conexion.persigo";

    public static final String ENTORNO = "entorno";
    public static final String ENTORNODESARROLLO = "desarrollo";
    public static final String ENTORNOPRODUCCION = "producción";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

}
