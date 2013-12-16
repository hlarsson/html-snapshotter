package com.hlarsson.htmlsnapshotter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class HTMLSnapshotter extends HttpServlet {
    private static final String ESCAPE_FRAGMENT = "fragment";
    private static final String URL = "url";

    public void init() throws ServletException {
    }
 
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        if (request.getParameter(URL) != null && request.getParameter(ESCAPE_FRAGMENT) != null) {
            String url = URLDecoder.decode(
                    request.getParameter(URL) + "#!" + request.getParameter(ESCAPE_FRAGMENT),
                    "UTF-8");

            final WebClient webClient = new WebClient();
            HtmlPage page = (HtmlPage) webClient.getPage(url);

            // important!  Give the headless browser enough time to execute JavaScript
            // The exact time to wait may depend on your application.
            int jobs = webClient.waitForBackgroundJavaScript(2000);

            out.println(page.asXml());
        } else {
            out.write("<html><head><title>Missing parameter</title><body><p>" +
                    "Missing parameter " + URL + " or " + ESCAPE_FRAGMENT + ".</p></body></html>");
        }
    }
  
    public void destroy() {
    }
}