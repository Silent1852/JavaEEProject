package controller;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import mySQL.Product;
import mySQL.ProductDB;

@WebServlet("/create")
public class CreateServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        final HttpSession session = request.getSession();
        final String state = (String) session.getAttribute("state");

        if (state != null) {
            request.getRequestDispatcher("/WEB-INF/views/create.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        final HttpSession session = request.getSession();
        final String state = (String) session.getAttribute("state");

            try {
                String name = request.getParameter("name");
                int price = Integer.parseInt(request.getParameter("price"));
                Product product = new Product(name, price);
                ProductDB.insert(product);
                response.sendRedirect(request.getContextPath() + "/output");
            } catch (Exception ex) {

                request.getRequestDispatcher("/WEB-INF/views/create.jsp").forward(request, response);
            }

    }
}