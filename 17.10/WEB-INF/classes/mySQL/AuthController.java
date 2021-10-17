package mySQL;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;

public class  AuthController extends HttpServlet {
    private static String URL = "jdbc:mysql://localhost/userdb?allowPublicKeyRetrieval=true&useSSL=false";
    private static String USERNAME = "root";
    private static String PASSWORD = "1337";
    private static final String SELECT = "SELECT * FROM userdb.users WHERE login = ?; ";

    protected void  doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User user = new User();

        PreparedStatement preparedStatement = null;

        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {

            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM products");
        }
    }
}































