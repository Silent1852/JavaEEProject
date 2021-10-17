package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import mySQL.Product;
import mySQL.ProductDB;


@WebServlet("/output")
public class OutputServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        final HttpSession session = request.getSession();

        final String state = (String) session.getAttribute("state");

        if (state != null){
            request.setAttribute("dataForView", session.getAttribute("state"));
        } else {
            request.getRequestDispatcher("/").forward(request, response);
        }
        ArrayList<Product> products = ProductDB.select();
        request.setAttribute("products", products);

        request.getRequestDispatcher("/WEB-INF/views/output.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    {
        PrintWriter pw = null;
        try
        {
            pw = response.getWriter();
        }
        catch (Exception ex)
        {
        }

        try
        {
            HttpSession session = request.getSession(true);
            String dataFromUser = request.getParameter("login");
            String currentState = (String) session.getAttribute("state");


            if (currentState != null) {
                session.setAttribute("state", currentState);
            } else {

                session.setAttribute("state", dataFromUser);
            }

            doGet(request, response);
        }
        catch (Exception ex)
        {
            pw.println("Error: "+ex.getMessage());
        }
    }
}