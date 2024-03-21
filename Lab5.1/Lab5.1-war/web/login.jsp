<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1 style="color: green"><strong>Employee Login</strong></h1>

        <form action="HomeServlet" method="POST">
            <table border="1" width="1" cellspacing="1" cellpadding="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Password</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><input type="text" name="txtId"/></td>
                        <td><input type="text" name="txtPass"/></td>
                        <td><input type="submit" value="Login" name="action"></td>
                    </tr>
                </tbody>
            </table>

        </form>

    </body>
</html>
