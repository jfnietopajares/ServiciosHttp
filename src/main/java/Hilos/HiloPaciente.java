package Hilos;

import es.sacyl.gsa.servicioshttp.bean.JimenaInformeBean;
import java.util.ArrayList;

/**
 *
 * @author 06551256M
 */
public class HiloPaciente extends Thread {

    String numerohc;

    public HiloPaciente(String numerohc) {
        this.numerohc = numerohc;
    }

    @Override
    public void run() {
        ArrayList<JimenaInformeBean> lista = new ArrayList<>();
        for (JimenaInformeBean unInforme : lista) {

        }
    }

}
