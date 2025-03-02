package org.bloodbank.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.bloodbank.utils.DatabaseConnection;

@WebServlet("/SearchDonorServlet")
public class SearchDonorServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        String bloodGroup = request.getParameter("bloodGroup"); // Get blood group filter from frontend

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = (bloodGroup != null && !bloodGroup.isEmpty())
                    ? "SELECT * FROM donors WHERE blood_group = ?"
                    : "SELECT * FROM donors";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                if (bloodGroup != null && !bloodGroup.isEmpty()) {
                    stmt.setString(1, bloodGroup);
                }

                try (ResultSet rs = stmt.executeQuery()) {
                    StringBuilder responseText = new StringBuilder();
                    int serialNumber = 1;

                    while (rs.next()) {
                        responseText.append("<tr>")
                                .append("<td>").append(serialNumber++).append("</td>")
                                .append("<td>").append(rs.getString("name")).append("</td>")
                                .append("<td>").append(rs.getString("blood_group")).append("</td>")
                                .append("<td>").append(rs.getString("phone")).append("</td>")
                                .append("<td>").append(rs.getString("sex")).append("</td>")
                                .append("<td>").append(rs.getString("place")).append("</td>")
                                .append("<td>").append(rs.getString("last_donation_date")).append("</td>")
                                .append("</tr>");
                    }

                    // If no donors found
                    if (serialNumber == 1) {
                        responseText.append("<tr><td colspan='7'>No donors found.</td></tr>");
                    }

                    response.getWriter().print(responseText.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<tr><td colspan='7'>Error fetching donors.</td></tr>");
        }
    }
}
