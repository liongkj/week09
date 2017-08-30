/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Student;
import model.StudentFacade;

/**
 *
 * @author lecturer
 */
@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {

    @EJB
    private StudentFacade studentFacade;

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
        String name = request.getParameter("name");
        int password = Integer.parseInt(request.getParameter("password"));

        Student s = null;
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            if (name.equalsIgnoreCase("admin")){
                if(password == 12345 ){
                    request.getRequestDispatcher("admin.jsp").forward(request,response);
                    
                } else{
                    System.out.println("Wrong password");
                }
            }
            
            s = studentFacade.find(name);
            
            
            
            if (s != null) {

                if (s.getPassword() == password) {  //retrieve password saved in rs to match with input.
                    if (s.getGender() == 'm' || s.getGender() == 'M') {
                        request.getRequestDispatcher("user.jsp").include(request, response);
                        out.println("<br></br><h1>Welcome Mr. " + s.getId()+ " !</h1>");
                    } else if (s.getGender() == 'm' || s.getGender() == 'M') {
                        request.getRequestDispatcher("user.jsp").include(request, response);
                        out.println("<br></br><h1>Welcome Ms. " + s.getId() + " !</h1>");
                    }
                    HttpSession session = request.getSession();
                    session.setAttribute("user", s);

                } else {
                    out.println("Wrong Password!<br></br>");
                    request.getRequestDispatcher("login.jsp").include(request, response);
                }

            } else {
                out.println("Wrong Username!<br></br>");
                request.getRequestDispatcher("login.jsp").include(request, response);
            }
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
        processRequest(request, response);
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

}
