package ee;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/task5")
public class Task5Servlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(Task5Servlet.class.getName());

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
//        LOGGER.log(Level.INFO,"my doGet");
        String num1 = req.getParameter("number1");
        String num2 = req.getParameter("number2");
        String operation = req.getParameter("operation");
        try {
            if(num1 != null && num2 != null && operation != null)
                sendResponse(resp, Integer.parseInt(num1), Integer.parseInt(num2), operation);
            else
                sendFormPage(req, resp);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            return;
        }

    }

    private void sendFormPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = "/statics/html/task5.html";
        ServletContext sc = getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher(path);
        rd.forward(req, resp);
    }
    private void sendResponse(HttpServletResponse resp, int n1, int n2, String operation) throws IOException {
        var w = resp.getWriter();

        w.write(String.format("%d %s %d = %f", n1, operation, n2, operate(n1, n2, operation)));
        w.close();
    }

    private double operate(int n1, int n2, String operation) {
        double res;
        switch (operation) {
            case "add": res = n1 + n2; break;
            case "sub": res = n1 - n2; break;
            case "mul": res = n1 * n2; break;
            case "div": res = n1 / (double)n2; break;
            case "pow": res = Math.pow(n1, n2); break;
            case "per": res = n1 * (n2*0.01); break;
            default: throw new RuntimeException("Wrong operation");
        }
        return res;
    }
}
