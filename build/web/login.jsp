<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <div id="content" align="center">
        <form method="post" action="Login">
            <h2>Login</h2>
            <input type="text" id="username" name="username" placeholder="Username" required><br>
            <input type="password" id="password" name="password" placeholder="Password" required><br>
            <label for="user">Login as</label><br>
            <input type="radio" id="consultant" name="role" value="consultant">
            <label for="user">Consultant</label><br>
            <input type="radio" id="emperor" name="role" value="emperor">
            <label for="admin">Emperor</label><br>
            <input type="submit" value="Login">
        </form>
    </div>
    </body>
</html>
