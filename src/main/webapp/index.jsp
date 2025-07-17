<!DOCTYPE html>
<html>
<head>
  <title>Login</title>
  <link rel="stylesheet" type="text/css" href="styleLogin.css">
</head>
<body>

<%
  String message = (String) session.getAttribute("message");
  if (message != null) {
%>
  <div class="login-error"><%= message %></div>
<%
    session.removeAttribute("message");
  }
%>


<form class="login-form" action="LoginServlet" method="post">
  <input type="email" name="email" placeholder="Email" required>
  <input type="password" name="password" placeholder="Password" required>
  <button class="loginButton" type="submit">Login</button>
</form>

<div class="register-link">
  <p>New user? <a href="register.jsp">Register here</a></p>
</div>

</body>
</html>

