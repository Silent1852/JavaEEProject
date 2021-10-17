package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import mySQL.ProductDB;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            ProductDB.delete(id);
            response.sendRedirect(request.getContextPath() + "/output");
        }
        catch(Exception ex) {
            getServletContext().getRequestDispatcher("/WEB-INF/views/notfound.jsp").forward(request, response);
        }
    }
}