<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Details Page</title>
    </head>
    <body>
        <h1 style="color: purple">Employee Details</h1>

        <table border="1" width="1" cellspacing="1" cellpadding="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>NAME</th>
                    <th>ONBOARD</th>
                    <th>SALARY</th>
                    <th>DEPARTMENT</th>
                    <th>ROLE</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="employee">
                <tr>
                    <td>${employee.getId()}</td>
                    <td>${employee.getName()}</td>
                    <td>${employee.getOnboard()}</td>
                    <td>${employee.getSalary()}</td>
                    <td>${employee.getDepartmentid().getName()}</td>
                    <td>${employee.getRole()}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>


</body>
</html>
