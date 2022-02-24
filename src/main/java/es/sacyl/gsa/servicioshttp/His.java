package es.sacyl.gsa.servicioshttp;

import com.google.gson.Gson;
import es.sacyl.gsa.servicioshttp.services.ServicesJimena;
import es.sacyl.gsa.servicioshttp.services.ServicesPdf;
import es.sacyl.gsa.servicioshttp.services.ServicesUrg;
import es.sacyl.gsa.servicioshttp.utils.Constantes;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 06551256M
 */
@WebServlet(name = "His", urlPatterns = {"/His"})
public class His extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet His</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet His at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String PDFPATHABSOLUTO = request.getSession().getServletContext().getRealPath(request.getSession().getServletContext().getContextPath());
        PDFPATHABSOLUTO = System.getProperty("catalina.base") + System.getProperty("file.separator") + "pdf" + System.getProperty("file.separator");
        //System.out.println ("Ruta
        String PDFPATHRELATIVOS = request.getSession().getServletContext().getContextPath() + "/pdf/";
        PDFPATHRELATIVOS = System.getProperty("file.separator") + "pdf" + System.getProperty("file.separator");
        String urlBase = request.getLocalAddr() + ":" + request.getLocalPort();
        urlBase = "http://10.36.65.10:8080";
        String respuesta = null;

        String tipoConsulta = request.getParameter("llamada");
        String numerohc = request.getParameter("numerohc");
        String dni = request.getParameter("dni");
        if (tipoConsulta != null && !tipoConsulta.isEmpty()) {
            switch (tipoConsulta.toUpperCase()) {
                case Constantes.LLAMADA_ENURGENICAS:
                    respuesta = new ServicesUrg().getEnUrgencias();
                    break;
                case Constantes.LLAMADA_INFORMES_PACIENTE:
                    respuesta = new ServicesJimena().getInformesPaciente("29675");
                    break;
                case Constantes.LLAMADA_PETICION_RX:
                    String motivo = request.getParameter("motivo");
                    String peticion = request.getParameter("peticion");

                    respuesta = new ServicesPdf().getUrlPeticionRX(numerohc, dni, motivo, peticion, PDFPATHABSOLUTO, PDFPATHRELATIVOS, urlBase);
                    break;
            }
        } else {
            respuesta = new Gson().toJson("LLamada no reconocida");
        }
        response(response, respuesta);
    }

    private void response(HttpServletResponse resp, String msg)
            throws IOException {
        PrintWriter out = resp.getWriter();

        out.println(msg);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    //for Preflight
    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        setAccessControlHeaders(resp);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    private void setAccessControlHeaders(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:81");
        resp.setHeader("Access-Control-Request-Headers", "x-requested-with");
        resp.setHeader("Access-Control-Allow-Headers", "origin, content-type, accept, Authorization");
        resp.setHeader("Access-Control-Allow-Methods", "HEAD,GET, POST, PUT, DELETE, OPTIONS");

        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,PATCH,OPTIONS");
        resp.setHeader("Access-Control-Max-Age", "3600");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me, Authorization, type ");
        resp.setHeader("Access-Control-Expose-Headers", "Authorization");
    }
}
