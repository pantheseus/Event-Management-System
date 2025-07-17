package com.event;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

public class EventRegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int userId = (int) request.getSession().getAttribute("userid");
        int eventId = Integer.parseInt(request.getParameter("event_id"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/eventdb", "root", "JarvisJarviss3000");

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO registrations(user_id, event_id) VALUES (?, ?)");
            ps.setInt(1, userId);
            ps.setInt(2, eventId);
            ps.executeUpdate();

            response.sendRedirect("event_success.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
