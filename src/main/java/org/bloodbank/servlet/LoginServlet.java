package org.bloodbank.servlet;

import org.bloodbank.utils.DatabaseConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Debugging logs
        System.out.println("LoginServlet called");
        System.out.println("Received Username: " + username);
        System.out.println("Received Password: " + password);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.setString(2, password);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) { // If credentials are correct
                        System.out.println("Valid credentials, creating session...");
                        HttpSession session = request.getSession();
                        session.setAttribute("user", username);

                        JSONObject json = new JSONObject();
                        json.put("success", true);
                        response.getWriter().write(json.toString());
                        return;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print error details
        }

        // If credentials are incorrect
        System.out.println("Invalid credentials, sending JSON response...");
        JSONObject json = new JSONObject();
        json.put("success", false);
        json.put("error", "Invalid Credentials. Please try again.");
        response.getWriter().write(json.toString());
    }
}
