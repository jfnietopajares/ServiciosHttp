package es.sacyl.gsa.servicioshttp.utils;

/**
 *
 * @author 06551256M
 */
public class Constantes {

    public static final String LLAMADA_ENURGENICAS = "ENURGENCIAS";
    public static final String LLAMADA_INFORMES_PACIENTE = "INFORMES_PACIENTE";
    public static final String LLAMADA_PETICION_RX = "PETICION_RX";
    public static final String LLAMADA_INFORME = "INFORME";

    public static final String PDFDIRECTORIO = "pdf";

    public static final String EXCELDIRECTORIO = "excel";
    /**
     * PDFPATHRELATIVO: Ruta relativa para guardar los pdf.
     */
    public static final String PDFPATHRELATIVO
            = System.getProperty("file.separator") + PDFDIRECTORIO + System.getProperty("file.separator");

    /**
     * PDFPATHABSOLUTO: Ruta absoluta para guardar los pdf.
     */
//    public static final String PDFPATHABSOLUTO = System.getProperty("catalina.base") + System.getProperty("file.separator") + PDFDIRECTORIO + System.getProperty("file.separator");
    //System.out.println ("Ruta en el servidor:" + request.getContextPath ());
    // La ruta en el servidor: / proj_valuation
    //System.out.println ("Ruta:" + request.getServletPath ());
    // ruta: / FileUploadServlet
    //  System.out.println ("Ruta absoluta:" + request.getSession (). GetServletContext (). GetRealPath ("/"));
    // Ruta absoluta: E: \ apache-tomcat-6.0.26 \ webapps \ proj_valuation \
    // String pathStr=request.getSession().getServletContext().getRealPath("/");
    //   (HttpServletRequest) VaadinRequest.getCurrent()).getPathTranslated()
    /**
     * PDFURL: Url del fichero accesible desde el navegador
     */
    public static final String PDFURL = System.getProperty("file.separator") + PDFDIRECTORIO + System.getProperty("file.separator");

}
