package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import mySQL.Product;
import mySQL.ProductDB;


@WebServlet("/edit")
public class EditServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Product product = ProductDB.selectOne(id);
            if(product!=null) {
                request.setAttribute("product", product);
                getServletContext().getRequestDispatcher("/WEB-INF/views/edit.jsp").forward(request, response);
            }
            else {
                getServletContext().getRequestDispatcher("/WEB-INF/views/notfound.jsp").forward(request, response);
            }
        }
        catch(Exception ex) {
            getServletContext().getRequestDispatcher("/WEB-INF/views/notfound.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            int price = Integer.parseInt(request.getParameter("price"));
            Product product = new Product(id, name, price);
            ProductDB.update(product);
            response.sendRedirect(request.getContextPath() + "/output");
        }
        catch(Exception ex) {

            getServletContext().getRequestDispatcher("/WEB-INF/views/notfound.jsp").forward(request, response);
        }
    }
}
