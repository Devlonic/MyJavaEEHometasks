package ee;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/task4")
public class Task4Servlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(Task4Servlet.class.getName());

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
//        LOGGER.log(Level.INFO,"my doGet");
        String text = req.getParameter("text");
        try {

            if(text != null) {
                int vowelsCount = 0;
                int consonantsCount = 0;
                int punctuationCount = 0;
                Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
                Set<Character> consonants = new HashSet<>(Arrays.asList('b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'y', 'z'));
                Set<Character> punctuation = new HashSet<>(Arrays.asList('.', ',', ';', ':', '?', '!', '-', '—', '(', ')', '[', ']', '{', '}'));

                for (char c : text.toLowerCase().toCharArray()) {
                    if (vowels.contains(c)) {
                        vowelsCount++;
                    } else if (consonants.contains(c)) {
                        consonantsCount++;
                    } else if (punctuation.contains(c)) {
                        punctuationCount++;
                    }
                }

                var w = resp.getWriter();
                w.write(String.format("<p>vowels: <b>%d</b></p><p>consonants: <b>%d</b></p><p>punctuation: <b>%d</b></p>", vowelsCount, consonantsCount, punctuationCount));
            }
            else
                sendFormPage(req, resp);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            return;
        }

    }

    private void sendFormPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = "/statics/html/task4.html";
        ServletContext sc = getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher(path);
        rd.forward(req, resp);
    }
}
