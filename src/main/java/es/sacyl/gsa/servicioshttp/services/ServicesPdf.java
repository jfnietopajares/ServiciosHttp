package es.sacyl.gsa.servicioshttp.services;

import es.sacyl.gsa.servicioshttp.bean.PacienteHis;
import es.sacyl.gsa.servicioshttp.bean.Usuario;
import es.sacyl.gsa.servicioshttp.dao.PacienteDao;
import es.sacyl.gsa.servicioshttp.dao.UsuarioDao;
import es.sacyl.gsa.servicioshttp.lisados.PeticionRxPdf;

/**
 *
 * @author 06551256M
 */
public class ServicesPdf {

    public String getUrlPeticionRX(String numerohc, String dni, String motivo, String peticion, String pathAbs, String pathrel, String urlBase) {
        PacienteHis paciente = new PacienteDao().getPacienteHnc(numerohc);
        Usuario usuario = new UsuarioDao().getusuarioDni(dni);
        PeticionRxPdf peticionRx = new PeticionRxPdf(paciente, usuario, motivo, motivo, motivo, pathAbs, pathrel, urlBase);
        peticionRx.doCreaFicheroPdf();
        return peticionRx.getUrlDelPdf();
    }

}
