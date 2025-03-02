import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Hardcoded credentials for now (replace with DB validation)
        if ("admin".equals(username) && "admin".equals(password)) {
            HttpSession session = request.getSession(); // Create session
            session.setAttribute("user", username);

            response.sendRedirect("html/home.html"); // Redirect after login
        } else {
            response.sendRedirect("html/login.html?error=Invalid Credentials"); // Redirect with error message
        }
    }
}

