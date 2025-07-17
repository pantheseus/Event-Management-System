package com.event;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

public class EventListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<String[]> events = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/eventdb", "root", "JarvisJarviss3000");

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM events");

            while (rs.next()) {
                events.add(new String[] {
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getString("date"),
                    rs.getString("id") // <-- ADD THIS (event ID)
                });
            }

            request.setAttribute("events", events);
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
