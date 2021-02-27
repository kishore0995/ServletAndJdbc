package com.ssb.mobileshop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutController extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1431475722720272486L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        //Remove all Atrributes from sesssion
        session.removeAttribute("login");
        //After removing Attribute session is setting as Invalid and redirected to login page
        session.invalidate();
        response.sendRedirect("index.html");
    }
    
}
