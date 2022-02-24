/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sacyl.gsa.servicioshttp.services;

import com.google.gson.Gson;
import es.sacyl.gsa.servicioshttp.bean.JimenaInformeBean;
import es.sacyl.gsa.servicioshttp.dao.InformesDao;
import java.util.ArrayList;

/**
 *
 * @author 06551256M
 */
public class ServicesJimena {

    public String getInformesPaciente(String numerohc) {
        ArrayList<JimenaInformeBean> lista = new InformesDao().getListaInformesPaciente(numerohc);
        String s = new Gson().toJson(lista);
        return s;
    }
}
