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
import model.Student;
import model.StudentFacade;

/**
 *
 * @author lecturer
 */
@WebServlet(name = "admin", urlPatterns = {"/admin"})
public class admin extends HttpServlet {

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
        
        char gender =  request.getParameter("gender").charAt(0);
        List<Student> sl = studentFacade.findAll();
        int count=0;
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            request.getRequestDispatcher("admin.jsp").include(request,response);
            out.print("<table border='1' width = '100%'><th>Student Name</th><th>Gender</th><th>Password</th><th>Balance</th>");
            for(int i = 0; i<sl.size();i++){
                if (sl.get(i).getGender() == gender ){
                    count++;
                    out.println("<tr>");
                    out.println("<td>" +sl.get(i).getId()+"</td>");
                    out.println("<td>" +sl.get(i).getGender()+"</td>");
                    out.println("<td>" +sl.get(i).getPassword()+"</td>");
                    out.println("<td>" +sl.get(i).getBalance()+"</td>");
                }
                
                
            }
            out.println("</table>");
            out.println(count +" students found.");
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
