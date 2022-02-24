package es.sacyl.gsa.servicioshttp.lisados;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import es.sacyl.gsa.servicioshttp.bean.PacienteHis;
import es.sacyl.gsa.servicioshttp.utils.Utilidades;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author david
 */
public class EventoPaginaInforme implements IEventHandler {

    private static final Logger logger = LogManager.getLogger(EventoPaginaInforme.class);
    private final Document documento;
    private PacienteHis paciente;
    private String municu;
    private String texto;
    private String texto1;

    public EventoPaginaInforme(Document doc, String municu, PacienteHis paciente, String texto, String texto1) {
        this.documento = doc;
        this.municu = municu;
        this.paciente = paciente;
        this.texto = texto;
        this.texto1 = texto1;
    }

    /**
     * Crea el rectangulo donde pondremos el encabezado
     *
     * @param docEvent Evento de documento
     * @return Area donde colocaremos el encabezado
     */
    private Rectangle crearRectanguloEncabezado(PdfDocumentEvent docEvent) {
        PdfDocument pdfDoc = docEvent.getDocument();
        PdfPage page = docEvent.getPage();

        float xEncabezado = pdfDoc.getDefaultPageSize().getX() + documento.getLeftMargin();
        float yEncabezado = pdfDoc.getDefaultPageSize().getTop() - documento.getTopMargin();
        float anchoEncabezado = page.getPageSize().getWidth() - 72;
        float altoEncabezado = 30F;

        Rectangle rectanguloEncabezado = new Rectangle(xEncabezado, yEncabezado, anchoEncabezado, altoEncabezado);

        return rectanguloEncabezado;
    }

    private Rectangle crearRectanguloEncabezadoPag1(PdfDocumentEvent docEvent) {
        PdfDocument pdfDoc = docEvent.getDocument();
        PdfPage page = docEvent.getPage();

        float xEncabezado = pdfDoc.getDefaultPageSize().getX() + documento.getLeftMargin();
        float yEncabezado = pdfDoc.getDefaultPageSize().getTop() - documento.getTopMargin() - 80;
        float anchoEncabezado = page.getPageSize().getWidth() - 72;
        float altoEncabezado = 130F;

        Rectangle rectanguloEncabezado = new Rectangle(xEncabezado, yEncabezado, anchoEncabezado, altoEncabezado);

        return rectanguloEncabezado;
    }

    /**
     * Crea el rectangulo donde pondremos el pie de pagina
     *
     * @param docEvent Evento del documento
     * @return Area donde colocaremos el pie de pagina
     */
    private Rectangle crearRectanguloPie(PdfDocumentEvent docEvent) {
        PdfDocument pdfDoc = docEvent.getDocument();
        PdfPage page = docEvent.getPage();

        float xPie = pdfDoc.getDefaultPageSize().getX() + documento.getLeftMargin();
        float yPie = pdfDoc.getDefaultPageSize().getBottom();
        float anchoPie = page.getPageSize().getWidth() - 72;
        float altoPie = 50F;

        Rectangle rectanguloPie = new Rectangle(xPie, yPie, anchoPie, altoPie);

        return rectanguloPie;
    }

    /**
     * Crea la tabla que contendra el mensaje del encabezado
     *
     * @param mensaje Mensaje que desplegaremos
     * @return Tabla con el mensaje de encabezado
     */
    private Table crearTablaEncabezado(String mensaje) {
        float[] anchos = {1F};
        Table tablaEncabezado = new Table(anchos);
        tablaEncabezado.setWidth(527F);

        tablaEncabezado.addCell(mensaje).setFontSize(9);

        return tablaEncabezado;
    }

    /**
     * Crea la tabla de pie de pagina, con el numero de pagina
     *
     * @param docEvent Evento del documento
     * @return Pie de pagina con el numero de pagina
     */
    private Table crearTablaPie(PdfDocumentEvent docEvent) {
        PdfPage page = docEvent.getPage();
        float[] anchos = {1F};
        Table tablaPie = new Table(anchos);
        tablaPie.setWidth(527F);
        Integer pageNum = docEvent.getDocument().getPageNumber(page);

        tablaPie.addCell("Pagina " + pageNum).setFontSize(9).setHorizontalAlignment(HorizontalAlignment.CENTER);

        return tablaPie;
    }

    /**
     * Manejador del evento de cambio de pagina, agrega el encabezado y pie de
     * pagina
     *
     * @param event Evento de pagina
     */
    @Override
    public void handleEvent(Event event) {
        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        PdfDocument pdfDoc = docEvent.getDocument();
        PdfPage page = docEvent.getPage();
        PdfCanvas canvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDoc);
        Table tablaEncabezado;
        Rectangle rectanguloEncabezado;
        Canvas canvasEncabezado;
        Integer pageNum = docEvent.getDocument().getPageNumber(page);
        Text first, second, tercero, cuarto;
        Cell celda;
        Paragraph parrafo;
        PdfFont normal = null, bold = null;
        float[] anchos2Columnas = {270f, 270f};
        if (pageNum == 1) {
            tablaEncabezado = new Table(anchos2Columnas);

            // estas dos lineas funciona para impresiones desde vaadin
            //String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
            //String imageFile = basepath + "/WEB-INF/images/logoimagenes.png";
            /*
                        Si pones rutras relativas a vaadin no funcionan los listados enviados desde los timer de Servlet.init
             */
 /*
                        En local da error pero en el servidor funciona
             */
            String imageFile = System.getProperty("catalina.home")
                    + System.getProperty("file.separator")
                    + "webapps"
                    + System.getProperty("file.separator")
                    + "ServiciosHttp"
                    + System.getProperty("file.separator")
                    + "WEB-INF"
                    + System.getProperty("file.separator")
                    + "img"
                    + System.getProperty("file.separator")
                    + "logoimagenes.png";
            try {
                normal = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
                bold = PdfFontFactory.createFont(FontConstants.TIMES_BOLD);

                ImageData data = ImageDataFactory.create(imageFile);
                Image image = new Image(data);
                tablaEncabezado.addCell(new Cell().add(image)); // primera columna para logo
            } catch (Exception e) {
                logger.error("Fichero de imagen no encontrado" + imageFile);
                //   new NotificacionInfo("Fichero de imagen no encontrado" + imageFile, true);
                tablaEncabezado.addCell(new Cell());
            } finally {

                first = new Text("NHC:").setFont(normal).setFontSize(15);
                if (paciente != null && paciente.getNumerohc() != null) {
                    second = new Text(paciente.getNumerohc()).setFont(bold).setFontSize(15);
                } else {
                    second = new Text("").setFont(bold).setFontSize(15);
                }
                if (paciente != null && paciente.getCipa() != null) {
                    tercero = new Text("    CIPA:").setFont(normal).setFontSize(15);
                    cuarto = new Text(paciente.getCipa()).setFont(bold).setFontSize(15);
                } else {
                    cuarto = new Text("");
                    tercero = new Text("");
                }

                parrafo = new Paragraph().add(first).add(second).add(tercero).add(cuarto);
                celda = new Cell().add(parrafo);
                if (paciente != null && paciente.getApellidosNombre() != null) {
                    parrafo = new Paragraph(paciente.getApellidosNombre()).setFont(bold).setFontSize(12);
                    celda.add(parrafo);
                }

                if (paciente != null && paciente.getFechanacimiento() != null) {
                    parrafo = new Paragraph().add(new Text("F.Naci:").setFont(normal))
                            .add(new Text(Utilidades.getFechadd_mm_yyyy(paciente.getFechanacimiento()) + "   ").setFont(bold))
                            .add(new Text("Edad :").setFont(normal)).add(paciente.getEdad().toString())
                            .setFont(bold);
                } else {
                    parrafo = new Paragraph().add(new Text("F.Naci:").setFont(normal).setFontSize(10))
                            .add(new Text("   ").setFont(bold)).add(new Text("Edad :").setFont(normal).setFontSize(10))
                            .add(new Text(" ")).setFont(bold);
                }
                celda.add(parrafo);
                //   tablaEncabezado.addCell(celda);

                second = new Text("");
                if (paciente != null && paciente.getDomicilio() != null) {
                    second = new Text("Domi:" + paciente.getDomicilio().replaceAll("\n", ", ")).setFont(bold).setFontSize(8);
                }
                parrafo = new Paragraph().add(second);

                celda.add(parrafo);

                String cadena = "";

                cadena = "";
                if (paciente != null && paciente.getTelefono1() != null) {
                    cadena = "Tf: " + paciente.getTelefono1();
                }
                if (paciente != null && paciente.getTelefono2() != null) {
                    cadena = cadena.concat("  " + paciente.getTelefono2());
                }
                parrafo = new Paragraph().add(new Text(cadena).setFont(bold).setFontSize(10));//
                celda.add(parrafo);
                tablaEncabezado.addCell(celda);

                celda = new Cell()
                        .add(new Paragraph(texto1).setFont(normal).setFontSize(9)
                                .setTextAlignment(TextAlignment.CENTER))
                        .setHorizontalAlignment(HorizontalAlignment.CENTER);
                tablaEncabezado.addCell(celda);

                celda = new Cell()
                        .add(new Paragraph(texto).setFont(normal).setFontSize(9)
                                .setTextAlignment(TextAlignment.CENTER))
                        .setHorizontalAlignment(HorizontalAlignment.CENTER);
                tablaEncabezado.addCell(celda);

                rectanguloEncabezado = this.crearRectanguloEncabezadoPag1(docEvent);

                canvasEncabezado = new Canvas(canvas, pdfDoc, rectanguloEncabezado);

                canvasEncabezado.add(tablaEncabezado);
            }

        } else {
            if (paciente != null && paciente.getApellidosNombre() != null && paciente.getNumerohc() != null) {
                tablaEncabezado = this.crearTablaEncabezado(texto + " de la paciente " + paciente.getApellidosNombre()
                        + "     Historia nº:  " + paciente.getNumerohc());
            } else {
                tablaEncabezado = this.crearTablaEncabezado(texto + " de la paciente ????     Historia nº: ???? ");
            }

            rectanguloEncabezado = this.crearRectanguloEncabezado(docEvent);

            canvasEncabezado = new Canvas(canvas, pdfDoc, rectanguloEncabezado);

            canvasEncabezado.add(tablaEncabezado);
        }

        // Table tablaNumeracion = this.crearTablaPie(docEvent);
        Rectangle rectanguloPie = this.crearRectanguloPie(docEvent);

        Canvas canvasPie = new Canvas(canvas, pdfDoc, rectanguloPie);

        Paragraph paragraph = new Paragraph().add(new Text("-" + Integer.toString(pageNum) + "-"))
                .setTextAlignment(TextAlignment.CENTER);
        canvasPie.add(paragraph);
    }
}
