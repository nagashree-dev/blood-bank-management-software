package org.bloodbank.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

@WebServlet("/sessionCheck")
public class SessionCheckServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Do not create a new session
        boolean loggedIn = (session != null && session.getAttribute("user") != null);

        System.out.println("SessionCheckServlet called. Session exists: " + (session != null));
        if (session != null) {
            System.out.println("Session ID: " + session.getId());
            System.out.println("Session user attribute: " + session.getAttribute("user"));
        }

        response.setContentType("application/json");
        response.getWriter().write(new JSONObject().put("loggedIn", loggedIn).toString());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true); // Create a new session
        session.setAttribute("user", "admin"); // Store user session

        System.out.println("Session created. ID: " + session.getId());
        System.out.println("User logged in: " + session.getAttribute("user"));

        response.setContentType("application/json");
        response.getWriter().write(new JSONObject().put("loggedIn", true).toString());
    }
}