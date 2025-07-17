package com.event;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/eventdb", "root", "JarvisJarviss3000");

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO users(name, email, password) VALUES (?, ?, ?)");
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.executeUpdate();

            HttpSession session = request.getSession();
            session.setAttribute("message", "Registered successfully!");
            response.sendRedirect("index.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
