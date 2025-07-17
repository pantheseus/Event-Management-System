<!DOCTYPE html>
<html>
<head>
  <title>Register</title>
  <link rel="stylesheet" type="text/css" href="styleReg.css">
</head>
<body>

  <form class="Register-Form" name="registerForm" action="RegisterServlet" method="post" onsubmit="return validateForm()">
    <input type="text" name="name" placeholder="Full Name" required>
    <input type="email" name="email" placeholder="Email" required>
    <input type="password" name="password" placeholder="Password" required>
    <input type="password" name="confirmPassword" placeholder="Confirm Password" required>
    <p id="error"></p>
    <button class="RegisterButton" type="submit">Register</button>
  </form>

  <div class="link-wrap">
    <a href="index.jsp">Already have an account?</a>
  </div>

  <script>
    function validateForm() {
      var password = document.registerForm.password.value;
      var confirmPassword = document.registerForm.confirmPassword.value;
      var error = document.getElementById("error");

      if (password.length < 8) {
        error.textContent = "Password must be at least 8 characters.";
        return false;
      }

      if (password !== confirmPassword) {
        error.textContent = "Passwords do not match.";
        return false;
      }

      error.textContent = "";
      return true;
    }
  </script>

</body>
</html>

</html>

