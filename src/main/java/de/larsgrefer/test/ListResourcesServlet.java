package de.larsgrefer.test;

import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

@Component
public class ListResourcesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter printWriter = new PrintWriter(resp.getOutputStream());

        ServletContext servletContext = req.getServletContext();

        Set<String> resourcePaths = servletContext.getResourcePaths("/");
        if (resourcePaths != null) {
            resourcePaths.forEach(printWriter::println);
        }

        printWriter.flush();
        resp.flushBuffer();
    }
}
