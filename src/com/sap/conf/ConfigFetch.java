package com.sap.conf;


import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: I827779
 * Date: 8/30/11
 * Time: 3:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConfigFetch extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Get the request url
        String reqUri = req.getRequestURI();
        // Get the context path
        String contextPath = req.getContextPath();

        // Construct the real path in the WEB-INF
        String filePath = reqUri.substring(contextPath.length(), reqUri.length());
        filePath = "/WEB-INF" + filePath.replaceFirst("conf", "configuration");

        // Set the content type to display xml as text
        res.setContentType("text/plain");

        // Write it out
        PrintWriter out = res.getWriter();
        InputStream in = req.getServletContext().getResourceAsStream(filePath);
        InputStreamReader reader = new InputStreamReader(in);
        IOUtils.copy(reader, out);
        reader.close();
        in.close();
        out.close();
    }
}
