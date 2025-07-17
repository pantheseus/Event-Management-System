<%@ page import="java.util.*" %>
<%
    List<String[]> events = (List<String[]>) request.getAttribute("events");
    if (session.getAttribute("email") == null) {
        response.sendRedirect("index.jsp");
        return;
    }
    String msg = (String) session.getAttribute("message");
    String color = "#000"; // default black
    if (msg != null) {
        if (msg.toLowerCase().contains("success")) {
            color = "#28a745"; // green
        } else if (msg.toLowerCase().contains("already")) {
            color = "#dc3545"; // red
        }
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard - Event Management</title>
    <link rel="stylesheet" type="text/css" href="styleevent.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet">
</head>
<body>

<!-- Logout and Heading -->
<div class="header">
    <h2>Welcome to the Event Dashboard</h2>
    <a href="LogoutServlet">Logout</a>
</div>

<!-- Message display -->
<% if (msg != null) { %>
    <p class="message" style="color: <%= color %>; font-weight: bold; text-align: center; margin: 10px auto;"><%= msg %></p>
    <% session.removeAttribute("message"); %>
<% } %>

<!-- Refresh and Event Listing -->
<a class="refresh-link" href="EventListServlet">Refresh Events</a>

<div class="container">
    <h3>Available Events:</h3>
    
    <% if (events != null && !events.isEmpty()) {
           for (String[] event : events) { %>

        <div class="event-card">
            <p><strong>Title:</strong> <%= event[0] %></p>
            <p><strong>Description:</strong> <%= event[1] %></p>
            <p><strong>Date:</strong> <%= event[2] %></p>
            <form method="post" action="RegisterEventServlet">
                <input type="hidden" name="eventId" value="<%= event[3] %>">
                <button class="register-button">Register for this event</button>
            </form>
        </div>

    <%   }
       } else { %>
        <p>No events found. Click 'Refresh Events'.</p>
    <% } %>
</div>
</body>
</html>

