package es.sacyl.gsa.servicioshttp.lisados;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import es.sacyl.gsa.servicioshttp.utils.Utilidades;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author juannietopajares
 */
public abstract class MasterReport {

    protected final ByteArrayOutputStream out = new ByteArrayOutputStream();

    protected PdfWriter writer;

    protected PdfDocument pdf;

    protected Document document;

    protected PdfFont normal;

    protected PdfFont bold;

    protected int fontSize = 13;

    protected int altoFila = 12;

    protected ArrayList<String> tituloStrings = new ArrayList<>();

    protected DeviceRgb GRISCLARO = new DeviceRgb(239, 238, 238);
    protected DeviceRgb BLANCO = new DeviceRgb(254, 254, 254);
    protected DeviceRgb NEGRO = new DeviceRgb(0, 0, 0);

    protected DeviceRgb AMARILLO = new DeviceRgb(248, 252, 120);  // Medico para tarjeta
    protected DeviceRgb AZULCLARO = new DeviceRgb(120, 228, 252);  // Enfermera para tarjeta
    protected DeviceRgb VERDE = new DeviceRgb(114, 240, 131);  // Aux Enfermera para tarjeta
    protected DeviceRgb AZULOSCURO = new DeviceRgb(10, 13, 198); // Técnico
    protected DeviceRgb ROJO = new DeviceRgb(246, 82, 57); // cELADOR
    protected DeviceRgb NARANJA = new DeviceRgb(250, 185, 79); // Naranja

    protected File file;
    protected String nombreDelFicheroPdf = null;
    protected String nombrePdfAbsoluto = null;
    protected String nombrePdfRelativo = null;

    protected String pathAbs;
    protected String pathrel;
    protected String urlbase;
    protected String urlDelPdf = null;

    protected final float[] ancho1columna = {540f};

    public MasterReport() {
        writer = new PdfWriter(out);

        pdf = new PdfDocument(writer);

        document = new Document(pdf, PageSize.A4).setTextAlignment(TextAlignment.LEFT);

        document.setMargins(25, 15, 5, 35);

        try {
            normal = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);

            bold = PdfFontFactory.createFont(StandardFonts.TIMES_BOLD);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract void doCreaPdf();

    public String getNombrePdfAbsoluto() {
        return nombrePdfAbsoluto;
    }

    public void setNombrePdfAbsoluto(String nombrePdfAbsoluto) {
        this.nombrePdfAbsoluto = nombrePdfAbsoluto;
    }

    public String getNombrePdfRelativo() {
        return nombrePdfRelativo;
    }

    public void setNombrePdfRelativo(String nombrePdfRelativo) {
        this.nombrePdfRelativo = nombrePdfRelativo;
    }

    public String getUrlDelPdf() {
        return urlDelPdf;
    }

    public void setUrlDelPdf(String urlDelPdf) {
        this.urlDelPdf = urlDelPdf;
    }

    public InputStream getStream() {
        // Here we return the pdf contents as a byte-array
        return new ByteArrayInputStream(out.toByteArray());
    }

    public ByteArrayOutputStream getOut() {
        return out;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void doCreaFicheroPdf() {
        Utilidades.iStoFile(getStream(), nombrePdfAbsoluto);
    }

    public void doBorraPdf() {
        File file = new File(nombrePdfAbsoluto);
        if (file.isFile()) {
            file.delete();
        }
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public PdfFont getNormal() {
        return normal;
    }

    public void setNormal(PdfFont normal) {
        this.normal = normal;
    }

    public PdfFont getBold() {
        return bold;
    }

    public void setBold(PdfFont bold) {
        this.bold = bold;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public int getAltoFila() {
        return altoFila;
    }

    public void setAltoFila(int altoFila) {
        this.altoFila = altoFila;
    }

    public String getNombreDelFicheroPdf() {
        return nombreDelFicheroPdf;
    }

    public void setNombreDelFicheroPdf(String nombreDelFicheroPdf) {
        this.nombreDelFicheroPdf = nombreDelFicheroPdf;
    }

    public PdfDocument getPdf() {
        return pdf;
    }

    public void setPdf(PdfDocument pdf) {
        this.pdf = pdf;
    }

    public String getPathAbs() {
        return pathAbs;
    }

    public void setPathAbs(String pathAbs) {
        this.pathAbs = pathAbs;
    }

    public String getPathrel() {
        return pathrel;
    }

    public void setPathrel(String pathrel) {
        this.pathrel = pathrel;
    }

    public String getUrlbase() {
        return urlbase;
    }

    public void setUrlbase(String urlbase) {
        this.urlbase = urlbase;
    }

    public void doActualizaNombreFicheros() {
        nombrePdfAbsoluto = pathAbs + nombreDelFicheroPdf;
        nombrePdfRelativo = pathrel + nombreDelFicheroPdf;

        urlDelPdf = "http://" + urlbase + "/" + nombrePdfRelativo;

        /*
        VaadinRequest currentRequest = VaadinRequest.getCurrent();
        VaadinServletRequest vaadinServletRequest = null;
        if (currentRequest instanceof VaadinServletRequest) {
            vaadinServletRequest = (VaadinServletRequest) currentRequest;
            adr = vaadinServletRequest.getLocalAddr();
            if (adr.charAt(0) == "0".charAt(0)) {
                adr = "localhost";
            }
            port = Integer.toString(vaadinServletRequest.getLocalPort());
            urlDelPdf = "http://" + adr + ":" + port + nombrePdfRelativo;
        } else {
            adr = new ParametroDao().getPorCodigo(ParametroBean.URL_INSTANCIASERVIDOR).getValor();
            urlDelPdf = "http://" + adr + nombrePdfRelativo;
        }
         */
    }

    /**
     * Cabecera de la tabla en función de los valores de tituloStrings
     *
     * @return
     */
    public Table getTablaConCabecera(float[] anchoscabecera) {
        Table tabla = new Table(anchoscabecera);
        for (String titulo : tituloStrings) {
            tabla.addCell(getCeldaCabecera(titulo));
        };
        return tabla;
    }

    /**
     *
     * @param valor
     * @return El valor del string en una celda con formato: fuente y bordes de
     * la celda
     */
    public Cell getCeldaDatoDerecha(String valor) {
        Cell cell = new Cell();
        Text texto = new Text(valor).setHorizontalAlignment(HorizontalAlignment.RIGHT);
        texto.setTextAlignment(TextAlignment.RIGHT);
        Paragraph parrafo1 = new Paragraph(texto).setHorizontalAlignment(HorizontalAlignment.RIGHT).setFontSize(this.getFontSize());
        cell.setBorder(new SolidBorder(GRISCLARO, 1)).add(parrafo1).setTextAlignment(TextAlignment.RIGHT);
        return cell;
    }

    public Cell getCeldaDatoCentro(String valor) {
        Cell cell = new Cell();
        Text texto = new Text(valor).setHorizontalAlignment(HorizontalAlignment.CENTER);
        texto.setTextAlignment(TextAlignment.CENTER);
        Paragraph parrafo1 = new Paragraph(texto).setHorizontalAlignment(HorizontalAlignment.CENTER).setFontSize(this.getFontSize());
        cell.setBorder(new SolidBorder(GRISCLARO, 1)).add(parrafo1).setTextAlignment(TextAlignment.CENTER);
        return cell;
    }

    public Cell getCeldaDatoIzquierda(String valor) {
        Cell cell = new Cell().setBorder(new SolidBorder(GRISCLARO, 1));
        if (valor != null) {
            Text texto = new Text(valor).setHorizontalAlignment(HorizontalAlignment.LEFT);
            texto.setTextAlignment(TextAlignment.LEFT);
            Paragraph parrafo1 = new Paragraph(texto).setHorizontalAlignment(HorizontalAlignment.LEFT).setFontSize(this.getFontSize());
            cell = new Cell();
            cell.setBorder(new SolidBorder(GRISCLARO, 1)).add(parrafo1).setTextAlignment(TextAlignment.LEFT);
        }
        return cell;
    }

    /**
     *
     * @param valor
     * @return en función del valor decimal
     *
     * Si hay digitos decimales pone formato con dos decimales
     *
     * Si no hay valor decimal pone formato sin punto decimal
     */
    public Cell getCeldaDato(double valor) {
        Cell cell = new Cell();
        Long iPart = (long) valor;
        Double fPart = valor - iPart;
        String textoValor;
        if (fPart.equals(new Double(0))) {
            DecimalFormat df2 = new DecimalFormat("#");
            textoValor = df2.format(valor);
        } else {
            DecimalFormat df2 = new DecimalFormat("#.##");
            textoValor = df2.format(valor);
        }
        return getCeldaDatoDerecha(textoValor);
    }

    public Cell getCeldaDato(LocalDate valor) {
        Cell cell = getCeldaVacia();
        if (valor == null) {
            return cell;
        } else {
            try {
                String textoValor = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                cell = getCeldaDatoDerecha(textoValor);
            } catch (Exception ex) {

            }
        }
        return cell;
    }

    /**
     *
     * @param valor
     * @return Cabecera de la tabla con fondo y bordes
     */
    public Cell getCeldaCabecera(String valor) {
        Cell cell = new Cell();
        Paragraph parrafo = new Paragraph(valor).setHorizontalAlignment(HorizontalAlignment.RIGHT).setFontSize(this.getFontSize());
        cell.setBorder(new SolidBorder(BLANCO, 1)).setBackgroundColor(GRISCLARO).add(parrafo).setTextAlignment(TextAlignment.CENTER);
        return cell;
    }

    /**
     *
     * @param valor
     * @return La primera columna del listado tiene diferente formato
     */
    public Cell getCeldaPrimera(String valor) {
        Cell cell = new Cell();
        Paragraph parrafo = new Paragraph(valor).setHorizontalAlignment(HorizontalAlignment.RIGHT).setFontSize(this.getFontSize());
        cell.setBorder(new SolidBorder(BLANCO, 1)).setBackgroundColor(GRISCLARO).add(parrafo).setTextAlignment(TextAlignment.LEFT);
        return cell;
    }

    public Cell getCeldaVacia() {
        return new Cell().add(new Paragraph("\n")).setBorder(new SolidBorder(GRISCLARO, 1));
    }

}
