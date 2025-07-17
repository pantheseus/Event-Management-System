package com.event;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/RegisterEventServlet")
public class RegisterEventServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email"); // Make sure email is stored during login
        String eventIdStr = request.getParameter("eventId");

        try {
            int eventId = Integer.parseInt(eventIdStr);
            Connection conn = DBUtil.getConnection();

            // Check if already registered
            PreparedStatement checkStmt = conn.prepareStatement(
                "SELECT * FROM registrations WHERE user_email = ? AND event_id = ?");
            checkStmt.setString(1, email);
            checkStmt.setInt(2, eventId);
            ResultSet rs = checkStmt.executeQuery();

            if (!rs.next()) {
                // ✅ Fetch event name using event ID
                String eventName = "";
                PreparedStatement eventStmt = conn.prepareStatement(
                    "SELECT title FROM events WHERE id = ?");
                eventStmt.setInt(1, eventId);
                ResultSet eventRs = eventStmt.executeQuery();
                if (eventRs.next()) {
                    eventName = eventRs.getString("title");
                }

                // ✅ Insert with event name and timestamp
                PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO registrations (user_email, event_id, event_name, registered_at) VALUES (?, ?, ?, NOW())");
                stmt.setString(1, email);
                stmt.setInt(2, eventId);
                stmt.setString(3, eventName);
                stmt.executeUpdate();

                session.setAttribute("message", "Event registered successfully!");

                eventRs.close();
                eventStmt.close();
                stmt.close();
            } else {
                session.setAttribute("message", "You already registered for this event.");
            }

            rs.close();
            checkStmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("message", "Something went wrong.");
        }

        response.sendRedirect("EventListServlet");
    }
}


