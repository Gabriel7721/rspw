package controller;

import entities.Department;
import entities.Employee;
import jakarta.ejb.EJB;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.Bidi;
import sb.HomeSBLocal;

public class HomeServlet extends HttpServlet {

    @EJB
    HomeSBLocal sb;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String action = request.getParameter("action");
            if (null == action) {
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                switch (action) {
                    case "Login":
                        String code = request.getParameter("txtId");
                        String pass = request.getParameter("txtPass");
                        Employee employee = sb.checkLogin(code, pass);

                        if (employee != null) {
                            if (employee.getRole() == true) {
                                request.setAttribute("dList", sb.getDepartment());
                                request.setAttribute("list", sb.findAll());
                                request.getRequestDispatcher("index.jsp").forward(request, response);

                            } else {
                                request.setAttribute("employee", employee);
                                request.getRequestDispatcher("details.jsp").forward(request, response);
                            }
                        } else {
                            out.println("<h3 style=\"color: red\">Employee Are Not Found...</h3>");
                            request.getRequestDispatcher("login").include(request, response);
                        }

                        break;
                    case "Create":
                        String id = request.getParameter("txtId");
                        String name = request.getParameter("txtName");
                        String password = request.getParameter("txtPass");
                        String onboard = request.getParameter("txtOnboard");
                        String sal = request.getParameter("txtSalary");

                        BigDecimal salary = new BigDecimal(sal);

                        //boolean role = Boolean.parseBoolean(request.getParameter("ckb"));
                        boolean role = request.getParameter("ckb") != null;

                        int pid = Integer.parseInt(request.getParameter("cbx"));

                        Department department = new Department();
                        department.setId(pid);

                        Employee newEmployee = new Employee(id, name, password, onboard, salary, role, department);

                        sb.saveEmployee(newEmployee);

                        request.setAttribute("dList", sb.getDepartment());
                        request.setAttribute("list", sb.findAll());
                        request.getRequestDispatcher("index.jsp").include(request, response);

                        break;
                    case "ResetPassword":
                        String employeeId = request.getParameter("txtId");
                        String newPassword = request.getParameter("newPassword");

                        if (employeeId != null && newPassword != null && !employeeId.isEmpty() && !newPassword.isEmpty()) {
                            sb.resetPassword(employeeId, newPassword);
                            request.getSession().setAttribute("message", "password has been reset successfully.");
                        } else {
                            request.getSession().setAttribute("message", "failed to reset password. Please check the employee ID & the New password.");
                        }
                        request.getRequestDispatcher("login.jsp").include(request, response);
                        break;
                    default:
                        throw new AssertionError();
                }
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
