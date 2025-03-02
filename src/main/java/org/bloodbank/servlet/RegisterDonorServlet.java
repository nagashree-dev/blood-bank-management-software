package org.bloodbank.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;












@WebServlet("/RegisterDonorServlet")
public class RegisterDonorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        request.setCharacterEncoding("UTF-8"); // ✅ Fix encoding issues

        String name = request.getParameter("name");
        String bloodGroup = request.getParameter("blood_group");
        String sex = request.getParameter("sex");
        String place = request.getParameter("place");
        String lastDonationDate = request.getParameter("last_donation_date");
        String phone = request.getParameter("phone");

        System.out.println("Received Data: " + name + ", " + bloodGroup + ", " + sex + ", " + place + ", " + lastDonationDate + ", " + phone); // ✅ Debugging line

        if (name == null || name.trim().isEmpty() || phone == null || phone.trim().isEmpty()) {
            System.out.println("Error: Name or phone is empty!");
            response.getWriter().write("error");
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bloodbank", "root", "root");

            String sql = "INSERT INTO donors (name, blood_group, sex, place, last_donation_date, phone) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, bloodGroup);
            stmt.setString(3, sex);
            stmt.setString(4, place);
            stmt.setString(5, (lastDonationDate == null || lastDonationDate.isEmpty()) ? null : lastDonationDate); // ✅ Fix for empty date
            stmt.setString(6, phone);

            int rows = stmt.executeUpdate();
            stmt.close();
            conn.close();

            if (rows > 0) {
                System.out.println("Registration successful!");
                response.getWriter().write("success");
            } else {
                System.out.println("Database insert failed!");
                response.getWriter().write("error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception occurred: " + e.getMessage());
            response.getWriter().write("error");
        }
    }
}
