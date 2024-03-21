<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>

      


        <h2 style="color: gray">Create New Employee</h2>

        <form action="HomeServlet" method="POST">

            <table border="1" width="1" cellspacing="1" cellpadding="1">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Password</th>
                        <th>Onboard</th>
                        <th>Salary</th>
                        <th>Role</th>
                        <th>Department</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><input type="text" name="txtId"></td>
                        <td><input type="text" name="txtName"></td>
                        <td><input type="text" name="txtPass"></td>
                        <td><input type="text" name="txtOnboard"></td>
                        <td><input type="text" name="txtSalary"></td>
                        <td><input type="checkbox" name="ckb"></td>
                        <td>
                            <select name="cbx">
                                <option value="first">--Select_Here--</option>
                                <c:forEach items="${dList}" var="it">
                                    <option value="${it.getId()}">
                                        ${it.getDescription()} - ${it.getName()}
                                    </option>
                                </c:forEach>             
                            </select>
                        </td>
                        <td>
                            <input type="submit" value="Create" name="action">
                        </td>
                    </tr>

                </tbody>
            </table>
        </form>

        <hr>

        <h1 style="color: blue">Employee Management System</h1>
        <table border="1" width="1" cellspacing="1" cellpadding="1">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Onboard</th>
                    <th>Salary</th>
                    <th>Department</th>
                    <th>Role</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${list}" var="it">
                    <tr>
                        <td><c:out value="${it.getId()}"/></td>
                        <td><c:out value="${it.getName()}"/></td>
                        <td><c:out value="${it.getOnboard()}"/></td>
                        <td><c:out value="${it.getSalary()}"/></td>
                        <td><c:out value="${it.getDepartmentid().getName()}"/></td>
                        <td><c:out value="${it.getRole()}"/></td>
                        <!--<!-- Homework: Create Reset Password Button, Have notification Yes or No ! -->
                        <td>
                            <button type="button" onclick="resetPassword('${it.getId()}')">Reset_Password</button>
                        </td>

                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </body>
</html>

<script type="text/javascript">
    function resetPassword(employeeId) {
        // Xác nhận trước khi tiến hành
        var confirmReset = confirm("Are You Sure You want to reset password for Employee ID " + employeeId + "?");
        if (confirmReset) {
            // Lấy mật khẩu mới từ người dùng qua prompt dialog
            var newPassword = prompt("Please enter the new password:");
            if (newPassword != null && newPassword !== "") {
                // Gửi thông tin tới servlet qua AJAX hoặc form submit
                var form = document.createElement('form');
                form.method = 'post';
                form.action = 'HomeServlet';

                // Tạo các trường ẩn chứa dữ liệu
                var idField = document.createElement('input');
                idField.type = 'hidden';
                idField.name = 'txtId';
                idField.value = employeeId;
                form.appendChild(idField);

                var passwordField = document.createElement('input');
                passwordField.type = 'hidden';
                passwordField.name = 'newPassword';
                passwordField.value = newPassword;
                form.appendChild(passwordField);

                var actionField = document.createElement('input');
                actionField.type = 'hidden';
                actionField.name = 'action';
                actionField.value = 'ResetPassword';
                form.appendChild(actionField);

                document.body.appendChild(form);
                form.submit();

                // Thông báo cho người dùng biết yêu cầu đã được gửi
                alert('Password reset request has been sent.');
            } else {
                // Người dùng không nhập mật khẩu mới
                alert('Password reset has been cancelled.');
            }
        } else {
            // Người dùng không xác nhận việc reset mật khẩu
            alert('Password reset has been cancelled.');
        }
    }
</script>



