
package org.bloodbank.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("LogoutServlet called"); // Debugging log
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
            System.out.println("Session invalidated");
        } else {
            System.out.println("No active session found");
        }

        // Hardcode the correct redirect path
        response.sendRedirect("/bloodbank/html/login.html");
    }
}