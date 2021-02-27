package com.ssb.mobileshop.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserController extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        HttpSession session=request.getSession();
        String name=(String) session.getAttribute("login");
            PrintWriter out = response.getWriter();
            out.print("<html><body>");
            out.print("<h1>Welcome " + name + "</h1><br><br>");
            out.print("<a href='user/viewphone'>View Phone</a><br>");
            out.print("<a href='user/viewbrand'>View Brands</a><br>");
            out.print("<a href='user/ram'>View By RAM</a><br>");
            out.print("<a href='user/price'>Filter By Price</a><br><br>");
            if(LoginController.role==1){
                out.print("<a href="+ "'" + request.getContextPath() + "/admin'>Admin</a><br><br>");
            }
            out.print("<a href='logout'>Logout</a>");
            
        }
    }
