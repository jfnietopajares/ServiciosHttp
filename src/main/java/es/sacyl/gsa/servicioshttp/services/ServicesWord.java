/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.gsa.servicioshttp.services;

import es.sacyl.gsa.servicioshttp.bean.PacienteHis;
import es.sacyl.gsa.servicioshttp.bean.Usuario;
import es.sacyl.gsa.servicioshttp.dao.PacienteDao;
import es.sacyl.gsa.servicioshttp.dao.UsuarioDao;
import es.sacyl.gsa.servicioshttp.lisados.InformeGeneralDoc;

/**
 *
 * @author 06551256M
 */
public class ServicesWord {

    public String getUrlInform(String numerohc, String dni, String pathAbs, String pathrel, String urlBase) {
        PacienteHis paciente = new PacienteDao().getPacienteHnc(numerohc);
        Usuario usuario = new UsuarioDao().getusuarioDni(dni);
        InformeGeneralDoc informeGeneralDoc = new InformeGeneralDoc(paciente, usuario);
        return "";
    }
}
