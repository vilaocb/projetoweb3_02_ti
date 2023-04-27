
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;


@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {
 //Atributos
    private String user;
    private String pass;        
            
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        this.user = request.getParameter("user");
        this.pass = request.getParameter("pass");
        
        User newUser = new User(this.user, this.pass);
        
        if(newUser.isLogged()){
           request.setAttribute("newUser", newUser);
           request.getRequestDispatcher("home.jsp")
                  .forward(request, response);
        }else{
        
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Transporte</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<script>");
            out.println("alert('Acesso negado!')");
            out.println("window.location.replace('index.jsp');");
            out.println("</script>");
            out.println("</body>");
            out.println("</html>");
        }
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
