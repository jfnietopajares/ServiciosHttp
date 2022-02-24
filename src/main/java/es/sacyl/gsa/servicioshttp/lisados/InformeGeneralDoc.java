package es.sacyl.gsa.servicioshttp.lisados;

import es.sacyl.gsa.servicioshttp.bean.PacienteHis;
import es.sacyl.gsa.servicioshttp.bean.Usuario;
import es.sacyl.gsa.servicioshttp.utils.Utilidades;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class InformeGeneralDoc {

    PacienteHis paciente = null;
    Usuario medico = null;

    public InformeGeneralDoc(PacienteHis paci, Usuario usu) {
        this.paciente = paci;
        this.medico = usu;
        try {
            //Blank Document
            XWPFDocument document = new XWPFDocument();

            //Write the Document in file system
            FileOutputStream out = new FileOutputStream(new File("C:\\temp\\" + paciente.getNumerohc() + ".docx"));
            /// tABLA CABECERA
            XWPFTable tableOne = document.createTable();
            XWPFTableRow tableOneRowOne = tableOne.getRow(0);
            tableOneRowOne.getCell(0).setText("___________________________________________\n"
                    + "             Hospital Ntra Sra Sonsoles                   \n");

            XWPFParagraph paragraph = document.createParagraph();

            XWPFParagraph paragraph1 = document.createParagraph();

            XWPFRun pacienteCab = paragraph1.createRun();
            pacienteCab.setBold(true);
            pacienteCab.setFontSize(14);
            pacienteCab.setText("Historia: " + paciente.getNumerohc());
            pacienteCab.addCarriageReturn();
            pacienteCab.setText("Nombre: " + paciente.getApellidosNombre());
            pacienteCab.addCarriageReturn();
            tableOneRowOne.addNewTableCell().addParagraph(paragraph1);
            /*
            tableOneRowOne.addNewTableCell().setText("___________________________________________\\n"
                    + " Paciente "
                    + "Historia: " + paciente.getNumerohc()
                    + "Nombre: " + paciente.getApellidosNombre()
                    + "Fecha Nacimiento: " + paciente.getFechanacimiento()
                    + "Dirección: " + paciente.getDomicilio()
                    + "Población :" + paciente.getPoblacion()
                    + "Provincia: " + paciente.getProvincia()
                    + "");
             */
            XWPFTableRow tableOneRowTwo = tableOne.createRow();
            tableOneRowTwo.getCell(0).setText("Informe médico");
            tableOneRowTwo.getCell(1).setText("Servicio de urgencias");

            //create paragraph
            //Set text Position
            XWPFRun titulo = paragraph.createRun();
            titulo.setBold(true);
            titulo.setFontSize(24);
            titulo.addTab();
            titulo.addTab();
            titulo.addTab();
            titulo.setText("INORME MÉDICO");

            int size = 14;
            XWPFRun motivo = paragraph.createRun();
            motivo.setFontSize(size);
            motivo.setBold(true);
            motivo.addBreak();
            motivo.addBreak();
            motivo.setText("Motivo de atención:");

            XWPFRun antecedentes = paragraph.createRun();
            antecedentes.setFontSize(size);
            antecedentes.setBold(true);
            antecedentes.addBreak();
            antecedentes.addBreak();
            antecedentes.setText("Antecedentes personales:");

            XWPFRun antecedentesf = paragraph.createRun();
            antecedentesf.setFontSize(size);
            antecedentesf.setBold(true);
            antecedentesf.addBreak();
            antecedentesf.addBreak();
            antecedentesf.setText("Antecedentes familiares:");

            XWPFRun alergia = paragraph.createRun();
            alergia.setFontSize(size);
            alergia.setBold(true);
            alergia.addBreak();
            alergia.addBreak();
            alergia.setText("Alergia :");

            XWPFRun exporacion = paragraph.createRun();
            exporacion.setFontSize(size);
            exporacion.setBold(true);
            exporacion.addBreak();
            exporacion.addBreak();
            alergia.setText("Exploración :");

            XWPFRun analitica = paragraph.createRun();
            analitica.setFontSize(size);
            analitica.setBold(true);
            analitica.addBreak();
            analitica.addBreak();
            alergia.setText("Analítica :");

            XWPFRun rx = paragraph.createRun();
            rx.setFontSize(size);
            rx.setBold(true);
            rx.addBreak();
            rx.addBreak();
            rx.setText("Pruebas radiológicas :");

            XWPFRun otras = paragraph.createRun();
            otras.setFontSize(size);
            otras.setBold(true);
            otras.addBreak();
            otras.addBreak();
            otras.setText("Otras pruebas diagnósticas:");

            XWPFRun diagnostico = paragraph.createRun();
            diagnostico.setFontSize(size);
            diagnostico.setBold(true);
            diagnostico.addBreak();
            diagnostico.addBreak();
            diagnostico.setText("Diagnóstico:");

            XWPFRun tratamiento = paragraph.createRun();
            tratamiento.setFontSize(size);
            tratamiento.setBold(true);
            tratamiento.addBreak();
            tratamiento.addBreak();
            tratamiento.setText("Tratamiento:");

            XWPFRun firma = paragraph.createRun();
            firma.setFontSize(10);
            firma.addBreak();
            firma.addBreak();
            if (medico != null && medico.getApellidosnombre() != null) {
                firma.setText("Fecha " + Utilidades.getFechaHoraActualFormato() + "  Fdo:" + medico.getApellidosnombre());
            } else {
                firma.setText("Fecha " + Utilidades.getFechaHoraActualFormato() + "  Fdo:");
            }

            document.write(out);
            out.close();
            System.out.println("fontstyle.docx written successully");
        } catch (IOException ex) {
            Logger.getLogger(InformeGeneralDoc.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
