package com.ssb.mobileshop.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssb.mobileshop.model.User;
import com.ssb.mobileshop.service.LoginService;

public class LoginController extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = -6458309473939735465L;
    public static int role=0;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        //Getting mobile number and password
        String mobileNumber = request.getParameter("mobile");
        String password = request.getParameter("password");
        
        //Check whether user provides Empty String
        if (mobileNumber.equals("") || password.equals("")) {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Name or Password Should not be blank');");
            out.println("location='index.html';");
            out.println("</script>");
        } else {
            try {
                //Validate Mobile number and password are correct
                User roleId = LoginService.getInstance().loginValidation(mobileNumber, password);
                
                if (roleId != null) {
                    role=roleId.getRoleId();
                    //If roleID is 1 then it will redirect to admin page
                    if (roleId.getRoleId() == 1) {
                        //creating Sesion
                        HttpSession session=request.getSession();
                        session.setAttribute("login",roleId.getName());
                        response.sendRedirect("admin");
                        
                    //If roleId is 2 then it will redirect to user page    
                    } else if (roleId.getRoleId() == 2) {
                       HttpSession session=request.getSession();
                        session.setAttribute("login",roleId.getName());
                        response.sendRedirect("user");
                    }
                } else {
                    out.println("<font color=red>Invalid Mobile Number or Password</font>");
                }
            } catch (NullPointerException e) {

                out.println("<script type=\"text/javascript\">");
                out.println("alert('User or password incorrect');");
                out.println("location='index.html';");
                out.println("</script>");
            }

        }
    }
}
