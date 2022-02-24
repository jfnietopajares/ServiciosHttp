package es.sacyl.gsa.servicioshttp.services;

import com.google.gson.Gson;
import es.sacyl.gsa.servicioshttp.bean.EpisodioUrg;
import es.sacyl.gsa.servicioshttp.dao.EpisodioDao;
import java.util.ArrayList;

/**
 *
 * @author 06551256M
 */
public class ServicesUrg {

    public String getEnUrgencias() {
        ArrayList<EpisodioUrg> lista = new EpisodioDao().getEnUrgencias();

        String s = new Gson().toJson(lista);

        return s;
    }
}
