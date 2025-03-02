package org.bloodbank.servlet;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterDonorServlet")
public class RegisterDonorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String bloodGroup = request.getParameter("blood_group");
        String sex = request.getParameter("sex");
        String place = request.getParameter("place");
        String lastDonationDate = request.getParameter("last_donation_date");
        String phone = request.getParameter("phone");

        try {
            // Database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bloodbank", "root", "root");

            // SQL query
            String sql = "INSERT INTO donors (name, blood_group, sex, place, last_donation_date, phone) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, bloodGroup);
            stmt.setString(3, sex);
            stmt.setString(4, place);
            stmt.setString(5, lastDonationDate);
            stmt.setString(6, phone);

            int rows = stmt.executeUpdate();
            stmt.close();
            conn.close();

            if (rows > 0) {
                response.sendRedirect("donors.jsp");
            } else {
                response.sendRedirect("register.jsp?error=Registration failed. Try again."); // Redirect to error page
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("register.jsp?error=Registration failed. Check with different Mobile Number and Try again.");
        }
    }
}