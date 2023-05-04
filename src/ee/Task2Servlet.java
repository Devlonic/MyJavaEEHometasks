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

@WebServlet("/task2")
public class Task2Servlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(Task2Servlet.class.getName());

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
//        LOGGER.log(Level.INFO,"my doGet");
        String num1 = req.getParameter("number1");
        String num2 = req.getParameter("number2");
        String num3 = req.getParameter("number3");
        String operation = req.getParameter("operation");
        try {
            if(num1 != null && num2 != null && num3 != null && operation != null)
                sendResponse(resp, Integer.parseInt(num1), Integer.parseInt(num2), Integer.parseInt(num3), operation);
            else
                sendFormPage(req, resp);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            return;
        }

    }

    private void sendFormPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = "/statics/html/task2.html";
        ServletContext sc = getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher(path);
        rd.forward(req, resp);
    }
    private void sendResponse(HttpServletResponse resp, int n1, int n2, int n3, String operation) throws IOException {
        var w = resp.getWriter();

        w.write(String.format("<p>%d</p><p>%d</p><p>%d</p><p>%s = %f</p>", n1, n2, n3, operation, operate(n1, n2, n3, operation)));
        w.close();
    }

    private double operate(int n1, int n2, int n3, String operation) {
        double res;
        switch (operation) {
            case "min": res = Integer.min(Integer.min(n1, n2), n3); break;
            case "max": res = Integer.max(Integer.max(n1, n2), n3); break;
            case "avg": res = (n1 + n2 + n3) / 3.0; break;
            default: throw new RuntimeException("Wrong operation");
        }
        return res;
    }
}
