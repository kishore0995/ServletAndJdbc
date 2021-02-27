package com.ssb.mobileshop.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssb.mobileshop.model.User;
import com.ssb.mobileshop.service.UserService;

public class RegistrationController extends HttpServlet {
    /**
     *
     */
    private static final long serialVersionUID = -5606787068207212599L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String mobileNumber = request.getParameter("mobileNumber");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        User user = new User();
        user.setName(name);
        user.setMobileNumber(mobileNumber);
        user.setPassword(password);
        user.setConfirmPassword(confirmPassword);
        user.setRoleId(2);
        if (user.getName().equals("") || user.getMobileNumber().equals("") || user.getPassword().equals("")
                || user.getConfirmPassword().equals("")) {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Fields Should not be Blank');");
            out.println("location='signup.html';");
            out.println("</script>");
        } else if (user.getMobileNumber().length() != 10) {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Enter valid Mobile Number');");
            out.println("location='signup.html';");
            out.println("</script>");
        } else {
            try {
                User register = UserService.getInstance().RegisterValidation(user);
                if (register != null) {
                    int status = UserService.getInstance().add(register);
                    if (status == 1) {
                        out.print("<h2>User Register SuccessFully</h2>");
                        out.print("<form action='index.html'>");
                        out.print("<input type='submit' value='login'>");
                        out.print("</form>");
                    }else{
                        out.print("<h2>User Already present</h2>");
                        out.print("<form action='signup.html'>");
                        out.print("<input type='submit' value='signup'>");
                        out.print("</form>");
                    }
                } else {

                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Password And Confirm Password must be same');");
                    out.println("location='signup.html';");
                    out.println("</script>");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
