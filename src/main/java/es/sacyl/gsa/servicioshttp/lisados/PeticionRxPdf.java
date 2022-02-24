package es.sacyl.gsa.servicioshttp.lisados;

import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import es.sacyl.gsa.servicioshttp.bean.PacienteHis;
import es.sacyl.gsa.servicioshttp.bean.Usuario;
import es.sacyl.gsa.servicioshttp.utils.Utilidades;
import java.io.Serializable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author 06551256M
 */
public class PeticionRxPdf extends MasterReport implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger(PeticionRxPdf.class);

    private PacienteHis paciente = null;
    private Usuario usuario = null;
    private String numcicu = null;
    private String motivo = null;
    private String prueba = null;

    public PeticionRxPdf(PacienteHis paciente, Usuario usuario, String numicu, String motivo, String prueba, String pathAbs, String pathrel, String urlBase) {
        super();
        this.pathAbs = pathAbs;
        this.pathrel = pathrel;
        this.urlbase = urlBase;
        this.paciente = paciente;
        this.usuario = usuario;
        this.numcicu = numicu;
        this.motivo = motivo;
        this.prueba = prueba;
        doCreaPdf();
    }

    @Override
    public void doCreaPdf() {

        nombreDelFicheroPdf = "peticionrx_" + paciente.getNumerohc() + ".pdf";

        this.setFontSize(9);
        this.getDocument().setTopMargin(75);
        this.getDocument().setBottomMargin(50);
        this.doActualizaNombreFicheros();
        try {
            EventoPaginaInforme evento = new EventoPaginaInforme(this.getDocument(), numcicu, paciente, "Peticion de Radiodiagnóstico ",
                    "..");
            this.getPdf().addEventHandler(PdfDocumentEvent.END_PAGE, evento);

            this.getDocument().add(new Paragraph("\n \n  \n   "));
            Table tabla = new Table(ancho1columna);
            tabla.addCell(
                    new Cell().add(new Paragraph().add(new Text(" SOLICITUD DE PRUEBAS DE RADIODIAGNÓSTICO").setFont(this.getBold()).setFontSize(14)))
                            .setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
            this.getDocument().add(tabla);

            Text motivoText = new Text("MOTIVO CLÍNICO").setHorizontalAlignment(HorizontalAlignment.CENTER).setFont(this.getBold()).setFontSize(this.getFontSize());
            this.getDocument().add(new Paragraph().add(motivoText).add(new Text("\n" + motivo
            ).setFont(this.getNormal()).setFontSize(this.getFontSize())
                    .setHorizontalAlignment(HorizontalAlignment.LEFT)));

            this.getDocument().add(new Paragraph("\n "));

            Text pruebasText = new Text("PRUEBAS SOLICITADAS").setHorizontalAlignment(HorizontalAlignment.CENTER).setFont(this.getBold()).setFontSize(this.getFontSize());
            this.getDocument().add(new Paragraph().add(pruebasText).add(new Text("\n" + prueba
            ).setFont(this.getNormal()).setFontSize(this.getFontSize())
                    .setHorizontalAlignment(HorizontalAlignment.LEFT)));
            this.getDocument().add(new Paragraph("Fecha " + Utilidades.getFechaActualString() + " " + Utilidades.getHoraActualInt()).setFont(this.getNormal()).setFontSize(this.getFontSize())
                    .setHorizontalAlignment(HorizontalAlignment.LEFT));
            this.getDocument().add(new Paragraph(" Fdo." + usuario.getApellidosnombre()).setFont(this.getNormal()).setFontSize(this.getFontSize())
                    .setHorizontalAlignment(HorizontalAlignment.LEFT));
            this.getDocument().add(new Paragraph(usuario.getCategoria()).setFont(this.getNormal()).setFontSize(this.getFontSize())
                    .setHorizontalAlignment(HorizontalAlignment.LEFT));

        } catch (Exception e) {
            logger.error(Utilidades.getStackTrace(e));
        } finally {
            document.close();
        }
    }
}
